package top.arexstorm.sharing.service.notify;

import top.arexstorm.sharing.bean.notify.VerifyCode;

public interface VerifyCodeService {

	/**
	 * 查找用户指定类型的验证码    type email or phone 
	 * @param userid
	 * @param type
	 * @return
	 */
	public VerifyCode findVerifyCodeByUserid(String userid, Short type);
	
	
	/**
	 * 添加一条验证记录
	 * @param verifyCode
	 */
	public void addVerifyCode(VerifyCode verifyCode);
	
	/**
	 * 更改状态
	 * @param userid
	 * @param status
	 */
	public void updateVerifyCodeStatus(String userid, Short type, Short status);
}
