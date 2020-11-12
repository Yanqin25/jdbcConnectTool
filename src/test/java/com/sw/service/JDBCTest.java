package com.sw.service;

import com.sw.utils.ConfigParseUtil;

public class JDBCTest {
    public static void main(String[] args) {
        String type = ConfigParseUtil.get("type");
        JDBCConnect.connect(type);
    }
}
