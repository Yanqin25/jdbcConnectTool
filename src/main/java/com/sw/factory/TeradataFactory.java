package com.sw.factory;

import com.sw.utils.ConfigParseUtil;
import com.sw.entity.CommonDBImpl;

public class TeradataFactory extends DBFactory {
    @Override
    public CommonDBImpl createDB() {
        CommonDBImpl teradata= new CommonDBImpl();
        teradata.setDriver(ConfigParseUtil.get("teradata.driverClassName"));
        teradata.setUrl(ConfigParseUtil.get("teradata.url"));
        teradata.setUsername(ConfigParseUtil.get("teradata.username"));
        teradata.setPassword(ConfigParseUtil.get("teradata.password"));
        teradata.setSql(ConfigParseUtil.get("teradata.sql"));
        return teradata;
    }
}
