package top.arexstorm.sharing.service.user.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.bean.user.User;
import top.arexstorm.sharing.mapper.UserMapper;
import top.arexstorm.sharing.service.user.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public CustomerUser findUserById(String userId) throws Exception {
		
		CustomerUser customerUser= null;
		
		if (StringUtils.isNotEmpty(userId)) {
			customerUser = userMapper.selectByUserId(userId);
		}
		
		return customerUser;
	}

}
