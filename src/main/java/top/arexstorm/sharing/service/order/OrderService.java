package top.arexstorm.sharing.service.order;

import java.util.List;

import top.arexstorm.sharing.bean.order.CustomerOrder;
import top.arexstorm.sharing.bean.order.Order;
import top.arexstorm.sharing.utils.PageResult;

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

	/**
	 * 保存订单同时生成订单明细
	 * @param userid
	 * @param sellerid
	 * @param informationid
	 */
	public void saveOrder(String userid, String sellerid, String informationid);

	/**
	 * 查询指定用户，指定信息的订单
	 * @param buyerid
	 * @param informationid
	 * @return
	 */
	public CustomerOrder findOrderByBuyeridAndInformationid(String buyerid, String informationid);

	/**
	 * 分页 查找所有订单
	 * @param pageNum
	 * @param pageSize
	 * @param searchKey
	 * @param searchValue
	 * @param status
	 * @return
	 */
	PageResult<CustomerOrder> findAllInformaionTypeWithPage(Integer pageNum, Integer pageSize, String searchKey, String searchValue, Short status) throws Exception;
}
