package com.lijian.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class ServerConnect {
    private static final int BUF_SIZE = 1024;
    private static final int PORT = 8080;
    private static final int TIMEOUT = 3000;

    public static void main(String[] args) {
        selector();
    }

    public static void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel(); //拿到 tcp channel
        SocketChannel sc = ssChannel.accept(); //监听 连接
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
        // 将 channel 注册到 selector 上
        //与Selector 一起使用时，channel必须处于 非阻塞模式下，这意味着不能将FileChannel与Selector一起使用，
        // 因为FileChannel不能切换到非阻塞模式。而套接字通道都可以
//        注意register()方法的第二个参数。这是一个“interest集合”，意思是在通过Selector监听Channel时对什么事件感兴趣。
//        可以监听四种不同类型的事件：OP_CONNECT,OP_ACCEPT,OP_READ,OP_WRITE
    }

    public static void handleRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buf = (ByteBuffer) key.attachment();
        long bytesRead = sc.read(buf);
        while (bytesRead > 0) {
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            System.out.println();
            buf.clear();
            bytesRead = sc.read(buf);
        }
        if (bytesRead == -1) {
            sc.close();
        }
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        writeBuffer.put(("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n".getBytes()));
        writeBuffer.put("helloworld".getBytes());
        writeBuffer.flip();
        while (writeBuffer.hasRemaining()) {
            sc.write(writeBuffer);
        }


    }

    public static void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buf = (ByteBuffer) key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while (buf.hasRemaining()) {
            sc.write(buf);
        }
        buf.compact();
    }

    public static void selector() {
        Selector selector = null;
        ServerSocketChannel ssc = null;
        try {
            selector = Selector.open();  //创建 selector
            ssc = ServerSocketChannel.open(); //打开 serverSocketChanel 开关
            ServerSocket socket = ssc.socket();
            socket.bind(new InetSocketAddress(PORT));
            ssc.configureBlocking(false);
            ssc.register(selector, SelectionKey.OP_ACCEPT); //关注 接受就绪 事件
            while (true) {
                if (selector.select(TIMEOUT) == 0) {
                    //select()阻塞到至少有一个通道在你注册的事件上就绪了。
                    // select(long timeout)和select()一样，除了最长会阻塞timeout毫秒(参数)。
                    //selectNow()不会阻塞，不管什么通道就绪都立刻返回（译者注：此方法执行非阻塞的选择操作。如果自从前一次选择操作后，没有通道变成可选择的，则此方法直接返回零。）。
                    System.out.println("==");       //select()方法返回的int值表示有多少通道已经就绪。亦即，自上次调用select()方法后有多少通道变成就绪状态。如果调用select()方法，因为有一个通道变成就绪状态，返回了1，
                    // 若再次调用select()方法，如果另一个通道就绪了，它会再次返回1。如果对第一个就绪的channel没有做任何操作，现在就有两个就绪的通道，但在每次select()方法调用之间，只有一个通道就绪了。
                    continue;
                }

//               selectionKey 键表示了一个特定的通道对象和一个特定的选择器对象之间的注册关系
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator(); // 然后可以通过调用selector的selectedKeys()方法，访问“已选择键集（selected key set）”中的就绪通道
                while (iter.hasNext()) {
                    SelectionKey key = iter.next(); //可以用像检测interest集合那样的方法，来检测channel中什么事件或操作已经就绪

                    if (!key.isValid()) {
                        key.cancel();

                        iter.remove();
                        continue;
                    }
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    }
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    if (key.isWritable() && key.isValid()) {
                        handleWrite(key);
                    }
                    if (key.isConnectable()) {
                        System.out.println("isConnectable = true");
                    }
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (selector != null) {
                    selector.close();
                }
                if (ssc != null) {
                    ssc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void client() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
            if (socketChannel.finishConnect()) {
                int i = 0;
                while (true) {
                    TimeUnit.SECONDS.sleep(1);
                    String info = "I'm " + i++ + "-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}