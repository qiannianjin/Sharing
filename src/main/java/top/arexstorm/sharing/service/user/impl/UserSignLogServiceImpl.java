package top.arexstorm.sharing.service.user.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.arexstorm.sharing.bean.user.CustomerUserSignLog;
import top.arexstorm.sharing.mapper.CustomerUserSignLogMapper;
import top.arexstorm.sharing.service.user.UserSignLogService;

import java.util.List;

@Service(value = "userSignLogService")
@Transactional
public class UserSignLogServiceImpl implements UserSignLogService {

    @Autowired
    private CustomerUserSignLogMapper customerUserSignLogMapper;

    @Override
    public List<CustomerUserSignLog> findUserSignLogList(String userid) {

        if (StringUtils.isNotBlank(userid)) {
            return customerUserSignLogMapper.findCustomerUserSignLogList(userid);
        } else {
            return null;
        }
    }
}
