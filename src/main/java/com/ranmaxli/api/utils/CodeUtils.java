package com.ranmaxli.api.utils;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by liran on 2017/3/17.
 */
public class CodeUtils {

    /**
     * md5 加密方法
     * @param value
     * @return
     */
    public static String getContentMD5(String value) {
        return getContentMD5(value.getBytes());
    }

    public static String getContentMD5(byte [] value) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(value, 0, value.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bufferToHex(digest.digest());
    }

    public static String bufferToHex(byte bytes[]) {
        int len = 2 * bytes.length;
        BigInteger bigInt = new BigInteger(1, bytes);
        String md5 = bigInt.toString(16);
        StringBuffer prev = new StringBuffer(32);
        len -= md5.length();
        for (int i = 0; i < len; i ++) {
            prev.append("0");
        }
        return prev.toString() + md5;
    }

    private static final String sKey = "jevicjob";

    public static String Encrypt(String beforeEncryStr)
    {
        String afterEncryStr = null;
        try{
            Cipher cipher = Cipher.getInstance("/DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(sKey.getBytes("UTF-8"));
            SecretKeyFactory keyFatory = SecretKeyFactory.getInstance("DES");
            SecretKey secretkey = keyFatory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(sKey.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, secretkey, iv);
            byte[] content = cipher.doFinal(beforeEncryStr.getBytes("UTF-8"));
            afterEncryStr = new BASE64Encoder().encode(content);
        }catch (Exception ex) {}
        return afterEncryStr;
    }

    public static String DeEncrypt(String beforeDeEncryStr)
    {
        String afterDeEncryStr = null;
        try {
            Cipher cipher = Cipher.getInstance("/DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(sKey.getBytes("UTF-8"));
            SecretKeyFactory keyFatory = SecretKeyFactory.getInstance("DES");
            SecretKey secretkey = keyFatory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(sKey.getBytes("UTF-8"));
            cipher.init(Cipher.DECRYPT_MODE, secretkey, iv);
            byte[] str = new sun.misc.BASE64Decoder().decodeBuffer(beforeDeEncryStr);
            byte[] retByte = cipher.doFinal(str);
            afterDeEncryStr = new String(retByte);
        }
        catch(Exception ex){}
        return afterDeEncryStr;
    }
}
