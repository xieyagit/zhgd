package com.hujiang.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {

    public static final String KEY_PAIRGENO = "RSA";
    public static final String CHARSET = "UTF-8";

    public static SaaSKey CreateKey() {
        SaaSKey key = new SaaSKey();
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_PAIRGENO);
            keyPairGen.initialize(512);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            String privateKey = getPrivateKeyStr(keyPair.getPrivate());
            String publicKey = getPublicKeyStr(keyPair.getPublic());
            key.setPrivateKey(privateKey);
            key.setPublicKey(publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * 使用公钥或私钥加密
     * @param msg 要加密的明文
     * @param keyStr key
     * @param keyType key的类型为公钥或私钥
     * @return 密文
     */
    public static String encrypt(String msg, String keyStr, Integer keyType) {
        try {
            Cipher cipher = Cipher.getInstance(KEY_PAIRGENO);
            Object key = keyType.equals(SaaSKeyType.PRIVATE) ? getPrivateKey(keyStr) : getPublicKey(keyStr);
            cipher.init(Cipher.ENCRYPT_MODE, (Key) key);
            int keySize = ((RSAKey) key).getModulus().bitLength();//长度是1024
            byte[] data = msg.getBytes(CHARSET);
            byte[] encryptedData = rsaSplitCode(cipher, data, Cipher.ENCRYPT_MODE, keySize);
            return Base64.encodeBase64URLSafeString(encryptedData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 使用公钥或私钥解密
     * @param msg 要解密的密文
     * @param keyStr key
     * @param keyType key的类型为公钥或私钥
     * @return 明文
     */
    public static String decryt(String msg, String keyStr, Integer keyType) {
        try {
            Cipher cipher = Cipher.getInstance(KEY_PAIRGENO);
            Object key = keyType.equals(SaaSKeyType.PRIVATE) ? getPrivateKey(keyStr) : getPublicKey(keyStr);
            cipher.init(Cipher.DECRYPT_MODE, (Key) key);
            int keySize = ((RSAKey) key).getModulus().bitLength();//长度是1024
            byte[] data = Base64.decodeBase64(msg);
            byte[] decryptedData = rsaSplitCode(cipher, data, Cipher.DECRYPT_MODE, keySize);
            return new String(decryptedData, CHARSET);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    private static String getPrivateKeyStr(PrivateKey privateKey) throws Exception {
        return Base64.encodeBase64URLSafeString(privateKey.getEncoded());
    }

    private static String getPublicKeyStr(PublicKey publicKey) throws Exception {
        return Base64.encodeBase64URLSafeString(publicKey.getEncoded());
    }

    /**
     * 把字符串公钥转为 RSAPublicKey 公钥
     */
    private static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_PAIRGENO);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 把字符串私钥转为 RSAPrivateKey 私钥
     */
    private static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_PAIRGENO);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    private static byte[] rsaSplitCode(Cipher cipher, byte[] data, int opmode, int keySize) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE)
            maxBlock = keySize / 8;//解密要求最大长度是128
        else
            maxBlock = keySize / 8 - 11; //加密要求最大长度是117

        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        try {
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > maxBlock) {
                    cache = cipher.doFinal(data, offSet, maxBlock);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * maxBlock;
            }
            byte[] bytes = out.toByteArray();
            out.close();
            return bytes;
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
    }

    public static class SaaSKey {
        private String privateKey;
        private String publicKey;

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public void setPublicKey(String publicKey) {
            this.publicKey = publicKey;
        }
    }

    public interface SaaSKeyType {
        Integer PUBLIC = 0;
        Integer PRIVATE = 1;
    }
}
