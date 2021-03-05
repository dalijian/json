package com.lijian.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.InputStream;

public class JdbcUtils {
 
    private JDBCClient dbClient;
    private static JsonObject config ;
 
    static {
        byte[] buff = new byte[102400];
        try {
            // 读取配置文件
            InputStream ins = new FileInputStream("db.json");
            int i = IOUtils.read(ins, buff);
            config = new JsonObject(new String(buff, 0, i));
        } catch (Exception e) {
            System.out.println("读取配置文件失败");
        }
    }
 
    public JdbcUtils(Vertx vertx, String dsName) {
        JsonObject dbConfig = config.getJsonObject(dsName);
        if(dbConfig == null) {
            throw new RuntimeException("没有找到指定的数据源");
        }
        dbClient = JDBCClient.createShared(vertx, dbConfig);
    }
 
    public JdbcUtils(Vertx vertx) {
        this(vertx, "default");
    }
 
    public JDBCClient getDbClient() {
        return dbClient;
    }
 
}
