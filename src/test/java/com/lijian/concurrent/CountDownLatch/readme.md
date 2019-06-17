## CountDownLathc 允许一个或多个线程等待其他线程完成操作
## join 用于让当前执行线程等待join线程执行结束。实现原理是不停检查join线程是否存活，如果join线程存活则让当前线程永远等待
## CountDownLatch 的await方法会阻塞当前线程，直到N变成零。
###1. 由于countDown方法可以使用在任何地方，所以N可以是N个线程，也可以是一个线程里的Nge执行步骤，
###2. 用在多个线程时，只需要把这个CountDownLatch的引用传递到线程里
