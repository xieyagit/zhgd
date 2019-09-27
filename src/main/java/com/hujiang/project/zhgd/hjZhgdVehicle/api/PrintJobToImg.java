package com.hujiang.project.zhgd.hjZhgdVehicle.api;

import com.hujiang.project.zhgd.hjZhgdVehicle.domain.Parameters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static java.awt.Font.getFont;

public class PrintJobToImg {

    /** 设置文字水印
     * @param sourceImg 源图片路径
     * @param targetImg 保存的图片路径
     * @param watermark 水印内容
     * @param color 水印颜色
     * @param font 水印字体
     * @throws IOException
     */
    public static void addWatermark(String sourceImg, String targetImg, String watermark,Color color,Font font) throws IOException {
        File srcImgFile = new File(sourceImg);
        Image srcImg = ImageIO.read(srcImgFile);
        int srcImgWidth = srcImg.getWidth(null);
        int srcImgHeight = srcImg.getHeight(null);
        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufImg.createGraphics();
        g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
        g.setColor(color);
        g.setFont(font);
        //设置水印的坐标
        int x = srcImgWidth - (g.getFontMetrics(g.getFont()).charsWidth(watermark.toCharArray(), 0, watermark.length())+20);
        int y = srcImgHeight - 25;
        g.drawString(watermark, x, y);  //加水印
        g.dispose();
        // 输出图片
        FileOutputStream outImgStream = new FileOutputStream(targetImg);
        ImageIO.write(bufImg, "jpg", outImgStream);
        System.out.println("添加水印完成");
        outImgStream.flush();
        outImgStream.close();
    }


    /**
     * 对接废弃物监管
     * @param path 对方或第三方提供的路径
     * @param data 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
     */
    public static String interfaceUtil(String path,Object data) {
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;
            //请求方式
            conn.setRequestMethod("POST");
//           //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //发送请求参数即数据
            out.print(data);

//            String result = "";
//            BufferedReader bufferedReader = null;
//            //获取所有响应头字段
//            Map<String, List<String>> map = conn.getHeaderFields();
//            //遍历所有的响应头字段
//            for(String key : map.keySet()) {
//                System.out.println(key + "---->" + map.get(key));
//            }
//            //6、定义BufferedReader输入流来读取URL的响应内容 ，UTF-8是后续自己加的设置编码格式，也可以去掉这个参数
//            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
//            String line = "";
//            while(null != (line = bufferedReader.readLine())) {
//                result += line;
//            }
//            System.out.println(result);
//
//
//            Parameters parameters = new Parameters();
//            if ( parameters.getCode() == "00000") {
//                System.out.println("请求成功");
//            }else if(parameters.getCode() == "00001"){
//                System.out.println("请求失败");
//            }else if(parameters.getCode() == "00002"){
//                System.out.println("请求异常");
//            }else if(parameters.getCode() == "00003"){
//                System.out.println("请求参数为空");
//            }else if(parameters.getCode() == "00004"){
//                System.out.println("请求信息被篡改");
//            }else if(parameters.getCode() == "00005"){
//                System.out.println("请求参数无效");
//            }else {
//                System.out.println("返回参数为接收到值");
//            }


            //缓冲数据
            out.flush();
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }


            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}