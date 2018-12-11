package com.lijian.json.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

//自定义解析
public class UserTypeAdepter extends TypeAdapter<User> {

   @Override
   public void write(JsonWriter jsonWriter, User user) throws IOException {
       jsonWriter.beginObject();
       jsonWriter.name("name").value(user.name);
       jsonWriter.name("age").value(user.age);
       jsonWriter.name("emai").value(user.email);
       jsonWriter.endObject();
   }

   @Override
   public User read(JsonReader jsonReader) throws IOException {
      User user = new User();
      jsonReader.beginObject();
       while (jsonReader.hasNext()) {
           switch (jsonReader.nextName()) {
               case "name":
                   user.name=jsonReader.nextString();
                   break;
               case "age":
                   user.age=jsonReader.nextInt();
                   break;
               case "email":
               case "email_address":
               case "emailAddress":
                   user.email=jsonReader.nextString();
                   break;
           }
       }
       jsonReader.endObject();


       return user;
   }
}
