package com.lijian.muti_thread.future.sample;
//@safeThread host  类 没有 属性 是 无状态 类
public class Host {

    public Data request(final int count, final char c) {
        System.out.println("    request(" + count + ", " + c + ") BEGIN");
//        Thread-pre-message  设计模式， 将 任务 交给 新 线程 执行
        // (1) 创建FutureData的实例    创建  取货单
        final FutureData future = new FutureData(); // 这个 FutureData 并不是 线程 共享 的

        // (2) 启动一个新线程，用于创建RealData的实例
        new Thread(() -> {

                RealData realdata = new RealData(count, c);
//                将 异步计算结果 封装到 future 中
                future.setRealData(realdata);

        }).start();

        System.out.println("    request(" + count + ", " + c + ") END");

        // (3) 返回FutureData的实例
        return future;
    }
}
