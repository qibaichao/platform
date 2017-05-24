package com.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.JedisPool;

/**
 * Created by qibaichao on 2016/7/8.
 */
public class ClassPathXmlApplicationContextTest {


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
        JedisPool jedisPool = (JedisPool) ctx.getBean("jedisPool");
        System.out.println(jedisPool);
    }
}
