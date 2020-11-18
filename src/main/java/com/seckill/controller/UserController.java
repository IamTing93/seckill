package com.seckill.controller;

import com.seckill.annotation.Authenticated;
import com.seckill.annotation.CurrentUser;
import com.seckill.common.CodeMsg;
import com.seckill.entity.common.ServerResponse;
import com.seckill.entity.dto.UserDTO;
import com.seckill.entity.vo.LoginInfoVO;
import com.seckill.entity.vo.UserVO;
import com.seckill.service.UserService;
import com.seckill.utils.FileUtil;
import com.seckill.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author boting.guo
 * @date 2020/11/10 16:44
 */

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${com.seckill.cookie.key-name}")
    private String COOKIE_FIXED_SALT;

    @Value("${com.seckill.performance.cookie-output-path}")
    private String COOKIE_OUTPUT_PATH;

    @Value("${com.seckill.performance.user-output-path}")
    private String USER_OUTPUT_PATH;

    @Autowired
    private UserService userService;

    public UserController() {
    }

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

        return ServerResponse.success(null);
    }

    @PostMapping("/doRegister")
    @ResponseBody
    public ServerResponse<CodeMsg> doRegister(HttpServletResponse response, @Validated LoginInfoVO loginInfoVO) {
        log.info("Register: " + loginInfoVO.toString());
        userService.registerAndLogin(response, loginInfoVO);
        return ServerResponse.success(null);
    }

    @GetMapping("/currentUser")
    @ResponseBody
    @Authenticated
    public ServerResponse<UserVO> getCurrentUser(@CurrentUser UserDTO user) {
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        return ServerResponse.success(vo);
    }

    @PostMapping("batchRegisterForTest")
    @ResponseBody
    public ServerResponse<CodeMsg> batchRegisterForTest(int num) throws IOException {
        for (int i = 1; i <= num; i++) {
            LoginInfoVO vo = new LoginInfoVO(String.valueOf(i), String.valueOf(i));
            vo.setPassword(MD5Util.getInstance().encrypt(vo.getPassword(), COOKIE_FIXED_SALT));
            userService.batchRegisterForTest(vo);
        }
        return ServerResponse.success(null);
    }

    @PostMapping("/doLoginForPerformance")
    @ResponseBody
    public ServerResponse<CodeMsg> doLoginForPerformance(HttpServletResponse response, @Validated LoginInfoVO loginInfoVO) {
        log.info("LoginForPerformance: " + loginInfoVO.toString());
        loginInfoVO.setPassword(MD5Util.getInstance().encrypt(loginInfoVO.getPassword(), COOKIE_FIXED_SALT));
        String token = userService.login(response, loginInfoVO);
        FileUtil.outputToFile(COOKIE_OUTPUT_PATH, token + "\n", true);
        return ServerResponse.success(null);
    }

    @PostMapping("/doRegisterForPerformance")
    @ResponseBody
    public ServerResponse<CodeMsg> doRegisterForPerformance(HttpServletResponse response, @Validated LoginInfoVO loginInfoVO) {
        log.info("RegisterForPerformance: " + loginInfoVO.toString());
        loginInfoVO.setPassword(MD5Util.getInstance().encrypt(loginInfoVO.getPassword(), COOKIE_FIXED_SALT));
        String token = userService.registerAndLogin(response, loginInfoVO);
        FileUtil.outputToFile(COOKIE_OUTPUT_PATH, token + "\n", true);
        return ServerResponse.success(null);
    }
}
