package com.lijian.jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

//如果只给字段添加JsonGetter注解，或者只添加JsonSetter注解。在序列化和反序列化时，都需要统一用注解的名称。
public class Article2 {

    private String title;
    private String content;

    @JsonGetter("t")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
    public void serialize2() throws JsonProcessingException {
        Article2 article = new Article2();
        article.setTitle("spring");
        article.setContent("flowers");

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(article));
    }
}