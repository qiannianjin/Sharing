package top.arexstorm.sharing.mapper;

import top.arexstorm.sharing.bean.user.CustomerUserSignLog;

import java.util.List;

public interface CustomerUserSignLogMapper {

    List<CustomerUserSignLog> findCustomerUserSignLogList(String userid);
}
