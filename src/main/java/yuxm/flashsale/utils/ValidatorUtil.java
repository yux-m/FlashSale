package yuxm.flashsale.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?``{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private static Matcher matcher;

    /**
     * Validate email address
     *
     * @param addr email address for validation
     * @return true valid email address, false invalid email address
     */
    public static boolean isEmailAddress(final String addr) {

        matcher = emailPattern.matcher(addr);
        return matcher.find();

    }

}
