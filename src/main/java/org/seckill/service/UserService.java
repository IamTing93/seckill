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

    private static final String TOKEN_NAME = "SECKILL_TOKEN";

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

        // 生成token
        String token = UUID.randomUUID().toString().replace("-", "");
        int expire = 7 * 24 * 3600;
        Cookie cookie = new Cookie(TOKEN_NAME, token);
        cookie.setPath("/");
        cookie.setMaxAge(expire);

        // 保存token到缓存中
        redisService.setValue(token, user, expire);
        return cookie;
    }

    public User getUserByUsername(String username) {
        return userDAO.selectOne(new QueryWrapper<User>().lambda().eq(User::getName, username));
    }
}
