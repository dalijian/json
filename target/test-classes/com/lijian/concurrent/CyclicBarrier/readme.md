##CyclicBarrier可以用于多线程计算数据，最后合并计算结果的场景
1. CyclicBarrier 默认的构造方法是CyclicBarrier(int parties) 其参数表示屏障拦截的线程数量，
1. 每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞
1. CyclicBarrier 高级构造函数CyclicBarrier(int parties,Runnable barrierAction) 当线程到达屏障时(parties 等于 0 时），优先执行barrierAction，方便处理更复杂的业务场景
    1. 当线程到达屏障时，会先执行 barrierAction, 等 barrierAction 执行完 子线程 再从等待的 地方 继续执行。
1. CountDownLatch 的计数器只能使用一次，而CyclicBarrier的计算器可以使用reset()方法重置，所以CyclicBarrier能处理更为复杂的业务场景。
    1. countDownLatch 当countDownLatch为0时 主线线程与子线程 应该是同时经行的 ，没有 先后之分
