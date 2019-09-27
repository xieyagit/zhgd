package com.hujiang.project.zhgd.hjFile.api;

import ch.qos.logback.core.util.FileSize;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjBlacklist.domain.HjBlacklist;
import com.hujiang.project.zhgd.hjFile.domain.HjFile;
import com.hujiang.project.zhgd.hjFile.service.IHjFileService;
import com.hujiang.project.zhgd.hjFileSize.domain.HjFileSize;
import com.hujiang.project.zhgd.hjFileSize.service.IHjFileSizeService;
import com.hujiang.project.zhgd.hjFolder.service.IHjFolderService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import com.hujiang.project.zhgd.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-28 16:25
 **/
@RestController
@RequestMapping(value = "/provider/FileApi")
public class FileApi extends BaseController {

    @Autowired
    private IHjFileService fileService;
    @Autowired
    private IHjFileSizeService fileSizeService;

    /**
     * 文件列表
     * @param folderId
     * @return
     */
    @PostMapping(value = "getFileList")
    public Map<String,Object> getFileListclient(@RequestParam(value = "folderId") Integer folderId,
                                                @RequestParam(value = "fileName",required = false) String fileName){
        HjFile file = new HjFile();
        file.setFolderId(folderId);
        file.setFileName(fileName);
        startPage();
        List<HjFile> hjFiles = fileService.selectHjFileList(file);
        return  AjaxResult.success(getDataTable(hjFiles));
    }


    /**
     * 上传文件
     * @param folderId
     * @param projectId
     * @param uploadAccount
     * @param folderName
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("uploadFile")
    public Map<String,Object> uploadFile(Integer folderId,
                                         Integer projectId,
                                         String uploadAccount,
                                         String folderName,
                                         MultipartFile file)throws Exception{
        Map map = fileService.selectHjFileSize(projectId);
        long fileSize = file.getSize() / 1024;//文件大小

        BigDecimal sum =  (BigDecimal)map.get("sum");//已使用的空间
        Integer size =  (Integer)map.get("size");//总存储空间
        if(sum.longValue()+fileSize>=size.longValue()){//小于总存储空间
            return AjaxResult.error(-1,"存储空间不足");
        }
        int i = 0 ;
        long millis = System.currentTimeMillis();
        HjFile hjFile = new HjFile();
        if (file != null&&!file.isEmpty()) {
            //上传图片到oss服务器
            String url = AliyunOSSClientUtil.uploadFileImg(file, "project/", millis +file.getOriginalFilename());
            hjFile.setFileSize(fileSize);
            hjFile.setFileUrl(url.substring(0,url.indexOf("?")));
            hjFile.setFolderId(folderId);
            hjFile.setUploadAccount(uploadAccount);
            hjFile.setFileName(millis+file.getOriginalFilename());
            i = fileService.insertHjFile(hjFile);
        }
        return i>0?AjaxResult.success("上传文件成功"):AjaxResult.error(-1,"上传文件失败");
    }

    /**
     * 删除文件
     * @param ids
     * @return
     */
    @PostMapping("deleteFile")
    public  Map<String,Object> deleteFile(@RequestParam(value = "ids") String ids){
        String[] split = ids.split(",");
        int count  = 0; //统计删除的文件大小
        for(int i = 0; i<split.length ; i++){
            HjFile hjFile = fileService.selectHjFileById(Integer.parseInt(split[i]));
            AliyunOSSClientUtil.deleteFile(AliyunOSSClientUtil.getOSSClient(), "hujiang", hjFile.getFileUrl());
            count+=hjFile.getFileSize();

        }
        int i = fileService.deleteHjFileByIds(ids);
        return i>0?AjaxResult.success("删除成功"):AjaxResult.error(-1,"删除失败");
    }

}
