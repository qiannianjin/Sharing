package top.arexstorm.sharing.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.arexstorm.sharing.bean.cart.Cart;
import top.arexstorm.sharing.config.Constants;

public class CookieUtils {

	public static Cart getCartFromCookie(HttpServletRequest request) {
		Cart cart = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (Constants.CART_NAME.equals(cookie.getName())) {
				String value = cookie.getValue();
				try {
					value = URLDecoder.decode(value, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				cart = (Cart) JSONUtils.fromJSON(value, Cart.class);
				break;
			}
		}
		
		return cart;
	}
	
	public static void saveCartToCookie(HttpServletResponse response, Cart cart) {
		
		String json = JSONUtils.toJSON(cart);
		//编码json
		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 保存cookie中
		Cookie cookie = new Cookie(Constants.CART_NAME, json);
		cookie.setMaxAge(7 * 24 * 60 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public static void expireCartCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(Constants.CART_NAME, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
