package yuxm.flashsale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.vo.LoginVO;
import yuxm.flashsale.vo.RespBean;

/**
 * User Table Service class
 *
 * @author yuxm
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVO loginVO);
}
