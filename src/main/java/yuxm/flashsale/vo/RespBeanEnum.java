package yuxm.flashsale.vo;

public enum RespBeanEnum {
    //common
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "SERVER ERROR"),

    //for login
    LOGIN_ERROR(500210, "Invalid email address and/or password"),
    EMAIL_ERROR(500211, "Invalid email address format"),
    BIND_ERROR(500212, "Validation error"),
    USER_NOT_EXIST(500213, "User not exist"),
    PASSWORD_UPDATE_FAIL(500214, "Password update failed"),
    SESSION_ERROR(500215, "SESSION not exist"),

    //for purchase
    EMPTY_STOCK(500500, "Empty Stock"),
    REPEATE_ERROR(500501, "Exceeds maximum products per user"),
    REQUEST_ILLEGAL(500502, "Illegal Request"),
    ERROR_CAPTCHA(500503, "Verification failed, please re-verify"),
    ACCESS_LIMIT_REACHED(500504, "Too many requests, please try again later"),

    //for orders
    ORDER_NOT_EXIST(500300, "Order not exist"),

    ;

    private final Integer code;
    private final String message;

    RespBeanEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
