package top.arexstorm.sharing.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.mapper.CustomerUserMapper;
import top.arexstorm.sharing.mapper.UserMapper;
import top.arexstorm.sharing.service.user.UserService;

@Service(value="userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CustomerUserMapper customerUserMapper;
	
	@Override
	public CustomerUser findUserById(String userId) throws Exception {
		
		CustomerUser customerUser= null;
		
		if (StringUtils.isNotEmpty(userId)) {
			customerUser = customerUserMapper.selectByUserId(userId);
		}
		
		return customerUser;
	}

	@Override
	public void addUser(CustomerUser customerUser) throws Exception {
		userMapper.insertSelective(customerUser);
	}

	@Override
	public void updateUser(String userId, CustomerUser customerUser) throws Exception {
		
		CustomerUser findUser = customerUserMapper.selectByUserId(userId);
		if (findUser!=null && findUser.getId()!=null) {
			customerUser.setId(findUser.getId());
			userMapper.updateByPrimaryKeySelective(customerUser);
		}
	}

	@Override
	public void updateUserStatus(String userId, Integer status) throws Exception {
		
		if (userId!=null && status!=null) {
			CustomerUser customerUser = new CustomerUser();
			customerUser.setUserid(userId);
			customerUser.setStatus(Byte.parseByte(status.toString()));
			customerUserMapper.updateUserStatus(customerUser);			
		}
	}

	@Override
	public void deleteUserTrue(String userId) throws Exception {
		if (StringUtils.isNotBlank(userId)) {
			CustomerUser findUser = customerUserMapper.selectByUserId(userId);
			if (findUser!=null && findUser.getId()!=null) {
				userMapper.deleteByPrimaryKey(findUser.getId());
			}
		}
		
	}

	@Override
	public List<CustomerUser> findUserList(Integer status) throws Exception {
		
		CustomerUser customerUser = new CustomerUser();
		if (status != null) {
			customerUser.setStatus(Byte.parseByte(status.toString()));
		}
		
		List<CustomerUser> customerUserList = customerUserMapper.findUserList(customerUser);
		
		return customerUserList;
	}

	@Override
	public CustomerUser findUserByEmailOrPhone(String email, String phone) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(email)) {
			paramMap.put("email", email);
		}
		if (StringUtils.isNotBlank(phone)) {
			paramMap.put("phone", phone);
		}
		
		return customerUserMapper.findUserByEmailOrPhone(paramMap);
	}

	@Override
	public int findUserEmailStatus(String userid) {
		String email = customerUserMapper.findEnabledUserEmail(userid);
		if (email != null) { //已经绑定过了
			return 1;
		} else { //还没有绑定
			return 0;
		}
	}

}
