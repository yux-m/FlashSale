package yuxm.flashsale.exception;

import yuxm.flashsale.vo.RespBeanEnum;

public class GlobalException extends RuntimeException {

    private RespBeanEnum respBeanEnum;

    public GlobalException(RespBeanEnum respBeanEnum) {
        this.respBeanEnum = respBeanEnum;
    }

    public RespBeanEnum getRespBeanEnum() {
        return respBeanEnum;
    }

    public void setRespBeanEnum(RespBeanEnum respBeanEnum) {
        this.respBeanEnum = respBeanEnum;
    }

}
