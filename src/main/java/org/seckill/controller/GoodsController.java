package org.seckill.controller;

import org.seckill.common.model.CommonResponse;
import org.seckill.service.GoodsService;
import org.seckill.vo.GoodsVO;
import org.seckill.vo.SeckillGoodsDetailVO;
import org.seckill.vo.SeckillGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/list")
    public CommonResponse<List<GoodsVO>> list() {
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();
        return CommonResponse.success(goodsVOList);
    }

    @GetMapping("/detail/{id}")
    public CommonResponse<GoodsVO> detail(@PathVariable("id") int id) {
        GoodsVO detailVO = goodsService.getGoodsVOByGoodsId(id);
        return CommonResponse.success(detailVO);
    }
}
