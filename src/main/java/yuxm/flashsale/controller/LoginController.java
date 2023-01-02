package yuxm.flashsale.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @RequestMapping("/toLogin") //direct to the login page
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/doLogin") //direct to the login page
    public String doLogin() {
        return "login";
    }
}
