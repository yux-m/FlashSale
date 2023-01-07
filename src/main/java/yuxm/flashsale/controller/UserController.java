package yuxm.flashsale.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.vo.RespBean;

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

}
