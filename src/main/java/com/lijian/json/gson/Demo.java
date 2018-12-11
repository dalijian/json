package com.lijian.json.gson;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo {



        public static void main(String[] args) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
            User user = new User();
            user.setAge(10);
            user.setName("lijian");
            user.setEmail("237922011@qq.com");
            System.out.println(gson.toJson(user));

            Gson gson1 = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                    // 这里作判断，决定要不要排除该字段,return true为排除
                    if ("email".equals(fieldAttributes.getName())) {
                        return true;
                    }
                    Expose expose = fieldAttributes.getAnnotation(Expose.class);
                    if (expose != null && expose.deserialize() == false) {
                        return true;
                    }
                    return false;
                }
                // 直接排除某个类型 ，return true为排除
                @Override
                public boolean shouldSkipClass(Class<?> aClass) {
                    return (aClass == int.class || aClass == Integer.class || aClass == String.class);
                }
            }).create();

            System.out.println(gson1.toJson(user));




            //        POJO与JSON的字段映射规则
            //        FieldNamingPolicy是个枚举类
            Gson gson3 = new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES).create();

            System.out.println(gson3.toJson(user));
            //        自定义映射规则
            Gson gson2 = new GsonBuilder().setFieldNamingStrategy(new FieldNamingStrategy() {
                @Override
                public String translateName(Field field) {
                    String mapName = field.getType().getSimpleName() + "-" + field.getName();
                    return mapName;
                }
            }).create();
            System.out.println(gson2.toJson(user));






            Gson gson4 = new GsonBuilder().registerTypeAdapter(User.class, new UserTypeAdepter()).create();





//            解决gson解析时的容错问题 使用try catch捕获
            Gson gson5 = new GsonBuilder()
                    .registerTypeAdapter(Integer.class, new TypeAdapter<Integer>() {
                        @Override
                        //序列化
                        public void write(JsonWriter out, Integer value) throws IOException {
                            out.value(String.valueOf(value));
                        }

                        @Override
                        //反序列化
                        public Integer read(JsonReader in) throws IOException {
                            try {
                                return Integer.parseInt(in.nextString());
                            } catch (NumberFormatException e) {
                                return -1;
                            }
                        }
                    })
                    .create();

            System.out.println(gson5.toJson(100));
            System.out.println(gson5.fromJson("\"\"", Integer.class));




//            单独使用 自定义反序列化
            Gson gson6 = new GsonBuilder().registerTypeAdapter(Integer.class, new JsonDeserializer<Integer>() {

                @Override
                public Integer deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    try {
                        return jsonElement.getAsInt();
                    } catch (NumberFormatException e) {
                        return -1;
                    }
                }
            }).create();
            System.out.println(gson6.toJson(100));
            System.out.println(gson6.fromJson("\"\"", Integer.class));




//            单独使用 自定义序列化
            JsonSerializer<Number> numberJsonSerializer = new JsonSerializer<Number>() {
                @Override
                public JsonElement serialize(Number src, Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(String.valueOf(src));
                }
            };
            Gson gson7 = new GsonBuilder()
                    .registerTypeAdapter(Integer.class, numberJsonSerializer)
                    .registerTypeAdapter(Long.class, numberJsonSerializer)
                    .registerTypeAdapter(Float.class, numberJsonSerializer)
                    .registerTypeAdapter(Double.class, numberJsonSerializer)
                    .create();
            System.out.println(gson7.toJson(100.0f));//结果："100.0





            Type type = new TypeToken<List<User>>() {
            }.getType();
            TypeAdapter typeAdapter = new TypeAdapter<List<User>>() {
                @Override
                public void write(JsonWriter jsonWriter, List<User> users) throws IOException {
                }

                @Override
                public List<User> read(JsonReader jsonReader) throws IOException {
                    return null;
                }
            };
            Gson gson8 = new GsonBuilder()
                    .registerTypeAdapter(type, typeAdapter)
                    .create();
            List<User> list = new ArrayList<>();
            list.add(new User("a", 11, "2379"));
            list.add(new User("b", 22, "24@qq"));
            //注意，多了个type参数
            String result = gson.toJson(list, type);

            Gson gson9 = new GsonBuilder().registerTypeAdapterFactory(new TypeAdapterFactory() {
                @Override
                public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                    return null;
                }
            }).create();




            /**
             * 解决    服务器返回的数据中data字段类型不固定，比如请求成功data是一个List,不成功的时候是String类型，这样前端在使用泛型解析的时候，怎么去处理呢？接口设计时没有没有保证数据的一致性，
             * 正确的数据返回姿势：同一个接口任何情况下不得改变返回类型，要么就不要返，要么就返空值，如null、[],{}。
             */
            Gson gson10 = new GsonBuilder().registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List<?>>() {
                @Override
                public List<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    if (json.isJsonArray()) {
                        JsonArray array = json.getAsJsonArray();
                        Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
                        List list = new ArrayList<>();
                        for (int i = 0; i < array.size(); i++) {
                            JsonElement element = array.get(i);
                            Object item = context.deserialize(element, itemType);
                            list.add(item);
                        }
                        return list;
                    } else {
                        //和接口类型不符，返回空List
                        return Collections.EMPTY_LIST;
                    }
                }
            }).create();
            System.out.println(gson10.toJson(user));


        }

    }

