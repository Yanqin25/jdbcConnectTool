package com.sw.utils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigParseUtil {

    public static String get(String key) {
        InputStream ins = null;
        try {
            //可以从外部配置文件替换，适合打包发布,但是不适用于idea开发
            ins = new FileInputStream("db.properties");
        } catch (FileNotFoundException e) {
            //無法從外部配置文件替换，适合idea本地调试
            ins=ClassLoader.getSystemResourceAsStream("db.properties");
        }

        Properties p = new Properties();
        try {
            p.load(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return p.getProperty(key);
    }
}
