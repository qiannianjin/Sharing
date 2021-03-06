package top.arexstorm.sharing.controller.info;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.info.CustomerInformationType;
import top.arexstorm.sharing.bean.info.Information;
import top.arexstorm.sharing.bean.order.CustomerOrder;
import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.service.info.InformationService;
import top.arexstorm.sharing.service.info.InformationTypeService;
import top.arexstorm.sharing.service.order.OrderService;
import top.arexstorm.sharing.service.user.UserService;
import top.arexstorm.sharing.utils.AppResponse;
import top.arexstorm.sharing.utils.PageResult;
import top.arexstorm.sharing.utils.UUIDUtils;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/jie")
public class InformationController {

	@Autowired
	private InformationService informationService;
	@Autowired
	private UserService userService;
	@Autowired
	private InformationTypeService informationTypeService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 跳转信息主页
	 * @return
	 */
	@GetMapping(value="/index")
	public String index(String type, Model model) {
		
		model.addAttribute("type", type);
		return "jie/index";
	}

	/**
	 * 跳转到发布共享信息页面
	 * 
	 * @return
	 */
	@GetMapping(value = "/add")
	public String addUI(String informationid, Model model) {

		if (StringUtils.isNotBlank(informationid)) {
			CustomerInformation info = informationService.findInformationById(informationid);
			model.addAttribute("info", info);
		}
		return "jie/add";
	}

	/**
	 * 添加或者更新信息
	 * 
	 * @param info
	 * @param session
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/add")
	@ResponseBody
	public AppResponse add(@ModelAttribute(value = "info") Information info, HttpSession session, Model model) {

		CustomerUser customerUser = (CustomerUser) session.getAttribute("user");

		if (customerUser != null) {
			if (StringUtils.isNotBlank(info.getInformationid())) { // 更新
				CustomerInformation findInfo = informationService.findInformationById(info.getInformationid());
				if (findInfo != null) {
					informationService.updateInformation(info, info.getInformationid());
					return AppResponse.okData(null, 0, "更新成功", "/");
				} else {
					return AppResponse.okData(-1, "更新的信息不存在");
				}
			} else { // 添加
				info.setStatus(Short.parseShort("1"));
				info.setUserid(customerUser.getUserid());
				info.setShortname(info.getName());
				info.setInformationid(UUIDUtils.generateUUIDString());
				CustomerInformationType type = informationTypeService.findInformationTypeById(info.getTypeid());
				info.setTypename(type != null ? type.getName() : "");
				informationService.addInformation(info);
				return AppResponse.okData(null, 0, "添加成功", "/");
			}

		} else {
			return AppResponse.okData(null, -1, "请登录", "/user/login");
		}
	}

	@GetMapping(value = "/detail")
	public String detail(@RequestParam(required = true) String informationid, HttpSession session, Model model) throws Exception {

		CustomerUser customerUser = (CustomerUser) session.getAttribute("user");
		CustomerInformation info = informationService.findInformationById(informationid);
		CustomerUser user = userService.findUserById(info.getUserid()); //作者信息
		if (customerUser!=null && customerUser.getUserid().equals(info.getUserid())) { //作者本身
			model.addAttribute("info", info);
			model.addAttribute("user", customerUser);
			return "jie/detail";
		}
		if (info.getPrice() != null && info.getPrice().compareTo(BigDecimal.ZERO)==0) { //免费 直接进入
			model.addAttribute("info", info);
			model.addAttribute("user", user);
			return "jie/detail";
		} else { //付费信息 跳转确认页面
			//判断是否登录 付费肯定是需要登录的
			if (customerUser == null) {
				return "redirect:/user/login";
			}

			//确认是否已经购买过
			//结合用户id，informationid综合查询订单
			CustomerOrder order = orderService.findOrderByBuyeridAndInformationid(customerUser.getUserid(), informationid);
			if (order != null) { //订购过了 直接进入
				model.addAttribute("info", info);
				model.addAttribute("user", user);
				return "jie/detail";
			} else { //跳转订单确认页
				model.addAttribute("price", info.getPrice());
				String important;
				if (info.getImportant() == 0) {
					important = "普通";
				} else if (info.getImportant() == 1) {
					important = "置顶";
				} else if (info.getImportant() == 2) {
					important = "推荐";
				} else {
					important = "精华";
				}
				model.addAttribute("important", important);
				model.addAttribute("title", info.getName());
				model.addAttribute("summary", info.getSummary());
				model.addAttribute("author", user.getNickname());
				model.addAttribute("sellerid", info.getUserid());
				model.addAttribute("informationid", info.getInformationid());
				return "order/confirm";	
			}
		}
	}
	
	@ResponseBody
	@PostMapping(value = "/list")
	public AppResponse list(Short status, Short important, String userid,
							String informationtypeid, Integer pageNum,
							Integer pageSize) throws Exception {
		
		//获取信息列表  返回包含  用户信息(头像, nickname) 信息 (标题, 时间) 
		List<CustomerInformation> infoList = informationService.findAllInformation(status, important, userid, informationtypeid, pageNum, pageSize);
		
		return AppResponse.okList(infoList);
	}
	
	/**
	 * 查询购买的信息
	 * @param status
	 * @param session
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/buylist")
	public AppResponse buyList(Short status, HttpSession session) {
		CustomerUser customerUser = (CustomerUser) session.getAttribute("user");
		if (customerUser != null) {
			List<CustomerInformation> infoList = informationService.findAllBuyInformation(customerUser.getUserid(), status);
			return AppResponse.okList(infoList);
		} else {
			return AppResponse.okData(null, -1, "请登录", "/user/login");
		}
	}

	@ResponseBody
	@PostMapping(value = "/change")
	public AppResponse changeInformationStatus(String informationid, Short status) {
		String msg = "修改";
		if (status == 9) {
			msg = "删除";
		}
		if (informationService.updateInformationStatus(informationid, status) > 0) {
			return AppResponse.okData(null, 1, msg + "成功!", "/");
		} else {
			return AppResponse.okData(-1, msg + "失败!");
		}
	}

	@GetMapping(value = "/search")
	public String search(String q, Model model) {

	    //按照关键字搜索
//        Integer pageNum, Integer pageSize, Short status, String searchKey, String searchValue
        Integer pageNum = 1;
        Integer pageSize = 100;
        PageResult<CustomerInformation> result = informationService.findAllInformationWithPage(pageNum, pageSize, null, "name", q);
        List<CustomerInformation> list = result.getData();

        //处理下日期显示  10分钟以内 显示 刚刚 否则 yyyy-MM-dd
		Date now = new Date();
		for (CustomerInformation ci : list) {
			if ((now.getTime() - ci.getCreatetime().getTime()) < 10 * 60 * 1000) {
				ci.setShowTime("刚刚");
			} else {
				ci.setShowTime(DateFormatUtils.format(ci.getCreatetime(), "yyyy-MM-dd"));
			}
		}

        model.addAttribute("list", list);

        return "other/search";
	}
}
