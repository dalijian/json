package com.lijian.algorithms.trie;

import java.util.LinkedList;
import java.util.Queue;

public class AcNode {
    public char data;
    public AcNode[] children = new AcNode[26]; // 字符集只包含a~z这26个字符
    public boolean isEndingChar = false; // 结尾字符为true
    public int length = -1; // 当isEndingChar=true时，记录模式串长度

    public AcNode fail; // 失败指针

    public AcNode(char data) {
        this.data = data;
    }


}