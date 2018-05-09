package top.arexstorm.sharing.service.order.impl;

import java.math.BigDecimal;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.arexstorm.sharing.annotation.TargetDataSource;
import top.arexstorm.sharing.bean.order.CustomerOrder;
import top.arexstorm.sharing.bean.order.Order;
import top.arexstorm.sharing.bean.order.OrderDetail;
import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.bean.user.User;
import top.arexstorm.sharing.mapper.CustomerOrderMapper;
import top.arexstorm.sharing.mapper.OrderMapper;
import top.arexstorm.sharing.service.order.OrderDetailService;
import top.arexstorm.sharing.service.order.OrderService;
import top.arexstorm.sharing.service.user.UserService;
import top.arexstorm.sharing.utils.PageResult;
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
	@Autowired
    private UserService userService;

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
	public void saveOrder(String buyerid, String sellerid, String informationid, BigDecimal price) throws Exception {
		// 形成订单
		Order order = new Order();
		order.setBuyerid(buyerid);
		order.setCount(1);
		String orderid = UUIDUtils.generateUUIDString();
		order.setOrderid(orderid);
		order.setSellerid(sellerid);
		order.setStatus(Short.parseShort("1"));
		order.setAmount(price);
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

		//扣钱
        CustomerUser user = userService.findUserById(buyerid);
        user.setAmount(user.getAmount().subtract(price));
        userService.updateUser(user.getUserid(), user);
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

	@Override
	public PageResult<CustomerOrder> findAllInformaionTypeWithPage(Integer pageNum, Integer pageSize, String searchKey, String searchValue, Short status) throws Exception {
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
		List<CustomerOrder> orders = customerOrderMapper.findAllInformaionTypeWithPage(status, searchKey, searchValue);
		for (CustomerOrder customerOrder : orders) {
			if (customerOrder.getBack() == 0) {
				customerOrder.setBackstr("购买");
			} else if (customerOrder.getBack() == 1) {
				customerOrder.setBackstr("退货");
			} else if (customerOrder.getBack() == 0) {
				customerOrder.setBackstr("换货");
			}
			User buyUser = userService.findUserById(customerOrder.getBuyerid());
			User sellUser = userService.findUserById(customerOrder.getSellerid());
			customerOrder.setBuyname(buyUser!=null ? buyUser.getNickname() : "");
			customerOrder.setSellname(sellUser!=null ? sellUser.getNickname() : "");
		}
		PageResult<CustomerOrder> result = new PageResult<CustomerOrder>();
		result.setData(orders);
		result.setCount(startPage.getTotal());
		return result;
	}
}
