package com.seckill.entity;

import com.seckill.entity.dto.UserDTO;

public class CurrentUserHolder {

    private static final ThreadLocal<UserDTO> threadLocal = new ThreadLocal<>();

    public static UserDTO get() {
        return threadLocal.get();
    }

    public static void set(UserDTO user) {
        threadLocal.set(user);
    }
}
