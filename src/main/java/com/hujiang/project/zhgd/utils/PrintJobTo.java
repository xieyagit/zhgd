package com.hujiang.project.zhgd.utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

public class PrintJobTo {

    /** 设置文字水印
     * @param sourceImg 源图片路径
     * @param targetImg 保存的图片路径
     * @param watermark 水印内容
     * @param color 水印颜色
     * @param font 水印字体
     * @throws IOException
     */
    public static void addWatermark(String sourceImg, String targetImg, String watermark, Color color, Font font,int x , int y) throws IOException {
        File srcImgFile = new File(sourceImg);
        Image srcImg = ImageIO.read(srcImgFile);
        int srcImgWidth = srcImg.getWidth(null);
        int srcImgHeight = srcImg.getHeight(null);
        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufImg.createGraphics();
        g.drawImage(srcImg, x, y, srcImgWidth, srcImgHeight, null);//x:横向坐标 ， y:纵向坐标
        g.setColor(color);
        g.setFont(font);
        //设置水印的坐标
        x = srcImgWidth - (g.getFontMetrics(g.getFont()).charsWidth(watermark.toCharArray(), 0, watermark.length())+20);
        y = srcImgHeight - 25;
        g.drawString(watermark, x, y);  //加水印
        g.dispose();
        // 输出图片
        FileOutputStream outImgStream = new FileOutputStream(targetImg);
        ImageIO.write(bufImg, "jpg", outImgStream);//.jpg 添加图片后的文件格式
        System.out.println("添加水印完成");
        outImgStream.flush();
        outImgStream.close();
    }

    /**
     * 在线图片添加时间水印返回base64
     * @param img 在线图片
     * @param date 时间水印
     * @throws Exception
     */
    public static String zxToWatermark(String img,String date) throws Exception{
        MultipartFile image = BASE64DecodedMultipartFile.base64ToMultipartOnt(BASE64DecodedMultipartFile.ImageToBase64ByOnline(img));
        System.gc();
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
//如果上传目录为/static/images/upload/,则可以如下获取
        File upload = new File(path.getAbsolutePath(), "static/upload/");

        if (!upload.exists()) {
            upload.mkdirs();
        }
        String getPath=upload.getAbsolutePath();
        String fileName=getPath+"\\"+new Date().getTime()+"1.jpg";
        String fileName2=getPath+"\\"+new Date().getTime()+"2.jpg";
        System.out.println(fileName2);
        File localFile = new File(fileName);
        image.transferTo(localFile);
        addWatermark(fileName,fileName2,date,Color.white,null,0,0);
        System.gc();

        String base64Img=BASE64DecodedMultipartFile.ImageToBase64ByLocal(fileName2);
        localFile.delete();
        new File(fileName2).delete();
        return  base64Img;
    }

    public static MultipartFile addWorkMarkToMutipartFile(MultipartFile multipartFile,
                                                      String word) {
        try{
            // 获取图片文件名 xxx.png xxx
        String originFileName = multipartFile.getOriginalFilename();
        // 获取原图片后缀 png
        int lastSplit = originFileName.lastIndexOf(".");
        String suffix = originFileName.substring(lastSplit + 1);
        // 获取图片原始信息
        String dOriginFileName = multipartFile.getOriginalFilename();
        String dContentType = multipartFile.getContentType();
        // 是图片且不是gif才加水印
        if (!suffix.equalsIgnoreCase("gif") && dContentType.contains("image")) {
            // 获取水印图片
            InputStream inputImg = multipartFile.getInputStream();
            Image img = ImageIO.read(inputImg);
            // 加图片水印
            int imgWidth = img.getWidth(null);
            int imgHeight = img.getHeight(null);

            BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight,
                    BufferedImage.TYPE_INT_RGB);
            //设置字体
            Font font = new Font("宋体", Font.PLAIN, 50);
            //调用画文字水印的方法
            markWord(bufImg, img, word, font , Color.white, 0, 35);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(bufImg, suffix, imOut);
            InputStream is = new ByteArrayInputStream(bs.toByteArray());

            // 加水印后的文件上传
            multipartFile = new MockMultipartFile(dOriginFileName, dOriginFileName, dContentType, is);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }finally {
            //返回加了水印的上传对象
            return multipartFile;
        }

    }
    public static void markWord(BufferedImage bufImg, Image img, String text, Font font, Color color, int x, int y) {
        //取到画笔
        Graphics2D g = bufImg.createGraphics();
        //画底片
        g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
        g.setColor(color);
        g.setFont(font);
        //位置
        g.drawString(text, x, y);
        g.dispose();
    }
}
