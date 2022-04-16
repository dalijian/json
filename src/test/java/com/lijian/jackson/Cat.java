package com.lijian.jackson;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class Cat {
    // 使用 @JsonAnySetter   catFood  不能 为空
    @JsonUnwrapped
    private CatFood catFood = new CatFood();
    private String name;
    private String owner;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String weight;

    //    @JsonValue
    private Integer age;


    // 序列化   -> obj -> json string
    @JsonGetter(value = "ali")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter(value = "owner")
    public String getOwner() {
        return owner;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    //     反序列化 -> json string -> obj
    @JsonSetter(value = "address")
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public CatFood getCatFood() {
        return catFood;
    }

    public void setCatFood(CatFood catFood) {
        this.catFood = catFood;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    /**
     * 没有匹配上的反序列化属性，放到这里
     * @param key
     * @param value
     */
    @JsonAnySetter
    public void setCatFood(String key, Object value) {
        if ("brand".equals(key)) {
            this.catFood.setBrand(String.valueOf(value));
        }
        if ("price".equals(key)) {
            this.catFood.setPrice(String.valueOf(value));

        }

    }

    public static class CatFood {

        private String brand;

        private String price;

//        @JsonCreator
//        public CatFood(@JsonProperty("brand") String brand, @JsonProperty("price") String price) {
//            this.brand = brand;
//            this.price = price;
//        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "CatFood{" +
                    "brand='" + brand + '\'' +
                    ", price='" + price + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Cat{" +
                "catFood=" + catFood +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", weight='" + weight + '\'' +
                ", age=" + age +
                '}';
    }


    @Test
    public void jsonSetterTest() {

        Cat cat = new Cat();
        cat.setName("little start");
        cat.setOwner("lijian");
        cat.setAge(3);
//        cat.setCatFood(new Cat.CatFood("guifen", "146"));
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonStr = objectMapper.writeValueAsString(cat);
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void jsonGetterTest() {
        String jsonStr = "{\"brand\":\"guifen\",\"price\":\"146\",\"owner\":\"lijian\",\"age\":3,\"ali\":\"little start\"}";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Cat cat = objectMapper.readValue(jsonStr, Cat.class);
            System.out.println(cat);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
