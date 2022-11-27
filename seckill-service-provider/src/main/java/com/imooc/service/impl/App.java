package com.imooc.service.impl;

import com.imooc.client.SeckillService;
import com.imooc.entity.Seckill;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext.xml"});
        context.start();
        SeckillService seckillService = context.getBean("seckillService",SeckillService.class);
        List<Seckill> getSeckillList = seckillService.getSeckillList();
        System.out.println(getSeckillList);
        System.out.println("press any key to exit.");
        System.in.read();
    }
}
