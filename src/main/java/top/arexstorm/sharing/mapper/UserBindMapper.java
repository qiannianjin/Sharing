package top.arexstorm.sharing.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.user.UserBind;
import top.arexstorm.sharing.bean.user.UserBindQuery;

public interface UserBindMapper {
    long countByExample(UserBindQuery example);

    int deleteByExample(UserBindQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBind record);

    int insertSelective(UserBind record);

    List<UserBind> selectByExample(UserBindQuery example);

    UserBind selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBind record, @Param("example") UserBindQuery example);

    int updateByExample(@Param("record") UserBind record, @Param("example") UserBindQuery example);

    int updateByPrimaryKeySelective(UserBind record);

    int updateByPrimaryKey(UserBind record);
}