package com.sw.factory;

import com.sw.utils.ConfigParseUtil;
import com.sw.entity.CommonDBImpl;

public class Db2Factory extends DBFactory {
    @Override
    public CommonDBImpl createDB() {
        CommonDBImpl db2= new CommonDBImpl();
        db2.setDriver(ConfigParseUtil.get("db2.driverClassName"));
        db2.setUrl(ConfigParseUtil.get("db2.url"));
        db2.setUsername(ConfigParseUtil.get("db2.username"));
        db2.setPassword(ConfigParseUtil.get("db2.password"));
        db2.setSql(ConfigParseUtil.get("db2.sql"));
        return db2;
    }
}
