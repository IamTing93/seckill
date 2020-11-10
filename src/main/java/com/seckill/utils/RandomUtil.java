package com.seckill.utils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    private static final ThreadLocalRandom localRandom = ThreadLocalRandom.current();

    public static String randomNumberString(int len) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append(localRandom.nextInt(10));
        }
        return sb.toString();
    }

    public static String RandomString(int len) {
        String s = UUID.randomUUID().toString();
        return s.substring(0, Math.min(len, s.length()));
    }
}
