package top.arexstorm.sharing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SharingApplicationTests {

//	@Autowired
//	private UserMapper userMapper;
	
	@Test
	public void contextLoads() {
//		CustomerUser customerUser = userMapper.selectByUserId("shenzhaoquan");
//		System.err.println(customerUser);
	}
}
