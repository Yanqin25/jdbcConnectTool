package com.sw.factory;

import com.sw.entity.RedisConnectImpl;
import com.sw.utils.ConfigParseUtil;

public class RedisFactory extends DBFactory {
    @Override
    public RedisConnectImpl createDB() {
        RedisConnectImpl redis= new RedisConnectImpl();
        redis.setHost(ConfigParseUtil.get("redis.host"));
        redis.setPort(Integer.valueOf(ConfigParseUtil.get("redis.port")));
        redis.setPassword(ConfigParseUtil.get("redis.password"));
        redis.setSql(ConfigParseUtil.get("redis.sql"));
        return redis;
    }
}
