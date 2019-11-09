package com.lijian.zip;


import java.io.*;
import java.util.zip.*;
public class ReadEmployee {
   public static void main(String argv[]) throws 
     Exception{
      //deserialize objects sarah and sam
      FileInputStream fis = new FileInputStream("db");
      GZIPInputStream gs = new GZIPInputStream(fis);
      ObjectInputStream ois = new ObjectInputStream(gs);
      Employee sarah = (Employee) ois.readObject();
      Employee sam = (Employee) ois.readObject();
      //print the records after reconstruction of state
      sarah.print();
      sam.print();
      ois.close();
      fis.close();
   }
}