package top.arexstorm.sharing.controller.info;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.arexstorm.sharing.bean.info.CustomerInformationType;
import top.arexstorm.sharing.bean.info.Information;
import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.service.info.InformationService;
import top.arexstorm.sharing.service.info.InformationTypeService;
import top.arexstorm.sharing.service.user.UserService;
import top.arexstorm.sharing.utils.AppResponse;
import top.arexstorm.sharing.utils.UUIDUtils;

@Controller
@RequestMapping(value="/jie")
public class InformationController {
	
	@Autowired
	private InformationService informationService;
	@Autowired
	private UserService userService;
	@Autowired
	private InformationTypeService informationTypeService;

	/**
	 * 跳转到发布恭喜信息页面
	 * @return
	 */
	@GetMapping(value="/add")
	public String addUI() {
		return "jie/add";
	}
	
	
	@PostMapping(value="/add")
	@ResponseBody
	public AppResponse add(@ModelAttribute(value="info") Information info, HttpSession session, Model model) {
		
		CustomerUser customerUser = (CustomerUser) session.getAttribute("user");
		if (customerUser != null) {
			info.setUserid(customerUser.getUserid());
			info.setShortname(info.getName());
			info.setInformationid(UUIDUtils.generateUUIDString());
			CustomerInformationType type = informationTypeService.findInformationTypeById(info.getTypeid());
			info.setTypename(type!=null ? type.getName() : "");
			informationService.addInformation(info);
			return AppResponse.okData(null, 0, "登陆成功", "/");
		} else {
			return AppResponse.okData(null, -1, "请登录", "/user/login");
		}
		
	}
}
