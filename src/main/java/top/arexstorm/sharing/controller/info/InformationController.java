package top.arexstorm.sharing.controller.info;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.info.CustomerInformationType;
import top.arexstorm.sharing.bean.info.Information;
import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.service.info.InformationService;
import top.arexstorm.sharing.service.info.InformationTypeService;
import top.arexstorm.sharing.service.user.UserService;
import top.arexstorm.sharing.utils.AppResponse;
import top.arexstorm.sharing.utils.UUIDUtils;

@Controller
@RequestMapping(value = "/jie")
public class InformationController {

	@Autowired
	private InformationService informationService;
	@Autowired
	private UserService userService;
	@Autowired
	private InformationTypeService informationTypeService;

	/**
	 * 跳转到发布恭喜信息页面
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
	public String detail(@RequestParam(required = true) String informationid, Model model) throws Exception {

		CustomerInformation info = informationService.findInformationById(informationid);
		CustomerUser user = userService.findUserById(info.getUserid());

		model.addAttribute("info", info);
		model.addAttribute("user", user);

		return "jie/detail";
	}
	
	@ResponseBody
	@PostMapping(value = "/list")
	public AppResponse list(Integer status, String userid) throws Exception {
		
		//获取信息列表  返回包含  用户信息(头像, nickname) 信息 (标题, 时间) 
		List<CustomerInformation> infoList = informationService.findAllInformation(status, userid);
		
		return AppResponse.okList(infoList);
	}
}
