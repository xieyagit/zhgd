package com.hujiang.common.utils;


import com.hujiang.common.exception.BusinessException;
import com.hujiang.project.zhgd.utils.Util;
import net.sourceforge.tess4j.util.ImageHelper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 阿里ORC工具类(身份证与银行卡识别)
 * @author XZP
 */
public class AliOcrUtil {

    private AliOcrUtil() {
        throw new Error("工具类不能实例化！");
    }

    static final String APPCODE = "4e29e0dfa48243df97d18c1b98da2fb9";

    static final String METHOD = "POST";

    /**
     * 获取 A阿里云-数据服务_5_1_身份证识别,得到身份证基本信息
     * @return 身份数据map
     * configStr:示上传图片是身份证正面/反面图像， "face" 表示正面,"back"表示反面
     */
    public static Map<String, String> getAliOcrIdcard(URL imgFileUrl, String configStr){

        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + APPCODE);
        Map<String, String> querys = new HashMap<>();

        String body=getJosnStrRequestBody(getStrImgBase64(imgFileUrl),configStr);

        try {
            HttpResponse response = HttpUtils.doPost("https://dm-51.data.aliyun.com", "/rest/160601/ocr/ocr_idcard.json", METHOD, headers, querys, body);
            System.out.println(response.getEntity());
            String result= EntityUtils.toString(response.getEntity());
            System.out.println(result);
            if(result.equals("Invalid Input - wrong category")){
                throw new BusinessException("身份证图片有误");
            }
            if(result.equals("Invalid Result - face results are all empty")){
                throw new BusinessException("无效的身份证图片");
            }
            JSONObject resultObj = new JSONObject(result);
            JSONArray outputArray = resultObj.getJSONArray("outputs");
            // 取出结果json字符串
            String output = outputArray.getJSONObject(0).getJSONObject("outputValue").getString("dataValue");
            JSONObject out = new JSONObject(output);
                if (out.getBoolean("success")) {
                    if(!configStr.isEmpty()&&configStr.equals("face")){
                        querys.put("address" ,out.getString("address"));// 获取地址
                        querys.put("title" ,out.getString("name")); // 获取名字
                        querys.put("idNumber" ,out.getString("num")); // 获取身份证号
                        querys.put("sex" ,out.getString("sex")); // 获取性别
                        querys.put("dateOfBirth" ,AliOcrUtil.selectTime(out.getString("birth"))); // 获取生日
                        querys.put("nation" ,out.getString("nationality")); // 获取民族

                        //  拿到人脸坐标
                        JSONObject faceRect = out.getJSONObject("face_rect");
                        JSONObject size = faceRect.getJSONObject("size");
                        JSONObject center = faceRect.getJSONObject("center");
                        Double x = center.getDouble("x");
                        Double y = center.getDouble("y");
                        Double height = size.getDouble("height");
                        Double width = size.getDouble("width");
                        int xx = x.intValue()-100;
                        int yy = y.intValue()-100;
                        int h = height.intValue()+80;
                        int w = width.intValue()+80;
                        BufferedImage img = ImageIO.read(imgFileUrl);
                        BufferedImage faceImg = ImageHelper.getSubImage(img,xx,yy,w,h);
                        String uuid = UUID.randomUUID().toString()+".jpg";
                        ImageIO.write(faceImg, "jpg", new File(Util.getPath(),uuid));
                        File file = new File(Util.getPath()+"/"+uuid);
                        FileInputStream inputStream = new FileInputStream(file);
                        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
                        String folder = AliyunOSSClientUtil.createFolder(AliyunOSSClientUtil.getOSSClient(), "hujiang", configStr.trim()+"/");  // 文件夹名称
                        String filename= StringUtil.getRandomStringByLength(6)+new SimpleDateFormat("HHmmss");
                        String fileUrl = AliyunOSSClientUtil.uploadFileImg(multipartFile, folder,filename+".jpg");// 文件上传
                        //删除临时文件
                        if (file != null && file.exists()) {
                            file.delete();
                        }
                        if(!"".equals(fileUrl)){
                            String url = fileUrl.substring(0, fileUrl.lastIndexOf("?"));
                            querys.put("empNaticeplace" ,url);// 身份证人脸照片
                        }
                    }
                    if(!configStr.isEmpty()&&configStr.equals("back")){
                        String ss = "长期";
                        String endDate = out.getString("end_date");
                        if(ss.equals(endDate)){
                            querys.put("idCardStartdate" ,AliOcrUtil.selectTime(out.getString("start_date"))+"-"+endDate); //获取有效期起始时间
                        }else {
                            querys.put("idCardStartdate" ,AliOcrUtil.selectTime(out.getString("start_date"))+"-"+AliOcrUtil.selectTime(out.getString("end_date"))); //获取有效期起始时间
                        }
                        querys.put("issue" ,out.getString("issue")); // 获取签发机关
                    }
                    return querys;
            } else {
                System.out.println("predict error");
                throw new BusinessException("校验错误，请检查身份证图片是否正确");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取 A阿里云-数据服务_5_1_银行卡识别,得到银行卡基本信息
     * @return 银行卡号数据map
     */
    public static Map<String, String> getAliOcrBankcard(URL imgFile){

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + APPCODE);
        Map<String, String> querys = new HashMap<>();

        String strImgBase64 = getStrImgBase64(imgFile);
        String bodys = "{\"image\":\" "+ strImgBase64 + "\"}";

        try {
            HttpResponse response = HttpUtils.doPost("https://yhk.market.alicloudapi.com", "/rest/160601/ocr/ocr_bank_card.json", METHOD, headers, querys, bodys);
            String result= EntityUtils.toString(response.getEntity());
            JSONObject resultObj = new JSONObject(result);

            if (resultObj.getBoolean("success")) {
                querys.put("card_num" ,resultObj.getString("card_num"));
                return querys;
            } else {
                System.out.println("predict error");
                throw new BusinessException("校验错误，请检查银行卡图片是否正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取图片Base64流
     * File file
     */
    public static String getStrImgBase64(URL url){
        // 对图像进行base64编码
        String strImgBase64 = "";
        try {
            InputStream is = url.openStream();
            byte[] imageBytes = IOUtils.toByteArray(is);
            strImgBase64 = new String(Base64.encodeBase64(imageBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  strImgBase64;
    }

    /**
     * 获取"身份证识别"请求报文Body(Josn格式的String串)
     * strImgBase64:
     * configStr:示上传图片是身份证正面/反面图像， "face" 表示正面,"back"表示反面
     */
    static String getJosnStrRequestBody(String strImgBase64,String configStr){
        // 拼装请求body的json字符串
        JSONObject requestObj = new JSONObject();
        try {
            JSONObject configObj = new JSONObject();
            JSONObject obj = new JSONObject();
            JSONArray inputArray = new JSONArray();
            //表示上传图片是身份证正面/反面图像， “face” 表示正面，”back”表示反面
            configObj.put("side", configStr);
            obj.put("image", getParam(50, strImgBase64));
            obj.put("configure", getParam(50, configObj.toString()));
            inputArray.put(obj);
            requestObj.put("inputs", inputArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestObj.toString();
    }

    /**
     * 获取参数的json对象
     */
    static JSONObject getParam(int type, String dataValue) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("dataType", type);
            obj.put("dataValue", dataValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }



    /**
     * 时间格式
     */
    public static String selectTime(String time){
        String yy = time.substring(0,4);
        String MM = time.substring(4,6);
        String dd = time.substring(6,8);
        String data = yy+"."+MM+"."+dd;
        return data;
    }







}

