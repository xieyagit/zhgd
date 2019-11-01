package com.hujiang.project.zhgd.utils;

import com.hujiang.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 一个工具类,用来处理日期等操作
 * @author Neo.Liao
 *
 */
public class Tools {


	/**
	 * 获得小时、分、秒、相比今天的日期，今天就输入0，明天输入1，昨天输入-1，以此类推
	 * @param hour 时
	 * @param minute 分
	 * @param second 秒
	 * @param addDay 日
	 * @param args 毫秒是可选参数，可以输入也可以不输入，毫秒的取值范围是0到999
	 * @return 日期对象
	 */
	public static Date getNeedTime(int hour,int minute,int second,int addDay,int ...args){
		Calendar calendar = Calendar.getInstance();
		if(addDay != 0){
			calendar.add(Calendar.DATE,addDay);
		}
		calendar.set(Calendar.HOUR_OF_DAY,hour);
		calendar.set(Calendar.MINUTE,minute);
		calendar.set(Calendar.SECOND,second);
		if(args.length==1){
			calendar.set(Calendar.MILLISECOND,args[0]);
		}
		return calendar.getTime();
	}

	/**
	 * 将日期字符串以特定格式(yyyy-MM-dd或者yyyy-MM-dd HH:mm:ss)转化为日期
	 * @param dateString 日期字符串
	 * @return 日期对象
	 */
	public static Date string2Date(String dateString)  {
		DateFormat df;
		try {
			if(dateString.trim().length() == 10) {
				df = new SimpleDateFormat("yyyy-MM-dd");
			}else {
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			if (!StringUtils.isEmpty(dateString)) {
				return df.parse(dateString);
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static String dateToString(Date date)  {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(date);
			return dateString;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 将日期以以特定格式((yyyy-MM-dd HH:mm:ss,yyyy-MM-dd或者HH:mm:ss)转化为字符串
	 * @param date 日期对象
	 * @return 日期字符串
	 */
	public static String date2String(Date date){
		if(date != null){
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = df.format(date);
			if(dateString.startsWith("1970-01-01")){
				return dateString.substring(11);
			}
			else if(dateString.endsWith("00:00:00")){
				return dateString.substring(0, 10);
			}else{
				return dateString;
			}
		}else{
			return "";
		}
	}
	
	
	/**
	 * 得到当天日期特定格式(yyyy-MM-dd)字符串
	 * @return 日期字符串
	 */
	public static String getDateString(){
		Date date= new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);

	}
	
	/**
	 * 在控制台打印调试消息
	 * @param msg 要打印的消息
	 */
	public static void println(String msg){
		System.out.println("\n");
		System.err.println("======== "+msg+" =======");
		System.out.println("\n");
	}
	
	
	/**
	 * 将中文汉字转化为首字母字符
	 * @param chinese 中文汉字
	 * @return 汉字首字母字符
	 */
	public static String chinese2PinYinShort(String chinese){
		return PinYin.toPinYinString(chinese);
	}

	public static void main(String[] args) {
		System.out.println(Tools.encodeToMD5("admin123"));
	}
	/**
	 * MD5 加密
	 * @param password 明文密码
	 * @return 加密后的密码字符串
	 */
	public static String encodePassword(String password){
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(password.getBytes());
			byte[] temp = digest.digest();
			String returnString = "";
			for(int i=0;i<temp.length;i++){
				String plainText = Integer.toHexString(temp[i] & 0xEF);
				if (plainText.length() < 2) {
					plainText = "0" + plainText;
				}
				returnString += plainText;
			}
			return returnString;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * @return 一个uuid字符串
	 */
	public static String uuid(){
		return UUID.randomUUID().toString();
	}

	public static byte[] decodeByBase64(String string) {
//		BASE64Decoder decoder = new BASE64Decoder();
//		try {
//			return decoder.decodeBuffer(string);
//		} catch (IOException e) {
//			return null;
//		}
		return null;
	}
	
	public static String encodeToBase64(byte[] bytes) {
//		BASE64Encoder encoder = new BASE64Encoder();
//		return encoder.encode(bytes);
		return null;
	}
	
	public static String toPercentString(double number){
		return new DecimalFormat("%").format(number);
	}
	/**
     * byte数组转int
     * @param b The byte array
     * @return The integer
     */
    public static int byteArrayToInt(byte[] b) {
    	return (b[0] << 24)
	        + ((b[1] & 0xFF) << 16)
	        + ((b[2] & 0xFF) << 8)
	        + (b[3] & 0xFF);
    }

	public static String generateOrderNo(String prefix,String postfix){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String nowdate = sdf.format(new Date());
		return  prefix + nowdate + postfix;
	}

    //32位大端
    public static String encodeToMD5(String signString){
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(signString.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes){
                int bt = b&0xff;
                if (bt < 16){
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }

    public static String encodeToMD5(String signString, String encode){
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(signString.getBytes(encode));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes){
                int bt = b&0xff;
                if (bt < 16){
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reStr;
    }

	//MD5加密32位大写
	public static String encodeToMD5s(String signString){
		String reStr = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(signString.getBytes());
			StringBuffer stringBuffer = new StringBuffer();
			for (byte b : bytes){
				int bt = b&0xff;
				if (bt < 16){
					stringBuffer.append(0);
				}
				stringBuffer.append(Integer.toHexString(bt));
			}
			reStr = stringBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return reStr.toUpperCase();
	}

    public static String generateOrderNo(){
        return  Tools.generateOrderNo("","");
    }
	/**
     * int转byte数组
     * @param value
     * @return
     */
    public static final byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
}

	/**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
	 * @return
	 */
    public static String timeStamp2Date(Integer seconds) {
		String result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(seconds * 1000));
		System.out.println("10位数的时间戳（秒）--->Date:" + result1);
		return  result1;
    }

//	public static void main(String[] args) throws IOException {
//		byte[] bs = {0,0,1,1};
//		System.out.println(Tools.byteArrayToInt(bs));
//		byte[] ob = Tools.intToByteArray(257);
//		System.out.println(ob);
//		System.out.println(ByteOrder.nativeOrder());
//	}
}
