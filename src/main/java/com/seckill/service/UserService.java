package com.seckill.service;

import com.seckill.entity.dto.UserDTO;
import com.seckill.entity.vo.LoginInfoVO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author boting.guo
 * @date 2020/11/10 19:14
 */


public interface UserService {

    String registerAndLogin(HttpServletResponse response, LoginInfoVO loginInfo);

    void batchRegisterForTest(LoginInfoVO loginInfo);

    String login(HttpServletResponse response, LoginInfoVO loginInfo);

    UserDTO getUserFromCookies(Cookie[] cookies);
}
