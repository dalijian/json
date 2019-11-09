package com.lijian.concurrent.design;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteTest {



//    路由信息
    public final class Router{


        private final String ip;
        private final Integer port;

        private final String iface;

        public Router(String ip, Integer port, String iface) {
            this.ip = ip;
            this.port = port;
            this.iface = iface;
        }

//重写equals 方法
        public boolean equals(Object object) {
            if (object instanceof Router) {
                Router r = (Router) object;
                return iface.equals(r.iface) &&
                        ip.equals(r.ip) &&
                        port.equals(r.port);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ip, port, iface);
        }
    }

//路由表信息
    public class RouterTable {
//        key 接口名；value 路由集合
        ConcurrentHashMap<String, CopyOnWriteArraySet<Router>>
        rt = new ConcurrentHashMap<>();


        public Set<Router> get(String iface){
            return rt.get(iface);
        }

        public void remove(Router router) {
            Set<Router> set = rt.get(router.iface);
            if (set != null) {
                set.remove(router);

            }
        }
//增加路由
        public void add(Router router){
            Set<Router> set = rt.computeIfAbsent(
                    router.iface, r ->
                            new CopyOnWriteArraySet<>(
                            )
            );
            set.add(router);
        }
    }
}
