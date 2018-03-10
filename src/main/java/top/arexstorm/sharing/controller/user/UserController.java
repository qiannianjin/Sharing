package top.arexstorm.sharing.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.service.user.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value="helloworld/{username}")
	public String helloWorld(@PathVariable("username") String username) {
		return "Hello world " + username;
	}
	
	@RequestMapping(value="findUser/{userId}")
	public String findtUser(@PathVariable("userId") String userId, Map<String, Object> map) throws Exception {
		
		CustomerUser customerUser = userService.findUserById(userId);
		map.put("customerUser", customerUser);
		map.put("good", "nam");
		return "test";
	}
}
