package top.arexstorm.sharing.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.order.CustomerOrder;

public interface CustomerOrderMapper {
   
	public CustomerOrder findOrderById(String orderid);
	
	public List<CustomerOrder> findAllOrder(CustomerOrder customerOrder);

	public CustomerOrder findOrderByBuyeridAndInformationid(CustomerOrder search);

	List<CustomerOrder> findAllInformaionTypeWithPage(@Param("status") Short status, @Param("searchKey") String searchKey, @Param("searchValue") String searchValue);
}