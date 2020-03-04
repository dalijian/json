package com.lijian.clone;

public class UnderlinePen implements Product {

    private final char decochar;

    public UnderlinePen(char decochar) {
        this.decochar = decochar;
        
    }

    @Override
    public void use(String s) {
        int length = s.getBytes().length;
        for (int i = 0; i < length + 4; i++) {
            System.out.println(decochar);

        }
        System.out.println("");
        System.out.println(decochar + " " + s + " " + decochar);
        for (int i = 0; i < length+4; i++) {
            System.out.println(decochar);

        }
        System.out.println("");

    }

    @Override
    public Product createClone() {
        Product p =null;
        try {
            p= (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return p;
    }
}