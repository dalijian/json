package com.lijian.concurrent.CyclicBarrier;

import java.util.Map;
import java.util.concurrent.*;
// 银行流水处理服务类
public class BankWaterService  implements Runnable{
//创建4个屏障，处理完之后执行当前类的run方法
    private CyclicBarrier c = new CyclicBarrier(2, this);
//假设只有4个sheet，所以只启动4个线程
    private Executor executor = Executors.newFixedThreadPool(2);
//保存每个sheet计算出的流水结果
    private ConcurrentHashMap<String,Integer> sheetBankWaterCount = new ConcurrentHashMap<>();
    private  void count(){
        for (int i = 0; i < 2; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
//                    计算当前sheet 的 流水数据，计算代码省略
                    sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                    try {
//                        流水计算完成，插入一个屏障

                        System.out.println("等待 队列长度:"+c.getNumberWaiting());

                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }
    @Override
    public void run() {
        int result =0;
//        汇总每个sheet计算出的结果
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result +=sheet.getValue();
        }
//        将结果输出
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
        System.out.println(sheetBankWaterCount);
    }

    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }
}