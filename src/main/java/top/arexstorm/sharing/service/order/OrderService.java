package top.arexstorm.sharing.service.order;

import java.util.List;

import top.arexstorm.sharing.bean.order.CustomerOrder;
import top.arexstorm.sharing.bean.order.Order;

public interface OrderService {

	/**
	 * 
	 * @param orderid
	 * @return
	 */
	public CustomerOrder findOrderById(String orderid);
	
	/**
	 * 
	 * @param status
	 * @param userid
	 * @return
	 */
	public List<CustomerOrder> findAllOrder(Integer status, String buyerid, String sellerid);
	
	
	/**
	 * save an order
	 * @param order
	 */
	public void saveOrder(Order order);
	
	/**
	 * 
	 * @param orderid
	 */
	public void deleteOrderByOrderId(String orderid);
	
	/**
	 * 
	 * @param orderid
	 * @param status
	 */
	public void updateOrderStatus(String orderid, Integer status);
	
	/**
	 * 
	 * @param order
	 * @param orderid
	 */
	public void updateOrder(Order order, String orderid);
	
}
