//package com.lijian.concurrent.threadPool;
//
//import com.lijian.test.Run;
//
//import java.util.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class ThreadPoolTest<Job extends Runnable> {
//    private Queue<Job> jobList = new LinkedList<>();
//
//    private List<Work> workList = Collections.synchronizedList(new LinkedList<>());
//
//    private AtomicInteger threadNum = new AtomicInteger();
//
//    public ThreadPoolTest(Queue<Job> jobList, int i) {
//
//        this.jobList = jobList;
//        for (int j = 0; j < i; j++) {
//           Work work = new Work();
//
//
//            workList.add(work);
//            Thread thread = new Thread(work);
//            thread.setName("work_" + thread.getId());
//
//            threadNum.incrementAndGet();
//
//        }
//    }
//
//    public void execute(Job job) {
//        synchronized (jobList) {
//            jobList.add(job);
//        }
//    }
//}
//class Work implements Runnable{
//
//    @Override
//    public void run() {
//        synchronized ()
//    }
//}