package top.arexstorm.sharing.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.file.PicPath;
import top.arexstorm.sharing.bean.file.PicPathQuery;

public interface PicPathMapper {
    long countByExample(PicPathQuery example);

    int deleteByExample(PicPathQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(PicPath record);

    int insertSelective(PicPath record);

    List<PicPath> selectByExample(PicPathQuery example);

    PicPath selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PicPath record, @Param("example") PicPathQuery example);

    int updateByExample(@Param("record") PicPath record, @Param("example") PicPathQuery example);

    int updateByPrimaryKeySelective(PicPath record);

    int updateByPrimaryKey(PicPath record);
}