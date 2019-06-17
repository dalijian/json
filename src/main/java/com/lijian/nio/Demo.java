package com.lijian.nio;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.file.FileSystem;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardOpenOption.CREATE;

public class Demo {

    private static FileChannel channel;
    private static FileChannel newChannel;

    @BeforeAll
    public static void init() {
        try {
            RandomAccessFile fromFile = new RandomAccessFile("result.json", "rw");
            RandomAccessFile newFile = new RandomAccessFile("newFile.txt", "rw");
            channel = fromFile.getChannel();
            newChannel = newFile.getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void write() {
        try {
            RandomAccessFile file = new RandomAccessFile("Alice.txt", "rw");
//            FileChannel fileChannel = FileChannel.open(Paths.get("alice.json"), CREATE);
            FileChannel fileChannel = file.getChannel();
            String content = "gather";
            ByteBuffer byteBuffer = ByteBuffer.allocate(1);
//            byteBuffer.put("hello Alice".getBytes());
            String newData = "New String to write to file..." + System.currentTimeMillis();

            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());

            buf.flip();

            while(buf.hasRemaining()) {
                fileChannel.write(buf);
            }
//            byteBuffer.flip();
//            fileChannel.write(byteBuffer);
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Test
    public void readTest(){
        try {


            RandomAccessFile file = new RandomAccessFile("result.json", "rw");
            FileChannel fileChannel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            fileChannel.read(byteBuffer);
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.print((char) byteBuffer.get());
                }

//                System.out.println(new String(byteBuffer.array()));
                byteBuffer.clear();
            }
//            System.out.println(new String (byteBuffer.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test1() {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("C:\\Users\\lijian\\IdeaProjects\\json\\result.json", "rw");
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48); // 分配buffer

            int bytesRead = 0;//从channel 中 写入 buffer
            while (bytesRead != -1) {
                bytesRead = inChannel.read(buf);//从channel 中 写入 buffer
                System.out.println("Read " + bytesRead);
                buf.flip();                                 //将buffer 从 写模式 切换到 读模式，会将 position设置成0，并将limit设置成 之前的position值

/*
                Writes a sequence of bytes to this channel from the given buffer.
*/
//                int bytesWritten = inChannel.write(buf);
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());   //中文占 2个char 肯定会乱码
                }

//                buf.clear();
                buf.compact();
                bytesRead = inChannel.read(buf);
                aFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }

    //    分散 将 channel 写入 不同的 buffer 中
    @Test
    public void scatter() {
        try {
            ByteBuffer header = ByteBuffer.allocate(128);
            ByteBuffer body = ByteBuffer.allocate(1024);

            ByteBuffer[] bufferArray = {header, body};

            channel.read(bufferArray);

            header.flip();
            System.out.println(new String(header.array()));
            body.flip();
            System.out.println("**************");
            System.out.println(new String(body.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //聚合 将 不同的 buffer 写入 channel 中
    @Test
    public void gather() {
        try {
            ByteBuffer header = ByteBuffer.allocate(128);
            ByteBuffer body = ByteBuffer.allocate(1024);

            header.put("hello world".getBytes());
            body.put("\nAlice".getBytes());
            header.flip();
            body.flip();
//write data into buffers

            ByteBuffer[] bufferArray = {header, body};

            newChannel.write(bufferArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    通道 之间 数据 传递 将 fromFile 流 覆盖 toChannel 流
    @Test
    public void transferFrom() {

        try {
            RandomAccessFile fromFile = new RandomAccessFile("C:\\Users\\lijian\\IdeaProjects\\json\\fromFile.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
            FileChannel toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();


//            toChannel.transferFrom(position, count, fromChannel);
            /*Transfers bytes into this channel's file from the given readable byte
             * channel.*/
            toChannel.transferFrom(fromChannel, position, count);
            ByteBuffer bf = ByteBuffer.allocate(24);
            int result = toChannel.read(bf);

            System.out.println("result:" + result);
            while (result != -1) {
                bf.flip();
                while (bf.hasRemaining()) {
                    System.out.println("**********");
                    System.out.println(new String(bf.array())); //array() 时 position  没减 所以会一直读下去
                }
                bf.clear();
                result = toChannel.read(bf);
            }
            fromFile.close();
            toFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void transferTo() {
        try {
            RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
            FileChannel toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();

            //
            fromChannel.transferTo(position, count, toChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*耗时4350ms*/
    @Test
    public void ByteBufferReadTest() {
        RandomAccessFile aFile = null;
        FileChannel fc = null;
        try {
            aFile = new RandomAccessFile("E:/Neco.z.Alenky.1988.爱丽丝.720p.Chi.BD-MP4.mp4", "rw");
            fc = aFile.getChannel();
            long timeBegin = System.currentTimeMillis();
            ByteBuffer buff = ByteBuffer.allocate((int) aFile.length());
            buff.clear();
            fc.read(buff);
            //System.out.println((char)buff.get((int)(aFile.length()/2-1)));
            //System.out.println((char)buff.get((int)(aFile.length()/2)));
            //System.out.println((char)buff.get((int)(aFile.length()/2)+1));
            long timeEnd = System.currentTimeMillis();
            System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
                if (fc != null) {
                    fc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*耗时6mx*/
    @Test
    public void MappedByteBufferReadTest() {
        RandomAccessFile aFile = null;
        FileChannel fc = null;
        try {
            aFile = new RandomAccessFile("E:/Neco.z.Alenky.1988.爱丽丝.720p.Chi.BD-MP4.mp4", "rw");
            fc = aFile.getChannel();
            long timeBegin = System.currentTimeMillis();
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, aFile.length());
            mbb.force();
            // System.out.println((char)mbb.get((int)(aFile.length()/2-1)));
            // System.out.println((char)mbb.get((int)(aFile.length()/2)));
            //System.out.println((char)mbb.get((int)(aFile.length()/2)+1));
            long timeEnd = System.currentTimeMillis();
            System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
                if (fc != null) {
                    fc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void PipeTest() {
        Pipe pipe = null;
        ExecutorService exec = Executors.newFixedThreadPool(2);
        try {
            pipe = Pipe.open();
            final Pipe pipeTemp = pipe;
            exec.submit(() -> {
                Pipe.SinkChannel sinkChannel = pipeTemp.sink();//向通道中写数据
                while (true) {
                    TimeUnit.SECONDS.sleep(1);
                    String newData = "Pipe Test At Time " + System.currentTimeMillis();
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    buf.clear(); //设置position 为 0， limit 为 capacity
                    buf.put(newData.getBytes());
                    buf.flip();
                    while (buf.hasRemaining()) {
                        System.out.println(buf);
                        sinkChannel.write(buf);
                    }
                }
            });
            exec.submit(() -> {
                Pipe.SourceChannel sourceChannel = pipeTemp.source();//向通道中读数据
                while (true) {
                    TimeUnit.SECONDS.sleep(1);
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    buf.clear();
                    int bytesRead = sourceChannel.read(buf);
                    System.out.println("bytesRead=" + bytesRead);
                    while (bytesRead > 0) {
                        buf.flip();
                        byte b[] = new byte[bytesRead];
                        int i = 0;
                        while (buf.hasRemaining()) {
                            b[i] = buf.get();
                            System.out.printf("%X", b[i]);
                            i++;
                        }
                        String s = new String(b);
                        System.out.println("=================||" + s);
                        bytesRead = sourceChannel.read(buf);
                    }
                }
            });
            Thread.sleep(20000L);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
        }
    }


    public void UDPreveive() {
        DatagramChannel channel = null;
        try {
            Selector selector = Selector.open();
            channel = DatagramChannel.open();
            channel.socket().bind(new InetSocketAddress("127.0.0.1", 8888));
            ByteBuffer buf = ByteBuffer.allocate(1024);
            buf.clear();

            channel.receive(buf);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.println("********");
                System.out.print((char) buf.get());
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void UDPsend() {
        DatagramChannel channel = null;
        try {
            channel = DatagramChannel.open();
            String info = "I'm the Sender!";
            ByteBuffer buf = ByteBuffer.allocate(1024);
            buf.clear();
            buf.put(info.getBytes());
            buf.flip();
            int bytesSent = channel.send(buf, new InetSocketAddress("127.0.0.1", 8888));
            System.out.println(bytesSent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void UDPTest() {
        UDPreveive();
//        UDPsend();

    }


    @Test
    public void selectorTest() {


//        int interestSet = selectionKey.interestOps();
//
//        boolean isInterestedInAccept  = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
//        boolean isInterestedInConnect = interestSet & SelectionKey.OP_CONNECT;
//        boolean isInterestedInRead    = interestSet & SelectionKey.OP_READ;
//        boolean isInterestedInWrite   = interestSet & SelectionKey.OP_WRITE;
    }

}
