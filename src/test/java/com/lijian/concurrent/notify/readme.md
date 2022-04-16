1. synchronize (.class) 不能使用 wait(),notify();
 1. 是 应为 synchronize 锁住 class 对象 会 导致 线程 执行 串行 化 , 也就 没用 线程 能够 唤醒 锁住 的 线程  
2. 不能使用 两把锁锁同一个 对象 