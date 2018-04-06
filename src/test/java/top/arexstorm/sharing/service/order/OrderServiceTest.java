package top.arexstorm.sharing.service.order;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.arexstorm.sharing.bean.order.CustomerOrder;
import top.arexstorm.sharing.bean.order.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

	@Autowired
	private OrderService orderService;
	
	@Test
	public void testFindOrderById() {
		String orderid = "1";
		CustomerOrder customerOrder = orderService.findOrderById(orderid);
		System.err.println(customerOrder);
	}

	@Test
	public void testFindAllOrder() {
		Integer status = 1;
		String buyerid = null;
		String sellerid = "谢波";
		List<CustomerOrder> list = orderService.findAllOrder(status, buyerid, sellerid);
		for (CustomerOrder customerOrder : list) {
			System.err.println(customerOrder);
		}
	}

	@Test
	public void testSaveOrder() {
		Order order = new Order();
		order.setBack(Short.parseShort("0"));
		order.setCount(1);
		order.setStatus(Short.parseShort("1"));
		order.setBuyerid("14010004");
		order.setSellerid("谢波");
		order.setOrderid("3");
		orderService.saveOrder(order);
	}

	@Test
	public void testDeleteOrderByOrderId() {
		String orderid = "1";
		orderService.deleteOrderByOrderId(orderid);
	}

	@Test
	public void testUpdateOrderStatus() {
		String orderid = "1";
		Integer status = 0;
		orderService.updateOrderStatus(orderid, status);
	}

	@Test
	public void testUpdateOrder() {
		Order order = new Order();
		order.setStatus(Short.parseShort("1"));
		order.setBack(Short.parseShort("1"));
		String orderid = "1";
		orderService.updateOrder(order, orderid);
	}

}
