package com.lijian.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class ClusterServer {

    public static void main(String[] args) {
        // 注意要添加对应的集群管理器依赖，详情见集群管理器章节
        VertxOptions options = new VertxOptions();
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                Vertx vertx = res.result(); // 获取到了集群模式下的 Vertx 对象
                // 做一些其他的事情
            } else {
                // 获取失败，可能是集群管理器出现了问题
            }
        });
    }
}
