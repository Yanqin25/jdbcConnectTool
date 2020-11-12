package com.sw.factory;

import com.sw.utils.ConfigParseUtil;
import com.sw.entity.CommonDBImpl;

public class GaussdbFactory extends DBFactory {
    @Override
    public CommonDBImpl createDB() {
        CommonDBImpl gaussdb= new CommonDBImpl();
        gaussdb.setDriver(ConfigParseUtil.get("gaussdb.driverClassName"));
        gaussdb.setUrl(ConfigParseUtil.get("gaussdb.url"));
        gaussdb.setUsername(ConfigParseUtil.get("gaussdb.username"));
        gaussdb.setPassword(ConfigParseUtil.get("gaussdb.password"));
        gaussdb.setSql(ConfigParseUtil.get("gaussdb.sql"));
        return gaussdb;
    }
}
