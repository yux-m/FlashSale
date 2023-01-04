package yuxm.flashsale.utilsTest;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import yuxm.flashsale.utils.MD5Util;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MD5UtilTest {
    @Test
    void encryptTest() {
        //original password
        String cleartext = "123456";
        String randomSalt = "randomsalt";
        //first layer
        String encrypted = "l5" + cleartext + "ro";
        System.out.println("manual 1st layer: " + encrypted);
        encrypted = DigestUtils.md5Hex(encrypted);
        System.out.println("                  " + encrypted);
        //second layer
        encrypted = "dt" + encrypted + "ao";
        System.out.println("manual 2nd layer: " + encrypted);
        encrypted = DigestUtils.md5Hex(encrypted);
        System.out.println("                  " + encrypted);
        //check encrypted output
        assertEquals(encrypted, MD5Util.encrypt(cleartext, randomSalt));
    }
}
