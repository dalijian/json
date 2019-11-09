package com.lijian.xa;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static javax.transaction.xa.XAResource.TMSUCCESS;
//xa分布式事务
public class XaDemo {
    public static void main(String[] args) {

        String connString1 = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false";
        String connString2 = "jdbc:mysql://127.0.0.1:3308/test?useSSL=false";
try {
//    拿到xa资源，mysql 节点
    MysqlXADataSource ds1 = getDateSource(connString1, "root", "645143");
    MysqlXADataSource ds2 = getDateSource(connString2, "root", "645143");
    XAConnection xaConnection1 = ds1.getXAConnection();
    XAResource xaResource1 = xaConnection1.getXAResource();
    Connection connection1 = xaConnection1.getConnection();
    Statement statement1 = connection1.createStatement();


    XAConnection xaConnection2 = ds2.getXAConnection();
    XAResource xaResource2 = xaConnection2.getXAResource();
    Connection connection2 = xaConnection2.getConnection();
    Statement statement2 = connection2.createStatement();


    Xid xid1 = new MyXid(100, new byte[]{0x01}, new byte[]{0x02});

    Xid xid2 = new MyXid(100, new byte[]{0x11}, new byte[]{0x12});

//            开启xa事务
//            执行xa事务
//            结束xa事务
    xaResource1.start(xid1, XAResource.TMNOFLAGS);
    statement1.execute("update account set money =money-1000 where name='lijian'");
    xaResource1.end(xid1, TMSUCCESS);


    xaResource2.start(xid2, XAResource.TMNOFLAGS);
    statement2.execute("update account set money =money+1000 where name='lijian'");

//    如果指定 TMSUCCESS，则将完成部分工作。
    xaResource2.end(xid2, TMSUCCESS);

//    xa准备  即所有的参与者准备执行事务并锁住需要的资源。参与者ready时，向transaction manager报告已准备就绪。
    int ret2 = xaResource2.prepare(xid2);
    int ret1 = xaResource1.prepare(xid1);

//    xa准备完成后同时提交

//    commit 参数 - 如果为 true，则资源管理器应使用单阶段提交协议提交代表 xid 执行的工作
    if (ret1 == XAResource.XA_OK && ret2 == XAResource.XA_OK) {
        xaResource1.commit(xid1, false);
        xaResource2.commit(xid2, false);
    }
} catch (SQLException e) {
    e.printStackTrace();
} catch (XAException e) {
    e.printStackTrace();
}
    }

    public static MysqlXADataSource getDateSource(String connString, String user, String password) {
        MysqlXADataSource dc = new MysqlXADataSource();
        dc.setUrl(connString);
        dc.setUser(user);
        dc.setPassword(password);
        return dc;
    }


}
