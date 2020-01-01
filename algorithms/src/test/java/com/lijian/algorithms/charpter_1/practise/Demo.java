package com.lijian.algorithms.charpter_1.practise;

import org.junit.Test;

import java.util.stream.Stream;

public class Demo {


    @Test
    public void question_1(){


        char[] array = "EASYQUESTION".toCharArray();

      array= selectSort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    private char[] selectSort(char[] array) {

        for (int i = 0; i < array.length; i++) {
            // 拿到 最小 值 所在 的 下标   int 引用的 是 值 ，不是 地址 ，如果 不显示 复制 ，即使 j 发生 改变 min 值 也 没 变
           int min =i;
            for (int j = i; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min =j;
                }
            }
            swap(array,i,min);

//            swap(array[i], array[min]);
        }

        return array;
    }
        // 这样交换 是 没用的
    private void swap(char c, char c1) {
        char temp = c;
        c=c1;
        c1=temp;
    }

    private void swap(char[] array, int i, int min) {

        char temp = array[i];
        array[i] = array[min];
        array[min]=temp;
    }

    @Test
    public void quest_2(){

//         选择 排序 最多 交换  n 次, 平均 交换 1 次

    }


    @Test
    public void question_4(){

        char[] array = "EASYQUESTION".toCharArray();

        array = insertSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
        // 插入 排序  索引 左边 所有 元素 都是 有序的 ，  插入时 ， 左边 有序 元素 向 右 移动 一位
    private char[] insertSort(char[] array) {


        for (int i = 0; i < array.length; i++) {
            //   内层 循环 从 i 开始  向 左 比较 ， 将 i 左边 排成 有序
            for (int j = i; j >0 ; j--) {
                if (array[j-1] > array[j]) {
//                  交换 j-1, 与 j
                    swap(array,j-1,j);


                }
            }
        }
        return array;
    }


    @Test
    public void question_5(){

        char[] array = {'a', 'b', 'c', 'd'};
             array=   insertSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

//    在所有的主键都相同时， 选择排序和插入排序谁更快？
    @Test
    public void quest_6(){

//        所有 主键 都 相同 ， 插入 排序    即使 不用 交换 数据 ，都要 判断 j-1,与 j  大小
        // 选择 排序 也是， 要 判断 i 到 array.length -1  之间的 最小值
    }

//    对于逆序数组， 选择排序和插入排序谁更快？
    @Test
    public void quest_7(){


    }

    @Test
    public void quest_9(){

        char[] array = {'E', 'A', 'S', 'Y', 'S', 'H', 'E', 'L', 'L', 'S', 'O', 'R', 'T', 'Q', 'U', 'E', 'S', 'T', 'I', 'O', 'N'};

      array =  SHELLSort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);

        }
    }

    private char[] SHELLSort(char[] array) {

        // 构造 h 生成 数列， 最终 要 已 h =1  结束  , shellSort  排序  和 插入 排序 一样 也是   构造 i 前面 的 有效 序列
        int h = 1;
        while (h < array.length/3) h = 3*h + 1; // h 由大到小

        while (h >= 1) {
//             i 初始值 设置 成 h  可以 跳过 i-h <0 的 元素，例如 i=0 ,  0 前面 没有 值 不需要 经行 排序， 所以 i=0 就是 浪费了
            for (int i = h; i < array.length; i++) {
//                要比较 j-h ,与 j 所以 j-h 要 大于 0
                for (int j=i; j-h >= 0; j = j - h) {
                    if (array[j - h] > array[j]) {
//                  交换 j-1, 与 j
                        swap(array, j - h, j);
                    }
                }
            }
                h = h / 3;


        }
        return array;
    }




}
