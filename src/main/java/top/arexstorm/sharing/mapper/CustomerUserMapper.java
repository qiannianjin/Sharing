package top.arexstorm.sharing.mapper;

import java.util.List;
import java.util.Map;

import top.arexstorm.sharing.bean.user.CustomerUser;

public interface CustomerUserMapper {
	
	/**
	 * 根据用户id(userid) 来查找用户
	 * @param userid
	 * @return
	 */
	CustomerUser selectByUserId(String userid);
	
    /**
     * 根据userid 更新用户的状态 
     * 0 停用
     * 1 启用
     * 9 删除
     * @param customerUser
     */
	void updateUserStatus(CustomerUser customerUser);

	/**
	 * 查询用户的列表
	 * @param customerUser
	 * @return
	 */
	List<CustomerUser> findUserList(CustomerUser customerUser);

	/**
	 * 通过邮箱或者手机号来查找用户
	 * @param paramMap
	 * @return
	 */
	CustomerUser findUserByEmailOrPhone(Map<String, Object> paramMap);

	/**
	 * 查询激活用户的邮箱
	 * 关联查询用户绑定表，判断用户是否绑定邮箱
	 * @param userid
	 * @return
	 */
	String findEnabledUserEmail(String userid);
}