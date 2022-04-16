package com.lijian.nio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class SimpleNIOSocketServer {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        ServerSocket serverSocket = serverSocketChannel.socket();

        serverSocketChannel.configureBlocking(false);
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 10002));
//        serverSocketChannel 不支持 OP_CONNECT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println(selector.keys());

        while (true) {
            try {
                System.out.println(Thread.currentThread().getName()+" start sleep");
                TimeUnit.MILLISECONDS.sleep(500L);
                System.out.println(Thread.currentThread().getName()+" end sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (selector.select() > 0) {
//                System.out.println("selector.select():"+selector.select());
                System.out.println( System.currentTimeMillis()+" selector.select() end");
                Iterator<SelectionKey> selectorIter = selector.selectedKeys().iterator();
                while (selectorIter.hasNext()) {
                    SelectionKey selectorKey = selectorIter.next();
//                selectorKey 是 ServerSocketChannel 与 selector  的 映射
                    if (selectorKey.isAcceptable()) {
                        doAcceptable(selectorKey, selector);
                    }
                    if (!selectorKey.isValid()) {
                        System.out.println("select cancel");
                        selectorKey.cancel();
                    }
//                selectorKey 是 SocketChannel  与 selector 的 映射
                    if (selectorKey.isReadable()) {
                        readByteBuffer(selectorKey,selector);
                    }
//                selectorKey 是 SocketChannel 与 selector 的 映射
                    if (selectorKey.isWritable()) {
                        writeByteBuffer(selectorKey,selector);
                    }
                    selectorIter.remove();


                }
                System.out.println( System.currentTimeMillis()+" selector.select start");
            }
        }
    }


    private static void doAcceptable(SelectionKey key, Selector selector) throws IOException,
            ClosedChannelException {
        System.out.println("is acceptable");
        ServerSocketChannel tempSsc = (ServerSocketChannel) key.channel();
        SocketChannel ss = tempSsc.accept(); // 创建 tcp 连接 通道
        ss.configureBlocking(false);
        ss.register(selector, SelectionKey.OP_READ);
    }

    private static void readByteBuffer(SelectionKey selectorKey, Selector selector) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectorKey.channel();
        socketChannel.register(selector, SelectionKey.OP_WRITE);
        ByteBuffer readBuffer = ByteBuffer.allocate(102400);
        while (socketChannel.read(readBuffer) > 0) {
            System.out.println(new String(readBuffer.array()));
        }
//        socketChannel.close();  不能 关闭 tcp 连接 通道


    }

    private static void writeByteBuffer(SelectionKey selectorKey, Selector selector) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectorKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(102400);
        System.out.println(new String(System.currentTimeMillis() + "_serverSocketChannel will write channel"));
        byteBuffer.put(new StringBuffer("HTTP/1.1 200 OK\n" +
                "Bdpagetype: 1\n" +
                "Bdqid: 0xe4dff0f500034afe\n" +
                "Cache-Control: private\n" +
                "Connection: keep-alive\n" +
                "Content-Type: text/html;charset=utf-8\n" +
                "Date: Sat, 09 Apr 2022 14:14:07 GMT\n" +
                "Expires: Sat, 09 Apr 2022 14:14:01 GMT\n" +
                "P3p: CP=\" OTI DSP COR IVA OUR IND COM \"\n" +
                "P3p: CP=\" OTI DSP COR IVA OUR IND COM \"\n" +
                "Server: BWS/1.1\n" +
               "Set-Cookie: BD_HOME=1; path=/\n" +
                "Set-Cookie: H_PS_PSSID=36070_31253_36020_34813_35914_36167_34584_36142_36120_36075_36265_36125_35864_36225_26350_36094_36061; path=/; domain=.baidu.com\n" +
                "Traceid: 1649513647045583207416492165295511915262\n" +
                "Vary: Accept-Encoding\n" +
                "Vary: Accept-Encoding\n" +
                "X-Frame-Options: sameorigin\n" +
                "X-Ua-Compatible: IE=Edge,chrome=1\n" +
                "Content-Length: 348187\n" +
                "\n" +
                "<!DOCTYPE html><!--STATUS OK-->\n" +
                "\n" +
                "\n" +
                "    <html><head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"><meta content=\"always\" name=\"referrer\"><meta name=\"theme-color\" content=\"#ffffff\"><meta name=\"description\" content=\"全球领先的中文搜索引擎、致力于让网民更便捷地获取信息，找到所求。百度超过千亿的中文网页数据库，可以瞬间找到相关的搜索结果。\">" +
                "*** FIDDLER: RawDisplay truncated at 262144 characters. Right-click to disable truncation. ***").toString().getBytes());
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            try {
                socketChannel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socketChannel.close(); // 写完 要 关闭 tcp channel 不然 selector.select() 返回 不为 0 ，当前 selectionKey 还是 isWritable   会 多次 写入

    }
}
