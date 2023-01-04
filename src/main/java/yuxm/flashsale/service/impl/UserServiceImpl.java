package yuxm.flashsale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import yuxm.flashsale.entity.User;
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


    @Resource
    private UserMapper userMapper;

    @Override
    public RespBean doLogin(@Valid LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) {
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
        request.getSession().setAttribute(ticket, user);
        CookieUtil.setCookie(request, response, "userTicket", ticket);

        //if both valid and correct, return success bean
        return RespBean.success();
    }
}
