package com.hujiang.project.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class CommonUtils {

    /**
     * 通过参数获取签名
     *
     * @param param  请求参数
     * @param secret 密钥
     * @return 签名
     */
    public static String getSign(Map param, String secret) {
        String sortString = "appid=" + param.get("appid");
        sortString += "&data=" + param.get("data");
        sortString += "&format=" + param.get("format");
        sortString += "&method=" + param.get("method");
        sortString += "&nonce=" + param.get("nonce");
        sortString += "&timestamp=" + param.get("timestamp");
        sortString += "&version=" + param.get("version");
        sortString += "&appsecret=" + secret;
        String sStr = sortString.toLowerCase();
        String sign = CommonUtils.getSHA256(sStr);
        return sign;
    }

    public static String getSHA256(String str) {
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            //将byte转为16进制
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("参数不合法，签名失败");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("参数不合法，签名失败");
        }
        return encodestr;
    }


    /**
     * 将byte转为16进制
     *
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }


}
