package top.arexstorm.sharing.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.user.Admin;
import top.arexstorm.sharing.bean.user.AdminQuery;

public interface AdminMapper {
    long countByExample(AdminQuery example);

    int deleteByExample(AdminQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminQuery example);

    Admin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminQuery example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminQuery example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}