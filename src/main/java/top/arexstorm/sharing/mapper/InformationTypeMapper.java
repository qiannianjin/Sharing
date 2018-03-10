package top.arexstorm.sharing.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.info.InformationType;
import top.arexstorm.sharing.bean.info.InformationTypeQuery;

public interface InformationTypeMapper {
    long countByExample(InformationTypeQuery example);

    int deleteByExample(InformationTypeQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(InformationType record);

    int insertSelective(InformationType record);

    List<InformationType> selectByExample(InformationTypeQuery example);

    InformationType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InformationType record, @Param("example") InformationTypeQuery example);

    int updateByExample(@Param("record") InformationType record, @Param("example") InformationTypeQuery example);

    int updateByPrimaryKeySelective(InformationType record);

    int updateByPrimaryKey(InformationType record);
}