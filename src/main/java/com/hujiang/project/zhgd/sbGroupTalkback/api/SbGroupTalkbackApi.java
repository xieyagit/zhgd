package com.hujiang.project.zhgd.sbGroupTalkback.api;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.sbGroupTalkback.domain.SbGroupTalkback;
import com.hujiang.project.zhgd.sbGroupTalkback.service.ISbGroupTalkbackService;

import com.hujiang.project.zhgd.sbGroupTalkback.ziputil.Zip;
import com.hujiang.project.zhgd.utils.FTPUtil;
import com.hujiang.project.zhgd.utils.Util;
import com.hujiang.project.zhgd.utils.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 获取集团对讲列表
 */
@RestController
@RequestMapping(value = "/provider/sbGroupTalkback")
public class SbGroupTalkbackApi extends BaseController{
        @Autowired
        private ISbGroupTalkbackService sbGroupTalkbackService;


        @PostMapping("/getAccountList")
        public List<SbGroupTalkback> getAccountList(@RequestBody SbGroupTalkback sbGroupTalkback){

            return sbGroupTalkbackService.getAccountList(sbGroupTalkback);
        }

        /**
         * 录音文件下载
         */
        @Autowired
        private FTPUtil ftpUtil;
        @GetMapping("/ftpDownload")
        public String ftpDownload(String ftpPath, String user, String date, String name, HttpServletResponse response)throws  Exception{
//                ftpPath=ftpPath.replaceFirst("0","");
                Map<String, Object> result = ftpUtil.downLoadTableFile(ftpPath, Util.getPath(),user,date,name);
                List<String> tableFileNameList=(List)result.get("fileNameList");
                String newResPath=Util.getPath()+"/"+ name+date;
                String zipPath =newResPath+ ".zip";                  // 压缩文件夹名
                ZipUtil.copyResource(tableFileNameList, newResPath);                  // 把pdf拷贝到同个文件目录下
                ZipUtil.createZip(newResPath, zipPath);                        // 打包改目录成.zip包

                String zipUrl = Zip.uploadObject2OSS(Zip.getOSSClient(), new File(zipPath), "hujiang", "zip");
//                System.out.println(zipUrl);
                //删除pdf文件
                boolean delete = new File(zipPath).delete();
                for(String s :tableFileNameList){
                        boolean delete1 = new File(s).delete();
                }






//         result.get("result");
                System.out.println(result.get("result"));
                if("true".equals(result.get("result").toString())){
                        return zipUrl;
                }
                return "-1";
        }

}

