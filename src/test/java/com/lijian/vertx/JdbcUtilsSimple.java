package com.lijian.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

public class JdbcUtilsSimple {
 
        // 用于操作数据库的客户端
	private JDBCClient dbClient;
 
	public JdbcUtilsSimple(Vertx vertx) {
    
                // 构造数据库的连接信息
		JsonObject dbConfig = new JsonObject();
		dbConfig.put("url", "jdbc:mysql://192.168.40.66:3306/test");
		dbConfig.put("driver_class", "com.mysql.jdbc.Driver");
		dbConfig.put("user", "xxxx");
		dbConfig.put("password", "xxxx");
 
                // 创建客户端
		dbClient = JDBCClient.createShared(vertx, dbConfig);
	}
 
        // 提供一个公共方法来获取客户端
	public JDBCClient getDbClient() {
		return dbClient;
	}
 
}
