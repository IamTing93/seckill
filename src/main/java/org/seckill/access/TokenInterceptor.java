package org.seckill.access;

import org.seckill.model.HostHolder;
import org.seckill.model.User;
import org.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            User user = this.getUser(request, response);
            HostHolder.setValue(user);
        }

        return true;
    }

    public User getUser(HttpServletRequest request, HttpServletResponse response) {
        String cookieToken = getCookieValue(request, UserService.TOKEN_NAME);

        if (StringUtils.isEmpty(cookieToken)) {
            return null;
        }
        return userService.getByToken(cookieToken);
    }

    public String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies) || cookies.length == 0) {
            return null;
        }

        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
