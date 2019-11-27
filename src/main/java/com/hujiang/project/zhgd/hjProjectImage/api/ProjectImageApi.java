package com.hujiang.project.zhgd.hjProjectImage.api;


import com.alibaba.fastjson.JSONArray;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjProjectImage.domain.HjProjectImage;
import com.hujiang.project.zhgd.hjProjectImage.service.IHjProjectImageService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;



/**
 * @program: Provider01
 * @description: 项目接口
 * @author: Mr.LiuYong
 * @create: 2019-05-14 17:37
 **/
@RestController
@RequestMapping(value = "/provider/projectImage", method = RequestMethod.POST)
public class ProjectImageApi extends BaseController {

    @Autowired
    private IHjProjectImageService hjProjectImageService;

    /**
     * 查询轮播图列表
     *
     * @param hjProjectImage
     * @return String
     * @author yant
     */
    @RequestMapping(value = "selectProjectImageList")
    public AjaxResult selectProjectImageList(@RequestBody HjProjectImage hjProjectImage) {
        List<HjProjectImage> list = hjProjectImageService.selectHjProjectImageList(hjProjectImage);
        return AjaxResult.success(list);
    }

    /**
     * 添加轮播图
     *
     * @return String
     * @author yant
     */
    @RequestMapping(value = "insertProjectImage")
    @ResponseBody
    public AjaxResult insertProjectImage(HjProjectImage hjProjectImage, MultipartFile[] files) {
        //照片
        if (files != null && files.length > 0) {
            String url;
            String photoName;
            for (int i = 0; i < files.length; i++) {
                photoName = files[i].getOriginalFilename();
                try {
                    url = AliyunOSSClientUtil.uploadFileImg(files[i], "zhgd_img/", photoName);
                    hjProjectImage.setUrl(url);
                    if (hjProjectImage.getPosition() == null) {
                        hjProjectImage.setPosition(i);
                    }
                    hjProjectImageService.insertHjProjectImage(hjProjectImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return AjaxResult.success();
        } else {
            return AjaxResult.error("没有添加图片");
        }
    }

    /**
     * 更新轮播图
     *
     * @param hjProjectImage
     * @return String
     */
    @RequestMapping(value = "updateProjectImage")
    public AjaxResult updateHjProjectImage(@RequestBody HjProjectImage hjProjectImage, @RequestParam(value = "file") MultipartFile file) {
        if (!file.isEmpty()) {
            HjProjectImage image = hjProjectImageService.selectHjProjectImageById(hjProjectImage.getId());
            if (image.getUrl() != null) {
                String[] files = image.getUrl().split("/");
//            String file = url.substring(url.lastIndexOf(".com/") + 5);
//            String folder = substring.substring(0, substring.indexOf("/"));
                String mfile = null;
                for (int i = 0; i < files.length; i++) {
                    mfile = files[i];
                }
                AliyunOSSClientUtil.deleteFile(AliyunOSSClientUtil.getOSSClient(), "hujiang", mfile);
            }
            try {
                String photoName = file.getOriginalFilename();
                String url = AliyunOSSClientUtil.uploadFileImg(file, "zhgd_img/", photoName);
                hjProjectImage.setUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return toAjax(hjProjectImageService.updateHjProjectImage(hjProjectImage));
    }

    /**
     * 删除轮播图
     *
     * @param id
     * @return String
     */
    @RequestMapping(value = "removeProjectImage")
    public AjaxResult remove(@RequestParam(value = "id") Integer id) {
        HjProjectImage image = hjProjectImageService.selectHjProjectImageById(id);
        if (image == null) {
            return AjaxResult.error("没有图片");
        }
        String url = image.getUrl();
        if (url != null) {
            String[] files = url.split("/");
            String file = null;
            for (int i = 0; i < files.length; i++) {
                file = files[i];
            }
            AliyunOSSClientUtil.deleteFile(AliyunOSSClientUtil.getOSSClient(), "hujiang", file);
        }
        return toAjax(hjProjectImageService.deleteHjProjectImageById(id));
    }
}
