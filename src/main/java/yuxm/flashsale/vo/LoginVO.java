package yuxm.flashsale.vo;

import lombok.Data;

/**
 * Value object for login.
 */
@Data
public class LoginVO {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
