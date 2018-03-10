package top.arexstorm.sharing.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.info.Comments;
import top.arexstorm.sharing.bean.info.CommentsQuery;

public interface CommentsMapper {
    long countByExample(CommentsQuery example);

    int deleteByExample(CommentsQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Comments record);

    int insertSelective(Comments record);

    List<Comments> selectByExample(CommentsQuery example);

    Comments selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Comments record, @Param("example") CommentsQuery example);

    int updateByExample(@Param("record") Comments record, @Param("example") CommentsQuery example);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);
}