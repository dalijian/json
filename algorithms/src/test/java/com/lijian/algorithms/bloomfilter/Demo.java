package com.lijian.algorithms.bloomfilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import org.springframework.util.StringUtils;

public class Demo {
    private final BloomFilter<String> dealIdBloomFilter = BloomFilter.create(new Funnel<String>() {

        private static final long serialVersionUID = 1L;

        @Override
        public void funnel(String arg0, PrimitiveSink arg1) {

            arg1.putString(arg0, Charsets.UTF_8);
        }

    }, 1024*1024*32);


    public synchronized boolean containsDealId(String deal_id){

        if(StringUtils.isEmpty(deal_id)){
//            mLogger.warn("deal_id is null");
            return true;
        }
        // 判断 bloomFilter  是否 包含
        boolean exists = dealIdBloomFilter.mightContain(deal_id);
        if(!exists){
            dealIdBloomFilter.put(deal_id);
        }
        return exists;
    }


}
