由于 对象监视器不同，所以运行结果就是异步的
同步代码块放在非同步synchronized方法中进行声明，并不能保证调用方法的线程的执行同步、顺序性，也就是线程调用方法的顺序是无序的
虽然在同步快中执行的顺序是同步的，这样极易出现脏读