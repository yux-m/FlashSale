package yuxm.flashsale.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuxm.flashsale.service.IUserService;

/**
 * <p>
 * User Table frontend controller
 * </p>
 *
 * @author yuxm
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("/toUpdatePassword")
    public String toUpdatePassword() {
        //
        return "updatePassword";    //direct to page for password updating called "updatePassword.html"
    }

    @RequestMapping("/doUpdatePassword")
    public String doUpdatePassword() {
        //
        return "login"; //redirect to login page
    }

}
