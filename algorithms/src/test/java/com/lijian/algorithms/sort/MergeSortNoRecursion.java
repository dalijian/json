package com.lijian.algorithms.sort;

import org.junit.Test;
//并归排序，非递归
public class MergeSortNoRecursion {


    public void Merge(int[] array, int low, int mid, int high) {
        int i = low; // i是第一段序列的下标
        int j = mid + 1; // j是第二段序列的下标
        int k = 0; // k是临时存放合并序列的下标
        int[] array2 = new int[high - low + 1]; // array2是临时合并序列

        // 扫描第一段和第二段序列，直到有一个扫描结束
        while (i <= mid && j <= high) {
            // 判断第一段和第二段取出的数哪个更小，将其存入合并序列，并继续向下扫描
            if (array[i] <= array[j]) {
                array2[k] = array[i];
                i++;
                k++;
            } else {
                array2[k] = array[j];
                j++;
                k++;
            }
        }

        // 若第一段序列还没扫描完，将其全部复制到合并序列
        while (i <= mid) {
            array2[k] = array[i];
            i++;
            k++;
        }

        // 若第二段序列还没扫描完，将其全部复制到合并序列
        while (j <= high) {
            array2[k] = array[j];
            j++;
            k++;
        }

        // 将合并序列复制到原始序列中
//        for (k = 0, i = low; i <= high; i++, k++) {
//            array[i] = array2[k];
//        }
        System.arraycopy(array2, 0, array, low, array2.length);
    }


//    在  某趟 并归中，设歌子表的长度为 gap,则 并归前 R[0...n-1] 中共有 n/gap  个 有序的子表 ：R[0...gap-1],R[gap...2*gap-1],... , R[(n/gap)*gap ... n-1]。
// 这个在拆分任务，没用使用 递归

    public void MergePass(int[] array, int gap, int length) {
        int i = 0;

        // 归并gap长度的两个相邻子表
        for (i = 0; i + 2 * gap - 1 < length; i = i + 2 * gap) {
            Merge(array, i, i + gap - 1, i + 2 * gap - 1);
        }

        // 余下两个子表，后者长度小于gap
        if (i + gap - 1 < length) {
            Merge(array, i, i + gap - 1, length - 1);
        }
    }

    public int[] sort(int[] list) {
        // mergePass (list,1,9),
        // mergePass(list,2,9),
        // mergePass(list,4,9),
        // mergePass(list,8,9)
        for (int gap = 1; gap < list.length; gap = 2 * gap) {
            MergePass(list, gap, list.length);
            System.out.print("gap = " + gap + ":\t");
            this.printAll(list);
        }
        return list;
    }

    // 打印完整序列
    public void printAll(int[] list) {
        for (int value : list) {
            System.out.print(value + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {
                9, 1, 5, 3, 4, 2, 6, 8, 7
        };

        MergeSortNoRecursion merge = new MergeSortNoRecursion();
        System.out.print("排序前:\t\t");
        merge.printAll(array);
        merge.sort(array);
        System.out.print("排序后:\t\t");
        merge.printAll(array);
    }

    @Test
    public void mergeTest() {
        int a[] = {
                1, 5, 9,
        };
        int b[] = {2, 4};

        int i = 0;
        int j = 0;
        int[] temp = new int[a.length + b.length];
        int k = 0;

        while (k < temp.length) {

            if (a[i] > b[j]) {
                temp[k] = b[j];
                k++;
                j++;
            } else {
                temp[k] = a[i];
                k++;
                i++;
            }
        }

//            if ((i == a.length - 1) && (j == (b.length - 1))) {
//                if (a[i] > b[j]) {
//                    temp[k] = b[j];
//                    k++;
//                    temp[k] = a[i];
//
//                }
//                if (a[i] < b[j]) {
//                    temp[k] = a[i];
//                    k++;
//                    temp[k] = b[j];
//
//                }
//            }
//            if (j == b.length - 1) {
//                while (i < a.length) {
//                    temp[k] = a[i];
//                    i++;
//                    k++;
//                }
//            }

        for (int l = 0; l < temp.length; l++) {
            System.out.println(temp[l]);
        }

    }


}