package top.arexstorm.sharing.controller.email;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.service.email.EmailService;
import top.arexstorm.sharing.utils.AppResponse;
import top.arexstorm.sharing.utils.UUIDUtils;

@Controller
@RequestMapping(value="/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;

	@ResponseBody
	@PostMapping(value="/send")
	public AppResponse send(HttpSession session) {
		
		CustomerUser customerUser = (CustomerUser) session.getAttribute("user");

		if (customerUser != null) {
			String toAddress = customerUser.getUserid();
			String subject = "Sharing邮箱激活";
			String code = UUIDUtils.generateUUIDString();
			String content = customerUser.getNickname() +",您好<br/>感谢您注册Sharing共享信息平台,请点击以下链接激活您的邮箱:<br/>"
					+ "<a href='http://www.arexstorm.top:9000/email/verity?userid=" + customerUser.getUserid() + "&code=" + code + "'>激活邮箱 </a>" +
					"<br/>如果以上链接无法访问，请将该网址复制并粘贴至浏览器窗口直接访问。";
			emailService.sendEmail(toAddress, subject, content);
			//插入邮箱验证记录，状态设置为待验证
			return AppResponse.okData(null, 0, "已向您的邮箱发送验证邮件，请打开邮箱验证", "/user/set#bind");
		} else {
			return AppResponse.okData(null, -1, "请登录", "/user/login");
		}
	}
	
	@ResponseBody
	@GetMapping(value="/verity")
	public String verity(String userid, String code) {
		
		//用户绑定
		
		return null;
	}
}
