package top.arexstorm.sharing.controller.user;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.config.Constants;
import top.arexstorm.sharing.service.user.UserService;
import top.arexstorm.sharing.utils.AppResponse;
import top.arexstorm.sharing.utils.FastDFSUtils;
import top.arexstorm.sharing.utils.JSONUtils;

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
	public String regUI() {
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
				
				Cookie cookie = new Cookie("user", URLEncoder.encode(JSONUtils.toJSON(findUser), "utf-8"));
				cookie.setPath("/");
				cookie.setMaxAge(60*60);
				resp.addCookie(cookie);
				
				return AppResponse.okData(findUser, "登陆成功", "/");
						
			} else {
				return AppResponse.okData(-1, "您输入的密码有误。");
			}
		}
	}
	
	/**
	 * 退出登录
	 * @param session
	 * @param resp
	 * @return
	 */
	@GetMapping(value="/logout")
	public String logout(HttpSession session, HttpServletResponse resp) {
		
		session.invalidate();
		Cookie cookie = new Cookie("user", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		
		return "index";
	}
	
	/**
	 * 用户注册
	 * @param customerUser
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/reg")
	@ResponseBody
	public AppResponse reg(@ModelAttribute(value="customerUser") CustomerUser customerUser, Model model) throws Exception {
		
		//验证用户输入的密码是否一致
		String password = customerUser.getPassword();
		String repassword = customerUser.getRepassword();
		if (password != null && repassword!=null && password.equals(repassword)) {
			if (customerUser.getUserid() != null) {
				CustomerUser findUser = userService.findUserById(customerUser.getUserid());
				if (findUser == null) {
					userService.addUser(customerUser);
					return AppResponse.okData(null, 0, "注册成功", "/user/login");
				} else {
					return AppResponse.okData(-1, "该邮箱已被注册，请更换邮箱");
				}
			} else {
				return AppResponse.okData(-1, "请输入用户邮箱");
			}
		} else {
			//两次密码不一致
			return AppResponse.okData(-1, "两次输入的密码不一致");
		}
	}
	
	/**
	 * 我的主页
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value="/home")
	public String home(HttpSession session, Model model) throws Exception {
		CustomerUser customerUser = (CustomerUser) session.getAttribute("user");
		if (customerUser != null) {
			CustomerUser findUser = userService.findUserById(customerUser.getUserid());
			model.addAttribute("user", findUser);
			return "user/home";
		} else {
			return "redirect:/user/login";
		}
	}
	
	/**
	 * 我的设置
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value="/set")
	public String setUI(HttpSession session, Model model) throws Exception {
		
		CustomerUser customerUser = (CustomerUser) session.getAttribute("user");
		if (customerUser != null) {
			CustomerUser findUser = userService.findUserById(customerUser.getUserid());
			model.addAttribute("user", findUser);
			return "user/set";
		} else {
			return "redirect:/user/login";
		}
	}
	
	/**
	 * 我的消息
	 * @return
	 */
	@GetMapping(value="/message")
	public String message() {
		return "user/message";
	}
	
	/**
	 * 用户中心
	 * @return
	 */
	@GetMapping(value="/index")
	public String index() {
		
		return "user/index";
	}
	
	/**
	 * 上传图片
	 * @param img
	 * @return
	 * @throws IOException 
	 */
	@PostMapping(value="/upload")
	@ResponseBody
	public AppResponse upload(MultipartFile file, HttpServletRequest req) throws IOException {
		
		String path = FastDFSUtils.savePic(file.getBytes(), file);
		String url = Constants.IMG_URL + path;
		Map map = new HashMap<String, Object>();
		map.put("url",url);
		
		return AppResponse.okData(map, 0, "图片上传成功", null);
	}
	
	@PostMapping(value="/set")
	@ResponseBody
	public AppResponse set(@ModelAttribute(value="customerUser") CustomerUser customerUser, 
			HttpSession session, Model model, HttpServletResponse resp) throws Exception {
		
		CustomerUser user = (CustomerUser) session.getAttribute("user");
		if (user != null) {
			userService.updateUser(user.getUserid(), customerUser);
			CustomerUser findUser = userService.findUserById(user.getUserid());
			session.setAttribute("user", findUser);
			Cookie cookie = new Cookie("user", URLEncoder.encode(JSONUtils.toJSON(findUser), "utf-8"));
			cookie.setPath("/");
			cookie.setMaxAge(60*60);
			resp.addCookie(cookie);
			return AppResponse.okData(findUser, 0, "修改成功", null);
		} else {
			return AppResponse.okData(-1, "请登录.");
		}
	}
	
}
