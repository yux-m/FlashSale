package yuxm.flashsale.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Value object for login.
 */
@Data
public class LoginVO {
    @NotNull
    private String email;
    @NotNull
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
