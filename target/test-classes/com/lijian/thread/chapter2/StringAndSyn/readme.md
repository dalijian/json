两个线程持有相同的锁，所以造成线程B不能执行，
同步synchronized代码块都不使用String作为锁对象，而改用其他，比如new Object（） 实例化一个Object对象，但它并不放入缓存中
