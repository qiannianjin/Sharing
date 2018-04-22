package top.arexstorm.sharing.service.order;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.arexstorm.sharing.bean.order.CustomerOrderDetail;
import top.arexstorm.sharing.bean.order.OrderDetail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailServiceTest {

	@Autowired
	private OrderDetailService orderDetailService;

	@Test
	public void testFindOrderDetailById() {
		String orderdetailid = "1";
		CustomerOrderDetail customerOrderDetail = orderDetailService.findOrderDetailById(orderdetailid);
		System.err.println(customerOrderDetail);
	}

	@Test
	public void testFindAllOrderDetail() {
		Short status = 1;
		String orderid = "3";
		List<CustomerOrderDetail> list = orderDetailService.findAllOrderDetail(status, orderid);
		for (CustomerOrderDetail customerOrderDetail : list) {
			System.err.println(customerOrderDetail);
		}
	}

	@Test
	public void testSaveOrderDetail() {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setBuyerid("14010004");
		orderDetail.setCount(1);
		orderDetail.setInformationid("1");
		orderDetail.setOrderdetailid("1");
		orderDetail.setOrderid("3");
		orderDetail.setSellerid("xiebo");
		orderDetail.setStatus(Short.parseShort("1"));
		
		orderDetailService.saveOrderDetail(orderDetail);
	}

	@Test
	public void testDeleteOrderDetailByOrderdetailid() {
		String orderdetailid = "1";
		orderDetailService.deleteOrderDetailByOrderdetailid(orderdetailid);
	}

	@Test
	public void testUpdateOrderDetail() {
		CustomerOrderDetail orderDetail = new CustomerOrderDetail();
		orderDetail.setCount(2);
		orderDetail.setStatus(Short.parseShort("1"));
		String orderdetailid = "1";
		orderDetailService.updateOrderDetail(orderdetailid, orderDetail);
	}

	@Test
	public void testUpdateOrderDetailStatus() {
		String orderdetailid = "1";
		Short status = Short.parseShort("0");
		orderDetailService.updateOrderDetailStatus(orderdetailid, status);
	}

}
