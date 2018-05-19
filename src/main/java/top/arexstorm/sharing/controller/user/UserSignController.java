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

    @PostMapping(value = "/in")
    public AppResponse doSign(@RequestParam(required = true) String userid) {

        CustomerUserSign cus = userSignService.doSign(userid);

        return AppResponse.okData(cus, null);
    }
}
