package com.seckill.service;

import com.seckill.entity.vo.LoginVO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author boting.guo
 * @date 2020/11/10 19:14
 */


public interface UserService {

    void login(HttpServletResponse response, LoginVO loginVO);
}
