package com.sw.entity;

import lombok.Data;

import java.sql.*;

@Data
public class CommonDBImpl implements Connect {
    private String driver;
    private String username;
    private String password;
    private String url;
    private String sql;

    @Override
    public void connect() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            printResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
