package com.spring;

import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/**
 * Created by qibaichao on 2016/7/1.
 */
public class BeanDefinitionReadeTest {

    @Test
    public void test3BeanDefinitionReader() {
        DefaultResourceLoader loader = new DefaultResourceLoader();
        Resource  resource= loader.getResource("/ioc/application.xml");
        BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);

        int count = reader.loadBeanDefinitions(resource);
        String[] beanDefinitionNames = reader.getRegistry().getBeanDefinitionNames();
        System.out.println("----------------------");
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }
}
