package com.hujiang.project.common;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

/**
 * @author jiangjian
 * @date 2019/12/31 16:05
 */
public class AesUtils {

    private static final String CHARSET_NAME = "UTF-8";
    private static final String AES_NAME = "AES";
    public static final String ALGORITHM = "AES/CBC/PKCS7Padding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 加密
     *
     * @param content
     * @param key
     * @return
     */
    public static String encrypt(String content, String key) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(CHARSET_NAME), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(subBytes(key.getBytes(CHARSET_NAME)));
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
            result = cipher.doFinal(content.getBytes(CHARSET_NAME));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return Base64.encodeBase64String(result);
    }

    /**
     * 解密
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(CHARSET_NAME), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(subBytes(key.getBytes(CHARSET_NAME)));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
            return new String(cipher.doFinal(Base64.decodeBase64(content)), CHARSET_NAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从一个byte[]数组中截取一部分
     *
     * @param src
     * @return
     */
    public static byte[] subBytes(byte[] src) {
        if (src.length < 16) {
            throw new RuntimeException("无法从Key中获取偏移量!");
        }
        byte[] bs = new byte[16];
        for (int i = 0; i < 16; i++) {
            bs[i] = src[i];
        }
        return bs;
    }

    public static void main(String[] args) {
        String mystr = "430523199007193388";
        String secret = "e2effa55d6a249a6992f18dc938f1c07";
        String encode = encrypt(mystr, secret);
        String decode = decrypt(encode, secret);

        System.out.println(encode);
        System.out.println(decode);
    }
}
