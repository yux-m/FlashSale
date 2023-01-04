package yuxm.flashsale.utils;

import java.util.UUID;

/**
 * UUID tools.
 */
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}