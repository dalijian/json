package com.lijian.serializable;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.Map;

public class ObjectInputStreamTest {

    @Test
    public void ObjectWriteTest()  {

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("ObjectOutputStream_map.txt"));

            os.writeObject(ImmutableMap.builder().put("key".getBytes(Charset.forName("UTF-8")),
                    "value".getBytes(Charset.forName("UTF-8"))
            ).put("key_1".getBytes(Charset.forName("UTF-8")),
                    "value_1".getBytes(Charset.forName("UTF-8"))).build());
           
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void ObjectReadTest()  {

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("ObjectOutputStream_map.txt"));
            Object obj = is.readObject();
            System.out.println(obj.getClass());
            Map<byte[], byte[]> raw = (Map<byte[], byte[]>) obj;
            raw.forEach((x,y)-> {
                System.out.println(new String(x));
                System.out.println(new String(y));

            });
            System.out.println(raw);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
