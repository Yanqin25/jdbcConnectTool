package com.sw;

import com.sw.service.JDBCConnect;
import com.sw.utils.ConfigParseUtil;

public class TestMain {
    public static void main(String[] args) {
        String type;
        if (args.length>0) {
            type = args[0];
        }else {
            type = ConfigParseUtil.get("type");
        }
        JDBCConnect.connect(type);

    }
}
