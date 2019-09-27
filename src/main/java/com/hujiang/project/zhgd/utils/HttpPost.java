package com.hujiang.project.zhgd.utils;



import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class
HttpPost {

    private static final String BOUNDARY = "-------45962402127348";
    private static final String FILE_ENCTYPE = "multipart/form-data";
    //存放数据
    Map<String, String> textParams = new HashMap<String, String>();
    //存放File文件
    Map<String, File> fileparams = new HashMap<String, File>();

    String url;

    public HttpPost(String url)throws Exception{
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addTextParams(String key,String value) {
        this.textParams.put(key, value);
    }

    public void addFileparams(String key,File file) {
        this.fileparams.put(key, file);
    }

    public String send()throws IOException{
        InputStream post = post(this.url, this.textParams, this.fileparams);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int b;
        while ((b = post.read()) != -1) {
            out.write(b);
        }
        return new String(out.toByteArray());
    }


    /**
     *
     * @param urlStr http请求路径
     * @param params 请求参数
     * @param images 上传文件
     * @return
     */
    public static InputStream post(String urlStr, Map<String, String> params,
                                   Map<String, File> images) {
        InputStream is = null;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setConnectTimeout(5000);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type", FILE_ENCTYPE + "; boundary=" + BOUNDARY);


            StringBuilder sb = null;
            DataOutputStream dos = new DataOutputStream(con.getOutputStream());
            if (params != null) {
                sb = new StringBuilder();
                for (String s : params.keySet()) {
                    sb.append("--");
                    sb.append(BOUNDARY);
                    sb.append("\r\n");
                    sb.append("Content-Disposition: form-data; name=\"");
                    sb.append(s);
                    sb.append("\"\r\n\r\n");
                    sb.append(params.get(s));
                    sb.append("\r\n");
                }

                dos.write(sb.toString().getBytes());
            }
            if (images != null) {
                for (String s : images.keySet()) {
                    File f = images.get(s);
                    sb = new StringBuilder();
                    sb.append("--");
                    sb.append(BOUNDARY);
                    sb.append("\r\n");
                    sb.append("Content-Disposition: form-data; name=\"");
                    sb.append(s);
                    sb.append("\"; filename=\"");
                    sb.append(f.getName());
                    sb.append("\"\r\n");
                    sb.append("Content-Type: multipart/form-data");//这里注意！如果上传的不是图片，要在这里改文件格式，比如txt文件，这里应该是text/plain
                    sb.append("\r\n\r\n");
                    dos.write(sb.toString().getBytes());

                    FileInputStream fis = new FileInputStream(f);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = fis.read(buffer)) != -1) {
                        dos.write(buffer, 0, len);
                    }
                    dos.write("\r\n".getBytes());
                    fis.close();
                }

                sb = new StringBuilder();
                sb.append("--");
                sb.append(BOUNDARY);
                sb.append("--\r\n");
                dos.write(sb.toString().getBytes());
            }
            dos.flush();

            if (con.getResponseCode() == 200){
                is = con.getInputStream();
            }


            dos.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }


//    public static void main(String[] args)throws Exception {
//        HttpPost post = new HttpPost("http://***");
//        post.addTextParams("a", "1");
//        post.addTextParams("b", "hahah");
//        post.addTextParams("c", "你好");
//        post.addFileparams("verifyFace", new File("c:/123.jpg"));
//        String result = post.send();
//        System.out.println(result);
//    }
}

