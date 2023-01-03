package yuxm.flashsale.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yuxm.flashsale.service.IUserService;
import yuxm.flashsale.vo.LoginVO;
import yuxm.flashsale.vo.RespBean;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * Direct to the login page.
     *
     * @return
     */
    @RequestMapping("/toLogin")

    public String toLogin() {
        return "login";
    }

    /**
     * Login.
     *
     * @param loginVO login value obejct containing email address and password.
     * @return response bean
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(LoginVO loginVO) {
        return userService.doLogin(loginVO);
    }
}
