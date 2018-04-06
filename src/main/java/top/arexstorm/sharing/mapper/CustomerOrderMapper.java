package top.arexstorm.sharing.mapper;

import java.util.List;

import top.arexstorm.sharing.bean.order.CustomerOrder;

public interface CustomerOrderMapper {
   
	public CustomerOrder findOrderById(String orderid);
	
	public List<CustomerOrder> findAllOrder(CustomerOrder customerOrder);
}