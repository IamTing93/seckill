package com.seckill.service.impl;

import com.seckill.common.CodeMsg;
import com.seckill.entity.CurrentUserHolder;
import com.seckill.entity.dto.UserDTO;
import com.seckill.entity.vo.LoginInfoVO;
import com.seckill.exception.GlobalException;
import com.seckill.mapper.UserMapper;
import com.seckill.service.UserService;
import com.seckill.utils.MD5Util;
import com.seckill.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author boting.guo
 * @date 2020/11/10 19:20
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Value("${com.seckill.cookie.key-prefix}")
    private String COOKIE_KEY_PREFIX;

    @Value("${com.seckill.cookie.max-age}")
    private int MAX_AGE;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void registerAndLogin(HttpServletResponse response, LoginInfoVO loginInfo) {
        if (Objects.isNull(loginInfo)) {
            throw new GlobalException(CodeMsg.BIND_ERROR);
        }

        UserDTO user = userMapper.selectById(loginInfo.getMobile());
        if (Objects.nonNull(user)) {
            throw new GlobalException(CodeMsg.MOBILE_EXISTS);
        }

        String salt = RandomUtil.RandomString(5);
        LocalDateTime now = LocalDateTime.now(ZoneId.of("+8"));

        user = UserDTO.builder()
                .id(Long.parseLong(loginInfo.getMobile()))
                .nickname("user_" + RandomUtil.randomNumberString(10))
                .password(MD5Util.getInstance().encrypt(loginInfo.getPassword(), salt))
                .salt(salt)
                .registerDate(now)
                .lastLoginDate(now)
                .loginCount(0)
                .head(null)
                .build();

        userMapper.insert(user);
        login(response, loginInfo);
    }

    @Override
    public void login(HttpServletResponse response, LoginInfoVO loginInfo) {
        if (Objects.isNull(loginInfo)) {
            throw new GlobalException(CodeMsg.BIND_ERROR);
        }

        UserDTO user = userMapper.selectById(loginInfo.getMobile());
        if (Objects.isNull(user)) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        String encryptedPsw = MD5Util.getInstance().encrypt(loginInfo.getPassword(), user.getSalt());
        if (Objects.isNull(encryptedPsw) || !user.getPassword().equals(encryptedPsw)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }

        addCookie(response, user);
        CurrentUserHolder.set(user);
    }

    private void addCookie(HttpServletResponse response, UserDTO user) {
        String token = createToken();
        String key = COOKIE_KEY_PREFIX + user.getId();
        Cookie cookie = new Cookie(key, token);
        cookie.setMaxAge(MAX_AGE);
        response.addCookie(cookie);

        // 更新redis缓存
        updateRedisCache(key, token);
    }

    private void updateRedisCache(String key, String value) {
        // 先删除之前的记录
        redisTemplate.delete(key);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(key, value, MAX_AGE, TimeUnit.SECONDS);
    }

    private String createToken() {
        return UUID.randomUUID().toString();
    }

}
