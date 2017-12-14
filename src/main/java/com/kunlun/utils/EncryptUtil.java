package com.kunlun.utils;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;

/**
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/13.
 */
public class EncryptUtil implements Serializable {

    private static final long serialVersionUID = -8961194921028981658L;

    public EncryptUtil() {
    }

    /**
     * 通过MD5进行加密
     *
     * @param password
     * @return
     * @throws Exception
     */
    public synchronized static String encryptMD5(String password) throws Exception {
        StringBuilder builder = new StringBuilder();
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes("UTF-8"));
            byte[] bytes = md5.digest();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xff & bytes[i]);
                if (hex.length() == 1)
                    builder.append('0');
                builder.append(hex);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return builder.toString();
    }

    /**
     * 64编码
     *
     * @param bytes
     * @return
     */
    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 64解码
     *
     * @param base64Code
     * @return
     * @throws IOException
     */
    public static byte[] base64Decode(String base64Code) throws IOException {
        return new BASE64Decoder().decodeBuffer(base64Code);
    }

    /**
     * 将字符串输出为字节码
     *
     * @param content
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        return cipher.doFinal(content.getBytes("UTF-8"));
    }


    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
     * 加密
     *
     * @param content
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * 解密
     *
     * @param encryptStr
     * @param descryptKey
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String descryptKey) throws Exception {
        return aesDecryptByBytes(base64Decode(encryptStr), descryptKey);
    }
}
