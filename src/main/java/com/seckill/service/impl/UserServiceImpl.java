package com.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import org.springframework.data.redis.core.RedisTemplate;
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

    @Value("${com.seckill.cookie.key-name}")
    private String COOKIE_KEY_NAME;

    @Value("${com.seckill.cookie.prefix}")
    private String COOKIE_PREFIX;

    @Value("${com.seckill.cookie.max-age}")
    private int COOKIE_MAX_AGE;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
        // 更新最后登录时间和登录次数
        LocalDateTime now = LocalDateTime.now(ZoneId.of("+8"));
        int loginCount = user.getLoginCount() + 1;
        userMapper.update(null, new UpdateWrapper<UserDTO>().lambda()
                .set(UserDTO::getLastLoginDate, now)
                .set(UserDTO::getLoginCount, loginCount)
                .eq(UserDTO::getId, user.getId()));

        user.setLastLoginDate(now);
        user.setLoginCount(loginCount);
        addCookie(response, user);
        CurrentUserHolder.set(user);
    }

    private void addCookie(HttpServletResponse response, UserDTO user) {
        String token = createToken();
        Cookie cookie = new Cookie(COOKIE_KEY_NAME, token);
        cookie.setPath("/");
        cookie.setMaxAge(COOKIE_MAX_AGE);
        response.addCookie(cookie);

        // 更新redis缓存
        updateRedisCache(token, user);
    }

    public String getTokenFromCookies(Cookie[] cookies) {
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(COOKIE_KEY_NAME)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public UserDTO getUserFromToken(String token) {
        UserDTO user = (UserDTO) redisTemplate.opsForValue().get(token);
        CurrentUserHolder.set(user);
        return user;
    }

    @Override
    public UserDTO getUserFromCookies(Cookie[] cookies) {
        return getUserFromToken(getTokenFromCookies(cookies));
    }

    private void updateRedisCache(String key, UserDTO value) {
        // 先删除之前的记录
        redisTemplate.delete(key);
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value, COOKIE_MAX_AGE, TimeUnit.SECONDS);
    }

    private String createToken() {
        return COOKIE_PREFIX + UUID.randomUUID().toString();
    }

}
