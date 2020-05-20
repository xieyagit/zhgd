package com.hujiang.project.zhgd.utils;

import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.face.AipFace;
import com.hujiang.common.utils.RSAUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.lang.reflect.Field;
import java.math.RoundingMode;
import java.net.*;
import java.text.*;
import java.util.*;
import java.util.regex.Pattern;

public class Util {
    public static  AipFace aipFace = new AipFace(Constants.BD_APP_ID, Constants.BD_API_KEY, Constants.BD_SECRET_KEY);
   // private static sbCameraApi cameraApi;


    public static void main(String[] args)throws Exception{
//    String s="G6DmcEuRBq6Wg3WJT3zW51kCg2_6bUoDrjVsTV_PnHcbZXat9AOMq08CUFPD0Q0ZbxucSdqIOh3JqITfUnRSfGhX2_Besaj3prRUQfZclS0pRw-eg-gpCUJKwfdpNEsCXzv9a0x6i8tFP7PIrEKDmGVTNUV0neanCNhOYnmlgr5Z7qt5kYtumskbf3GSXyGNysu1LbJeVDHtWLATwBwxxJeadJPAMqkihgwbAZwKXuZjBxKQE489xid1YXd-ushNDe7CQjgIwfLqFzYBdAPWsJzXJd5yKBCDhL0Gmrl0g2K1Z1Yo2CoZLpSXeX4oI9era0IJ4nngtftpgUs0ABVDbyfi2IVguXwZHKMcQmnq5BJwvq4byJ_n3HCnCarw9fffhTkG49ptY7mEqtsF7m2U8fb0MpfAHX6MDYoBO_007tl8JvnQNRO4M5R6n5tjlo5ZvYgGtDrJvWdruyWSayTIR9cqL6nxgIwihTwlszys0fS--TiDBCQMXZ1AlG7SnyprHTbEbDUWQsHv4je3u3SL9jESx7wJr61vTzicm9ALr8zCLT6z6D0_4oKvBuzprzCjmQ0bkmrizFSfB8btFcCvF1ioUhZOCfQZMn4JkSUElIGjaETyfVM21wVVw3dC44uwEthjq2G6Em1OEWe_bi3eHLKV1YKZOhUEinsb2I9NeZol1rCsjcAqVvz0KZWfAxDxVvdQdnnt8z072je8BMZTyjMDhvG9B1KfJkl3mTochPzjU_xw29IfCm_4wEprRquljW-zR8bPWqTDf2YWBPTj7Grik3juvCKFCBEurYPETGoMwkA6aIm2xvZtb6Y61S2ImIuH6s10tL8_zUGnDmlHLSupEyOil8h_kwudeNRpzkUihQ58sBwdMxcYHs2elkAukP17gkeG_AJ_EahTnZ_nGLSHJU9UKLgcQJbKTgtpx96flqrhpq6rCg5BFu6dH7XeOTXBUxfBZBaBicsKhj9WGg_iZohADoYlhVVVGHXyS0Mo73yxg9Ak69BmaqHB59zdl1X0BM3GOGo3lkp7NbpZM43whmFJtMfiwa_m_O4ChOxfOPljogklqOsN6_90h1dAdVC7TSmyXdzChstHChxuxqvczJd_P7d8osKp5IWyefGvMSNcUeqmaeLbRBaKzuMlhQIOy-x8p6HSERhW_tJPJ4LMclAhQkZQidTmlCvZaPIP8B8u55lVX76cLJpPmUPCKn43kWZQ0H9sgJapLXiGyxSD83n3Wg43JC6bL-uHGSHPqhIqOlGCgyL5BhBOTLb3jwKHRYx5WX4_1HKzIDs1SJFIQlKUmZemmkung1S-BhqkdfLmj0aAMd3N4aohORAlmIVCZ7VQ87cq3tB-bFuKbnHhQZZe967-nHEKVw0XPJRW0ZBRed9skycAkbxx5TKi9OLl0jLJX8YSWIS19K7dNRqc93FJ64-0Wl8B9k8xZswbuY08dUcULQKnrbuPGF1t9qrMZrmG4sKQYu_l8Ohj2d1ajLsAPL479vLwuSw5Nvgtc1KBN6RIFb4KsmgtvQM_";
//    String a="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALSVIKf42krSyRAb-YfF4lQz81DhmWoHDJVivlc8HkzQwZghOz6a60LkupLtOq4NIh91yaJFheRukVcC5Z9lIXcCAwEAAQ";
//String key= RSAUtil.decryt(s,a,0);
        String a="01234";
//        Integer i = Integer.valueOf(a);
//        System.out.println(a instanceof Integer);
        Pattern pattern = Pattern.compile("^[0-9]*$");
        System.out.println( pattern.matcher(a).matches());
    }

    /**
     * 获取图片上传路径
     * @return
     * @throws Exception
     */
    public static String getPath() {
        File upload=null;
        //路径
        try{
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
//如果上传目录为/static/images/upload/,则可以如下获取
            upload = new File(path.getAbsolutePath(), "static/upload/");

            if (!upload.exists()) {
                upload.mkdirs();
//            System.out.println(upload.getAbsolutePath());
                //在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
                //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return upload.getAbsolutePath();
    }

    /**
     * 除数保留两位小数
     * @param a
     * @param b
     * @return
     */
    public static String txfloat(Object a,int b) {
        // TODO 自动生成的方法存根

        DecimalFormat df=new DecimalFormat("0.00");//设置保留位数

        return df.format((float)a/b);

    }

    /**
     * String转base64
     * @param strSource
     * @Author:tony
     */
    public static String EncodeBase64(String strSource) {
        if (strSource == null){
            return null;
        }
        return (new sun.misc.BASE64Encoder()).encode( strSource.getBytes() );
    }

    /**
     * @param imgStr base64编码字符串
     * @param path   图片路径-具体到文件
     * @return
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     */
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 将秒数转换为时间
     *
     * @param second 1555226142
     * @param patten yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String secondToDate(long second, String patten) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(second * 1000);//转换为毫秒
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(patten);
        String dateString = format.format(date);
        return dateString;
    }



    /**
     * POST报文客户端
     *
     * @param url       调用地址字符串
     * @param jsonParam 报文实体JSON
     * @return String
     * @author Al1en
     */
    public static String httpPostWithForm(String url, JSONObject jsonParam) {
        URL u = null;
        HttpURLConnection con = null;
        // 构建请求参数
        StringBuffer sb = new StringBuffer();
        for (String s : jsonParam.keySet()) {
            sb.append(s);
            sb.append("=");
            sb.append(jsonParam.getString(s));
            sb.append("&");
        }

        System.out.println("send_url:" + url);
        System.out.println("send_data:" + sb.toString());
        // 尝试发送请求
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            //// POST 只能为大写，严格限制，post会不识别
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            osw.write(sb.toString());
            osw.flush();
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        // 读取返回内容
        StringBuffer buffer = new StringBuffer();
        try {
            //一定要有返回值，否则无法把请求发送给server端。
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }


    /**
     * POST报文客户端
     *
     * @param urlString 调用地址字符串
     * @param jsonParam 报文实体JSON
     * @return String
     * @author Al1en
     */
    public static String httpPostWithJSON(String urlString, JSONObject jsonParam) throws URISyntaxException, IOException {
        // 由于URL字符串当中存在空格等特殊字符，所以需要转为URI。
        CloseableHttpClient client;
        URL url = new URL(urlString);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);
        HttpPost httpPost = new HttpPost(uri);
        client = HttpClients.createDefault();
        String respContent = null;
        // 设置报文实体编码为UTF-8，否则会出现中文乱码以及无法正确调用服务。
        StringEntity entity = new StringEntity(jsonParam.toString(),
                "UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity rspEntity = resp.getEntity();
            respContent = EntityUtils.toString(rspEntity, "UTF-8");
        }
        client.close();
        // 如需要重发机制，可在此部分编写重发代码
        System.out.println("请求服务:" + urlString);
        System.out.println(respContent);
        return respContent;
    }


    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param; System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {

                result += line;
            }
            System.out.println("结果："+result);
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String httpGet(String url1) throws URISyntaxException, IOException
    {
        CloseableHttpClient client;
        URL url = new URL(url1);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);

        HttpGet http= new HttpGet(uri);


        client = HttpClients.createDefault();
        String respContent = null;
        // 设置报文实体编码为UTF-8，否则会出现中文乱码以及无法正确调用服务。
//        StringEntity entity = new StringEntity(param,"UTF-8");
//        entity.setContentType("application/json");
//        http.setEntity(entity);
        HttpResponse resp = client.execute(http);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity rspEntity = resp.getEntity();
            respContent = EntityUtils.toString(rspEntity, "UTF-8");
        }
        client.close();
        // 如需要重发机制，可在此部分编写重发代码
        System.out.println("请求服务:" + url1);
        System.out.println(respContent);
        return respContent;
    }
    /**
     * 异常返回
     *
     * @param error
     * @return
     */
    public static JSONObject ErrorReturnedValue(String error) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", error);
        return jsonObject;
    }

    /**
     * 计算百分百
     *
     * @param num
     * @param total 100
     * @param scale 精确几位小数
     * @return
     */
    public static String accuracy(double num, double total, int scale) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        //可以设置精确几位小数
        df.setMaximumFractionDigits(scale);
        //模式 例如四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);
        double accuracy_num = num / total * 100;
        return df.format(accuracy_num);
    }

    /**
     * 判断对象属性是否为空
     * @param object
     * @return
     */
    public static JSONObject checkObjAllFieldsIsNull(Object object) {
        JSONObject jsonObject = new JSONObject();
        if (null == object) {
            jsonObject.put("result","ERROR");
            jsonObject.put("message","对象为空");
            jsonObject.put("server_timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            return jsonObject;
        }
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                System.out.print(f.getName() + ":");
                System.out.println(f.get(object));
                if(!f.getName().equals("id") ){
                    if (f.get(object)==null||f.get(object).toString().equals("")) {
                        jsonObject.put("result","ERROR");
                        jsonObject.put("message",f.getName()+"为空");
                        jsonObject.put("server_timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        return jsonObject;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("result","SUCCEED");
        jsonObject.put("message","验证成功");
        jsonObject.put("server_timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return jsonObject;
    }

    /**
     * 验证时间格式
     * @param str
     * @return
     */
    public static boolean isValidDate(String str) {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {

            Date date = (Date) formatter.parse(str);

            return str.equals(formatter.format(date));

        } catch (Exception e) {

            return false;

        }
    }
    /**
     * 获取两个时间相差多少小时
     * @param time1
     * @param time2
     * @return
     */
    public static Float getTime(String time1,String time2){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date a,b;
        long c=0;
        try {
            //提交时间
            a = sdf.parse(time1.replaceAll("/","-"));

            //截止时间
            b=sdf.parse(time2.replaceAll("/","-"));
            c=(b.getTime()-a.getTime())/1000/60;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DecimalFormat df=new DecimalFormat("0.0");
        return Float.parseFloat(df.format(c/(float)60));
    }
}
