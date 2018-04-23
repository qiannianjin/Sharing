package top.arexstorm.sharing.service.user;

import java.util.List;

import top.arexstorm.sharing.bean.user.CustomerUser;

public interface UserService {

	/**
	 * 根据用户id查询用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public CustomerUser findUserById(String userId) throws Exception;
	
	/**
	 * 查询用户列表
	 * @return
	 * @throws Exception
	 */
	public List<CustomerUser> findUserList(Integer status) throws Exception;
	
	/**
	 * 根据user的扩展对象来 添加一个用户
	 * @param customerUser
	 * @throws Exception
	 */
	public void addUser(CustomerUser customerUser) throws Exception;
	
	/**
	 * 根据用户id来更新用户信息  部分更新  selective
	 * @param userId
	 * @param customerUser
	 * @throws Exception
	 */
	public void updateUser(String userId, CustomerUser customerUser) throws Exception;
	
	/**
	 * 更新指定的用户的状态  
	 * 0 停用
	 * 1 启用
	 * 9 删除
	 * @param usreId
	 * @throws Exception
	 */
	public void updateUserStatus(String usreId, Integer status) throws Exception;
	
	/**
	 * 真正删除指定的用户
	 * @param userId
	 * @throws Exception
	 */
	public void deleteUserTrue(String userId) throws Exception;
	
	
	/**
	 * 通过邮箱或者手机号码查找用户
	 * @param email
	 * @param phone
	 * @return
	 */
	public CustomerUser findUserByEmailOrPhone(String email, String phone);
}
