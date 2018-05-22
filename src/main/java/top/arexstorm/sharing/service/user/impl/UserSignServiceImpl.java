package top.arexstorm.sharing.service.user.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.arexstorm.sharing.bean.user.CustomerUserSign;
import top.arexstorm.sharing.bean.user.UserSignLog;
import top.arexstorm.sharing.mapper.CustomerUserSignMapper;
import top.arexstorm.sharing.mapper.UserSignLogMapper;
import top.arexstorm.sharing.mapper.UserSignMapper;
import top.arexstorm.sharing.service.user.UserSignService;

import java.math.BigDecimal;
import java.util.Date;

@Service(value = "userSignService")
@Transactional
public class UserSignServiceImpl implements UserSignService {

    @Autowired
    private CustomerUserSignMapper customerUserSignMapper;
    @Autowired
    private UserSignMapper userSignMapper;
    @Autowired
    private UserSignLogMapper userSignLogMapper;

    @Override
    public CustomerUserSign doSign(String userid) {
        CustomerUserSign userSign = customerUserSignMapper.findCustomerUserSignByUserId(userid);
        if (userSign == null) { //第一次签到
            userSign = new CustomerUserSign();
            userSign.setSign(1l);
            userSign.setPoints(new BigDecimal(5));
            userSign.setDays(1);
            userSign.setUserid(userid);
            userSignMapper.insertSelective(userSign);
        } else {
            Date lasttime = userSign.getLasttime();
            Date today = new Date();
            if (DateUtils.isSameDay(lasttime, today)) { //今天已经签到过了
                return new CustomerUserSign(userSign.getDays(), userSign.getPoints(), true);
            }
            Date yesterday = DateUtils.addDays(today, -1);
            if (DateUtils.isSameDay(lasttime, yesterday)) { //昨天已经签到过了，连续签到
                userSign.setDays(userSign.getDays() + 1);
            } else { //断签
                userSign.setDays(1);
            }
            userSign.setPoints(getPointsByDays(userSign.getDays()));
            userSign.setSign(userSign.getSign() + 1);
            userSign.setLasttime(new Date());

            userSignMapper.updateByPrimaryKeySelective(userSign);
        }

        //加入签到日志表
        UserSignLog userSignLog = new UserSignLog();
        userSignLog.setDays(userSign.getDays());
        userSignLog.setPoints(userSign.getPoints());
        userSignLog.setUserid(userSign.getUserid());
        userSignLogMapper.insertSelective(userSignLog);

        return new CustomerUserSign(userSign.getDays(), userSign.getPoints(), false);
    }

    @Override
    public CustomerUserSign checkUserSign(String userid) {
        CustomerUserSign userSign = customerUserSignMapper.findCustomerUserSignByUserId(userid);
        if (userSign == null) { //第一次签到
//            Integer days, BigDecimal points, Boolean signed
            return new CustomerUserSign(1, new BigDecimal(5), false);
        } else {
            Date lasttime = userSign.getLasttime();
            Date today = new Date();
            if (DateUtils.isSameDay(lasttime, today)) { //今天已经签到过了
                userSign.setSigned(true);
                return userSign;
            } else {
               userSign.setSigned(false);
               return userSign;
            }
        }
    }

    private BigDecimal getPointsByDays(Integer days) {

        if (days != null) {
            BigDecimal points;
            if (days > 0 && days < 5) {
                points = new BigDecimal(5);
            } else if (days >= 5 && days <15) {
                points = new BigDecimal(10);
            } else if (days >=15 && days < 30) {
                points = new BigDecimal(15);
            } else {
                points = new BigDecimal(20);
            }
            return points;
        } else {
            return BigDecimal.ZERO;
        }
    }
}
