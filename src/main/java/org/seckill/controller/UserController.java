package org.seckill.controller;

import org.seckill.common.model.CommonResponse;
import org.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public CommonResponse<String> login(HttpServletResponse response, String username, String password) {
        Cookie cookie = userService.login(username, password);
        response.addCookie(cookie);
        return CommonResponse.success("");
    }

    @PostMapping("/register")
    public CommonResponse<String> registerAndLogin(HttpServletResponse response, String username, String password) {
        Cookie cookie = userService.registerAndLogin(username, password);
        response.addCookie(cookie);
        return CommonResponse.success("");
    }
}
