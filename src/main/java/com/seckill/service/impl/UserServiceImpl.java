package com.seckill.service.impl;

import com.seckill.common.CodeMsg;
import com.seckill.entity.dto.UserDTO;
import com.seckill.entity.vo.LoginVO;
import com.seckill.exception.GlobalException;
import com.seckill.mapper.UserMapper;
import com.seckill.service.UserService;
import com.seckill.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author boting.guo
 * @date 2020/11/10 19:20
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void login(HttpServletResponse response, LoginVO loginVO) {
        if (Objects.isNull(loginVO)) {
            throw new GlobalException(CodeMsg.BIND_ERROR);
        }

        UserDTO user = userMapper.selectById(loginVO.getId());
        if (Objects.isNull(user)) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        String encryptedPsw = MD5Util.getInstance().encrypt(loginVO.getPassword(), user.getSalt());
        if (Objects.isNull(encryptedPsw) || !user.getPassword().equals(encryptedPsw)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }


    }
}
