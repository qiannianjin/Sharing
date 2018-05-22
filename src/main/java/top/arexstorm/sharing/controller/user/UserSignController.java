package top.arexstorm.sharing.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.arexstorm.sharing.bean.user.CustomerUserSign;
import top.arexstorm.sharing.service.user.UserSignService;
import top.arexstorm.sharing.utils.AppResponse;

@RestController
@RequestMapping(value = "/sign")
public class UserSignController {

    @Autowired
    private UserSignService userSignService;

    /**
     * 用户的签到
     * signed true 说明已经签到过了， false 为 签到成功
     * @param userid
     * @return
     */
    @PostMapping(value = "/in")
    public AppResponse doSign(@RequestParam(required = true) String userid) {

        CustomerUserSign cus = userSignService.doSign(userid);

        return AppResponse.okData(cus, null);
    }

    /**
     * 检测用户是否已经 签到  （Boolean, point）
     * @param userid
     * @return
     */
    @PostMapping(value = "/check")
    public AppResponse checkUserSign(@RequestParam(required = true) String userid) {
        CustomerUserSign cus = userSignService.checkUserSign(userid);

        return AppResponse.okData(cus, 0, null, null);
    }
}
