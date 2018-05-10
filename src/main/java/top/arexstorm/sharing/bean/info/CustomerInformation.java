package top.arexstorm.sharing.bean.info;

public class CustomerInformation extends Information {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用户的信息
	private String nickname;
	private String avatar;

	//分页
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private Integer startRow;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        this.startRow = (pageNum-1) * this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        this.startRow = (this.pageNum - 1) * pageSize;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
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
