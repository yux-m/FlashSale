package yuxm.flashsale.config;

import yuxm.flashsale.entity.User;

public class UserContext {

    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static User getUser() {
        return userThreadLocal.get();
    }

    public static void setUser(User tUser) {
        userThreadLocal.set(tUser);
    }
}