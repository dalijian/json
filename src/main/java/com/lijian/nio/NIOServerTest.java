package com.lijian.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServerTest {
    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();

        // configureBlocking 如果不设置非阻塞，register的时候会报异常
        // java.nio.channels.IllegalBlockingModeException
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        int max = 0;
        while (true) {
            int selected = selector.select();
            max=Math.max(max,selected);
            System.out.println("selector.select() max :"+max);
            if (selected > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isAcceptable()) {
                        System.err.println("Acceptable");
                        SocketChannel socketChannel = serverSocketChannel.accept();// 新建 客户端 SocketChannel
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        System.err.println("Readable");
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
//                         while (socketChannel.read(buffer)!=-1){
//                             buffer.flip();
//                             while (buffer.hasRemaining()) {
//                                 System.out.print((char)buffer.get());
//                             }
//                             buffer.clear();
//                         };
                        int flag = socketChannel.read(buffer);
                        System.out.println("socketChannel.read result:" + flag);
                        if (flag == -1) {
                            System.out.println("client 发送 完成 并且 已关闭");

                        }

                        System.out.println("接收来自客户端的数据：" + new String(buffer.array()));
                        selectionKey.interestOps(SelectionKey.OP_WRITE);
                    } else if (selectionKey.isWritable()) {
                        System.err.println("Writable");
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        System.out.println("向客户端发送数据 : " + System.currentTimeMillis());
                        String content = ("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\nhelloworld");
                        ByteBuffer buffer = ByteBuffer.wrap(content.getBytes());
                        channel.write(buffer);

//                        selectionKey.interestOps(SelectionKey.OP_READ);
//                        client 请求 在 一个 connect 中 重复 读取 需要  使用 SelectionKey.OP_READ  因为 client interestOps(SelectionKey.OP_READ)
                        selectionKey.interestOps(SelectionKey.OP_CONNECT);
                        // http 请求 需要 close()
//                        channel.close();
                    }
                }
            }
        }
    }
//    public void write(MessageSession session, ByteBuffer buffer) throws ClosedChannelException {
//        SelectionKey key = session.key();
//        if((key.interestOps() & SelectionKey.OP_WRITE) == 0) {
//
//
//// 注销 OP_WRITE 事件
//            //            key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
//        }
//
//
//        try {
//            writebuf.put(buffer);
//        } catch(Exception e) {
//            System.out.println("want put:"+buffer.remaining()+", left:"+writebuf.remaining());
//            e.printStackTrace();
//        }
//        selector.wakeup();
//
//
//        while(true) {
//        selector.select();
//
//        if(key.isWritable()) {
//            MessageSession session = (MessageSession)key.attachment();
//            //System.out.println("Select a write");
//            synchronized(session) {
//                writebuf.flip();
//                SocketChannel channel = (SocketChannel)key.channel();
//                int count = channel.write(writebuf);
//                //System.out.println("write "+count+" bytes");
//                writebuf.clear();
//                key.interestOps(SelectionKey.OP_READ);
//            }
//        }
//    }
}
