//package com.lijian.sms;
//
//public class Qimu {
//    public static void main(String[] args) {
////        String host="http://apis.7moor.com";
//        String time = getDateTime();
//        String account ="N00000021424";
//        String secret ="3b780e10-4121-11e8-a90d-717111521221";
//
//        String phones ="15856661684";
//        String[] msgContent= new String []{"1234","1"};
//
//
//        String templateId ="1";
//        String sig = md5(account + secret + time);
//        String interfacePath = "/v20160818/sms/sendInterfaceTemplateSms/";
//        String url = host + interfacePath + account + "?sig=" + sig;
//        String auth = base64(account + ":" + time);
//
//        HttpClientBuilder builder = HttpClientBuilder.create();
//        CloseableHttpClient client = builder.build();
//        HttpPost post = new HttpPost(url);
//
////			HttpHost proxy = new HttpHost(proxyHost, proxyPort, "http");
////			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
////			post.setConfig(config);
//
//        post.addHeader("Accept", "application/json");
//        post.addHeader("Content-Type","application/json;charset=utf-8");
//        post.addHeader("Authorization",auth);
//        StringEntity requestEntity = null;
//        //根据需要发送的数据做相应替换
//        JSONObject json = new JSONObject();
//        json.put("num", phones);
//        json.put("templateNum", templateId);
//        for(int i = 0; i < msgContent.length; i++) {
//            json.put("var" + (i+1), msgContent[i]);
//        }
//        requestEntity = new StringEntity(json.toJSONString(),"UTF-8");
//        post.setEntity(requestEntity);
//        CloseableHttpResponse response = null;
//
//        try {
//            response = client.execute(post);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        HttpEntity entity = response.getEntity();
//        String repStr = null;
//        try {
//            repStr = EntityUtils.toString(entity,"utf8");
//        } catch (ParseException | IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        System.out.println(repStr);
//    }
//    }
//}
