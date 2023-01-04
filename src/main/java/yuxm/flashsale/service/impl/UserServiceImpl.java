package yuxm.flashsale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.mapper.UserMapper;
import yuxm.flashsale.service.IUserService;
import yuxm.flashsale.utils.MD5utils;
import yuxm.flashsale.utils.Validators;
import yuxm.flashsale.vo.LoginVO;
import yuxm.flashsale.vo.RespBean;
import yuxm.flashsale.vo.RespBeanEnum;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public RespBean doLogin(@Valid LoginVO loginVO) {
        String email = loginVO.getEmail();
        String password = loginVO.getPassword();

        //check if email address is valid
        if (!Validators.isEmailAddress(email)) return RespBean.error(RespBeanEnum.EMAIL_ERROR);

        //get user
        User user = userMapper.selectById(email);
        //if user not found
        if (user == null) return RespBean.error(RespBeanEnum.USER_NOT_EXIST);

        //check if password is correct
        if (!MD5utils.secondLayer(password, user.getSalt()).equals(user.getPassword()))
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);

        //if both valid and correct, return success bean
        return RespBean.success();
    }
}
