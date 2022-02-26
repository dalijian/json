package com.lijian.string;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo {

    public static void main(String[] args) throws IOException {


        String icpcodeList = "0001,0002,0004,0005,0006,0008,0010,0012,0013,0021,0027,0030,0033,0032,0031";
        System.out.println(Stream.of(icpcodeList.split(",")).map(x -> "'" + x + "'").collect(Collectors.joining(",")));
//        String str = "PARTITION part_"+0003 +"VALUES"+ +( '0002' ),
//
//
//        System.out.printf("a=%d,b=%d",a,b);

//        for(String s:icpcodeList.split(",")){
//            System.out.printf("PARTITION part_%s VALUES ( '%s' ),",s,s);
//        }

//        String sql=   "SELECT\n" +
//                "\tcount( * ) AS count_3,\n" +
//                "\ttemp_2.jgbm,\n" +
//                "\t( SELECT jgmc FROM T_GOVINFO WHERE jgbm = temp_2.jgbm ) AS jgmc \n" +
//                "FROM\n" +
//                "\t(\n" +
//                "\tSELECT\n" +
//                "\t\tSJPT_CFLS_LARGE_RECIPE.jgbm,\n" +
//                "\t\tSJPT_CFLS_LARGE_RECIPE.VISIT_ID,\n" +
//                "\t\tSJPT_CFLS_LARGE_RECIPE.PATIENT_ID,\n" +
//                "\t\tSJPT_CFLS_LARGE_RECIPE.CFH,\n" +
//                "\t\tcount( * ) \n" +
//                "\tFROM\n" +
//                "\t\tSJPT_CFLS_LARGE_RECIPE\n" +
//                "\t\tLEFT JOIN SJPT_GHLS B ON SJPT_CFLS_LARGE_RECIPE.VISIT_ID = b.VISIT_ID \n" +
//                "\t\tAND SJPT_CFLS_LARGE_RECIPE.JGBM = b.JGBM \n" +
//                "\t\tAND SJPT_CFLS_LARGE_RECIPE.PATIENT_ID = b.PATIENT_ID\n" +
//                "\t\tINNER JOIN NODE_CENTER_REGION n ON SJPT_CFLS_LARGE_RECIPE.JGBM = n.nodeid \n" +
//                "\tWHERE\n" +
//                "\t\tSJPT_CFLS_LARGE_RECIPE.Prescribe_date BETWEEN to_date(:startTime, 'yyyy-mm-dd' ) \n" +
//                "\t\tAND to_date( :endTime, 'yyyy-mm-dd' ) \n" +
//                "\t\tAND SJPT_CFLS_LARGE_RECIPE.XMLX IN ( '1', '11', '12', '13' ) \n" +
//                "\t\tAND SJPT_CFLS_LARGE_RECIPE.zje >= 500 \n" +
//                "\t\tAND SJPT_CFLS_LARGE_RECIPE.zje < 1000 \n" +
//                "\tGROUP BY\n" +
//                "\t\tSJPT_CFLS_LARGE_RECIPE.jgbm,\n" +
//                "\t\tSJPT_CFLS_LARGE_RECIPE.VISIT_ID,\n" +
//                "\t\tSJPT_CFLS_LARGE_RECIPE.PATIENT_ID,\n" +
//                "\t\tSJPT_CFLS_LARGE_RECIPE.CFH \n" +
//                "\t) temp_2 \n" +
//                "GROUP BY\n" +
//                "\ttemp_2.jgbm";
//
//        System.out.println(sql);
//
//
//        System.out.println("***************88");
//        System.out.println("\t\tSJPT_CFLS_LARGE_RECIPE\n" +
//                "\t\tLEFT JOIN SJPT_GHLS B ON SJPT_CFLS_LARGE_RECIPE.VISIT_ID = b.VISIT_ID \n" +
//                "\t\tAND SJPT_CFLS_LARGE_RECIPE.JGBM = b.JGBM \n" +
//                "\t\tAND SJPT_CFLS_LARGE_RECIPE.PATIENT_ID = b.PATIENT_ID\n" );
//
//
//        System.out.println("*****************");
//
//
//        System.out.println("select temp_1.*, (select jgmc from t_govinfo where t_govinfo.jgbm=temp_1.jgbm) as jgmc  from ( select " +
//                "FX_MENJIZHEN_DETAIL_PARTITION.*" +
//                " FROM\n" +
//                "FX_MENJIZHEN_DETAIL_PARTITION "+
//                "\t where FX_MENJIZHEN_DETAIL_PARTITION.XMLX in('24') and FX_MENJIZHEN_DETAIL_PARTITION.jgbm=:jgbm  "+
//                " and CHARGE_DATETIME  BETWEEN TO_DATE(:startTime,'yyyy-mm-dd') and TO_DATE(:endTime, 'yyyy-mm-dd') " +
//                "" +
//                "order by CHARGE_DATETIME desc  " +
//                ") temp_1 ");
//
//        System.out.println("****************");
//
//
//        String sql33= "select temp_1.* from ( select FX_MENJIZHEN_DETAIL_PARTITION.* " +
//                " from FX_MENJIZHEN_DETAIL_PARTITION"+
//                "  where FX_MENJIZHEN_DETAIL_PARTITION.XMLX in('26') and FX_MENJIZHEN_DETAIL_PARTITION.centerid=:jgbm"+
//                " and CHARGE_DATETIME  BETWEEN TO_DATE(:startTime,'yyyy-mm-dd') and TO_DATE(:endTime, 'yyyy-mm-dd') " +
//                "" +
//                "order by CHARGE_DATETIME desc  " +
//                ") temp_1 ";
//
//        System.out.println(sql33);
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            System.out.println(Math.sqrt(0.5) * random.nextGaussian() + 0.5);

        }

        System.out.println("*******************");

        String sql = "message=[500][JSONException] illegal identifier :  pos 3, line 2, column 2{\n" +
                "    \"arg0\": {\n" +
                "        \"infno\": \"9001\",\n" +
                "        \"msgid\": \"H341623000792021101215120602\",\n" +
                "        \"insuplc_admdvs\": \"341600\",\n" +
                "        \"mdtrtarea_admvs\": \"341600\",\n" +
                "        \"recer_sys_code\": \"MBS_LOCAL\",\n" +
                "        \"dev_no\": \"\",\n" +
                "        \"dev_safe_info\": \"\",\n" +
                "        \"cainfo\": \"\",\n" +
                "        \"signtype\": \"\",\n" +
                "        \"infver\": \"V1.0\",\n" +
                "        \"opter_type\": \"1\",\n" +
                "        \"opter\": \"888888\",\n" +
                "        \"opter_name\": \"张三\",\n" +
                "        \"inf_time\": \"2021-10-12 15:14:12\",\n" +
                "        \"fixmedins_code\": \"H34162300079\",\n" +
                "        \"fixmedins_name\": \"利辛康宁医院\",\n" +
                "        \"sign_no\": \"\",\n" +
                "        \"input\": {\n" +
                "            \"signIn\": {\n" +
                "                \"opter_no\": \"888888\",\n" +
                "                \"mac\": \"01-02-03-04-05-06\",\n" +
                "                \"ip\": \"192.168.148.30\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
//        System.out.println(sql);


        System.out.println("340121199304197612".substring(6, 14));

        System.out.println("************************************");


//        System.out.println(Float.MAX_VALUE);

        System.out.println("****************************************");


        float aa = 1234567890123456.12f;
        System.out.println(aa);
        String ss = "20211124335004  20211124471008  340100P0000004274044    12.9    0   0   2";
        String[] result = ss.split("\t");
        System.out.println(result);
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\lijian\\IdeaProjects\\json\\9101_3202.txt")));
        String line = null;
        while ((line = br.readLine()) != null) { //循环读取行
            String[] segments = line.split("\\s+"); //按tab分割
        }
    }

    @Test
    public void TESTTEST() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<LinkedHashMap<String,String>> result=   objectMapper.readValue(new File("C:\\Users\\lijian\\IdeaProjects\\json\\model_bi.json"), List.class);
        InputStream inputStream = createInputStream(result,Stream.of("amount_public","jgmc").collect(Collectors.toList()));
       String str =  StreamUtils.copyToString(inputStream,Charset.defaultCharset());
        System.out.println(str);
    }


    /**
     *  创建 上传 文件 流
     * @param content
     * @return
     * @throws IOException
     */
    public static InputStream createInputStream(List<LinkedHashMap<String,String>> content) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (LinkedHashMap<String, String> tempMap : content) {
            for (Map.Entry<String, String> entry : tempMap.entrySet()) {
                byteArrayOutputStream.write(String.valueOf(entry.getValue()).getBytes());
                byteArrayOutputStream.write("\t".getBytes());
            }
            byteArrayOutputStream.write("\r\n".getBytes());
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }

    /**
     *  创建 上传 文件 流 , 根据 column 顺序 添加 value 到 流 中
     * @param content
     * @param columnList
     * @return
     * @throws IOException
     */
    public static InputStream createInputStream(List<LinkedHashMap<String,String>> content,List<String> columnList) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (LinkedHashMap<String, String> tempMap : content) {
            for (String column : columnList) {
                byteArrayOutputStream.write(String.valueOf(tempMap.get(column)).getBytes());
                byteArrayOutputStream.write("\t".getBytes());
            }
            byteArrayOutputStream.write("\r\n".getBytes());
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }

    @Test
    public void TEST_ICD10() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\lijian\\IdeaProjects\\json\\src\\test\\java\\com\\lijian\\string\\20211022718011245295249870.txt")));
        String line = null;
        while ((line = br.readLine()) != null) { //循环读取行
            String[] segments = line.split("\\s+"); //按tab分割
            System.out.println("诊断代码:" + segments[10] + ",诊断名称:" + segments[11]);
        }
    }
    @Test
            public void TEST_ICD10_2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\lijian\\IdeaProjects\\json\\src\\test\\java\\com\\lijian\\string\\202110228859322060136927201.txt")));
        String line = null;
        while ((line = br.readLine()) != null) { //循环读取行
            String[] segments = line.split("\\s+"); //按tab分割
            System.out.println("诊断代码:" + segments[10] + ",诊断名称:" + segments[11]);
        }

    }

    @Test
    public void testReverse(){
      String   result =   new StringBuffer("lijian").reverse().toString();
        System.out.println(result);
    }
    @Test
    public void testSubString(){
        String feedetl_sn = "1234567890,1234567890,1234567890,qwe";
        if (feedetl_sn.length() >= 30) {
            feedetl_sn = feedetl_sn.substring(feedetl_sn.length() - 30);
        }
        System.out.println(feedetl_sn);
    }

    @Test
    public void floatTest(){
        float medfee_sumamt = 78.232001F;
        BigDecimal bigDecimal = new BigDecimal(medfee_sumamt);
        medfee_sumamt = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
        System.out.println(medfee_sumamt);
    }

    @Test
    public void test_123(){

      String result =  "#00123".substring(1);
        System.out.println(result);
    }
    @Test
    public void test_concurrentMap(){
        ConcurrentHashMap<String, String> sign_no_map = new ConcurrentHashMap<String, String>();
        sign_no_map.get(null);
    }

    @Test
    public void forTest(){
        String icpcodeList = "0001,0002,0004,0005,0006,0008,0010,0012,0013,0021,0027,0030,0033,0032,0031";
        String[] str = icpcodeList.split(",");
        Collection<String> unmodifiableCollection = Collections.unmodifiableCollection(Arrays.asList(str));

        for (int i = 0; i < unmodifiableCollection.size(); i++) {
            if (i == 0) {
                unmodifiableCollection.clear();
            }

        }

        System.out.println(Arrays.asList(str));

    }
}
