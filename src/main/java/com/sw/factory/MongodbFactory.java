package com.sw.factory;

import com.sw.entity.MongodbConnectImpl;
import com.sw.utils.ConfigParseUtil;

public class MongodbFactory extends DBFactory {
    @Override
    public MongodbConnectImpl createDB() {
        MongodbConnectImpl mongodb= new MongodbConnectImpl();
        mongodb.setHost(ConfigParseUtil.get("mongodb.host"));
        mongodb.setPort(Integer.valueOf(ConfigParseUtil.get("mongodb.port")));
        mongodb.setUsername(ConfigParseUtil.get("mongodb.username"));
        mongodb.setPassword(ConfigParseUtil.get("mongodb.password"));
        mongodb.setDatabase(ConfigParseUtil.get("mongodb.database"));
        mongodb.setAuthDatabase(ConfigParseUtil.get("mongodb.authenticationDatabase"));
        mongodb.setSql(ConfigParseUtil.get("mongodb.sql"));
        return mongodb;
    }
}
