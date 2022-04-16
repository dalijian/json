package com.sihai.dubbo.provider.service;

import com.sihai.dubbo.provider.service.ProviderService;

/**
 * xml方式服务提供者实现类
 */
public class ProviderServiceImpl implements ProviderService {

    @Override
    public String SayHello(String word) {
        return word;
    }
}