package top.arexstorm.sharing.service.order.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.arexstorm.sharing.annotation.TargetDataSource;
import top.arexstorm.sharing.bean.order.CustomerOrderDetail;
import top.arexstorm.sharing.bean.order.OrderDetail;
import top.arexstorm.sharing.mapper.CustomerOrderDetailMapper;
import top.arexstorm.sharing.mapper.OrderDetailMapper;
import top.arexstorm.sharing.service.order.OrderDetailService;

@Service(value="orderDetailService")
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailMapper orderdetailMapper;
	@Autowired
	private CustomerOrderDetailMapper customerOrderDetailMapper;

	@TargetDataSource("slave")
	@Override
	public CustomerOrderDetail findOrderDetailById(String orderdetailid) {
		return customerOrderDetailMapper.findOrderDetailById(orderdetailid);
	}

	@TargetDataSource("slave")
	@Override
	public List<CustomerOrderDetail> findAllOrderDetail(Short status, String orderid) {
		CustomerOrderDetail orderDetail = new CustomerOrderDetail();
		if (status != null) {
			orderDetail.setStatus(status);
		}
		if (StringUtils.isNotBlank(orderid)) {
			orderDetail.setOrderid(orderid);
		}
		return customerOrderDetailMapper.findAllOrderDetail(orderDetail);
	}

	@Override
	public void saveOrderDetail(OrderDetail orderDetail) {
		orderdetailMapper.insertSelective(orderDetail);
	}

	@Override
	public void deleteOrderDetailByOrderdetailid(String orderdetailid) {
		CustomerOrderDetail customerOrderDetail = this.findOrderDetailById(orderdetailid);
		if (customerOrderDetail != null) {
			Long id = customerOrderDetail.getId();
			orderdetailMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void updateOrderDetail(String orderdetailid, OrderDetail orderdetail) {
		CustomerOrderDetail customerOrderDetail = this.findOrderDetailById(orderdetailid);
		if (customerOrderDetail != null) {
			Long id = customerOrderDetail.getId();
			orderdetail.setId(id);
			orderdetailMapper.updateByPrimaryKeySelective(orderdetail);
		}
	}

	@Override
	public void updateOrderDetailStatus(String orderdetailid, Short status) {
		CustomerOrderDetail customerOrderDetail = this.findOrderDetailById(orderdetailid);
		if (customerOrderDetail != null) {
			customerOrderDetail.setStatus(status);
			orderdetailMapper.updateByPrimaryKeySelective(customerOrderDetail);
		}
	}

}
