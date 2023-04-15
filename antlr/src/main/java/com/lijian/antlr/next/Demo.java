package com.lijian.antlr.next;

import com.lijian.antlr.next.auto.PropertyFileLexer;
import com.lijian.antlr.next.auto.PropertyFileParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.UnbufferedCharStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Demo {
    public static void main(String[] args) throws FileNotFoundException {
        PropertyFileLexer lexer = new PropertyFileLexer(new UnbufferedCharStream(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\antlr\\src\\main\\java\\com.lijian.antlr\\CSV.g4")));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        PropertyFilePrinter propertyFilePrinter= new PropertyFilePrinter(tokenStream);
        propertyFilePrinter.file();

    }
}
