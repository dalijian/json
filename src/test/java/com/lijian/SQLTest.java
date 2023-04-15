//package com.lijian;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.util.EntityUtils;
//import org.junit.Test;
//import org.springframework.util.StreamUtils;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.Charset;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//public class SQLTest {
//
//    @Test
//    public void test(){
//
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//        List<Map<String,String>>    without =  mapper.readValue(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\src\\test\\resources\\like_or_without_include.json"), List.class);
//            List<Map<String,String>>    with =  mapper.readValue(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\src\\test\\resources\\like_or_with_include.json"), List.class);
//            System.out.println("without'size" + without.size());
//            System.out.println("with'size" + with.size());
//
//            without.forEach(x->{
//
//                Optional<Map<String, String>> option = with.stream().filter(y -> y.get("ITEM_CODE").equals(x.get("ITEM_CODE")) &&
//                        y.get("CREATE_TIME").equals(x.get("CREATE_TIME")) &&
//                        y.get("JGBM").equals(x.get("JGBM"))&& y.get("ITEM_NAME").equals(x.get("ITEM_NAME"))
//                        &&y.get("XMLX").equals(x.get("XMLX"))
//                ).findFirst();
//
//                if (!option.isPresent()) {
//                    System.out.println(x);
////                    System.out.println(option.get());
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    @Test
//    public void test001(){
//        String sql= "select temp_1.*, (select jgmc from t_govinfo where t_govinfo.jgbm=temp_1.jgbm) as jgmc  from ( select " +
//                "SJPT_GHLS.GHKS , SJPT_GHLS.ghsj,SJPT_GHLS.sfz,SJPT_MZFYMXJL.*" +
//                " FROM\n" +
//                "\t\tSJPT_GHLS inner join SJPT_MZFYMXJL on SJPT_GHLS.jgbm= SJPT_MZFYMXJL.jgbm and \n" +
//                "\tSJPT_GHLS.VISIT_ID=SJPT_MZFYMXJL.VISIT_ID and SJPT_GHLS.PATIENT_ID= SJPT_MZFYMXJL.PATIENT_ID\n" +
//                "\t\n" +
//                "\tINNER JOIN SJPT_MZFYZJL  on SJPT_GHLS.jgbm= SJPT_MZFYZJL.jgbm and \n" +
//                "\tSJPT_GHLS.VISIT_ID=SJPT_MZFYZJL.VISIT_ID and SJPT_GHLS.PATIENT_ID= SJPT_MZFYZJL.PATIENT_ID\n" +
//                "\tand SJPT_MZFYZJL.id=SJPT_MZFYMXJL.SF_ID\n" +
//                "\t\n" +
//                "\t where SJPT_MZFYMXJL.XMLX in('23') and SJPT_MZFYMXJL.item_code <> '0000003' and SJPT_MZFYMXJL.jgbm=:jgbm"+
//                " and CHARGE_DATETIME  BETWEEN TO_DATE(:startTime,'yyyy-mm-dd') and TO_DATE(:endTime, 'yyyy-mm-dd') " +
//                "" +
//                "order by CHARGE_DATETIME desc  " +
//                ") temp_1 ";
////        System.out.println(sql);
//
//    }
//
//
//
//    @Test
//    public void test04(){
//        String feedetl_sn = "1234567890"+"1234567890"+"12345678";
//        if (feedetl_sn.length() >= 30) {
//            feedetl_sn = feedetl_sn.substring(feedetl_sn.length() - 30);
//        }
//        System.out.println(feedetl_sn);
//
//    }
//    @Test
//    public void test05() throws IOException {
//        String insurance_code = null;
//// 使用 中文 括号 代替 英文 括号
//        if (StringUtils.isNotBlank(insurance_code)) {
//            insurance_code= insurance_code.replace("(", "（");
//            insurance_code= insurance_code.replace(")", "）");
//        }
//        System.out.println(insurance_code);
//
//        String sql = "2021-11-24 11:37:07:315";
//        if (sql.split(":").length ==4) {
//            System.out.println(sql.substring(0,sql.lastIndexOf(":")));
//
//        }
//
//        InputStream inputStream = SQLTest.class.getResourceAsStream("9101_3202.txt");
//
//        byte[] bytes = StreamUtils.copyToByteArray(inputStream);
//        System.out.println(bytes);
//    }
//
//
//}
