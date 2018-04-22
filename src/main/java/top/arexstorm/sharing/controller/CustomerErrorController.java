package top.arexstorm.sharing.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	@RequestMapping(value = ERROR_PATH)
	public String handleError(HttpServletRequest req) {
		
		Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
		if (statusCode == 404) {
			return "other/404";
		} else {
			return "other/500";
		}
		
	}
}
