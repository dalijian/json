package com.lijian.qrcode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

//二维码生成
public class QRcodeEncode {

    public static void main(String[] args) {
        try {

            //二维码内容
//            String content = "Hello QRcode!";
//            String content = "https://mvs.gsafetyweixinsupport.cn:1080/cas/login";
//            String content = "http://www.baidu.com/s?ie=UTF-8&wd=java+%E4%BA%8C%E7%BB%B4%E7%A0%81";
            String content = "http://weather.123.duba.net/static/weather_info/101120201.html?callback=";
//            String content = "<!DOCTYPE html>\n" +
//                    "<html lang=\"en\" xmlns:p=\"http://www.w3.org/1999/xhtml\">\n" +
//                    "<head>\n" +
//                    "    <meta charset=\"UTF-8\">\n" +
//                    "    <title>form</title>\n" +
//                    "    <style type=\"text/css\">\n" +
//                    "        html,body{\n" +
//                    "            padding: 0;\n" +
//                    "            margin: 0;\n" +
//                    "        }\n" +
//                    "        form {\n" +
//                    "            border: 1px solid gold;\n" +
//                    "            margin: 0 ;\n" +
//                    "            padding: 0 ;\n" +
//                    "        }\n" +
//                    "        b {\n" +
//                    "            border: inherit;\n" +
//                    "        }\n" +
//                    "        h1 {\n" +
//                    "            padding: 0;\n" +
//                    "            margin: 0;\n" +
//                    "            height: 100%;\n" +
//                    "            width: 400px;\n" +
//                    "\n" +
//                    "            font-size: 25px;\n" +
//                    "            border: 1px solid red;\n" +
//                    "        }\n" +
//                    "        input {\n" +
//                    "            display: inline-block;\n" +
//                    "            width: 500px;\n" +
//                    "            height: 40px;\n" +
//                    "            border: 1px solid rgba(100,200,200,0.4);\n" +
//                    "            border-radius: 5%;\n" +
//                    "        }\n" +
//                    "        input.submit{\n" +
//                    "            cursor:pointer;\n" +
//                    "        }\n" +
//                    "    </style>\n" +
//                    "\n" +
//                    "    <script>\n" +
//                    "        username = document.getElementById(\"username\");\n" +
//                    "        password = document.getElementById(\"password\");\n" +
//                    "        // alert(username + password);\n" +
//                    "        function loadXMLDoc()\n" +
//                    "        {\n" +
//                    "            var xmlhttp;\n" +
//                    "            if (window.XMLHttpRequest)\n" +
//                    "            {\n" +
//                    "                // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码\n" +
//                    "                xmlhttp=new XMLHttpRequest();\n" +
//                    "            }\n" +
//                    "            else\n" +
//                    "            {\n" +
//                    "                // IE6, IE5 浏览器执行代码\n" +
//                    "                xmlhttp=new ActiveXObject(\"Microsoft.XMLHTTP\");\n" +
//                    "            }\n" +
//                    "            xmlhttp.onreadystatechange=function()\n" +
//                    "            {\n" +
//                    "                if (xmlhttp.readyState==4 && xmlhttp.status==200)\n" +
//                    "                {\n" +
//                    "                    document.getElementById(\"myDiv\").innerHTML=xmlhttp.responseText;\n" +
//                    "                }\n" +
//                    "            }\n" +
//                    "            xmlhttp.open(\"GET\", \"http://www.baidu.com?username\" + username + \"&password=\" + password, true);\n" +
//                    "            xmlhttp.send();\n" +
//                    "        }\n" +
//                    "    </script>\n" +
//                    "</head>\n" +
//                    "<body>\n" +
//                    "\n" +
//                    "<form action=\"http://www.baidu.com\" method=\"post\">\n" +
//                    "    <br>\n" +
//                    "    <label for=\"username\" title=\"this is login username\">username</label>\n" +
//                    "    <br>\n" +
//                    "    <input type=\"text\" id=\"username\" name=\"username\">\n" +
//                    "    <br>\n" +
//                    "    <label for=\"password\">password</label>\n" +
//                    "    <br>\n" +
//                    "    <input type=\"password\" id=\"password\" width=\"300px\" height=\"50px\" name=\"password\">\n" +
//                    "    <br>\n" +
//                    "    <input  class=\"submit\" type=\"submit\" value=\"提交\" onclick=\"loadXMLDoc()\">\n" +
//                    "    <br>\n" +
//                    "</form>\n" +
//                    "</body>\n" +
//                    "</html>";
            //二维码生成路径
            String path = "E:";
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter(); 
            Map hints = new HashMap();     
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300,hints);
            File file1 = new File(path,"test.jpg"); 
            ImageWrite.writeToFile(bitMatrix, "jpg", file1);
            System.out.println("二维码已生成！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        } 
    }
}