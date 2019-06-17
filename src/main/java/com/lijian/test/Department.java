package com.lijian.test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Handler;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Department {

    @Test
    public void Long(){

        Long i =1L;
        Double j = Double.valueOf(1);
        System.out.println(i);
        System.out.println(j);
    }

    @Test
    public  void Round(){

        System.out.println(Math.round(94*0.9));

    }
    @Test
    public void regex(){
        List<Term> list = HanLP.segment("AHAQ_HKJJPJD1");
        System.out.println(list);

    }

}
