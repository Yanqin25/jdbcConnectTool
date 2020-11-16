package com.sw.service;


import com.sw.entity.Connect;
import com.sw.utils.SpringUtil;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

public class JDBCConnect {
    public static void connect(String type) {
        try {
            Connect db = SpringUtil.getBean(type);
            System.out.println("连接参数：");
            System.out.println(db);
            db.connect();
        } catch (NoSuchBeanDefinitionException e) {
            System.out.println("暂不支持该数据库类型："+type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
