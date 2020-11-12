package com.sw.factory;

import com.sw.utils.ConfigParseUtil;
import com.sw.entity.CommonDBImpl;

public class MysqlFactory extends DBFactory {
    @Override
    public CommonDBImpl createDB() {
        CommonDBImpl mysql= new CommonDBImpl();
        mysql.setDriver(ConfigParseUtil.get("mysql.driverClassName"));
        mysql.setUrl(ConfigParseUtil.get("mysql.url"));
        mysql.setUsername(ConfigParseUtil.get("mysql.username"));
        mysql.setPassword(ConfigParseUtil.get("mysql.password"));
        mysql.setSql(ConfigParseUtil.get("mysql.sql"));
        return mysql;
    }
}
