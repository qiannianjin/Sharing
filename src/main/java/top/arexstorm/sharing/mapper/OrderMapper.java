package top.arexstorm.sharing.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.order.Order;
import top.arexstorm.sharing.bean.order.OrderQuery;

public interface OrderMapper {
    long countByExample(OrderQuery example);

    int deleteByExample(OrderQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderQuery example);

    Order selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderQuery example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderQuery example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}