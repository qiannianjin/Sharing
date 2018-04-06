package top.arexstorm.sharing.service.order.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.arexstorm.sharing.bean.order.CustomerOrder;
import top.arexstorm.sharing.bean.order.Order;
import top.arexstorm.sharing.mapper.CustomerOrderMapper;
import top.arexstorm.sharing.mapper.OrderMapper;
import top.arexstorm.sharing.service.order.OrderService;

@Service(value="orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private CustomerOrderMapper customerOrderMapper;

	@Override
	public CustomerOrder findOrderById(String orderid) {
		return customerOrderMapper.findOrderById(orderid);
	}

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
	
}
