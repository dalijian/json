int getHoldCount() 的作用是查询当前线程保持此锁定的个数，也就是调用lock()方法的次数。
int getQueueLength()的作用是返回正等待获取此锁定的线程估计数
int getWaitQueuelLength(Condition condition) 的作用是返回等待与此锁定相关的给定条件Condition
的线程估计数，比如有5个线程，每个线程都执行了同一个condition对象的await(),
则调用getWaitQueueLength（Condition condition） 方法时返回的int 值是 5
方法 boolean hasQueuedThread(Thread thread) 的作用是查询指定的线程是否正在等待获取此锁定。
方法 boolean hasQueuedThreads() 的作用是查询是否有线程是否正在等待获取此锁定。
方法 boolean hasWaiters(Condition condition)的作用是 查询是否有线程正在等待与此锁定有关的condition条件
方法 boolean isFair()的作用是判断是不是公平锁。
ReentrantLock  默认使用非公平锁
boolean isHeldByCurrentThread()  的作用是查询当前线程是否保持此锁定
boolean isLocked() 的作用是查询此锁定是否由任意线程保持
方法 void lockInterruptibly() 如果当前线程未被中断，则获得锁定，如果已经被中断则出现异常。
方法 boolean tryLock() 仅在调用时锁定未被另一个线程保持的情况下，才获取该锁定


