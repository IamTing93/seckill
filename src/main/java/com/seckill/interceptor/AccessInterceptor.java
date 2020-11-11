package com.seckill.interceptor;

import com.seckill.annotation.Authenticated;
import com.seckill.entity.dto.UserDTO;
import com.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author boting.guo
 * @date 2020/11/11 13:48
 */

@Component
public class AccessInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        UserDTO user = userService.getUserFromCookies(cookies);
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            if (hm.hasMethodAnnotation(Authenticated.class) &&
                    Objects.isNull(user)) {
                response.sendRedirect("/user/login");
                return false;
            }
        }
        return true;
    }
}
