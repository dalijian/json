package com.lijian.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClientTest {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);

        SelectionKey key = socketChannel.register(selector, SelectionKey.OP_CONNECT|SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9000));
        key.attach(socketChannel);
        while (true) {
            int select = selector.select();
            System.out.println("selector.select():" + select);
            if (select > 0) {

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {

                    SelectionKey selectionKey = iterator.next();

                    if (selectionKey.isConnectable()) {
                        System.err.println("Connectable");
//                        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                        SocketChannel clientChannel = (SocketChannel) selectionKey.attachment();
                        boolean flag = clientChannel.finishConnect();

//                        selectionKey.interestOps(SelectionKey.OP_WRITE);

                    } else if (selectionKey.isReadable()) {
                        System.out.println("Readable");
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(128);
                        channel.read(buffer);
//                        selectionKey.interestOps(SelectionKey.OP_WRITE);
                        System.out.println("收到服务端数据" + new String(buffer.array()));
                    } else if (selectionKey.isWritable()) {
                        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                        String str = "qiwoo mobile";
                        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
                        clientChannel.write(buffer);
//                        selectionKey.interestOps(SelectionKey.OP_READ);
                        System.out.println("向服务端发送数据" + new String(buffer.array()));
                    }

                    iterator.remove();
                }
            }
        }
    }
}
