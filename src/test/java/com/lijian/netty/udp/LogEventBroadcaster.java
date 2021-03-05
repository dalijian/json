package com.lijian.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;

public class LogEventBroadcaster {

    private final EventLoopGroup group;

    private final Bootstrap bootstrap;

    private final File file;

    public LogEventBroadcaster(InetSocketAddress address, File file) {

        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        // 引导 该 NioDatagramChannel (无连接的)
        bootstrap.group(group).channel(NioDatagramChannel.class).option(ChannelOption.SO_BROADCAST, true).handler(new LogEventEncoder(address));
        this.file =file;
    }

    public void stop(){
        group.shutdownGracefully();
    }
   public void run() throws InterruptedException, IOException {
        Channel channel = bootstrap.bind(0).sync().channel() ;  //绑定 channel

       long pointer =0;

//       启动 主处理 循环
       while (true) {
           long len = file.length();
           // 若有 必要 将 文件 指针 设置 到 该 文件 的 最后 一个 字节
           if (len < pointer) {
               pointer =len;
           } else if (len > pointer) {
               // content was added
                // nio FileChannel
               RandomAccessFile raf = new RandomAccessFile(file, "r");
               // s设置 当前 文件 指针，以确保 没有 任何 的 旧 日志 被 发送
               raf.seek(pointer);
               String line;
               while ((line=raf.readLine())!=null){
                   //对于 每个 日志 条目 ， 写入 一个 LogEvent 到 Channel
                   channel.writeAndFlush(new LogEvent(null, -1, file.getAbsolutePath(), line));
               }
//               存储 其 在 文件 中的 当前 位置
               pointer =raf.getFilePointer();

               raf.close();

           }
           try {
               Thread.sleep(1000);

           } catch (InterruptedException e) {
               Thread.interrupted();
               break;
           }
       }
   }

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        }
        LogEventBroadcaster broadcaster= new LogEventBroadcaster(
                new InetSocketAddress("255.255.255.255",Integer.parseInt(args[0])),new File(args[1]));

        try{
            broadcaster.run();

        }finally {
            broadcaster.stop();

        }
    }



}
