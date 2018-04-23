package top.arexstorm.sharing.service.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.arexstorm.sharing.bean.user.UserBind;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBindServiceTest {

	@Autowired
	private UserBindService userBindService;
	
	@Test
	public void testAddUserBind() {
		UserBind userBind = new UserBind();
		userBind.setEmail("islambao@126.com");
		userBind.setType(Short.parseShort("0")); // 0 邮箱  1 手机号
		userBind.setUserid("islambao@126.com");
		
		userBindService.addUserBind(userBind);
	}

}
