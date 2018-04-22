package top.arexstorm.sharing.mapper;

import java.util.List;

import top.arexstorm.sharing.bean.order.CustomerOrder;
import top.arexstorm.sharing.bean.order.CustomerOrderDetail;

public interface CustomerOrderDetailMapper {

	public CustomerOrderDetail findOrderDetailById(String orderdetailid);
	
	public List<CustomerOrderDetail> findAllOrderDetail(CustomerOrderDetail customerOrderDetail);
}
