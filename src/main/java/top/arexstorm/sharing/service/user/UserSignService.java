package top.arexstorm.sharing.service.user;

import top.arexstorm.sharing.bean.user.CustomerUserSign;

public interface UserSignService {

    CustomerUserSign doSign(String userid);
}
