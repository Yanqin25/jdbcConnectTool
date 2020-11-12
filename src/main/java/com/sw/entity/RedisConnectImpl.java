package com.sw.entity;

import com.sw.utils.StringUtils;
import lombok.Data;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.List;
@Data
public class RedisConnectImpl implements Connect {
    private String host;
    private Integer port;
    private String password;
    private String sql;
    @Override
    public void connect() {
        Jedis jedis = new Jedis(host, port);
        System.out.println("从jedis.info()中根据sql(正则)过滤到的信息是：");
        System.out.println("--------------------------------------------------");
        try {
            List<String> list = StringUtils.extractMessage(sql, jedis.info());
            list.forEach(System.out::println);
            System.out.println("无密登录");

        } catch (JedisDataException e) {
            if (e.getMessage().contains("NOAUTH Authentication required")) {
                jedis.auth(password);
                List<String> list = StringUtils.extractMessage(sql, jedis.info());
                list.forEach(System.out::println);
            } else {
                e.printStackTrace();
            }

        } finally {
            System.out.println("--------------------------------------------------");
            jedis.close();
        }
    }

}
