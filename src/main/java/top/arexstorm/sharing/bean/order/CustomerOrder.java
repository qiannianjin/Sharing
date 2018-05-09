package top.arexstorm.sharing.bean.order;

public class CustomerOrder extends Order {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String informationid;

	private String backstr;
	private String buyname;
	private String sellname;

	public String getInformationid() {
		return informationid;
	}

	public void setInformationid(String informationid) {
		this.informationid = informationid;
	}

	public String getBackstr() {
		return backstr;
	}

	public void setBackstr(String backstr) {
		this.backstr = backstr;
	}

	public String getBuyname() {
		return buyname;
	}

	public void setBuyname(String buyname) {
		this.buyname = buyname;
	}

	public String getSellname() {
		return sellname;
	}

	public void setSellname(String sellname) {
		this.sellname = sellname;
	}
}
