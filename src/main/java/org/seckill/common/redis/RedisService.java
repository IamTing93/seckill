package org.seckill.common.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public boolean setValue(String key, Object value, int expire) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value) || expire < 0) {
            return false;
        }

        String val;
        try {
            val = toString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }

        redisTemplate.opsForValue().set(key, val, expire, TimeUnit.SECONDS);
        return true;
    }

    public <T> T getValue(String key, Class<T> clazz) {
        String str = redisTemplate.opsForValue().get(key);
        try {
            return stringToBean(str, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toString(Object value) throws JsonProcessingException {
        Class<?> clazz = value.getClass();
        if (clazz == Integer.class || clazz == Long.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else {
            return objectMapper.writeValueAsString(value);
        }
    }

    public <T> T stringToBean(String str, Class<T> clazz) throws JsonProcessingException {
        if (StringUtils.isEmpty(str) || Objects.isNull(clazz)) {
            return null;
        }
        return objectMapper.readValue(str, clazz);
    }
}
