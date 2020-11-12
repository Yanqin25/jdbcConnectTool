package com.sw.factory;

import com.sw.utils.ConfigParseUtil;
import com.sw.entity.CommonDBImpl;

public class MssqlFactory extends DBFactory {
    @Override
    public CommonDBImpl createDB() {
        CommonDBImpl mssql= new CommonDBImpl();
        mssql.setDriver(ConfigParseUtil.get("mssql.driverClassName"));
        mssql.setUrl(ConfigParseUtil.get("mssql.url"));
        mssql.setUsername(ConfigParseUtil.get("mssql.username"));
        mssql.setPassword(ConfigParseUtil.get("mssql.password"));
        mssql.setSql(ConfigParseUtil.get("mssql.sql"));
        return mssql;
    }
}
