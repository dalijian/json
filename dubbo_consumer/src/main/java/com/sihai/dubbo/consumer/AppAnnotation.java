package com.sihai.dubbo.consumer;

import com.sihai.dubbo.consumer.Annotation.ConsumerAnnotationService;
import com.sihai.dubbo.consumer.configuration.ConsumerConfiguration;
import com.sihai.dubbo.provider.service.ProviderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 注解方式启动
 *
 */
public class AppAnnotation
{
    public static void main( String[] args ) throws IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class); 
        context.start(); // 启动
        ConsumerAnnotationService consumerAnnotationService = context.getBean(ConsumerAnnotationService.class); 
        String hello = consumerAnnotationService.doSayHello("annotation"); // 调用方法
        System.out.println("result: " + hello); // 输出结果

    }
}
