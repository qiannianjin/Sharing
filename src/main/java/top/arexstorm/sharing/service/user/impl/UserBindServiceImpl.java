package top.arexstorm.sharing.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.arexstorm.sharing.bean.user.UserBind;
import top.arexstorm.sharing.mapper.UserBindMapper;
import top.arexstorm.sharing.service.user.UserBindService;

@Service(value="userBindService")
public class UserBindServiceImpl implements UserBindService {

	@Autowired
	private UserBindMapper userBindMapper;
	
	@Override
	public void addUserBind(UserBind userBind) {
		userBindMapper.insertSelective(userBind);
	}

}
