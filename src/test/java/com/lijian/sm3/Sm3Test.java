package com.lijian.sm3;


import org.bouncycastle.crypto.digests.SM3Digest;

public class Sm3Test {
    /**
     * sm3 加密
     * @param args
     */
    public static void main(String[] args) {
        byte[] asciiArray = ascii("gybx1234");
        SM3Digest sm3Digest = new SM3Digest();
        sm3Digest.update(asciiArray, 0, asciiArray.length);
        byte[] encyptByte = new byte[sm3Digest.getDigestSize()];
        sm3Digest.doFinal(encyptByte,0);
        System.out.println(encyptByte);

    }

    public static byte[] ascii(String str) {

        char [] ch= str.toCharArray();
        byte[] bytesArray = new byte[ch.length];
        for (int i = 0; i < ch.length; i++) {
           bytesArray[i]=(byte)ch[i];

        }
        return bytesArray;

    }
}
