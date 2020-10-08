package org.seckill.controller;

import org.seckill.common.exception.Exceptions;
import org.seckill.common.model.CommonResponse;
import org.seckill.model.User;
import org.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public CommonResponse<String> login(HttpServletResponse response, String username, String password) {
        Cookie cookie = userService.login(username, password);
        response.addCookie(cookie);
        return CommonResponse.success("");
    }
}
