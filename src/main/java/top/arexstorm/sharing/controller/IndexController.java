package top.arexstorm.sharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class IndexController {

	@RequestMapping(value = "")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/about")
	public String about() {
		return "other/about";
	}

	@RequestMapping(value = "/communication")
	public String communication() {
		return "other/communication";
	}

    @RequestMapping(value = "/case")
    public String case1() {
        return "case/case";
    }
}
