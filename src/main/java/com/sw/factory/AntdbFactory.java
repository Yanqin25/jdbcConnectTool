package com.sw.factory;

import com.sw.utils.ConfigParseUtil;
import com.sw.entity.CommonDBImpl;

public class AntdbFactory extends DBFactory {
    @Override
    public CommonDBImpl createDB() {
        CommonDBImpl antdb= new CommonDBImpl();
        antdb.setDriver(ConfigParseUtil.get("antdb.driverClassName"));
        antdb.setUrl(ConfigParseUtil.get("antdb.url"));
        antdb.setUsername(ConfigParseUtil.get("antdb.username"));
        antdb.setPassword(ConfigParseUtil.get("antdb.password"));
        antdb.setSql(ConfigParseUtil.get("antdb.sql"));
        return antdb;
    }
}
