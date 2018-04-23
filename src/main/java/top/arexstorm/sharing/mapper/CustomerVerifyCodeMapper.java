package top.arexstorm.sharing.mapper;

import java.util.Map;

import top.arexstorm.sharing.bean.notify.VerifyCode;

public interface CustomerVerifyCodeMapper {

	/**
	 * 查找用户的验证码
	 * @param userid
	 * @param type
	 * @return
	 */
	VerifyCode findVerifyCodeByUserid(Map<String, Object> paramMap);

	/**
	 * 更改验证码的状态
	 * @param paramMap
	 */
	void updateVerifyCodeStatus(Map<String, Object> paramMap);

}
