package top.arexstorm.sharing.bean.user;

import java.io.Serializable;

public class CustomerUser extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String repassword;

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	
}
