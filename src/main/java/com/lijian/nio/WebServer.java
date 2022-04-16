package com.lijian.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class WebServer {
    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
            ssc.configureBlocking(false);

            Selector selector = Selector.open();
            // 注册 channel，并且指定感兴趣的事件是 Accept
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer readBuff = ByteBuffer.allocate(1024);
            ByteBuffer writeBuff = ByteBuffer.allocate(12800);
            writeBuff.put(new StringBuffer("HTTP/1.1 200 OK\n" +
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
            writeBuff.flip();

            while (true) {
                System.out.println(System.currentTimeMillis() + " selector.select() start ");

                int nReady = selector.select();
                System.out.println(System.currentTimeMillis() + " selector.select() end ");
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isAcceptable()) {
                        // 创建新的连接，并且把连接注册到selector上，而且，
                        // 声明这个channel只对读操作感兴趣。
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                    else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuff.clear();
                        socketChannel.read(readBuff);
                        readBuff.flip();
                        System.out.println("received : " + new String(readBuff.array()));
                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                    else if (key.isWritable()) {
                        writeBuff.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        // 向通道中写
                        socketChannel.write(writeBuff);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}