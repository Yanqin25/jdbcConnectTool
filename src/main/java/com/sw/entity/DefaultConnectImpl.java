package com.sw.entity;

import com.sw.utils.StringUtils;
import lombok.Data;

import java.sql.*;
import java.util.Date;
import java.util.*;

@Data
public class DefaultConnectImpl implements Connect {
    private String driver;
    private String username;
    private String password;
    private String url;
    private String sql;
    private String ddl;
    private String addSql;
    private int tableCount;
    private boolean createTable;
    private boolean executeSql;

    @Override
    public void connect() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("版本信息：" + connection.getMetaData().getDatabaseProductVersion());
            if (!StringUtils.isEmpty(sql)&&executeSql) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                printResult(resultSet);
            }


            if (createTable &&!StringUtils.isEmpty(ddl) && !StringUtils.isEmpty(addSql)) {
                long benginTime = new Date().getTime();
//            createBatch(connection, 100);
//            Map<String, PreparedStatement> tableBatch = createTableBatch(connection, 500);
//            Set<String> tables = tableBatch.keySet();
//            List<PreparedStatement> statementCollection = new ArrayList<>(tableBatch.values());
//            CountDownLatch countDownLatch = new CountDownLatch(4);
//            ThreadTest threadTest = new ThreadTest(countDownLatch);
//            threadTest.handList(statementCollection);
//            Thread.sleep(15000);
//            countDownLatch.await();
                Set<String> tables = new HashSet<>();

                if (connection.getTransactionIsolation()==0) {
                    tables = createTableBatchNoTransaction(connection, tableCount);
                    insertTableNoTransaction(connection,tables);
                }else {
                    tables = createTableBatchWithTransaction(connection, tableCount);
                    insertTable(connection,tables);
                }

                long endTime = new Date().getTime();
                System.out.println(endTime - benginTime);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量创建表和插入语句
     * @param connection
     * @param tableCount
     * @throws SQLException
     */
    private void createAndInsertBatch(Connection connection, int tableCount) throws SQLException {
        connection.setAutoCommit(false);
        for (int i = 0; i < tableCount; i++) {
            String table = UUID.randomUUID().toString().replaceAll("-", "").substring(4, 12);
            String sql = ddl.replace("${table}", table);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeLargeUpdate();
            String add = addSql.replace("${table}", table);
            for (int j = 0; j < 20; j++) {
                PreparedStatement addStatement = connection.prepareStatement(add);
                addStatement.executeLargeUpdate();
            }
        }
        connection.commit();
    }


    /**
     * 测试多线程使用，@link{TestMul},实际不需要
     * @param connection
     * @param tableCount
     * @return
     * @throws SQLException
     */
    private Map<String, PreparedStatement> createTableBatch(Connection connection, int tableCount) throws SQLException {
        Map<String, PreparedStatement> result = new HashMap<>();
        for (int i = 0; i < tableCount; i++) {
            String table = UUID.randomUUID().toString().replaceAll("-", "").substring(5, 12);
            String sql = ddl.replace("${table}", table);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            result.put(table, preparedStatement);
        }
        return result;
    }

    /**
     * 非事物创建多张表
     * @param connection
     * @param tableCount
     * @return
     * @throws SQLException
     */
    private Set<String> createTableBatchNoTransaction(Connection connection, int tableCount) throws SQLException {
        Set<String> tables = new HashSet<>();
        for (int i = 0; i < tableCount; i++) {
            String table = UUID.randomUUID().toString().replaceAll("-", "").substring(5, 12);
            tables.add(table);
            String sql = ddl.replace("${table}", table);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
        return tables;
    }


    /**
     * 开启事物创建多张表
     * @param connection
     * @param tableCount
     * @return
     * @throws SQLException
     */
    private Set<String> createTableBatchWithTransaction(Connection connection, int tableCount) throws SQLException {
        connection.setAutoCommit(false);
        Set<String> tables = new HashSet<>();
        Statement statement = connection.createStatement();
        for (int i = 0; i < tableCount; i++) {
            String table = UUID.randomUUID().toString().replaceAll("-", "").substring(5, 12);
            tables.add(table);
            statement.addBatch(ddl.replace("${table}", table));
        }
        statement.executeBatch();
        connection.commit();
        return tables;
    }

    /**
     * 以事物方式插入表
     * @param connection
     * @param tables
     * @throws SQLException
     */
    private void insertTable(Connection connection, Set<String> tables) throws SQLException {
        connection.setAutoCommit(false);
        for (String table : tables) {
            String add = addSql.replace("${table}", table);
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            for (int i = 0; i < 10; i++) {
                preparedStatement.addBatch();

            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
        }
        connection.commit();
    }


    /**
     * 非事物插入表
     * @param connection
     * @param tables
     * @throws SQLException
     */
    private void insertTableNoTransaction(Connection connection, Set<String> tables) throws SQLException {
        for (String table : tables) {
            String add = addSql.replace("${table}", table);
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            for (int i = 0; i < 10; i++) {
                preparedStatement.executeUpdate();

            }
        }
    }

}



