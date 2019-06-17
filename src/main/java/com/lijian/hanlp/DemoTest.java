package com.lijian.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.junit.jupiter.api.Test;

public class DemoTest {

    @Test
    public void parse(){
        HanLP.Config.enableDebug();
        CustomDictionary.insert("感烟","gc 1");
        CustomDictionary.insert("点型感烟","gc 1");

        System.out.println(HanLP.segment("餐饮部2层北1包厢烟感顶楼-点型感烟"));
    }

}
