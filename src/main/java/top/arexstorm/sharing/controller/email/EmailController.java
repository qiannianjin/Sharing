package top.arexstorm.sharing.controller.email;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;

import top.arexstorm.sharing.bean.notify.VerifyCode;
import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.bean.user.UserBind;
import top.arexstorm.sharing.config.Template;
import top.arexstorm.sharing.service.email.EmailService;
import top.arexstorm.sharing.service.notify.VerifyCodeService;
import top.arexstorm.sharing.service.user.UserBindService;
import top.arexstorm.sharing.utils.AppResponse;
import top.arexstorm.sharing.utils.UUIDUtils;

@Controller
@RequestMapping(value="/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private VerifyCodeService verifyCodeService;
	@Autowired
	private UserBindService userBindService;

	@ResponseBody
	@PostMapping(value="/send")
	public AppResponse send(HttpSession session) {
		
		CustomerUser customerUser = (CustomerUser) session.getAttribute("user");

		if (customerUser != null && StringUtils.isNotBlank(customerUser.getEmail())) {
			//发送邮件
			String toAddress = customerUser.getEmail();
			String subject = "Sharing邮箱激活";
			String code = UUIDUtils.generateUUIDString();
			String content = Template.EmailTemplate.replace("{nickname}", customerUser.getNickname())
					.replace("{userid}", customerUser.getUserid())
					.replace("{code}", code)
					.replace("{email}", customerUser.getEmail())
					.replace("{type}", "1");
			SingleSendMailResponse response = emailService.sendEmail(toAddress, subject, content);
			
			//插入邮箱验证记录，状态设置为待验证
			VerifyCode verifyCode = new VerifyCode();
			verifyCode.setCode(code);
			verifyCode.setEmail(customerUser.getEmail());
			verifyCode.setType(Short.parseShort("0")); //type 为0 邮箱类型
			verifyCode.setUserid(customerUser.getUserid());
			verifyCode.setStatus(Short.parseShort("0"));
			verifyCodeService.addVerifyCode(verifyCode);
			return AppResponse.okData(null, 0, "已向您的邮箱发送验证邮件，请打开邮箱验证", "/user/set");
		} else {
			return AppResponse.okData(null, -1, "请登录", "/user/login");
		}
	}
	
	@GetMapping(value="/verify")
	public String verityUI() {
		
		return "other/emailconfirm";
	}
	
	@ResponseBody
	@PostMapping(value="/verity")
	public AppResponse verity(String userid, String code, String email, Short type) {
		
		VerifyCode verifyCode = verifyCodeService.findVerifyCodeByUserid(userid, type);
		if (verifyCode == null) {
			return AppResponse.okData(-1, "请到我的设置页面重新绑定邮箱！");
		} else {
			if (verifyCode.getEmail().equals(email) && verifyCode.getCode().equals(code)) { //验证成功
				//更改验证码状态
				verifyCodeService.updateVerifyCodeStatus(userid, type, Short.parseShort("1"));
				//绑定
				UserBind userBind = new UserBind();
				userBind.setEmail(email);
				userBind.setType(Short.parseShort("0")); // 0 邮箱  1 手机号
				userBind.setUserid(userid);
				userBindService.addUserBind(userBind);
				return AppResponse.okData(0, "恭喜您的邮箱校验成功！");
			} else { //验证失败
				return AppResponse.okData(-1, "该链接有误或者您已经验证过了");
			}
		}
	}
}
