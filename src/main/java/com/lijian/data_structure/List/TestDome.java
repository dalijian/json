package com.lijian.data_structure.List;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDome {


    @Test
    public void insert(){

        List list = new ArrayList<>();
        String []  name =new String[]{"l", "j"};
        list.add(1);
        list.add(null);
        list.add(0,"ok");
        System.out.println(list.toString());
    }
}
