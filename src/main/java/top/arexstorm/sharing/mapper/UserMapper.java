package top.arexstorm.sharing.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.bean.user.User;
import top.arexstorm.sharing.bean.user.UserQuery;

public interface UserMapper {
	
    long countByExample(UserQuery example);

    int deleteByExample(UserQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserQuery example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserQuery example);

    int updateByExample(@Param("record") User record, @Param("example") UserQuery example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}