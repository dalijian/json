timer--> 调用 timerTask
timer 定时函数
1. schedule(task,time) 等于或超过time 时执行
2. schedule(task,time,period) ,time 执行时间，period 执行间隔
3. schedule(task,delay) delay 执行任务的延时时间 单位毫秒
4. schedule(task,delay,period) 
####scheduleAtFiexedRate
1.scheduleAtFiexedRate(task,time,period)
2.scheduleAtFiexedTate(task,delay,period)

####其他重要函数
1. timerTask
    1. cancel 取消当前timerTask函数
    2. scheduledExecutionTime() 返回最近一次计划执行时间
2. timer
    1. cancel 终止次计时器
    2. purge() 从队列中移除取消的timerTask，返回移除取消的timerTask个数需，要先在timerTask 中cancel
    
