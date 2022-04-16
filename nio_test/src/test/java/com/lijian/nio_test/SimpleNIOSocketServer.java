package com.lijian.nio_test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SimpleNIOSocketServer {

    public static void main(String[] args) throws IOException {
        Selector selector =  Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        ServerSocket serverSocket = serverSocketChannel.socket();

        serverSocketChannel.configureBlocking(false);
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 10002));
//        serverSocketChannel 不支持 OP_CONNECT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select()>0) {
            Iterator<SelectionKey> selectorIter = selector.selectedKeys().iterator();
            while (selectorIter.hasNext()) {
                SelectionKey selectorKey = selectorIter.next();
//                selectorKey 是 ServerSocketChannel 与 selector  的 映射
                if (selectorKey.isAcceptable()) {
                    doAcceptable(selectorKey,selector);
                }
                if (!selectorKey.isValid()) {
                    System.out.println("select cancel");
                    selectorKey.cancel();
                }
//                selectorKey 是 SocketChannel  与 selector 的 映射
                if (selectorKey.isReadable()) {
                    readByteBuffer(selectorKey);
                }
//                selectorKey 是 SocketChannel 与 selector 的 映射
                if (selectorKey.isWritable()) {
                    writeByteBuffer(selectorKey);
                }
                selectorIter.remove();


            }
        }

    }


    private static void doAcceptable(SelectionKey key, Selector selector) throws IOException,
            ClosedChannelException {
        System.out.println("is acceptable");
        ServerSocketChannel tempSsc = (ServerSocketChannel) key.channel();
        SocketChannel ss = tempSsc.accept();
        ss.configureBlocking(false);
        ss.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }
    private static void readByteBuffer(SelectionKey selectorKey) throws IOException {
      SocketChannel socketChannel = (SocketChannel) selectorKey.channel();

        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        while (socketChannel.read(readBuffer) > 0) {
            System.out.println(new String(readBuffer.array()));
        };


    }

    private static void writeByteBuffer(SelectionKey selectorKey) throws IOException {
       SocketChannel socketChannel = (SocketChannel) selectorKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(new String(System.currentTimeMillis() + "_serverSocketChannel will write channel").getBytes());
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            try {
                socketChannel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
