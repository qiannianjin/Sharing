package top.arexstorm.sharing.service.user;

import top.arexstorm.sharing.bean.user.CustomerUser;

public interface UserService {

	public CustomerUser findUserById(String userId) throws Exception;
	
}
