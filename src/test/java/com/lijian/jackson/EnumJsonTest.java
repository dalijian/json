package com.lijian.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class EnumJsonTest {


    @Test
    public void enumTest() throws JsonProcessingException {

        ObjectMapper objectMapper =new ObjectMapper();

        String json = objectMapper.writeValueAsString(State.VALID);
        System.out.println(json);
    }

    @Test
    public void enumTest2() throws IOException {

        ObjectMapper objectMapper =new ObjectMapper();

//        String json = objectMapper.writeValueAsString(State.VALID);

        String json = " {\n" +
                "            \"name\":\"lijian\",\n" +
                "            \"state\":2\n" +
                "        }";

        Component component = objectMapper.readValue(json, Component.class);
        System.out.println(component);
    }
}
