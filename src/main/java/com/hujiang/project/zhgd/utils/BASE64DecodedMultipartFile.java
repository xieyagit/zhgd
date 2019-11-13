package com.hujiang.project.zhgd.utils;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * base64转MultipartFile
 */
public class BASE64DecodedMultipartFile implements MultipartFile {

    private final byte[] imgContent;
    private final String header;

    public BASE64DecodedMultipartFile(byte[] imgContent, String header) {
        this.imgContent = imgContent;
        this.header = header.split(";")[0];
    }

    /**
     * base64转MultipartFile文件
     *
     * @param base64
     * @return
     */
    public static MultipartFile base64ToMultipartOnt(String base64) {
        try {

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            try{
                b = decoder.decodeBuffer(base64);
            }catch (Exception e){
                e.printStackTrace();
                return  null;
            }


            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            return new BASE64DecodedMultipartFile(b, base64);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getName() {
        // TODO - implementation depends on your requirements
        return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
    }

    @Override
    public String getOriginalFilename() {
        // TODO - implementation depends on your requirements
        return System.currentTimeMillis() + (int) Math.random() * 10000 + "." + header.split("/")[1];
    }

    @Override
    public String getContentType() {
        // TODO - implementation depends on your requirements
        return header.split(":")[1];
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imgContent);
    }

    /**
     * base64转MultipartFile文件
     *
     * @param base64
     * @return
     */
    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStrs = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStrs[1]);

            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            return new BASE64DecodedMultipartFile(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 本地图片转换成base64字符串
     * @param imgFile  本地图片地址
     * @return
     */
    public static String ImageToBase64ByLocal(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理


        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }


    /**
     * 在线图片转换成base64字符串
     * @param imgURL  图片地址
     * @return
     */
    public static String ImageToBase64ByOnline(String imgURL) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }
    /**
     * 在线图片转换成base64字符串
     * @param imgURL  图片地址
     * @return
     */
    public static byte[] ImageToByteArray(String imgURL) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data.toByteArray();
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

    public static void main(String[] args)throws Exception {
//        String strImgBase64 = getStrImgBase64(new URL("https://hujiang.oss-cn-shenzhen.aliyuncs.com/in/c99s4w104156.jpg"));
        String strImgBase64 = ImageToBase64ByOnline("https://hujiang.oss-cn-shenzhen.aliyuncs.com/in/c99s4w104156.jpg");
        System.out.println(strImgBase64);
        Map<String, String> params = new HashMap<>();

        HttpPost post = new HttpPost("http://192.168.1.49:8080/api/FaceAttendanceAPI/getAttendance");
        post.addTextParams("personId","123");
        post.addTextParams("attendanceTime","2018-07-25 19:35:40");
        post.addTextParams("equipmentNumber","213");
        post.addTextParams("faceUrl",strImgBase64);
        String result = post.send();
        System.out.println(result);
//        Base64ToImage(strImgBase64,"c:/12.jpg");
    }
    /**
     * base64字符串转换成图片
     * @param imgStr		base64字符串
     * @param imgFilePath	图片存放路径
     * @return
     */
    public static boolean Base64ToImage(String imgStr,String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr==null){
            // 图像数据为空
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static String MultipartFileToBase64(MultipartFile file) {
        try {
        BASE64Encoder base64Encoder =new BASE64Encoder();
        String base64EncoderImg =base64Encoder.encode(file.getBytes()).replaceAll("\r|\n", "");
        return base64EncoderImg;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
