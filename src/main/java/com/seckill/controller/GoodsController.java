package com.seckill.controller;

import com.seckill.entity.dto.GoodsDTO;
import com.seckill.entity.vo.GoodsVO;
import com.seckill.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
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

    @GetMapping("/list")
    public String listGoods(ModelMap map) {
        List<GoodsDTO> list = goodsService.listGoods();
        List<GoodsVO> voList = new ArrayList<>(list.size());
        for (GoodsDTO goodsDTO: list) {
            GoodsVO vo = new GoodsVO();
            BeanUtils.copyProperties(goodsDTO, vo);
            voList.add(vo);
        }
        map.put("goodsList", voList);
        return "goods_list";
    }

    @GetMapping("/detail/{id}")
    public String goodsDetail(@PathVariable("id") Long id, ModelMap map) {
        GoodsDTO dto = goodsService.goodsDetail(id);


        return "goods_detail";
    }
}
