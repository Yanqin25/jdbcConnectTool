package com.sw.factory;

import com.sw.entity.EsConnectImpl;
import com.sw.utils.ConfigParseUtil;

public class EsFactory extends DBFactory {
    @Override
    public EsConnectImpl createDB() {
        EsConnectImpl es= new EsConnectImpl();
        es.setHost(ConfigParseUtil.get("es.host"));
        es.setPort(Integer.valueOf(ConfigParseUtil.get("es.port")));
        es.setUsername(ConfigParseUtil.get("es.username"));
        es.setPassword(ConfigParseUtil.get("es.password"));
        es.setSql(ConfigParseUtil.get("es.sql"));
        return es;
    }
}
