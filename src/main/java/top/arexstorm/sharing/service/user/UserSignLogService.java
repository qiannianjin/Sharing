package top.arexstorm.sharing.service.user;

import top.arexstorm.sharing.bean.user.CustomerUserSignLog;

import java.util.List;

public interface UserSignLogService {

    List<CustomerUserSignLog> findUserSignLogList(String userid);
}
