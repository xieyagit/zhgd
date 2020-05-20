package com.hujiang.project.zhgd.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MoreDianAliyunOSSClientUtil {

 //log日志
    private static Logger logger = LoggerFactory.getLogger(MoreDianAliyunOSSClientUtil.class);

    //阿里云API的内或外网域名
    private static final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    //阿里云API的密钥Access Key ID
    private static final String accessKeyId = "xIqLQSxkCgFfcSfws7cVO+nFqfcfK7Mx";
    //阿里云API的密钥Access Key Secret
    private static final String accessKeySecret = "4nzYQGBQQjCikUCenmAzl3MbVC33DO+biXdU1NXKVu0=";
    //阿里云API的bucket名称
    private static final String bucketName = "moredians";
    //阿里云API的bucket路径
    public static final String urlPrefix = "http://moredians.oss-cn-hangzhou.aliyuncs.com";

    private static String readproperties(String key) {
        try {
            Locale locale = Locale.getDefault();
            ResourceBundle localResource = ResourceBundle.getBundle("oss", locale);
            String string = localResource.getString(key);
            System.out.println("value: " + string);
            return string;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 获取阿里云OSS客户端对象
     * @return ossClient
     */
    public static  OSSClient getOSSClient(){
        return new OSSClient(endpoint,DESUtil.desEncrypt(accessKeyId,"88888888","88888888"), DESUtil.desEncrypt(accessKeySecret,"88888888","88888888"));
    }

    /**
     * 创建存储空间
     * @param ossClient      OSS连接
     * @param bucketName 存储空间
     * @return
     */
    public  static String createBucketName(OSSClient ossClient,String bucketName){
        //存储空间
        final String bucketNames=bucketName;
        if(!ossClient.doesBucketExist(bucketName)){
            //创建存储空间
            Bucket bucket=ossClient.createBucket(bucketName);
            logger.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间buckName
     * @param ossClient  oss对象
     * @param bucketName  存储空间
     */
    public static  void deleteBucket(OSSClient ossClient, String bucketName){
        ossClient.deleteBucket(bucketName);
        logger.info("删除" + bucketName + "Bucket成功");
    }

    /**
     * 创建模拟文件夹
     * @param ossClient oss连接
     * @param bucketName 存储空间
     * @param folder   模拟文件夹名如"qj_nanjing/"
     * @return  文件夹名
     */
    public  static String createFolder(OSSClient ossClient,String bucketName,String folder){
        //文件夹名
        final String keySuffixWithSlash =folder;
        //判断文件夹是否存在，不存在则创建
        if(!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)){
            //创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            logger.info("创建文件夹成功");
            //得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir=object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    /**
     * 根据key删除OSS服务器上的文件
     * @param ossClient  oss连接
     * @param bucketName  存储空间
     * @param folder  模拟文件夹名 如"qj_nanjing/"
     * @param key Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public static void deleteFile(OSSClient ossClient, String bucketName, String folder, String key){
        ossClient.deleteObject(bucketName, folder + key);
        logger.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }
    /**
     * 根据key删除OSS服务器上的文件
     * @param ossClient  oss连接
     * @param bucketName  存储空间
     * @param fileName 文件名
     */
    public static void deleteFile(OSSClient ossClient, String bucketName,  String fileName){
        ossClient.deleteObject(bucketName, fileName);
        logger.info("删除" + bucketName + "下的文件" + fileName + "成功");
    }
    /**
     * 上传图片至OSS
     * @param ossClient  oss连接
     * @param file 上传文件（文件全路径如：D:\\image\\cake.jpg）
     * @param bucketName  存储空间
     * @param folder 模拟文件夹名 如"qj_nanjing/"
     * @return String 返回的唯一MD5数字签名
     * */
    public static  String uploadObject2OSS(OSSClient ossClient, File file, String bucketName, String folder) {
        String resultStr = null;
        try {
            //以输入流的形式上传文件
            InputStream is = new FileInputStream(file);
            //文件名
            String fileName = file.getName();
            //文件大小
            Long fileSize = file.length();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);

            //连接的失效时间
            Date dates = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
            //上传图片的路径
            String url = ossClient.generatePresignedUrl(bucketName, folder + fileName, dates).toString();

            System.out.println(url);

            //解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static  String getContentType(String fileName){
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("/"));
        if(".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if(".pdf".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.pdf";
        }
        if(".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if(".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)  || ".png".equalsIgnoreCase(fileExtension) ) {
            return "image/jpeg";
        }
        if(".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if(".rar".equalsIgnoreCase(fileExtension)) {
            return "text/rar";
        }
        if(".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if(".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if(".mp4".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.mp4";
        }
        if(".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if(".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if(".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "image/jpeg";
    }

    /*
     *    文件上传
     * @param file 文件
     * @param 文件夹名
     * @return  文件路径
     *
     * */
    public static String uploadFileImg(MultipartFile file, String folder, String filename) throws IOException {
        String fileurl=null;
        try {

            InputStream fis = (InputStream) file.getInputStream();

            OSSClient client = new OSSClient(endpoint,DESUtil.desEncrypt(accessKeyId,"88888888","88888888"), DESUtil.desEncrypt(accessKeySecret,"88888888","88888888"));

            client.putObject(bucketName,folder+filename,fis);

            Date dates = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);

            URL url = client.generatePresignedUrl(bucketName,folder+filename,dates);

            fileurl = url.toString().replaceAll("http", "https");

            client.shutdown();

            fis.close();

        } catch (Exception e) {
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return fileurl;
    }


}
