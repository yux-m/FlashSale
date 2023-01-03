package yuxm.flashsale.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * Password encryption utils class.
 */
@Component
public class MD5utils {

    private static final String SALT = "4eilo8trk5";

    /**
     * Double-layer encryption with md5 hashing.
     *
     * @param input user input (password)
     * @param salt  random salt generated
     * @return encoded(hashed) password
     */
    public static String encrypt(String input, String salt) {
        return secondLayer(firstLayer(input), salt);
    }

    /**
     * MD5 hash with hex encoding.
     *
     * @param text original text
     * @return hashed text
     */
    private static String md5(String text) {
        return DigestUtils.md5Hex(text);
    }

    /**
     * Transfer user input to hashed password received by backend.
     *
     * @param input input
     * @return encoded password
     */
    private static String firstLayer(String input) {
        String salted = "" + SALT.charAt(3) + SALT.charAt(9) + input + SALT.charAt(7) + SALT.charAt(4);
        return md5(salted);
    }

    /**
     * Transfer password received by backend to encoded password stored in database.
     *
     * @param received   password received at backend
     * @param randomSalt random salt generated
     * @return encoded password
     */
    public static String secondLayer(String received, String randomSalt) {
        String salted = "" + randomSalt.charAt(3) + randomSalt.charAt(9) + received + randomSalt.charAt(7) + randomSalt.charAt(4);
        return md5(salted);
    }

}
