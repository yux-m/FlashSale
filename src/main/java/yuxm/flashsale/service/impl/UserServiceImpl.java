package yuxm.flashsale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.exception.GlobalException;
import yuxm.flashsale.mapper.UserMapper;
import yuxm.flashsale.service.IUserService;
import yuxm.flashsale.utils.CookieUtil;
import yuxm.flashsale.utils.MD5Util;
import yuxm.flashsale.utils.UUIDUtil;
import yuxm.flashsale.utils.ValidatorUtil;
import yuxm.flashsale.vo.LoginVO;
import yuxm.flashsale.vo.RespBean;
import yuxm.flashsale.vo.RespBeanEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Login function implementation.
     * Verify given account id (email) and password. If verification suceeds, generate and save cookie (userTicket).
     *
     * @param loginVO
     * @param request
     * @param response
     * @return
     */
    @Override
    public RespBean doLogin(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) {
        String email = loginVO.getEmail();
        String password = loginVO.getPassword();

        //check if email address is valid
        if (!ValidatorUtil.isEmailAddress(email)) return RespBean.error(RespBeanEnum.EMAIL_ERROR);

        //get user
        User user = userMapper.selectById(email);
        //if user not found
        if (user == null) return RespBean.error(RespBeanEnum.USER_NOT_EXIST);

        //check if password is correct
        if (!MD5Util.secondLayer(password, user.getSalt()).equals(user.getPassword()))
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);

        //generate cookie
        String ticket = UUIDUtil.uuid();
        //save user info to redis (instead of session)
        redisTemplate.opsForValue().set("user:" + ticket, user);
//        request.getSession().setAttribute(ticket, user);
        CookieUtil.setCookie(request, response, "userTicket", ticket);

        //if both valid and correct, return success bean
        return RespBean.success(ticket);
    }

    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if (userTicket == null || userTicket.isEmpty()) {
            return null;
        }
        Object debug = redisTemplate.opsForValue();
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        if (user != null) {
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }
        return user;
    }

    /**
     * Update password for current user.
     *
     * @param userTicket
     * @param password
     * @param request
     * @param response
     * @return
     */
    @Override
    public RespBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = getUserByCookie(userTicket, request, response);
        if (user == null) throw new GlobalException(RespBeanEnum.USER_NOT_EXIST);
        //update password
        user.setPassword(MD5Util.secondLayer(password, user.getSalt()));
        int res = userMapper.updateById(user);
        //ensured cache consistency by cache invalidation
        if (res == 1) {
            //delete cache in redis
            redisTemplate.delete("user:" + userTicket);
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.PASSWORD_UPDATE_FAIL);
    }

    /**
     * Check if new password is valid and if two passwords are the same.
     *
     * @param password
     * @param repeat
     * @return
     */
    @Override
    public RespBean confirmPassword(String password, String repeat) {
        if (passwordInvalid(password)) return RespBean.error(RespBeanEnum.PASSWORD_FORMAT_ERROR);
        if (!password.equals(repeat)) return RespBean.error(RespBeanEnum.PASSWORD_MATCH_ERROR);
        else return RespBean.success();
    }

    /**
     * Detailed validation of password format.
     * Written as an extra method for easy future modification on restriction(s).
     *
     * @param password
     * @return
     */
    @Override
    public boolean passwordInvalid(String password) {
        return password.length() < 6;
    }

}
