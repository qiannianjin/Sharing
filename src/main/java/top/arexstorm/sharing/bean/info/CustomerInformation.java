package top.arexstorm.sharing.bean.info;

public class CustomerInformation extends Information {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用户的信息
	private String nickname;
	private String avatar;

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
