 ## THread.sleep(long) 方法不释放锁
 由于线程THreadA 使用THread.sleep(long) 
 一枝持有ThreadB对象的锁，所以线程ThreadC 
 只有在ThreadA 时间到达6秒后释放ThreadB的锁
 才能调用ThreadB中的同步方法synchronized
 