package com.sw.entity;

import com.sw.utils.PrintTable;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Connect {
    void connect();
    default void printResult(ResultSet resultSet) {
        System.out.println("sql执行结果:");
        List<List<String>> resultList = new ArrayList<>();
        try {
            int columnCount;
            ResultSetMetaData metaData = resultSet.getMetaData();
            columnCount = metaData.getColumnCount();
            //表头
            List<String> headerList = new ArrayList<>();
            for (int i = 0; i < columnCount; i++) {
                headerList.add(metaData.getColumnName(i+1));
            }
            resultList.add(headerList);
            //内容:
            while (resultSet.next()) {
                List<String> tmp = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    tmp.add(resultSet.getString(i));
                }
               resultList.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        PrintTable pt = new PrintTable();
//        pt.printTable(pt.buildTable(resultList));

//        int width = Integer.valueOf(ConfigParseUtil.get("maxWidth"));
//        int maxLen = Integer.valueOf(ConfigParseUtil.get("maxCount"));;
//        new PrintTable(resultList, width, maxLen).printTable("|");

        new PrintTable(resultList).printTable();
    }
}
