package com.seckill.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Objects;

/**
 * @author boting.guo
 * @date 2020/11/10 19:56
 */

@Component
public class MD5Util {

    @Getter
    @Value("${com.seckill.fixed-salt}")
    private String fixedSalt;

    private MD5Util() {}

    private static volatile MD5Util instance;

    public static MD5Util getInstance() {
        if (instance == null) {
            synchronized (MD5Util.class) {
                if (instance == null) {
                    instance = new MD5Util();
                }
            }
        }
        return instance;
    }

    public String encrypt(String str, String salt) {
        if (str == null || salt == null) {
            return null;
        }
        String s = salt.substring(0, salt.length() / 2) + str + salt.substring(salt.length() / 2);
        return DigestUtils.md5DigestAsHex(s.getBytes());
    }
}
