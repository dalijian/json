package com.lijian.jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class Article1 {

    private String title;
    private String content;

    @JsonGetter("t")
    public String getTitle() {
        return title;
    }

    @JsonSetter("t")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonGetter("c")
    public String getContent() {
        return content;
    }

    @JsonSetter("c")
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 使用注解的别名进行序列化
     *
     * @throws JsonProcessingException
     */
    @Test
    public void serialize1() throws JsonProcessingException {
        Article1 article = new Article1();
        article.setTitle("spring");
        article.setContent("flowers");

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(article));
    }

    /**
     * 反序列化时，只支持使用字段别名
     *
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    @Test
    public void deserialize1() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // 不支持使用原字段名
//  String jsonString = "{\"title\":\"spring\",\"content\":\"flowers\"}";
        // 正确. 使用别名
        String jsonString = "{\"t\":\"spring\",\"c\":\"flowers\"}";
        Article1 article = null;
        try {
            article = mapper.readValue(jsonString, Article1.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(mapper.writeValueAsString(article));
    }
}