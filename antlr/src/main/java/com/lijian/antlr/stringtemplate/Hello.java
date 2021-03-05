package com.lijian.antlr.stringtemplate;

import org.stringtemplate.v4.*;

public class Hello {

    public static void main(String[] args) {
        ST hello = new ST("Hello, <name>");
        hello.add("name", "World");
        System.out.println(hello.render());

        System.out.println("select * from orders where cust_id = 'SDYT987645".toUpperCase());
    }
}