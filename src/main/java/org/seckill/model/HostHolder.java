package org.seckill.model;

public class HostHolder {

    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void setValue(User user) {
        threadLocal.set(user);
    }

    public static User getValue() {
        return threadLocal.get();
    }
}
