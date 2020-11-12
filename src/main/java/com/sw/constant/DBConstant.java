package com.sw.constant;

import java.util.HashMap;
import java.util.Map;

public class DBConstant {
    private static Map<String, String> dbMap = new HashMap<>();
    static {
        dbMap.put("mysql", "com.sw.factory.MysqlFactory");
        dbMap.put("oracle", "com.sw.factory.OracleFactory");
        dbMap.put("antdb", "com.sw.factory.AntdbFactory");
        dbMap.put("db2", "com.sw.factory.Db2Factory");
        dbMap.put("mssql", "com.sw.factory.MssqlFactory");
        dbMap.put("teradata", "com.sw.factory.TeradataFactory");
        dbMap.put("postgresql", "com.sw.factory.PostgreSqlFactory");
        dbMap.put("gaussdb", "com.sw.factory.GaussdbFactory");
        dbMap.put("gbase", "com.sw.factory.GbaseFactory");
        dbMap.put("es", "com.sw.factory.EsFactory");
        dbMap.put("redis", "com.sw.factory.RedisFactory");
        dbMap.put("mongodb", "com.sw.factory.MongodbFactory");
    }
    public static String getDBClass(String type) {
        return dbMap.get(type.toLowerCase());
    }
}
