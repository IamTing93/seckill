package org.seckill.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean setValue(String key, String value, int expire) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value) || expire < 0) {
            return false;
        }

        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
        return true;
    }
}
