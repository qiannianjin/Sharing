package top.arexstorm.sharing.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.user.UserSignLog;
import top.arexstorm.sharing.bean.user.UserSignLogQuery;

public interface UserSignLogMapper {
    long countByExample(UserSignLogQuery example);

    int deleteByExample(UserSignLogQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(UserSignLog record);

    int insertSelective(UserSignLog record);

    List<UserSignLog> selectByExample(UserSignLogQuery example);

    UserSignLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserSignLog record, @Param("example") UserSignLogQuery example);

    int updateByExample(@Param("record") UserSignLog record, @Param("example") UserSignLogQuery example);

    int updateByPrimaryKeySelective(UserSignLog record);

    int updateByPrimaryKey(UserSignLog record);
}