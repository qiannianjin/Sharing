package top.arexstorm.sharing.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.info.Information;
import top.arexstorm.sharing.bean.info.InformationQuery;

public interface InformationMapper {
    long countByExample(InformationQuery example);

    int deleteByExample(InformationQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Information record);

    int insertSelective(Information record);

    List<Information> selectByExample(InformationQuery example);

    Information selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Information record, @Param("example") InformationQuery example);

    int updateByExample(@Param("record") Information record, @Param("example") InformationQuery example);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);
}