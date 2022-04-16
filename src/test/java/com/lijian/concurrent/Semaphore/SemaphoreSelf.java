package com.lijian.concurrent.Semaphore;

import com.lijian.concurrent.lock.BoundedQueue;

import java.util.LinkedList;
import java.util.Queue;

public class SemaphoreSelf{
  // 计数器
  int count;
  // 等待队列
  BoundedQueue queue ;

  public SemaphoreSelf(int count, BoundedQueue queue) {
    this.count = count;
    this.queue = queue;
  }

  // 初始化操作
  SemaphoreSelf(int c){
    this.count=c;
  }
  // 
  void down(){
    this.count--;
    if(this.count<0){
      // 将当前线程插入等待队列
      // 阻塞当前线程
      try {
        queue.add(Thread.currentThread());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  void up(){
    this.count++;
    if(this.count<=0) {
      // 移除等待队列中的某个线程 T
      // 唤醒线程 T
      try {
        queue.remove();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}