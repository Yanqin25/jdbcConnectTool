package com.sw.factory;

import com.sw.utils.ConfigParseUtil;
import com.sw.entity.CommonDBImpl;

public class PostgreSqlFactory extends DBFactory {
    @Override
    public CommonDBImpl createDB() {
        CommonDBImpl postgresql= new CommonDBImpl();
        postgresql.setDriver(ConfigParseUtil.get("postgresql.driverClassName"));
        postgresql.setUrl(ConfigParseUtil.get("postgresql.url"));
        postgresql.setUsername(ConfigParseUtil.get("postgresql.username"));
        postgresql.setPassword(ConfigParseUtil.get("postgresql.password"));
        postgresql.setSql(ConfigParseUtil.get("postgresql.sql"));
        return postgresql;
    }
}
