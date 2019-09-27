package com.hujiang.project.common;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.AliyunOSSClientUtil;
import com.hujiang.common.utils.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping(value = "/provider/fileApi",method = RequestMethod.POST)
public class FileController {



    private String prefix = "lz/file";
    /**
     * 文件上传返回路径
     */
    @RequestMapping(value ="/upload")
    public void upload(
            @RequestParam(value = "file", required = true) MultipartFile[] file,
            @RequestParam(value = "folderName", required = true) String folderName,
            HttpServletRequest request, HttpServletResponse response)throws Exception {
        response.setContentType("application/json;charset=utf-8");
        System.out.println(file.length);
        System.out.println(folderName+"****************");
        folderName=folderName.replaceAll("\"","");
        System.out.println(folderName+"****************");
        JSONObject result=new JSONObject();
        List<Map<String,Object>> resultlist = new ArrayList<>();
        try {
            String folder = AliyunOSSClientUtil.createFolder(AliyunOSSClientUtil.getOSSClient(),
                    "hujiang", folderName.trim()+"/");
            if(file!=null&&file.length>0){
                for (MultipartFile multipartFile : file) {
                    //文件名称
                    String filename= StringUtil.getRandomStringByLength(6)+new SimpleDateFormat("HHmmss").format(new Date())+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename
                            ().lastIndexOf("."));
                    String FileImgurl = AliyunOSSClientUtil.uploadFileImg(multipartFile, folder,filename);
                    if(!"".equals(FileImgurl)){

                        String name = FileImgurl.substring(0,FileImgurl.lastIndexOf("?"));

                        Map<String,Object> resultJson=new HashMap<>();
                        resultJson.put("fileimgurl", name);
                        resultlist.add(resultJson);
                    }
                }
                result.put("data", resultlist);
                result.put("code", 0);
                result.put("msg", "上传成功！");
                System.out.println("--------------------"+resultlist);
            }else{
                result.put("data","file is null");
                result.put("msg", "上传失败！");
            }
            response.getWriter().write(result.toJSONString());
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
