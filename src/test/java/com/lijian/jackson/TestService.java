package com.lijian.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijian.jackon.Friend;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestService {


    @Test
    public void testClass() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Friend friend = new Friend("lijian",25);

//        写成字符串

        String text = mapper.writeValueAsString(friend);
        System.out.println(text);

//        写为文件
        mapper.writeValue(new File("friend.json"),friend);

//        写为字符流

        byte[] bytes = mapper.writeValueAsBytes(friend);
        System.out.println(bytes);
        System.out.println(new String(bytes));


//        从字符串中读取

        Friend newFriend = mapper.readValue(text, Friend.class);
        System.out.println(newFriend);
//        从字节流中读取

        newFriend = mapper.readValue(bytes, Friend.class);
        System.out.println(newFriend);


//        从文件中读取
        newFriend = mapper.readValue(new File("friend.json"), Friend.class);

        System.out.println(newFriend);


        File file = new File("friend.json");
        System.out.println(file.getAbsolutePath());


    }

    @Test
    public void testList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object>  map= new HashMap<>();
        Friend friend = new Friend("lijian",25);
        map.put("age", 25);
        map.put("name", "lijain");
        map.put("friend", friend);
        map.put("interests", new String[]{"pc games", "music"});

        String text = mapper.writeValueAsString(map);

        System.out.println(text);
//        需要注意的是从JSON转换为Map对象的时候，
// 由于Java的类型擦除，所以类型需要我们手动用new TypeReference<T>给出
        Map<String,Object> map2= mapper.readValue(text, new TypeReference<Map<String,Object>>(){

        });
        System.out.println(map2);

        JsonNode root=mapper.readTree(text);

//        由 key  得到 value
        String name= root.get("name").asText();
        int age = root.get("age").asInt();

        String friend1 = root.get("friend").get("nickname").asText();
        System.out.println(friend1);

        System.out.println("name:" + name + "age:" + age);







    }
}
