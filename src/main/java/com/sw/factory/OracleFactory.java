package com.sw.factory;

import com.sw.utils.ConfigParseUtil;
import com.sw.entity.CommonDBImpl;

public class OracleFactory extends DBFactory {
    @Override
    public CommonDBImpl createDB() {
        CommonDBImpl oracle= new CommonDBImpl();
        oracle.setDriver(ConfigParseUtil.get("oracle.driverClassName"));
        oracle.setUrl(ConfigParseUtil.get("oracle.url"));
        oracle.setUsername(ConfigParseUtil.get("oracle.username"));
        oracle.setPassword(ConfigParseUtil.get("oracle.password"));
        oracle.setSql(ConfigParseUtil.get("oracle.sql"));
        return oracle;
    }
}
