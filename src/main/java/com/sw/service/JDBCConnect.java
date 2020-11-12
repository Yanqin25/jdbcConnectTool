package com.sw.service;

import com.sw.constant.DBConstant;
import com.sw.entity.Connect;
import com.sw.factory.DBFactoryBuilder;

public class JDBCConnect {
    public static void connect(String type) {
        String dbFactoryName = DBConstant.getDBClass(type);
        if (dbFactoryName==null) {
            System.out.println("暂不支持该数据库类型："+type);
            return;
        }
        try {
            Connect db = DBFactoryBuilder.build(dbFactoryName).createDB();
            System.out.println("连接参数：");
            System.out.println(db);

            db.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
