package com.lijian.algorithms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//递归 主要 要 找到 f(n) 与 f(n-1)  之间的关系
public class RecursionTest {

    //    1 到 100 总和 使用 for 循环实现
    @Test
    public void test() {
        int sum = 0;
        for (int i = 0; i < 101; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    @Test
    public void stack() {

        Stack<Integer> stack = new Stack<>();
        int total = 0;
        int step = 100;
        Integer value;
        while (step >= 0) {
            stack.push(step);
            step--;
        }
        while ((value = stack.pop()) != null) {
            total += value;
        }
        System.out.println("total==" + total);
    }

    //递归求1 到 100 总和
    @Test
    public void recursionTest2() {
        int result = recursion(100);
        System.out.println(result);

    }

    public int recursion(int i) {
        if (i == 1) {
            return 1;
        }
        return i + recursion(i - 1);

    }


    @Test
    public void febonacciTest() {


        int result = febonacci(2);
        System.out.println(result);
    }

    public int febonacci(int i) {

        if (i == 1) {
            return 1;
        }
        if (i <= 0) {
            return 0;
        }
        return febonacci(i - 1) + febonacci(i - 2);
    }

    @Test
    public void recursionTest() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<Map<String, String>> list = mapper.readValue(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\algorithms\\src\\test\\java\\com\\lijian\\algorithms\\itemPara.json"), List.class);
            String result = recursion(list, "temp_code_target");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据 公式计算 记录 推导 最终 计算 公式
     *
     * @param list 记录
     * @param key  初始 公式 key
     * @return 最终 计算 公式
     */
    public String recursion(List<Map<String, String>> list, String key) {
        Optional<Map<String, String>> result = list.stream().filter(x -> x.get("end_factor").equals(key)).findFirst();

        if (!result.isPresent()) {
            throw new RuntimeException("不存在");
        }
        String begin_factor = result.get().get("begin_factor");
        String middle_factor = result.get().get("middle_factor");
        String operator = result.get().get("operator");

        if (isNotTemp(begin_factor) && isNotTemp(middle_factor)) {
            return "(" + begin_factor + operator + middle_factor + ")";
        } else if (!isNotTemp(begin_factor)) {
            begin_factor = recursion(list, begin_factor);
        } else if (!isNotTemp(middle_factor)) {
            middle_factor = recursion(list, middle_factor);
        }
        return "(" + begin_factor + operator + middle_factor + ")";
//                return  begin_factor + operator + middle_factor ;

    }

    private boolean isNotTemp(String begin_factor) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Map<String, String>> list = mapper.readValue(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\algorithms\\src\\test\\java\\com\\lijian\\algorithms\\itemCodeList.json"), List.class);
            boolean flag = list.stream().filter(x -> begin_factor.equals(x.get("factor_id"))).findFirst().isPresent();
            return flag;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    static Pattern pattern = Pattern.compile("\\([a-zA-Z0-9_]+[\\+\\-\\*\\/][a-zA-Z0-9_]+\\)");
    static Pattern endPattern = Pattern.compile("^[a-zA-Z0-9_]+[\\+\\-\\*\\/][a-zA-Z0-9_]+$");
    List<Map<String, String>> formulaList = new ArrayList<>();
    String endFormula = "";

    /**
     * 根据 公式 反推 生成 公式 的 记录
     *
     * @param formula 公式
     * @param i       记录 初始 个数
     */
    public void recursion2(String formula, int i) {
        Matcher matcher = pattern.matcher(formula);
        Matcher endMatcher = endPattern.matcher(formula);
        if (endMatcher.find()) {
            endFormula = formula;
            return;
        }
        while (matcher.find()) {
            String group = matcher.group();

            formula = formula.replace(group, "temp_code_" + i);
            formulaList.add(new ImmutableMap.Builder<String, String>()
                    .put("key", "temp_code_" + i)
                    .put("value", group)
                    .build());
            i++;
        }
        recursion2(formula, i);
    }

    @Test
    public void recursion2Test() {
//      recursion2("((B0087*B0086)-(B0088+B0087))+(B0086-B0088)", 0);
        recursion2("(((((B0217+B0217)-B0122)/B0211)-B0121)*B0196)*B0140", 0);


        System.out.println("endFormula-->" + endFormula);
        System.out.println(formulaList);
    }

    @Test
    public void endPatternTest() {
        Pattern endPattern = Pattern.compile("^[a-zA-Z0-9_]+[\\+\\-\\*\\/][a-zA-Z0-9_]+$");
        Matcher endMatcher = endPattern.matcher("temp_code_1-temp_code_2");
        System.out.println(endMatcher.matches());
    }

    @Test
    public void recursionBI_test() throws IOException {
        List<Map<String, Object>> modelList = new ObjectMapper().readValue(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\model_bi.json"), List.class);
        List<Map<String, Object>> resultList = new ObjectMapper().readValue(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\result_bi.json"), List.class);
        recursion_BI(modelList, resultList);
    }

    private void recursion_BI(List<Map<String, Object>> model, List<Map<String, Object>> result) {
        for (Map<String, Object> map : model) {
            map.put("amount_self",
                    add(Double.valueOf(String.valueOf(map.get("amount_self"))),
                            result.stream().filter(x -> x.get("parent_jgbm").equals(map.get("icpcode"))).mapToDouble(x -> Double.valueOf(String.valueOf(x.get("amount_self")))).sum()));
            map.put("amount_public",
                    add(Double.valueOf(String.valueOf(map.get("amount_public"))),
                            result.stream().filter(x -> x.get("parent_jgbm").equals(map.get("icpcode"))).mapToDouble(x -> Double.valueOf(String.valueOf(x.get("amount_public")))).sum()));


            recursion_BI(result.stream().filter(x -> x.get("parent_jgbm").equals(map.get("icpcode"))).collect(Collectors.toList()), result);
        }
    }

    public double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).intValue();
    }


    // 过滤 指标树  只展示 叶子节点 中 人工填报的 指标
    @Test
    public void filterItemTreeByValueFlag() throws IOException {
        List<Map<String, Object>> result = new ObjectMapper().readValue(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\item_tree_filter_by_value_flag.json"), List.class);

        recursionFilter(result);

        System.out.println(result);

        new ObjectMapper().writeValue(new FileOutputStream("item_tree_by_value_flag_result.json"), result);
    }

    private void recursionFilter(List<Map<String, Object>> list) {

        Iterator<Map<String, Object>> iterator = list.iterator();

        while (iterator.hasNext()) {
            Map<String, Object> tempMap = iterator.next();

            if (!tempMap.containsKey("children")) {

                if (!tempMap.get("value_flag").equals("jx_item_calculate_1")) {
                    iterator.remove();
                }
            } else {
                recursionFilter((List<Map<String, Object>>) tempMap.get("children"));
            }
        }
//    @Test
//    public void getParentItemName(){
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            Map<String, Object> map  = mapper.readValue(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\algorithms\\itemTree.json"), Map.class);
//            String result = recursionParentItemName((Map)(map.get("data")), "001001");
//
//            System.out.println(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public String recursionParentItemName(Map<String, Object> data, String parentItemCode) {
//        if (data.get("item_code").equals(parentItemCode)) {
//            return String.valueOf(data.get("item_name"));
//        }
//        if ((List<Map<String,Object>>)data.get("children"))) {
//            data.get("children").forEach(x->);
//        }
//    }
    }
}
