package com.wh.controller;

import com.wh.entity.Banner;
import com.wh.service.BannerService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Banner)表控制层
 *
 * @author makejava
 * @since 2021-04-27 15:13:47
 */
@RestController
@RequestMapping(value = "banner", method = {RequestMethod.POST,RequestMethod.GET})
public class BannerController {
    /**
     * 服务对象
     */
    @Resource
    private BannerService bannerService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Banner selectOne(@ApiParam("广告id")Integer id) {
        return this.bannerService.queryById(id);
    }

    @RequestMapping("/getAllBanner")
    public List<Banner> getAllBanner() {
        return this.bannerService.queryAll();
    }

    @RequestMapping("/onBanner")
    public int onBanner(@ApiParam("广告id")int bid) {
        Banner banner = bannerService.queryById(bid);
        banner.setIsShow(1);
        bannerService.update(banner);
        return 1;
    }

    @RequestMapping("/offBanner")
    public int offBanner(@ApiParam("广告id")int bid) {
        Banner banner = bannerService.queryById(bid);
        banner.setIsShow(0);
        bannerService.update(banner);
        return 0;
    }

    @RequestMapping("/deleteBanner")
    public int deleteBanner(@ApiParam("广告id")int bid) {
        return bannerService.deleteById(bid) ? 1 : 0;
    }

}
