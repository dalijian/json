package com.lijian.vfs;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileFilter;
import org.apache.commons.vfs2.FileFilterSelector;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileSystem;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.events.DeleteEvent;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;
import org.apache.commons.vfs2.provider.ftp.FtpFileObject;
import org.apache.commons.vfs2.provider.ftp.FtpFileProvider;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.zip.ZipFileObject;
import org.apache.commons.vfs2.provider.zip.ZipFileProvider;
import org.apache.commons.vfs2.provider.zip.ZipFileSystem;
import org.apache.commons.vfs2.provider.zip.ZipFileSystemConfigBuilder;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import static org.apache.commons.vfs2.FileType.FOLDER;


public class VFSTest {

    @Test
    public void zip() throws IOException {

        FileSystemManager fsManager = org.apache.commons.vfs2.VFS.getManager();

        FileObject fileBase = fsManager.resolveFile("zip://C:/users/lijian/desktop/e-record.zip");

        recursion(fileBase);

    }

    private void recursion(FileObject fileBase) throws IOException {
        if (fileBase.getType() == FileType.FILE) {
            System.out.println(new String(fileBase.getContent().getByteArray()));
            return;
        }
        if (fileBase.getType() == FileType.FOLDER) {

            for (int i = 0; i < fileBase.getChildren().length; i++) {
                recursion(fileBase.getChildren()[i]);
            }
        }
    }


    @Test
    public void ftpTest() throws IOException {
        FileSystemManager manager = VFS.getManager();

//        FileObject file = manager.resolveFile("ftp://ftpuser:password@106.13.145.160:21/home/ftpuser/123/00010132b.jpg");
        FileSystemOptions options = new FileSystemOptions();
        FtpFileSystemConfigBuilder.getInstance().setPassiveMode(options, true);

        // url 总 ftp 的 根路径 是 相对 与 登录用户 权限 ， 例如 lijian 用户 的根路径 是 /home/lijian/
        FileObject ftpFile = manager.resolveFile("ftp://lijian:lijian520@106.13.145.160:21/test/friend.json", options);

//                FileObject ftpFile = manager.resolveFile("ftp://lijian:lijian520@106.13.145.160:21/", options);

        recursion(ftpFile);

    }


    @Test
    public void ftpADDTest() throws IOException {
        FileSystemManager manager = VFS.getManager();

//        FileObject file = manager.resolveFile("ftp://ftpuser:password@106.13.145.160:21/home/ftpuser/123/00010132b.jpg");
        FileSystemOptions options = new FileSystemOptions();
        FtpFileSystemConfigBuilder.getInstance().setPassiveMode(options, true);

        // url 总 ftp 的 根路径 是 相对 与 登录用户 权限 ， 例如 lijian 用户 的根路径 是 /home/lijian/
        // 503 异常 无法 创建 文件 是 因为 登录的 ftp 用户 没有 对应 文件夹 的 权限
        FileObject ftpFile = manager.resolveFile("ftp://lijian:lijian520@106.13.145.160:21/AHHF_QHHFY_2018040_lijian_syn_30/test/test/test/vfs.json", options);


        OutputStream outputStream = ftpFile.getContent().getOutputStream();

        outputStream.write("apche-commons-vfs".getBytes());

        outputStream.close();
        ftpFile.createFile();

    }


    // 监听 路径
    @Test
    public void test() {
        try {
            FileSystemManager fsm = VFS.getManager();
            FileObject file = fsm.resolveFile(new File("D://vfs").getAbsolutePath());
            DefaultFileMonitor fileMonitor = new DefaultFileMonitor(new FileListener() {
                @Override
                public void fileCreated(FileChangeEvent event) throws Exception {
                    resolveEvent("Created", event);
                }

                @Override
                public void fileDeleted(FileChangeEvent event) throws Exception {
                    resolveEvent("Deleted", event);
                }

                @Override
                public void fileChanged(FileChangeEvent event) throws Exception {
                    resolveEvent("Changed", event);
                }

                private void resolveEvent(String type, FileChangeEvent event) {
                    FileObject fileObject = event.getFile();
                    FileName fileName = fileObject.getName();
                    System.out.println(type + ": " + fileName.toString());
                }
            });
            fileMonitor.addFile(file);
            fileMonitor.start();
            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    // 写入文件
    public void createFile() throws IOException {
        FileSystemManager manager = VFS.getManager();
        FileObject localFile = manager.resolveFile("file://C:/users/lijian/desktop//test/test/test/test.json");

//        If the file does not exist, this method creates it,
//          and the parent folder, if necessary. If the file does exist,
//     * it is replaced with whatever is written to the output stream.
        OutputStream outputStream = localFile.getContent().getOutputStream();
        outputStream.write("hello,world".getBytes());
        outputStream.close();
        localFile.createFile();
    }


    // vfs  不支持 生成 zip 包
    @Test
    public void createZIP() throws IOException {

        FileSystemManager manager = VFS.getManager();
        FileSystemOptions options = new FileSystemOptions();
        ZipFileSystemConfigBuilder.getInstance().setCharset(options, Charset.forName("gbk"));
        FileObject file = manager.resolveFile("zip://C:/users/lijian/desktop/temp.zip", options);
        OutputStream outputStream = file.getContent().getOutputStream();
        outputStream.write("zip".getBytes());

        outputStream.close();
        file.createFile();
    }

    // 文件 过滤
    @Test
    public void filterTest() throws FileSystemException {

        FileFilter ff = fileInfo -> {
            FileObject fo = fileInfo.getFile();
            return fo.getName().getBaseName().startsWith("ABC-");
        };
        VFS.getManager().resolveFile("/base/folder").findFiles(new FileFilterSelector(ff));
    }

    @Test
    public void selectorTest() throws FileSystemException {
        FileSelector ff = new FileSelector()
        {
            public boolean includeFile(FileSelectInfo fileInfo) throws Exception
            {
                FileObject fo = fileInfo.getFile();
                return fo.getName().getBaseName().startsWith("ABC-");
            }
            public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception
            {
                return true;
            }
        };
        VFS.getManager().resolveFile("/base/folder").findFiles(ff);
    }
}
