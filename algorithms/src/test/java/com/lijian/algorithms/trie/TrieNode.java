package com.lijian.algorithms.trie;

//public class TrieNode {
//    char data;
//    //  用来 存储 子树 ， 若 只存储 a 到 z 26 个小写字母， 那么 在 数组 中 下标 为 0 的位置，存储 指向 子节点 a 的 指针
//      // 依次 类推 在 下标 为 25 的 位置 存储 指向 节点 为 z 的 指针 ，如果 子节点 不存在，则 在 对应 下标位置 存储 null
//    TrieNode children[];
//
//}
public class TrieNode {
    public char data;
    public TrieNode[] children = new TrieNode[26];
    public boolean isEndingChar = false;
    public TrieNode(char data) {
        this.data = data;
    }
}