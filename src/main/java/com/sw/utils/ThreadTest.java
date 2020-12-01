package com.sw.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 多线程创建表，实际不需要这样做
 */
public class ThreadTest{
    private CountDownLatch countDownLatch;

    public ThreadTest(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void handList(List<PreparedStatement> preparedStatementList) throws InterruptedException {
        int total = preparedStatementList.size();
        int threadNum = (int) countDownLatch.getCount();
        int step = total % threadNum == 0 ? total / threadNum : total / threadNum + 1;
        for (int i = 0; i < threadNum; i++) {
            int start = i * step;
            int end = start + step > total ? total : start + step;
            List<PreparedStatement> statements = preparedStatementList.subList(start, end);
            new HandThread(statements).start();
        }


    }

    class HandThread extends Thread{
        private List<PreparedStatement> preparedStatementList;

        HandThread(List<PreparedStatement> statements) {
            this.preparedStatementList = statements;
        }

        @Override
        public void run() {
            for (PreparedStatement preparedStatement : preparedStatementList) {
                try {
                    preparedStatement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName()+"执行条数："+preparedStatementList.size());
        }
    }

}
