package com.lijian.algorithms.sort;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class MergeSortCurrent {
    public static void main (String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int length = 100000000;
        int[] data = (new DataRandom(length)).getData();
        printArr(data);
        System.out.println();
        // mergeSort(data);
        //在这里修改
        // int center = data.length/2;

        int[] tmp = new int[data.length];
        // CountDownLatch latch = new CountDownLatch(2);//CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行
        // new Thread(new Runnable(){

        //     @Override
        //     public void run() {
        //         mergeSort(data,tmp,0,center);
        //         latch.countDown();
        //     }
        // }).start();

        // new Thread(new Runnable(){

        //     @Override
        //     public void run() {
        //         mergeSort(data,tmp,center+1,data.length-1);
        //         latch.countDown();
        //     }
        // }).start();

        // latch.await();

        // merge(data, tmp, 0, center+1, data.length-1);

        //Fork/Join 从这里开始
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MergeSortCurrent.mergeTask task = new MergeSortCurrent.mergeTask(data, tmp, 0, data.length-1);//创建任务
        forkJoinPool.execute(task);//执行任务
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);//阻塞当前线程直到pool中的任务都完成了

        printArr(data);
        System.out.println("cost time :"+(System.currentTimeMillis()-startTime));
//        verify(data);

    }

    //递归
    private static void mergeSort(int[] nums,int[] tmp,int left,int right){
        if(left<right){
            int center = (left+right)/2;
            mergeSort(nums,tmp,left,center);
            mergeSort(nums,tmp,center+1,right);
            merge(nums,tmp,left,center+1,right);
        }
    }

    //合并
    private static void merge(int[] nums,int[] tmp,int leftPos, int rightPos, int rightEnd){
        int leftEnd = rightPos-1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while(leftPos<=leftEnd&&rightPos<=rightEnd){
            if(nums[leftPos]<nums[rightPos])
                tmp[tmpPos++]=nums[leftPos++];
            else
                tmp[tmpPos++]=nums[rightPos++];
        }
        while(leftPos<=leftEnd)
            tmp[tmpPos++]=nums[leftPos++];

        while(rightPos<=rightEnd)
            tmp[tmpPos++]=nums[rightPos++];

        for(int i = 0;i<numElements;i++,rightEnd--)
            nums[rightEnd]=tmp[rightEnd];
    }
    public static void mergeSort(int[] nums){
        int[] tmp = new int[nums.length];
        mergeSort(nums,tmp,0,nums.length-1);
    }

    //打印
    public static void printArr(int[] arr) {
        for(int i : arr){
            System.out.print(i+" ");
        }
    }

    public static void verify(int[] nums) {
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]>nums[i+1]){
                System.out.println("排序失败");
                return;
            }

        }
        System.out.println("排序成功");
    }


    static class mergeTask extends RecursiveAction {
        private static final int THRESHOLD = 2;//设置任务大小阈值
        private int start;
        private int end;
        private int[] data;
        private int[] tmp;

        public mergeTask(int[] data, int[] tmp, int start, int end){
            this.data = data;
            this.tmp = tmp;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute(){
            if((end - start)<=THRESHOLD){
                mergeSort(data,tmp,start,end);
            }else{
                int center = (start + end)/2;
                MergeSortCurrent.mergeTask leftTask = new MergeSortCurrent.mergeTask(data, tmp, start, center);
                MergeSortCurrent.mergeTask rightTask = new MergeSortCurrent.mergeTask(data, tmp, center+1, end);

                leftTask.fork();
                rightTask.fork();

                leftTask.join();
                rightTask.join();

                merge(data, tmp, start, center+1, end);

            }
        }
    }

}


/**
 * 产生随机数据
 */
class DataRandom{
    int length;
    int[] data;

    public DataRandom(int length){
        this.length = length;
        data = new int[length];
    }

    public int[] getData(){

        Random random = new Random(System.currentTimeMillis());
        for(int i=0;i<length;i++){
            data[i]=random.nextInt(2*length);
        }
        return data;
    }

}
