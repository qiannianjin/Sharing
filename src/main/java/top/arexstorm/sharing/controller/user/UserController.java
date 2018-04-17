package top.arexstorm.sharing.controller.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.service.user.UserService;
import top.arexstorm.sharing.utils.AppResponse;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value="helloworld/{username}")
	public String helloWorld(@PathVariable("username") String username) {
		return "Hello world " + username;
	}
	
	
	
	/**
	 * 个人中心
	 * @param userId
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="findUser/{userId}")
	public String findtUser(@PathVariable(value="userId") String userId, Model model) throws Exception {
		
		CustomerUser customerUser = userService.findUserById(userId);
		model.addAttribute("customerUser", customerUser);
		model.addAttribute("good", "nam");
		return "test";
	}
	
	/**
	 * 跳转登陆页面
	 * @return
	 */
	@GetMapping(value="/login")
	public String loginUI() {
		return "user/login";
	}
	
	/**
	 * 跳转注册页面
	 */
	@GetMapping(value="/reg")
	public String reg() {
		return "user/reg";
	}
	
	@PostMapping(value="/login")
	@ResponseBody
	public AppResponse login(@RequestParam(required=true) String userid, @RequestParam(required=true) String password,
			Model model, HttpSession session, HttpServletResponse resp) throws Exception {
		
		CustomerUser findUser = userService.findUserById(userid);
		
		if (findUser == null) {
			
			return AppResponse.okData(-1, "请输入正确的用户名");
		} else {
			if (findUser.getPassword().equals(password)) {
				//添加session
				session.setAttribute("user", findUser);
				Cookie cookie = new Cookie("userid", findUser.getUserid());
				cookie.setPath("/");
				cookie.setMaxAge(60*60);
				resp.addCookie(cookie);
				
				return AppResponse.okData(findUser, "登陆成功", "/");
						
			} else {
				return AppResponse.okData(-1, "您输入的密码有误。");
			}
		}
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpSession session, HttpServletResponse resp) {
		
		session.invalidate();
		Cookie cookie = new Cookie("userid", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		
		return "index";
	}
}
