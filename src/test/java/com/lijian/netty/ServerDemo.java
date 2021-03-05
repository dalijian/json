package com.lijian.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

public class ServerDemo {
//    1. 自定义  channelHandler 实现 业务 逻辑
//    2. 创建 一个 ServerBootstrap 的实例 以引导和绑定服务器
//    3. 创建 并分配 一个 NioEventLoopGroup 实例 以 进行 事件的 处理，如接受 新 连接 以及 读写数据
//    4. 指定服务器 绑定的本地 的 InetSocketAddress
//    5. 使用一个EchoServerHandler的实例初始化每一个新的Channel
//    6. 调用 ServerBootstrap.bind()方法以绑定服务器
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
//        serverBootstrap wrapper of ServerChannel
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(eventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(10001)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                System.out.println("server received:" + byteBuf.toString(CharsetUtil.UTF_8));
                                ctx.write(byteBuf);
                            }

                            @Override
                            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                                        .addListener(ChannelFutureListener.CLOSE);

                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                cause.printStackTrace();
                                ctx.close();
                            }
                        });
                    }
                });

        try {
            ChannelFuture future = serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully().sync();
        }


    }
}
