package com.lijian.design.builder;

public class Builder {

    private String color;
    private String size;

    static Builder getBuilder(){
        return new Builder();
    }
    public Builder setColor(String color){
        this.color = color;
        return this;
    }
    public Builder setSize(String size){
        this.size = size;
        return this;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Builder{" +
                "color='" + color + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Builder builder = Builder.getBuilder().setColor("red").setSize("10");
        System.out.println(builder);
    }
}
