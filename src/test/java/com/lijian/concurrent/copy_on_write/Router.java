package com.lijian.concurrent.copy_on_write;

import java.util.Objects;

public final class Router {
    private final String ip;
    private final Integer port;
    private final String iface;

    public String getIp() {
        return ip;
    }

    public Integer getPort() {
        return port;
    }

    public String getIface() {
        return iface;
    }

    public Router(String ip, Integer port, String iface) {
        this.ip = ip;
        this.port = port;
        this.iface = iface;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Router router = (Router) o;
        return Objects.equals(ip, router.ip) &&
                Objects.equals(port, router.port) &&
                Objects.equals(iface, router.iface);
    }


    @Override
    public int hashCode() {
        return Objects.hash(ip, port, iface);
    }
}
