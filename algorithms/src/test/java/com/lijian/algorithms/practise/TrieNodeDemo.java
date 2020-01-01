//package com.lijian.algorithms.practise;
//
//import com.lijian.algorithms.trie.Trie;
//
//public class TrieNodeDemo {
//
//    private Trie.TrieNode root = new Trie.TrieNode('/');
//
////    插入 trie 树 中 插入 一个 字符串
//    public void insert(char[] text) {
//        Trie.TrieNode p =  root;
//
//        for (int i = 0; i < text.length; i++) {
//
//            int index = text[i] - 'a';
//
//            if (p.children[index] == null) {
//                Trie.TrieNode newNode = new Trie.TrieNode(text[i]);
//                p.children[index] = newNode;
//            }
////            将当前 节点 的 根节点 设置 成 子节点 ， 然后 在 下次 循环 中 就能 继续
////                    在 子节点 添加
//            p = p.children[index];
//        }
//        p.isEndingChar=true;
//    }
//
//    public boolean find(char[] pattern) {
//        Trie.TrieNode p =root;
//        for (int i = 0; i < pattern.length; i++) {
//            int index = pattern[i] - 'a';
//            if (p.children[index] == null) {
//                return false;
//            }
//            p = p.children[index];
//        }
//        if (p.isEndingChar == false) {
//            return false;
//        }
//        return true;
//
//    }
//}
