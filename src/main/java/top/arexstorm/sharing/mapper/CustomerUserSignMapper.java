package top.arexstorm.sharing.mapper;

import top.arexstorm.sharing.bean.user.CustomerUserSign;

public interface CustomerUserSignMapper {

    CustomerUserSign findCustomerUserSignByUserId(String userid);

}
