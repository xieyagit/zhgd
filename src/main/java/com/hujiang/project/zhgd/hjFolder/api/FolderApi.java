package com.hujiang.project.zhgd.hjFolder.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjFile.domain.HjFile;
import com.hujiang.project.zhgd.hjFile.service.IHjFileService;
import com.hujiang.project.zhgd.hjFolder.domain.HjFolder;
import com.hujiang.project.zhgd.hjFolder.service.IHjFolderService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-28 11:34
 **/
@RestController
@RequestMapping(value = "/provider/folder/")
public class FolderApi extends BaseController {

    @Autowired
    private IHjFolderService folderService;

    @Autowired
    private IHjFileService fileService;

    /**
     *创建文件夹
     * @param folder
     * @return
     */
    @PostMapping("createFolder")
    public Map<String,Object> createFolder(@RequestBody HjFolder folder){
        Map<String,Object> map = new HashMap<>();
        int i = folderService.insertHjFolder(folder);
        if(i>0){
            return AjaxResult.success("创建成功");
        }
        return AjaxResult.error(-1,"创建失败");
    }

    /**
     * 获取文件夹列表
     * @param folder
     * @return
     */
    @PostMapping("folderList")
    public Map<String,Object> folderList(@RequestBody HjFolder folder){
        List<HjFolder> hjFolders = folderService.selectHjFolderList(folder);
        return AjaxResult.success(hjFolders);
    }

    /**
     * 删除文件夹及文件
     * @param ids
     * @return
     */
    @PostMapping("removeFolder")
    public Map<String,Object> removeFolder(@RequestParam(value = "ids") String ids ){
        Map<String,Object> map = new HashMap<>();
        String[] split = ids.split(",");
        for(int i =0 ; i<split.length ;i++){
            HjFile file = new HjFile();
            file.setFolderId(Integer.parseInt(split[i]));
            List<HjFile> hjFiles = fileService.selectHjFileList(file);
            if(hjFiles!=null && hjFiles.size()>0){
                HjFolder folder = folderService.selectHjFolderById(Integer.parseInt(split[i]));
                for(HjFile file1:hjFiles){
                    //删除文件
                    AliyunOSSClientUtil.deleteFile(AliyunOSSClientUtil.getOSSClient(), "hujiang", "project/"+file1.getFileName());
                }
                //删除文件路径
                int i1 = fileService.deleteHjFileByFolderId(Integer.parseInt(split[i]));
            }
        }
        int i = folderService.deleteHjFolderByIds(ids);

        return i>0?AjaxResult.success("删除成功"):AjaxResult.error(-1,"删除失败");
    }



}
