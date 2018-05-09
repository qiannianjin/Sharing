package top.arexstorm.sharing.controller.order;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.service.order.OrderService;
import top.arexstorm.sharing.utils.AppResponse;

import java.math.BigDecimal;

@Controller
@RequestMapping(value="/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping(value="/confirm")
	public String confirm(Model model) {
		
		return "order/confirm";
	}
	
	/**
	 * 确认订单
	 * @param informationid
	 * @param sellerid
	 * @param session
	 * @return
	 */
	@ResponseBody
	@PostMapping(value="/confirm")
	public AppResponse buy(@RequestParam(required=true) String informationid, String sellerid, BigDecimal price, HttpSession session) throws Exception {
		
		CustomerUser customerUser = (CustomerUser) session.getAttribute("user");

		if (customerUser!=null && (customerUser.getAmount().compareTo(price) < 0)) {
			return AppResponse.okData(-1, "您的账号余额不足，请充值");
		}
		orderService.saveOrder(customerUser.getUserid(), sellerid, informationid, price);
		return AppResponse.okData(null, 0, "购买成功", "/jie/detail?informationid=" + informationid);
	}
}
