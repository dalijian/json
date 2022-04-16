package com.lijian.jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class Article3 {

    private String title;
    private String content;

    @JsonGetter("tr")
    public String getTitle() {
        return title;
    }

    @JsonSetter("tw")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonGetter("cr")
    public String getContent() {
        return content;
    }

    @JsonSetter("cw")
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * 使用JsonGetter注解的别名进行序列化
     *
     * @throws JsonProcessingException
     */
    @Test
    public void serialize3() throws JsonProcessingException {
        Article3 article = new Article3();
        article.setTitle("spring");
        article.setContent("flowers");

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(article));
    }

    /**
     * 使用JsonSetter注解的别名进行反序列化
     *
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    @Test
    public void deserialize3() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = "{\"tw\":\"spring\",\"cw\":\"flowers\"}";
        Article3 article = null;
        try {
            article = mapper.readValue(jsonString, Article3.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(mapper.writeValueAsString(article));
    }
}