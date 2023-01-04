package yuxm.flashsale.utilsTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static yuxm.flashsale.utils.ValidatorUtil.isEmailAddress;

public class ValidatorUtilTest {
    @Test
    void isEmailAddressTest() {
        //valid cases
        assertTrue(isEmailAddress("1@3.com"));
        assertTrue(isEmailAddress("xx@gmail.com"));
        assertTrue(isEmailAddress("cit@seas.upenn.edu"));
        //invalid cases
        assertFalse(isEmailAddress("@email"));
        assertFalse(isEmailAddress("url.com"));
        assertFalse(isEmailAddress("111yuxm"));
    }
}
