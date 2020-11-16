package com.sw.utils;

import com.sw.entity.Connect;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringUtil {
    public static Connect getBean(String type) {
        ApplicationContext context;
        try {
            context = new FileSystemXmlApplicationContext("applicationContext.xml");
        } catch (BeanDefinitionStoreException e) {
            context = new ClassPathXmlApplicationContext("applicationContext.xml");
        }

        return (Connect) context.getBean(type);
    }
}
