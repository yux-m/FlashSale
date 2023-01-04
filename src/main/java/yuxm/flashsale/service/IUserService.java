package yuxm.flashsale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.vo.LoginVO;
import yuxm.flashsale.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User Table Service class
 *
 * @author yuxm
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response);

    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);

}
