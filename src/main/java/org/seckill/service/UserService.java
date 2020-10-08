package org.seckill.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.seckill.common.exception.Exceptions;
import org.seckill.common.redis.RedisService;
import org.seckill.dao.UserDAO;
import org.seckill.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {

    public static final String TOKEN_NAME = "SECKILL_TOKEN";
    public static final String TOKEN_PRE = "token_";

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RedisService redisService;

    public Cookie login(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            throw new Exceptions.AccountEmpty();
        }

        if (StringUtils.isEmpty(password)) {
            throw new Exceptions.PasswordEmpty();
        }

        User user = this.getUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new Exceptions.AccountNotExist();
        }

        if (!DigestUtils.md5DigestAsHex((password + user.getSalt()).getBytes()).equals(user.getPassword())) {
            throw new Exceptions.PasswordError();
        }

        return createCookie(user);
    }

    Cookie createCookie(User user) {

        int expire = 7 * 24 * 3600;

        // 生成token
        String token = UUID.randomUUID().toString().replace("-", "");

        // 保存token到缓存中
        redisService.setValue(TOKEN_PRE + token, user, expire);

        Cookie cookie = new Cookie(TOKEN_NAME, token);
        cookie.setPath("/");
        cookie.setMaxAge(expire);

        return cookie;
    }

    public User getUserByUsername(String username) {
        return userDAO.selectOne(new QueryWrapper<User>().lambda().eq(User::getName, username));
    }

    public Cookie registerAndLogin(String username, String password) {

        if (StringUtils.isEmpty(username)) {
            throw new Exceptions.AccountEmpty();
        }

        if (StringUtils.isEmpty(password)) {
            throw new Exceptions.PasswordEmpty();
        }

        User user = this.getUserByUsername(username);
        if (!Objects.isNull(user)) {
            throw new Exceptions.AccountExist();
        }

        String salt = UUID.randomUUID().toString().replace("-", "");
        String pwd = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        user = User.builder().name(username).password(pwd).salt(salt).build();
        userDAO.insert(user);

        return createCookie(user);
    }

    public User getByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        User user = redisService.getValue(TOKEN_PRE + token, User.class);
        if (!Objects.isNull(user)) {
            return user;
        }
        return null;
    }
}
