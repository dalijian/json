//package com.lijian.array.translate;
//
//import com.lijian.array.translate.baidu.TransApi;
//
//import java.nio.charset.Charset;
//import sun.io.ByteToCharConverter;
//public class Main {
//
//    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
//    private static final String APP_ID = "20210727000899217";
//    private static final String SECURITY_KEY = "NZkx0ZbOBlp57DQt37kd";
//
//    public static void main(String[] args) {
//        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
//
//        String query = "height 600";
//        System.out.println(AsciiToChineseString(api.getTransResult(query, "auto", "zh")));
//    }
//
//    public static String AsciiToChineseString(String s) {
//        if (s == null)
//            return s;
//        char[] orig = s.toCharArray();
//        byte[] dest = new byte[orig.length];
//        for (int i = 0; i < orig.length; i++)
//            dest[i] = (byte) (orig[i] & 0xFF);
//        try {
//            ByteToCharConverter toChar = ByteToCharConverter.getConverter("utf-8");
//            return new String(toChar.convertAll(dest));
//        } catch (Exception e) {
//            System.out.println(e);
//            return s;
//        }
//
//
//    }
//
//}