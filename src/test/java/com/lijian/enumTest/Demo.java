package com.lijian.enumTest;

import java.util.stream.Stream;

import static com.lijian.enumTest.Demo.FactorCalMethodParameters.JX_FACTOR_CALMETHOD_1;

public class Demo {

    //    计算方式
    public  enum    FactorCalMethodParameters {
        JX_FACTOR_CALMETHOD_1("jx_factor_calmethod_1"),

        JX_FACTOR_CALMETHOD_2("jx_factor_calmethod_2"),

        JX_FACTOR_CALMETHOD_3("jx_factor_calmethod_3"),

        JX_FACTOR_CALMETHOD_4("jx_factor_calmethod_4"),

        JX_FACTOR_CALMETHOD_5("jx_factor_calmethod_5"),

        JX_FACTOR_CALMETHOD_6("jx_factor_calmethod_6");

        private static String value;

        FactorCalMethodParameters(String jx_factor_calmethod_1) {

        }

//        FactorCalMethodParameters(String value) {
//            this.value=value;
//
//        }

        @Override
        public String toString() {
            return "FactorCalMethodParameters{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }
    // 枚举类型 支持 values() 方法
    public static void main(String[] args) {
        for (FactorCalMethodParameters factorCalMethodParameters : FactorCalMethodParameters.values()) {
            System.out.println(factorCalMethodParameters.name());
        }
    }
}
