package top.arexstorm.sharing.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.bean.user.User;
import top.arexstorm.sharing.bean.user.UserQuery;

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
}