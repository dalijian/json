package com.lijian.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;

import java.util.List;

public class JdbcTestVerticle extends AbstractVerticle {
 
	@Override
	public void start() throws Exception {
 
		// 获取到数据库连接的客户端
		JDBCClient jdbcClient = new JdbcUtilsSimple(vertx).getDbClient();
		String sql = "select * from t_user where age > ?";
		// 构造参数
		JsonArray params = new JsonArray().add(18);
		// 执行查询
		jdbcClient.queryWithParams(sql, params, qryRes->{
			if(qryRes.succeeded()) {
				// 获取到查询的结果，Vert.x对ResultSet进行了封装
				ResultSet resultSet = qryRes.result();
				// 把ResultSet转为List<JsonObject>形式
				List<JsonObject> rows = resultSet.getRows();
				// 输出结果
				System.out.println(rows);
			} else {
				System.out.println("查询数据库出错！");
			}
		});
	
	}
	
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new JdbcTestVerticle());
	}
	
}
