package com.lijian.crypt;

import org.junit.Assert;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class DESUtils {
    // 密钥
    final static String key = "36235041";
    // 偏移量
    final static String iv = "36235041";

    public static void main(String[] args) throws Exception {

        String secrt = "14192bf09b64605b229a0e2bb9dfae137f9480fd4486d877c4db521441f739ad87387e32d4d2fde29b00cb83f42a889747482fa32db6147b3ca5bc115afcba24226a39ff5f0425ec099255631b3a582fe965ecf93d95de081423059673990316d3de94dde25fb937936d0321b7de9e2e5bf58e4f23b45d0721e63e87e02b85f51a3ed586f64164eb";
       String message = decryp(
               secrt);
        System.out.println(message);
       String enStr = encrypt(message);
        System.out.println(enStr.replace("-",""));
        Assert.assertEquals(enStr.replace("-","").toLowerCase(),secrt);
    }

    public static String decryp(String message) {
        try {
            return new DESUtils().decrypt(message, key, iv);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String message) {
        try {
            byte[] b = new DESUtils().encrypt(message, key, iv);
            return toHexString(b).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 解密数据
    private String decrypt(String message, String key, String iv) throws Exception {

        byte[] bytesrc = convertHexString(message);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec ivParameter = new IvParameterSpec(iv.getBytes("UTF-8"));

        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameter);

        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);
    }

    private byte[] encrypt(String message, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec ivParameter = new IvParameterSpec(iv.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameter);

        return cipher.doFinal(message.getBytes("UTF-8"));
    }

    private static byte[] convertHexString(String ss) {
        if (ss.length() > 0) {
            ss = ss.replace("-", "");
        }
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }

        return digest;
    }

    private static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            // hexString.append(plainText);
            hexString.append("-").append(plainText);

        }
        String str = "";
        if (hexString.length() > 0) {
            str = hexString.toString().substring(1);
        }
        return str;
    }
}
