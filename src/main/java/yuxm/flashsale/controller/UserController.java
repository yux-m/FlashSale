package yuxm.flashsale.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.rabbitmq.MQSender;
import yuxm.flashsale.service.IUserService;
import yuxm.flashsale.utils.CookieUtil;
import yuxm.flashsale.vo.RespBean;
import yuxm.flashsale.vo.RespBeanEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @Autowired
    private MQSender mqSender;

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

    @RequestMapping("/toUpdatePassword")
    public String toUpdatePassword() {
        return "updatePassword";    //direct to page for password updating called "updatePassword.html"
    }

    @RequestMapping("/doUpdatePassword")
    public RespBean doUpdatePassword(String newPassword, HttpServletRequest request, HttpServletResponse response) {
        //
        String ticket = CookieUtil.getCookieValue(request, "userTicket");
        if (ticket == null || ticket.isEmpty()) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        return userService.updatePassword(ticket, newPassword, request, response);
        //redirect to login page in html jquery function
    }

    @RequestMapping("/confirmPassword")
    public RespBean confirmPassword(String password, String repeat) {
        return userService.confirmPassword(password, repeat);
    }

//    /**
//     * For mq testing
//     */
//    @RequestMapping(value = "/mq")
//    @ResponseBody
//    public void mq() {
//        mqSender.send("Hello");
//    }

}
