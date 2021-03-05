package com.lijian.antlr.predicates;

import com.lijian.antlr.predicates.auto.EnumParser;

public class TestEnum {

    public static void main(String[] args) {
        int i=0;
        EnumParser.java5=false;
        if (args.length > 0 && args[i].equals("-java5")) {
            EnumParser.java5=true;
            i++;
        }
    }
}
