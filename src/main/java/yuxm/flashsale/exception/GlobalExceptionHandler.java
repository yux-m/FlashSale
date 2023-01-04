package yuxm.flashsale.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yuxm.flashsale.vo.RespBean;
import yuxm.flashsale.vo.RespBeanEnum;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RespBean ExceptionHandler(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException exception = (GlobalException) e;
            return RespBean.error(exception.getRespBeanEnum());
        }
        System.out.println("Message: " + e);
        return RespBean.error(RespBeanEnum.ERROR);
    }

}
