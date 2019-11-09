package com.lijian.clone;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        UnderlinePen underlinePen = new UnderlinePen('&');
        MessageBox messageBox = new MessageBox('*');
        MessageBox messageBox1 = new MessageBox('/');
        manager.register("strong message",underlinePen);


    }
}
