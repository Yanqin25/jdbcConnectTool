package com.sw.factory;

import com.sw.utils.ConfigParseUtil;
import com.sw.entity.CommonDBImpl;

public class GbaseFactory extends DBFactory {
    @Override
    public CommonDBImpl createDB() {
        CommonDBImpl gbase= new CommonDBImpl();
        gbase.setDriver(ConfigParseUtil.get("gbase.driverClassName"));
        gbase.setUrl(ConfigParseUtil.get("gbase.url"));
        gbase.setUsername(ConfigParseUtil.get("gbase.username"));
        gbase.setPassword(ConfigParseUtil.get("gbase.password"));
        gbase.setSql(ConfigParseUtil.get("gbase.sql"));
        return gbase;
    }
}
