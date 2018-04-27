package top.arexstorm.sharing.service.order.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.arexstorm.sharing.annotation.TargetDataSource;
import top.arexstorm.sharing.bean.order.CustomerOrder;
import top.arexstorm.sharing.bean.order.Order;
import top.arexstorm.sharing.bean.order.OrderDetail;
import top.arexstorm.sharing.mapper.CustomerOrderMapper;
import top.arexstorm.sharing.mapper.OrderMapper;
import top.arexstorm.sharing.service.order.OrderDetailService;
import top.arexstorm.sharing.service.order.OrderService;
import top.arexstorm.sharing.utils.UUIDUtils;

@Service(value = "orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private CustomerOrderMapper customerOrderMapper;
	@Autowired
	private OrderDetailService orderDetailService;

	@TargetDataSource("slave")
	@Override
	public CustomerOrder findOrderById(String orderid) {
		return customerOrderMapper.findOrderById(orderid);
	}

	@TargetDataSource("slave")
	@Override
	public List<CustomerOrder> findAllOrder(Integer status, String buyerid, String sellerid) {
		CustomerOrder customerOrder = new CustomerOrder();
		if (status != null) {
			customerOrder.setStatus(Short.parseShort(status.toString()));
		}
		if (StringUtils.isNotBlank(buyerid)) {
			customerOrder.setBuyerid(buyerid);
		}
		if (StringUtils.isNotBlank(sellerid)) {
			customerOrder.setSellerid(sellerid);
		}
		return customerOrderMapper.findAllOrder(customerOrder);
	}

	@Override
	public void saveOrder(Order order) {
		orderMapper.insertSelective(order);
	}

	@Override
	public void deleteOrderByOrderId(String orderid) {
		CustomerOrder customerOrder = this.findOrderById(orderid);
		if (customerOrder != null && customerOrder.getId() != null) {
			orderMapper.deleteByPrimaryKey(customerOrder.getId());
		}
	}

	@Override
	public void updateOrderStatus(String orderid, Integer status) {
		CustomerOrder customerOrder = this.findOrderById(orderid);
		if (status != null && customerOrder != null) {
			customerOrder.setStatus(Short.parseShort(status.toString()));
			orderMapper.updateByPrimaryKey(customerOrder);
		}
	}

	@Override
	public void updateOrder(Order order, String orderid) {

		CustomerOrder customerOrder = this.findOrderById(orderid);
		if (customerOrder != null) {
			order.setId(customerOrder.getId());
			orderMapper.updateByPrimaryKeySelective(order);
		}
	}

	@Override
	public void saveOrder(String buyerid, String sellerid, String informationid) {
		// 形成订单
		Order order = new Order();
		order.setBuyerid(buyerid);
		order.setCount(1);
		String orderid = UUIDUtils.generateUUIDString();
		order.setOrderid(orderid);
		order.setSellerid(sellerid);
		order.setStatus(Short.parseShort("1"));
		this.saveOrder(order);
		
		//生成订单明细
		OrderDetail detail = new OrderDetail();
		detail.setBuyerid(buyerid);
		detail.setCount(1);
		detail.setInformationid(informationid);
		String orderdetailid = UUIDUtils.generateUUIDString();
		detail.setOrderdetailid(orderdetailid);
		detail.setOrderid(orderid);
		detail.setSellerid(sellerid);
		orderDetailService.saveOrderDetail(detail);
	}

	@TargetDataSource("slave")
	@Override
	public CustomerOrder findOrderByBuyeridAndInformationid(String buyerid, String informationid) {
		CustomerOrder search = new CustomerOrder();
		if (StringUtils.isNotBlank(buyerid)) {
			search.setBuyerid(buyerid);
		}
		if (StringUtils.isNotBlank(informationid)) {
			search.setInformationid(informationid);
		}
		
		return customerOrderMapper.findOrderByBuyeridAndInformationid(search);
	}

}
