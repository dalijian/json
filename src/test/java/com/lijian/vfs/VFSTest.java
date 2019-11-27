package com.lijian.vfs;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.junit.Test;

public class Demo {

    @Test
    public void test() throws FileSystemException {

        FileSystemManager fsManager = VFS.getManager();
        FileObject jarFile = fsManager.resolveFile( "jar:lib/aJarFile.jar" );
    }
}
