package top.arexstorm.sharing.bean.info;

public class CustomerComments extends Comments {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nickname;
	private String avatar;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
