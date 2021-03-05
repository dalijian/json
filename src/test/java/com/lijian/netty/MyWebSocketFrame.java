package com.lijian.netty;

import io.netty.buffer.ByteBuf;

public  class MyWebSocketFrame {

    public enum FrameType{
        BINARY,
        CLOSE,
        PING,
        PONG,
        TEXT,
        CONTINUATION
    }
    private final FrameType type;
    private final ByteBuf data;

    public MyWebSocketFrame(FrameType type, ByteBuf data) {
        this.type = type;
        this.data = data;
    }

    public FrameType getType() {
        return type;
    }

    public ByteBuf getData() {
        return data;
    }
}
