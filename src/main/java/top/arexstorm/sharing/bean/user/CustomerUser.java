package top.arexstorm.sharing.bean.user;

import java.io.Serializable;

public class CustomerUser extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String repassword;
	private String loginName; //邮箱或者手机号

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	
}
