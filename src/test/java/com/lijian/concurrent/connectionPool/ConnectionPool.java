package com.lijian.concurrent.connectionPool;

import java.sql.Connection;
import java.util.LinkedList;



public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.remove(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            //完全超时
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();

                }
                return pool.removeFirst();
            }else{
                long future = System.currentTimeMillis()+mills;  //超时时间
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {  //判断是否超时
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis(); //拿到时间间隔
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    return  pool.removeFirst();
                }
                return result;
            }
        }
    }
}
