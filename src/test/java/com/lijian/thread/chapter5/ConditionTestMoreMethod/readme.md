调用lock.lock() 代码的线程就持有了对象监视器，其他线程只有等待锁被释放时再次争抢。效果和使用synchronized关键字一样，线程之间还是顺序执行的