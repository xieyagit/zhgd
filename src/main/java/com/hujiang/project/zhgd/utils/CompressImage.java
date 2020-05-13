package com.hujiang.project.zhgd.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class CompressImage {
    /**
     * url :   图片路径  : 找到一张图片复制链接地址
     *       desFileSize 指定图片大小，单位kb
     *      * @param imageId     影像编号
     *      * @return 压缩质量后的图片字节数组
     * @param args
     */
    public static void main(String[] args)throws Exception {
//        String a=BASE64DecodedMultipartFile.ImageToBase64ByOnline("https://hujiang.oss-cn-shenzhen.aliyuncs.com/hj/1qk4es163606.jpg").replaceAll("\r|\n", "");
//        System.out.println(a);
        String s = UrlConvertImage("https://hujiang.oss-cn-shenzhen.aliyuncs.com/hj/1qk4es163606.jpg",  30);
        System.out.println("2:"+s);
//        String s2 = convertImageToBase64s(a,  30);
//        System.out.println("4:"+s2);
//          System.out.println("压缩后数据大小:" + s);

    }
    /**
     * 将图片转换为base64格式
     *
     * @param imageUrl：图片路径
     * @param sizeLimit：原图大小上限，当图片原图大小超过该值时先将图片大小 设置为该值以下再转换成base64格式,单位kb
     * @return
     */
    public static String UrlConvertImage(String imageUrl, Integer sizeLimit) throws IOException {
        //默认上限为200k
        if (sizeLimit == null) {
            sizeLimit = 200;
        }
        sizeLimit = sizeLimit * 1024;
        String base64Image;
        DataInputStream dataInputStream = null;
        ByteArrayOutputStream outputStream = null;
        ByteArrayInputStream inputStream = null;
        try {
            //从远程读取图片
            URL url = new URL(imageUrl);
            dataInputStream = new DataInputStream(url.openStream());
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            byte[] context = outputStream.toByteArray();
            System.out.println("1:"+new BASE64Encoder().encode(context).replaceAll("\r|\n", ""));
            //将图片数据还原为图片
            inputStream = new ByteArrayInputStream(context);
            BufferedImage image = ImageIO.read(inputStream);
            int imageSize = context.length;
            int type = image.getType();
            int height = image.getHeight();
            int width = image.getWidth();

            BufferedImage tempImage;
            //判断文件大小是否大于size,循环压缩，直到大小小于给定的值
            while (imageSize > sizeLimit) {
                //将图片长宽压缩到原来的90%
                height = new Double(height * 0.9).intValue();
                width = new Double(width * 0.9).intValue();
                tempImage = new BufferedImage(width, height, type);
                // 绘制缩小后的图
                tempImage.getGraphics().drawImage(image, 0, 0, width, height, null);
                //重新计算图片大小
                outputStream.reset();
                ImageIO.write(tempImage, "JPEG", outputStream);
                imageSize = outputStream.toByteArray().length;
            }

            //将图片转化为base64并返回
            byte[] data = outputStream.toByteArray();
            //此处一定要使用org.apache.tomcat.util.codec.binary.Base64，防止再linux上出现换行等特殊符号
            base64Image = Base64.encodeBase64String(data);
        } catch (Exception e) {
            //抛出异常
            throw e;
        } finally {
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64Image;
    }
    /**
     * 将图片转换为base64格式
     *
     * @param base64：图片数据
     * @param sizeLimit：原图大小上限，当图片原图大小超过该值时先将图片大小 设置为该值以下再转换成base64格式,单位kb
     * @return
     */
    public static String base64ConvertImage(String base64, Integer sizeLimit) throws IOException {
        //默认上限为200k
        if (sizeLimit == null) {
            sizeLimit = 200;
        }
        sizeLimit = sizeLimit * 1024;
        String base64Image;
        DataInputStream dataInputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = null;
        try {
            //从远程读取图片
//            URL url = new URL(imageUrl);
//            dataInputStream = new DataInputStream(url.openStream());
//            outputStream = new ByteArrayOutputStream();
//            byte[] buffer = new byte[2048];
//            int length;
//            while ((length = dataInputStream.read(buffer)) > 0) {
//                outputStream.write(buffer, 0, length);
//            }
            byte[] context =new BASE64Decoder().decodeBuffer(base64);
//            outputStream.write(context);
            System.out.println("3:"+new BASE64Encoder().encode(context).replaceAll("\r|\n", ""));
            //将图片数据还原为图片
            inputStream = new ByteArrayInputStream(context);
            BufferedImage image = ImageIO.read(inputStream);
            int imageSize = context.length;
            int type = image.getType();
            int height = image.getHeight();
            int width = image.getWidth();

            BufferedImage tempImage;
            //判断文件大小是否大于size,循环压缩，直到大小小于给定的值
            while (imageSize > sizeLimit) {
                //将图片长宽压缩到原来的90%
                height = new Double(height * 0.9).intValue();
                width = new Double(width * 0.9).intValue();
                tempImage = new BufferedImage(width, height, type);
                // 绘制缩小后的图
                tempImage.getGraphics().drawImage(image, 0, 0, width, height, null);
                //重新计算图片大小
                outputStream.reset();
                ImageIO.write(tempImage, "JPEG", outputStream);
                imageSize = outputStream.toByteArray().length;
            }

            //将图片转化为base64并返回
            byte[] data = outputStream.toByteArray();
            //此处一定要使用org.apache.tomcat.util.codec.binary.Base64，防止再linux上出现换行等特殊符号
            base64Image = Base64.encodeBase64String(data);
        } catch (Exception e) {
            //抛出异常
            throw e;
        } finally {
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64Image;
    }
}
