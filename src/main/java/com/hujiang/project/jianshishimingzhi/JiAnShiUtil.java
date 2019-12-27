package com.hujiang.project.jianshishimingzhi;

import java.security.MessageDigest;

public class JiAnShiUtil {
    /**
     * 获取签名
     * @param keySecret
     * @param projectCode
     * @param systemTime
     * @param projectKeySecret
     * @return
     * @throws Exception
     */
    public static String getSignature(String keySecret,String projectCode,String systemTime,String projectKeySecret)throws Exception{
        StringBuilder s=new StringBuilder(keySecret+"-"+projectCode+"-"+systemTime+"-"+projectKeySecret);
        return shaEncode(s.toString());
    }

    /**
     * sha1加密
     * @param inStr
     * @return
     * @throws Exception
     */
    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
