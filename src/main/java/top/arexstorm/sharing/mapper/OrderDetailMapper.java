package top.arexstorm.sharing.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.order.OrderDetail;
import top.arexstorm.sharing.bean.order.OrderDetailQuery;

public interface OrderDetailMapper {
    long countByExample(OrderDetailQuery example);

    int deleteByExample(OrderDetailQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    List<OrderDetail> selectByExample(OrderDetailQuery example);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderDetail record, @Param("example") OrderDetailQuery example);

    int updateByExample(@Param("record") OrderDetail record, @Param("example") OrderDetailQuery example);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}