package com.wh.controller;

import com.wh.entity.Banner;
import com.wh.service.BannerService;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @author : WH
 * @date : 2021/4/25 13:36
 */
@RestController
public class FileController {
    @Resource
    private BannerService bannerService;

    @PostMapping(value = "/file")
    public String file() {
        return "file";
    }

    @PostMapping(value = "/fileUpload")
    public int fileUpload(MultipartFile file,HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        System.out.println(fileName);
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名

        fileName = UUID.randomUUID() + suffixName; // 新文件名

        //存储路径
        String filePath = null;
        try {
            filePath = ResourceUtils.getURL("classpath:").getPath() + "static/" + fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(filePath);
        File dest = new File(filePath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s1=request.getServletPath();
        String s2=request.getRequestURL().toString();
        String s3 = s2.replace(s1, "");
        System.out.println(s3);

        Banner banner = new Banner();
        String BannerUrl=s3+"/"+fileName;
        banner.setBannerUrl(BannerUrl);
        banner.setIsShow(1);
        Banner insert = bannerService.insert(banner);
        System.out.println(insert.getBId());
        //将图片的id返回
        return insert.getBId();   //返回值是这个格式"http://localhost:8080/4fa632ba-5aaf-4748-bbaa-54f05f6fc3a1.jpg"
    }
}
