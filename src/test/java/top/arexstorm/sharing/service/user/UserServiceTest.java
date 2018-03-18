package top.arexstorm.sharing.service.user;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.arexstorm.sharing.bean.user.CustomerUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testFindUserById() throws Exception {
		String userId = "shenzhaoquan";
		CustomerUser customerUser = userService.findUserById(userId);
		System.err.println(customerUser);
	}

	@Test
	public void testAddUser() throws Exception {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setPassword("xiebo");
		customerUser.setUserid("xiebo");
		userService.addUser(customerUser);
	}

	@Test
	public void testUpdateUser() throws Exception {
		String userId = "谢波";
		CustomerUser customerUser = new CustomerUser();
		customerUser.setAvatar("/Sharing/images/avatar.png");
		userService.updateUser(userId, customerUser);
	}

	@Test
	public void testUpdateUserStatus() throws Exception {
		String userId = "谢波";
		Integer status = 1;
		userService.updateUserStatus(userId, status);
	}

	@Test
	public void testDeleteUserTrue() throws Exception {
		String userId = "谢波";
		userService.deleteUserTrue(userId);
	}
	
	@Test
	public void testFindUserList() throws Exception {
//		Integer status = null;
		Integer status = 1;
		List<CustomerUser> findUserList = userService.findUserList(status);
		for (CustomerUser customerUser : findUserList) {
			System.err.println(customerUser);
		}
	}

}
