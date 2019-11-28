package com.lijian.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SimpleNIOSocketClient {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 10002));
        while (!socketChannel.finishConnect()) {
            System.out.println("check finish connection");
        }
        while (true) {
            while (selector.select() > 0) {
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey selectorKey = iter.next();
                    SocketChannel socketChannelz = (SocketChannel) selectorKey.channel();
                    if (selectorKey.isReadable()) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        while (socketChannelz.read(byteBuffer) > 0) {
                            System.out.println(new String(byteBuffer.array()));
                            System.out.println();
                            byteBuffer.clear();
                        }
                        if (selectorKey.isWritable()) {
                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            writeBuffer.put("I receive message".getBytes());
                            writeBuffer.flip();
                            while (writeBuffer.hasRemaining()) {
                                socketChannelz.write(writeBuffer);
                            }
                        }

                    }
                }
            }
        }
    }
}
