package com.lijian.json.jackon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    //日期格式转换

//    @DateTimeFormat(pattern="yyyy-MM-dd")//页面写入数据库时格式化
@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")//数据库导出页面时json格式化
    private Date birthday;

    private String nickname;

    private int age;

    @JsonProperty(value = "count")
    private String amount;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
