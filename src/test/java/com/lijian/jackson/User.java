package com.lijian.jackson;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.io.IOException;

@JsonFilter("userFilter")  //在这里加注解并且指定过滤器的名称
public class User {

  private String username;
  private String password;
  private Integer age;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public static void main(String[] args) throws IOException {
      SimpleFilterProvider filterProvider = new SimpleFilterProvider();
      filterProvider.addFilter("userFilter",   //添加过滤器名称
              SimpleBeanPropertyFilter.serializeAllExcept("username", "password")); //这里指定不序列化的属性
/*        Set exclude = new HashSet();
        exclude.add("username");
        exclude.add("password");
        filterProvider.addFilter("userFilter",
                SimpleBeanPropertyFilter.serializeAllExcept(exclude)); //这里指定不序列化的属性也可以放到Set集合里面
        filterProvider.addFilter("userFilter",
                SimpleBeanPropertyFilter.serializeAll());  // serializeAll()序列化所有属性，
        filterProvider.addFilter("userFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("age")); //只序列化这里包含的属性*/
      ObjectMapper mapper = new ObjectMapper();
      mapper.setFilterProvider(filterProvider);
      User user = new User();
      user.setUsername("小明");
      user.setPassword("123");
      user.setAge(18);
      String s = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(user);
      System.out.println("我是序列化" + s);
      User user1 = mapper.readValue("{\"username\":\"小明\",\"password\":\"123\",\"age\":18}", User.class);
      System.out.println("我是反序列化" + user1);  //这里注意只是在序列化的时候过滤字段，在反序列化的时候是不过滤的
  }
}
