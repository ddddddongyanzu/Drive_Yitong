package com.wh.controller;

import com.wh.entity.Price;
import com.wh.service.PriceService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Price)表控制层
 *
 * @author makejava
 * @since 2021-04-27 15:13:54
 */
@RestController
@RequestMapping(value = "price", method = {RequestMethod.POST,RequestMethod.GET})
public class PriceController {
    /**
     * 服务对象
     */
    @Resource
    private PriceService priceService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @PostMapping("/selectPrice")
    public List<Price> selectPrice() {
        return this.priceService.queryAll();
    }

    @RequestMapping("/selectOne")
    public Price selectOne(@ApiParam("价格id：1代表c2,2代c2")int id) {
        return this.priceService.queryById(id);
    }

    @PostMapping("/updatePrice")
    public String updatePrice(@ApiParam("价格")double price,@ApiParam("价格id：1代表c2,2代c2")int id) {
        Price newPrice = new Price();
        newPrice.setpId(id);
        newPrice.setPrice(price);
        this.priceService.update(newPrice);
        return "ok";
    }
}
