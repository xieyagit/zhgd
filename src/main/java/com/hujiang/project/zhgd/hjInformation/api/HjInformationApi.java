package com.hujiang.project.zhgd.hjInformation.api;


import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjInformation.domain.HjInformation;
import com.hujiang.project.zhgd.hjInformation.service.IHjInformationService;
import com.hujiang.project.zhgd.hjMenu.service.IHjMenuService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import com.hujiang.project.zhgd.utils.Download;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

import static com.hujiang.project.zhgd.utils.Download.saveUrlAs;

@RestController
@RequestMapping(value = "/provider/lzfw")
public class HjInformationApi extends BaseController {

    @Autowired
    Download download;
    @Autowired
    IHjInformationService informationService;

    @Autowired
    IHjMenuService menuService;
    /**
     * 查看详情
     * */
    @RequestMapping("/particulars")
    @ResponseBody
    public JSONObject particulars(@RequestBody HjInformation hjInformation){
        startPage();
        HjInformation information = new HjInformation();
        information.setId(hjInformation.getId());
        information.setMenuId(hjInformation.getMenuId());
        information.setProjectId(hjInformation.getProjectId());
        List<HjInformation> result = informationService.particulars(information);
        TableDataInfo dataTable = getDataTable(result);
        List<HjInformation> rows = (List<HjInformation>)dataTable.getRows();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","查询成功");
        jsonObject.put("total",dataTable.getTotal());
        jsonObject.put("code",0);
        jsonObject.put("data",dataTable);
        return jsonObject;
    }

     /**
     * 删除
     * */
    @PostMapping(value = "/deleteHjInformationById")
    public JSONObject deleteHjInformationById(@RequestParam(value ="id" )Integer id){
        JSONObject jsonObject = new JSONObject();
        HjInformation information = new HjInformation();
        information.setId(id);
        List<HjInformation> list = informationService.particulars(information);
        AliyunOSSClientUtil.deleteFile(AliyunOSSClientUtil.getOSSClient(), "hujiang", list.get(0).getFilePath());
        Integer result = informationService.deleteHjInformationById(id);
        HjInformation tion = new HjInformation();
        tion.setMenuId(list.get(0).getMenuId());
        tion.setProjectId(list.get(0).getProjectId());
        List<HjInformation> lists = informationService.particulars(tion);
        if (lists.size()<=0){
            int i = menuService.updates(list.get(0).getMenuId());
            if (i>0){
                jsonObject.put("mig","状态修改成功");
            }else {
                jsonObject.put("mig","状态修改失败");
            }
        }
        if (result > 0) {
            jsonObject.put("msg", "删除成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", result);
            return jsonObject;
        } else {
            jsonObject.put("msg", "删除失败");
            jsonObject.put("code", 1);
            jsonObject.put("data", result);
            return jsonObject;
        }
    }

    /**
     * 上传
     * */
    @PostMapping(value = "/instadd")
//    @ResponseBody、
    public JSONObject instadd(@RequestBody HjInformation hjInformation) throws IOException {
        JSONObject result = new JSONObject();
        HjInformation information = new HjInformation();
        //文件路径
        for (int i = 0;i<hjInformation.getFileNames().length;i++){
            information.setMenuId(hjInformation.getMenuId());
            information.setFileName(hjInformation.getFileNames()[i]);
            information.setUploadingName(hjInformation.getUploadingName());
            information.setUploadingDate(new Date());
            information.setRemark(hjInformation.getRemark());
            information.setProjectId(hjInformation.getProjectId());
            information.setFilePath(hjInformation.getFilePaths()[i]);
            int k = informationService.instadd(information);
            int id = hjInformation.getMenuId();
            if (k>0){
                menuService.update(id);
            }
            result.put("msg", k > 0 ? "添加成功" : "添加失败");
            result.put("code", k > 0 ? 0 : -1);
            result.put("data", k);
        }
        return result;
    }
    /**
     * 编辑
     * */
    @PostMapping(value = "/upda")
    public JSONObject upda(HjInformation hjInformation) throws IOException {
        JSONObject result = new JSONObject();
        HjInformation information = new HjInformation();
        //文件路径
        String url = null;
        int j =1; //删除服务器上的文件
        if (j>0) {
            for (int i = 0; i < hjInformation.getFile().length; i++) {
                information.setFileName(hjInformation.getFile()[i].getOriginalFilename());
                information.setUploadingName(hjInformation.getUploadingName());
                information.setUploadingDate(new Date());
                information.setRemark(hjInformation.getRemark());
                information.setId(hjInformation.getId());
                url = AliyunOSSClientUtil.uploadFileImg(hjInformation.getFile()[i], "zhgd_img", information.getFileName());
                String name = url.substring(0, url.lastIndexOf("?"));
                information.setFilePath(name);
                int k = informationService.upda(information);
                result.put("msg", k > 0 ? "编辑成功" : "编辑失败");
                result.put("code", k > 0 ? 0 : -1);
                result.put("data",k);
            }
            return result;
        }else {
            result.put("msg","编辑失败");
            result.put("code",-1);
            return result;
        }

    }

}

