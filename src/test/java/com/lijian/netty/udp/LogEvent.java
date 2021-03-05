package com.lijian.netty.udp;

import java.net.InetSocketAddress;

public class LogEvent {

    public static final byte SEPARATOR = (byte) ':';

    private final InetSocketAddress source; //发送 LogEvent 的源 的InetSocketAddress
    private final String logfile;   // LogEvent 日志 文件 名称
    private final String msg;     // 接受 消息
    private final long received; // 接受 时间

    // 用于 传出消息的构造 函数
    public LogEvent(String logfile, String msg) {
        this(null, -1, logfile, msg);
    }

    // 用于 传入消息的构造函数
    public LogEvent(InetSocketAddress source,long received, String logfile, String msg) {
        this.source = source;
        this.logfile = logfile;
        this.msg = msg;
        this.received = received;
    }

    public static byte getSEPARATOR() {
        return SEPARATOR;
    }

    public InetSocketAddress getSource() {
        return source;
    }

    public String getLogfile() {
        return logfile;
    }

    public String getMsg() {
        return msg;
    }

    public long getReceived() {
        return received;
    }
}
