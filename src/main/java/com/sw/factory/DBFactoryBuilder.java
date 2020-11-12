package com.sw.factory;

public class DBFactoryBuilder {
    public static DBFactory build(String type) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (DBFactory) Class.forName(type).newInstance();
    }
}
