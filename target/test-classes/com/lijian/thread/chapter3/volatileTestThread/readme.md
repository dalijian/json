volatile 不是原子性的 所以计算结果不为10000
但是把Mythread implements Runnable 实现 run（） 结果却是 10000 不知道 怎么回事？？？ 