package com.lijian.log;

import java.io.*;
import java.sql.Timestamp;

public class FileLog {


    private static final String  BASE_FILE = "E:/tomcat";
    private String date="";
    private String directory="log";
    private String prefix="my-";
    private String suffix=".log";
    private PrintWriter writer;
    private boolean timestamp=false;

    public void log(String msg) {

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String tsString = ts.toString().substring(0, 19);
        String tsDate = tsString.substring(0, 19).replace(" ","_").replace(":","_");  //2019-02-26


        if (!date.equals(tsDate)) {
            close();
            date = tsDate;
            open();
        }
        if (writer != null) {
            if (timestamp) {
                writer.println(tsString+" "+msg);

            }else{
                writer.println(msg);
            }
        }

    }

    private void close() {
        if (writer == null) {
            return;
        }
        writer.flush();
        writer.close();
        writer=null;
        date = "";
    }
    private void open() {
        File file = new File(directory);
        if (!file.isAbsolute()) {

            file = new File(BASE_FILE, directory);
        }
        file.mkdirs();

        String pathname = file.getAbsolutePath()+File.separator+ prefix +date+suffix;
        try {
            writer = new PrintWriter(new FileWriter(pathname, true), true);
        } catch (IOException e) {
            writer=null;
            e.printStackTrace();
        }

    }

}
