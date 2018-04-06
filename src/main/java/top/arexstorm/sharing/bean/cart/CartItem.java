package top.arexstorm.sharing.bean.cart;

import java.io.Serializable;

import top.arexstorm.sharing.bean.info.Information;

public class CartItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Information information;
	private Integer amount;
	
	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (information == null) {
			if (other.information != null)
				return false;
		} else if (!information.getId().equals(other.information.getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CartItem [information=" + information + ", amount=" + amount + "]";
	}
}
