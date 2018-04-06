package top.arexstorm.sharing.bean.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<CartItem> items = new ArrayList<CartItem>();
	//小计  运费   总计等等
	
	public void addItem(CartItem cartItem) {
		if (items.contains(cartItem)) {
			for (CartItem item : items) {
				if (item.equals(cartItem)) {
					Integer total = item.getAmount() + cartItem.getAmount();
					item.setAmount(total);
					break;
				}
			}
		} else {
			items.add(cartItem);
		}
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	@JsonIgnore
	public Integer getInformationAmount() {
		Integer result = 0;
		for (CartItem cartItem : items) {
			result += cartItem.getAmount();
		}
		
		return result;
	}
	
	@JsonIgnore
	public Double getInformationPrice() {
		Double result = 0.0;
		for (CartItem cartItem : items) {
			result += cartItem.getInformation().getPrice().doubleValue() * cartItem.getAmount();
		}
		
		return result;
	}
}
