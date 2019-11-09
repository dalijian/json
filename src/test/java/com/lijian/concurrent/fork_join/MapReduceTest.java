package com.lijian.concurrent.fork_join;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MapReduceTest {
    public static void main(String[] args) {
        String [] fc = {
            "hello world", "hello me", "hello fork", "hello join", "fork join in world"
        };

        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        MR mr = new MR(fc, 0, fc.length);

        //启动任务
        Map<String, Long> result = forkJoinPool.invoke(mr);
        result.forEach((k, v) ->
                System.out.println(k + ":" + v));

    }

    static class MR extends RecursiveTask<Map<String, Long>> {
        private String [] fc;
        private int start,end;

        public MR(String[] fc, int i, int length) {
            this.fc=fc;
            this.start=i;
            this.end=length;
        }

        @Override
        protected Map<String, Long> compute() {
            if (end - start == 1) {
                return calc(fc[start]);
            }else{
                int mid = (start+end)/2;
                MR mr1 = new MR(fc, start, mid);
                mr1.fork();
                MR mr2 = new MR(fc, mid, end);
                return merge(mr2.compute(), mr1.join());

            }
        }
//分离
        private Map<String, Long> calc(String s) {
            Map<String,Long> result = new HashMap<>();
            String [] words = s.split("\\s+");
            for (String w : words) {
                Long v = result.get(w);
                if (v != null) {
                    result.put(w, v + 1);
                }else{
                    result.put(w, 1L);
                }
            }
            return result;
        }

        private Map<String, Long> merge(Map<String, Long> compute, Map<String, Long> join) {
            {
                Map<String,Long> result = new HashMap<>();
                result.putAll(compute);
                join.forEach((k,v)->{
                    Long c = result.get(k);
                    if (c != null) {
                        result.put(k, c + v);

                    }else{
                        result.put(k, v);

                    }
                });
                return result;
            }

        }

    }








//计算数组值
    class SumTask extends RecursiveTask<Long> {

        static final int THRESHOLD = 100;
        long[] array;
        int start;
        int end;

        SumTask(long[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                // 如果任务足够小,直接计算:
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println(String.format("compute %d~%d = %d", start, end, sum));
                return sum;
            }
            // 任务太大,一分为二:
            int middle = (end + start) / 2;
            System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
            SumTask subtask1 = new SumTask(this.array, start, middle);
            SumTask subtask2 = new SumTask(this.array, middle, end);
            invokeAll(subtask1, subtask2);
            Long subresult1 = subtask1.join();
            Long subresult2 = subtask2.join();
            Long result = subresult1 + subresult2;
            System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
            return result;
        }
    }
}
