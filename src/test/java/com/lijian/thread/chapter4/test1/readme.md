##IllegalMonitorStateException
wait,没有对象监视器，就是没有在同步代码块内
2. 当线程1遇到wait（）时，会将锁释放给其他线程，Thread2 拿到锁后 调用 notify（） 唤醒 Thread1 ，
但是 Thread1 要等待Thread2执行完毕 释放锁 才 从 wait（） 的地方开始执行