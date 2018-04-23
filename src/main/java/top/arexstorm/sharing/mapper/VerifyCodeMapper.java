package top.arexstorm.sharing.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.notify.VerifyCode;
import top.arexstorm.sharing.bean.notify.VerifyCodeQuery;

public interface VerifyCodeMapper {
    long countByExample(VerifyCodeQuery example);

    int deleteByExample(VerifyCodeQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(VerifyCode record);

    int insertSelective(VerifyCode record);

    List<VerifyCode> selectByExample(VerifyCodeQuery example);

    VerifyCode selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VerifyCode record, @Param("example") VerifyCodeQuery example);

    int updateByExample(@Param("record") VerifyCode record, @Param("example") VerifyCodeQuery example);

    int updateByPrimaryKeySelective(VerifyCode record);

    int updateByPrimaryKey(VerifyCode record);
}