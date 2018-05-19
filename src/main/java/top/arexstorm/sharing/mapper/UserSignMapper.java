package top.arexstorm.sharing.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.user.UserSign;
import top.arexstorm.sharing.bean.user.UserSignQuery;

public interface UserSignMapper {
    long countByExample(UserSignQuery example);

    int deleteByExample(UserSignQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(UserSign record);

    int insertSelective(UserSign record);

    List<UserSign> selectByExample(UserSignQuery example);

    UserSign selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserSign record, @Param("example") UserSignQuery example);

    int updateByExample(@Param("record") UserSign record, @Param("example") UserSignQuery example);

    int updateByPrimaryKeySelective(UserSign record);

    int updateByPrimaryKey(UserSign record);
}