package com.lijian.netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;


public class WebSocketConvertHandler extends MessageToMessageCodec<WebSocketFrame,WebSocketConvertHandler.MyWebSocketFrame> {

    //编码
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MyWebSocketFrame myWebSocketFrame, List<Object> list) throws Exception {
        ByteBuf payload = myWebSocketFrame.getData().duplicate().retain();

        switch (myWebSocketFrame.getType()) {
            case BINARY:
                list.add(new BinaryWebSocketFrame(payload));
                break;
            case TEXT:
                list.add(new TextWebSocketFrame(payload));
                break;
            case CLOSE:
                list.add(new CloseWebSocketFrame(true, 0, payload));
                break;
            case CONTINUATION:
                list.add(new ContinuationWebSocketFrame(payload));
                break;
            case PING:
                list.add(new PingWebSocketFrame(payload));
                break;
            case PONG:
                list.add(new PongWebSocketFrame(payload));
                break;
                default:
                    throw new IllegalAccessException("unsupported websocket msg " + myWebSocketFrame);
        }
    }
    // 解码
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        ByteBuf payload = webSocketFrame.content().duplicate().retain();

        if (webSocketFrame instanceof BinaryWebSocketFrame) {
            list.add(new MyWebSocketFrame(com.lijian.netty.MyWebSocketFrame.FrameType.BINARY, payload));

        }
    }

    public static final   class MyWebSocketFrame {

        public enum FrameType{
            BINARY,
            CLOSE,
            PING,
            PONG,
            TEXT,
            CONTINUATION
        }
        private final com.lijian.netty.MyWebSocketFrame.FrameType type;
        private final ByteBuf data;

        public MyWebSocketFrame(com.lijian.netty.MyWebSocketFrame.FrameType type, ByteBuf data) {
            this.type = type;
            this.data = data;
        }

        public com.lijian.netty.MyWebSocketFrame.FrameType getType() {
            return type;
        }

        public ByteBuf getData() {
            return data;
        }
    }
}
