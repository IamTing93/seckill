package com.seckill.controller;

import com.seckill.entity.SeckillStatus;
import com.seckill.entity.dto.SeckillGoodsDTO;
import com.seckill.entity.vo.SeckillGoodsVO;
import com.seckill.service.GoodsService;
import com.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SeckillService seckillService;

    @GetMapping("/list")
    public String listGoods(ModelMap map) {
        List<SeckillGoodsDTO> list = goodsService.listSeckillGoods();
        List<SeckillGoodsVO> voList = new ArrayList<>(list.size());
        for (SeckillGoodsDTO goodsDTO: list) {
            SeckillGoodsVO vo = new SeckillGoodsVO();
            BeanUtils.copyProperties(goodsDTO, vo);
            voList.add(vo);
        }
        map.put("goodsList", voList);
        return "goods_list";
    }

    @GetMapping("/detail/{id}")
    public String goodsDetail(@PathVariable("id") Long id, ModelMap map) {
        SeckillGoodsDTO dto = goodsService.seckillGoodsDetail(id);
        SeckillGoodsVO vo = new SeckillGoodsVO();
        BeanUtils.copyProperties(dto, vo);

        SeckillStatus status = seckillService.getSeckillGoodsStatus(dto);

        map.put("goods", vo);
        map.put("goodsStatus", status);
        return "goods_detail";
    }
}
