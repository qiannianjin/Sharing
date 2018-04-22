package top.arexstorm.sharing.service.order;

import java.util.List;

import top.arexstorm.sharing.bean.order.CustomerOrderDetail;
import top.arexstorm.sharing.bean.order.OrderDetail;

public interface OrderDetailService {

	/**
	 * 
	 * @param orderdetailid
	 * @return
	 */
	public CustomerOrderDetail findOrderDetailById(String orderdetailid);
	
	/**
	 * 
	 * @param status
	 * @param orderid
	 * @return
	 */
	public List<CustomerOrderDetail> findAllOrderDetail(Short status, String orderid);
	
	/**
	 * 
	 * @param order
	 */
	public void saveOrderDetail(OrderDetail orderDetail);
	
	/**
	 * 
	 * @param orderid
	 */
	public void deleteOrderDetailByOrderdetailid(String orderdetailid);
	
	/**
	 * 
	 * @param orderdetailid
	 * @param orderdetail
	 */
	public void updateOrderDetail(String orderdetailid, OrderDetail orderdetail);
	
	/**
	 * 
	 * @param orderdetailid
	 * @param status
	 */
	public void updateOrderDetailStatus(String orderdetailid, Short status);
}
