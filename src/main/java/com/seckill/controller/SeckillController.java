package com.seckill.controller;

import com.seckill.annotation.Authenticated;
import com.seckill.annotation.CurrentUser;
import com.seckill.entity.common.ServerResponse;
import com.seckill.entity.dto.UserDTO;
import com.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @PostMapping("doSeckill")
    @Authenticated
    public ServerResponse<String> doSeckill(@CurrentUser UserDTO user, Long goodsId) {
        seckillService.doSeckill(user, goodsId);

        return ServerResponse.success("Successful");
    }
}
