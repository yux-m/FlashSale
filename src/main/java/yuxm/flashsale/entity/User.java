package yuxm.flashsale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * User Table
 * </p>
 *
 * @author yuxm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * userID
     */
    private String id;

    private String username;

    /**
     * double hashed, MD5(MD5(cleartext + static salt) + random salt)
     */
    private String password;

    private String salt;

    private String avatar;

    private Date registerDate;

    private Date lastLoginDate;

    private Integer loginCount;


}
