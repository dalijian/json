package com.lijian.algorithms.practise;

import com.lijian.algorithms.graph.Vertex;
import org.junit.Test;

import java.util.LinkedList;

public class Demo {

    int[] array = {4, 3, 6, 8, 1, 0};
    @Test
    public void bubble(){

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i <array.length; i++) {
            System.out.println(array[i]);
        }
    }

    @Test
    public void insertTest(){



    }


    @Test
    public void mergeTest(){

        int[] temp = new int [array.length ];
        sort(array, 0, array.length - 1,temp);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public void sort(int[] array, int left, int right, int[] temp) {

        if (left < right) {
            int middle = (left+right)/2;
            sort(array, left, middle, temp);
            sort(array, middle + 1, right, temp);
            merge(array, left, middle, right, temp);
        }



    }

    private void merge(int[] array, int left, int middle, int right, int[] temp) {
        int i = left;
        int j = middle+1;
        int k =0;
        while (i<=middle&&j<=right){
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            }else{
                temp[k++] = array[j++];
            }
        }
        while (i <= middle) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
         k=0;

//        System.arraycopy(temp, 0, array, left, right - left+1);
        System.arraycopy(temp, 0, array, left, right - left+1);


    }

    @Test
    public void fast(){
        fastSort(array, 0, array.length - 1);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);

        }
    }

    public void fastSort(int[] array, int left, int right) {

        if (left >= right) {
            return;
        }
     int position = getPosition(array, left, right);
        fastSort(array, left, position);
        fastSort(array, position + 1, right);

    }

    public int getPosition(int array[], int left, int right) {

        int base = array[left];

        while (left < right) {

            while (left < right && array[right] >= base) {

                right--;

            }
            array[left] = array[right];

            while (left < right && array[left] <= base) {
                left++;
            }

            array[right] = array[left];


        }

        array[left] =base;
        return left;


    }



    @Test
    public void testDivision(){
        division(array, 0, array.length - 1);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

    }
    public void division(int[] array, int left, int right) {
//        基质取中间值
//        int base = (left+right)/2;
        int base = array[left];

        while (left < right) {
            while (left < right && array[right] >= base) {
                right--;
            }
//            array[right] 的值小于 array[base],交换
            array[left] = array[right];
            while (left < right && array[left] <= base) {
                left++;
            }
            array[right] = array[left];
        }

        array[left]= base;
    }

//    @Test
//    public  void DFS(Vertex vertex){
//
//        LinkedList result = new LinkedList();
//        DFSRecur(vertex, result);
//        //  拿到 全部  结点
//        LinkedList allVertex =   getVertex();
//
//        for (int i = 0; i < allVertex.size(); i++) {
//            if (!allVertex[i].isVisited()) {
//                DFSRecur(allVertex[i],result);
//            }
//        }
//
//
//    }

//    private void DFSRecur(Vertex vertex, LinkedList result) {
//        vertex.isVisited();
//        result.add(vertex);
//        LinkedList<Vertex>  adjVertexs =   vertex.adjVertexs(vertex);
//        for (int i = 0; i < adjVertexs.size(); i++) {
//            if (!adjVertexs[i].isVisited()){
//                DFSRecur(adjVertexs[i], result);
//            }
//
//        }
//
//    }


}
