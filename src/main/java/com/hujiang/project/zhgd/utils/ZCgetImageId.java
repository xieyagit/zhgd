package com.hujiang.project.zhgd.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.util.Date;

/**
 * 上传工务署实名制
 */
public class ZCgetImageId {


    public static String getImageId(String apiKey,String imgUrl) throws  Exception{
        if(!"http://gd.17hr.net:8018/".equals(imgUrl)&&!"".equals(imgUrl)&&imgUrl!=null) {
            System.out.println("==========imgUrl:"+imgUrl);
            //先将在线图片转换成base64,再将base64转换成MultipartFile文件
            MultipartFile image = BASE64DecodedMultipartFile.base64ToMultipartOnt(BASE64DecodedMultipartFile.ImageToBase64ByOnline(imgUrl));
            System.gc();
            //工务署实名制地址
            String url = Constants.ZC_FORMALHOST + "/UploadImage";
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
//如果上传目录为/static/images/upload/,则可以如下获取
            File upload = new File(path.getAbsolutePath(), "static/upload/");

            if (!upload.exists()) {
                upload.mkdirs();
//            System.out.println(upload.getAbsolutePath());
                //在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
                //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
            }
            String getPath = upload.getAbsolutePath();
            //保存临时文件
            String fileName = getPath + "\\" + imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
//        System.out.println(fileName);
            File localFile = new File(fileName);
            image.transferTo(localFile);
//        System.out.println(fileName);

            HttpPost post = new HttpPost(url);
            post.addTextParams("api_key", apiKey);
            post.addFileparams("file", localFile);
            String send = post.send();
            System.out.println(send);
            System.gc();
            localFile.delete();

            JSONObject s = JSONObject.parseObject(send);
            if (!"true".equals(s.getString("result"))) {
                System.out.println("===========================");
                throw new Exception("上传图片错误！图片在线地址：" + imgUrl);
            }
            JSONObject data = JSONObject.parseObject(s.getString("result_data"));
            //返回图片ID
            return data.getString("image_id");
        }
        return "";
    }

    /**
     * base64转换id
     * @param apiKey
     * @param base64
     * @return
     * @throws Exception
     */
    public static String base64ToimgId(String apiKey,String base64) throws  Exception{
        //先将在线图片转换成base64,再将base64转换成MultipartFile文件
        MultipartFile image = BASE64DecodedMultipartFile.base64ToMultipartOnt(base64);
        System.gc();
        //工务署实名制地址
        String url= Constants.ZC_FORMALHOST+"/UploadImage";
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
//如果上传目录为/static/images/upload/,则可以如下获取
        File upload = new File(path.getAbsolutePath(), "static/upload/");

        if (!upload.exists()) {
            upload.mkdirs();
//            System.out.println(upload.getAbsolutePath());
            //在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
            //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
        }
        String getPath=upload.getAbsolutePath();
        //保存临时文件
        String fileName=getPath+"\\"+ System.currentTimeMillis() + ".jpg";
//        System.out.println(fileName);
        File localFile = new File(fileName);
        image.transferTo(localFile);
//        System.out.println(fileName);

        HttpPost post = new HttpPost(url);
        post.addTextParams("api_key",apiKey);
        post.addFileparams("file",localFile);
        String send = post.send();
        System.out.println(send);
        System.gc();
//        localFile.delete();

        JSONObject s=JSONObject.parseObject(send);
        if(!"true".equals(s.getString("result"))) {
            System.out.println("===========================");
            throw new Exception("上传图片错误！图片在线地址："+base64);
        }
        JSONObject data=JSONObject.parseObject(s.getString("result_data"));
        //返回图片ID
        return data.getString("image_id");
    }
    /**
     * 需要添加水印
     * @param apiKey
     * @param imgUrl
     * @param date
     * @return
     * @throws Exception
     */
    public static String getImageIds(String apiKey,String imgUrl,String date) throws  Exception{
        //先将在线图片转换成base64,再将base64转换成MultipartFile文件
        MultipartFile image = BASE64DecodedMultipartFile.base64ToMultipartOnt(BASE64DecodedMultipartFile.ImageToBase64ByOnline(imgUrl));
        System.gc();
        //工务署实名制地址
        String url= Constants.ZC_FORMALHOST+"/UploadImage";
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
//如果上传目录为/static/images/upload/,则可以如下获取
        File upload = new File(path.getAbsolutePath(), "static/upload/");

        if (!upload.exists()) {
            upload.mkdirs();
//            System.out.println(upload.getAbsolutePath());
            //在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
            //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
        }
        String getPath=upload.getAbsolutePath();
        //保存临时文件
        String fileName=getPath+"\\"+new Date().getTime()+imgUrl.substring(imgUrl.lastIndexOf("/")+1);
        String fileName2=getPath+"\\"+new Date().getTime()+imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.lastIndexOf("."))+".jpg";
//        System.out.println(fileName2);
        File localFile = new File(fileName);
        image.transferTo(localFile);
//        System.out.println(fileName);
        PrintJobTo.addWatermark(fileName,fileName2,date, Color.white,null,0,0);
        File localFile2 = new File(fileName2);

        HttpPost post = new HttpPost(url);
        post.addTextParams("api_key",apiKey);
        post.addFileparams("file",localFile2);
        String send = post.send();
        System.out.println(send);
        System.gc();
        localFile.delete();
        localFile2.delete();

        JSONObject s=JSONObject.parseObject(send);
        if(!"true".equals(s.getString("result"))) {
            System.out.println("===========================");
            throw new Exception("上传图片错误！图片在线地址："+imgUrl);
        }
        JSONObject data=JSONObject.parseObject(s.getString("result_data"));
        //返回图片ID
        return data.getString("image_id");
    }
    /**
     *
     *车辆
     * */
    public static JSONObject getCurrentImg(String apiKey,String imgUrl) throws Exception{
        //先将在线图片转换成base64,再将base64转换成MultipartFile文件
        MultipartFile image = BASE64DecodedMultipartFile.base64ToMultipartOnt(BASE64DecodedMultipartFile.ImageToBase64ByOnline(imgUrl));
        System.out.println(image);
        System.gc();
        //工务署车辆地址
        String url= Constants.SB+"uploadImage";
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (path.length() == 0) {
            path = new File("");
        }
        //如果上传目录为/static/images/upload/,则可以如下获取
        File upload = new File(path.getAbsolutePath(), "static/upload/");

        if (!upload.exists() || upload.length() == 0) {
            upload.mkdirs();
            //在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
            //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
        }
        String getPath=upload.getAbsolutePath();
        //保存临时文件
        String TruePath = imgUrl.substring(1, imgUrl.length()-26);
        String fileName=getPath+"/"+TruePath.substring(TruePath.lastIndexOf("/")+1 );
        String faName = fileName+".png";
        File localFile = new File(faName);
        image.transferTo(localFile);
        System.out.println(image);
        HttpPost post = new HttpPost(url);
        post.addTextParams("api_key",apiKey);
        post.addFileparams("file",localFile);
        System.out.println(post);
        String send = post.send();
        System.out.println(send);
        System.gc();
        localFile.delete();

        JSONObject s=JSONObject.parseObject(send);
        if(!"true".equals(s.getString("result"))) {
            System.out.println("===========================");
            throw new Exception("上传图片错误！图片在线地址："+imgUrl);
        }
        return s;
    }

}
