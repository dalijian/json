package com.lijian.image;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
//    thumbnailator是一个开源的图片工具包，提供诸如图片缩放、裁剪、旋转、加水印等一些列功能
public class Demo {

    @Test
    public void create() throws IOException {

        Thumbnails.of(new File("tiger.jpg"))
                .size(1606, 826)
                .keepAspectRatio(false)
                .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
    }


    @Test
    public void importStream() throws IOException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        Thumbnails.of(new File("tiger.jpg"))
                .size(1606, 829)
                .outputFormat("jpg")
                .toOutputStream(os);

    }


}
