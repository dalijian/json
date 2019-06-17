 ## join(long) 方法释放锁
 由于线程THreadA 释放了ThreadB 的锁，所以线程ThreadC 可以调用ThreadB
 中的同步方法 synchronized