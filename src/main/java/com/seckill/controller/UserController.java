package com.seckill.controller;

import com.seckill.common.CodeMsg;
import com.seckill.entity.vo.LoginInfoVO;
import com.seckill.entity.common.ServerResponse;
import com.seckill.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author boting.guo
 * @date 2020/11/10 16:44
 */

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/doLogin")
    @ResponseBody
    public ServerResponse<CodeMsg> doLogin(HttpServletResponse response, @Validated LoginInfoVO loginInfoVO) {
        log.info("Login: " + loginInfoVO.toString());

        userService.login(response, loginInfoVO);

        return ServerResponse.success(CodeMsg.SUCCESS);
    }

    @PostMapping("/doRegister")
    @ResponseBody
    public ServerResponse<CodeMsg> doRegister(HttpServletResponse response, @Validated LoginInfoVO loginInfoVO) {
        log.info("Register: " + loginInfoVO.toString());
        userService.registerAndLogin(response, loginInfoVO);
        return ServerResponse.success(CodeMsg.SUCCESS);
    }
}
