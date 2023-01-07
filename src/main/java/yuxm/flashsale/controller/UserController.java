package yuxm.flashsale.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.vo.RespBean;
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

    /**
     * Request for user info. For load testing.
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public RespBean info(User user) {
        return RespBean.success(user);
    }

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
