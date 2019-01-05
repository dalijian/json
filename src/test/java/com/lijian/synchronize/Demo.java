package com.lijian.synchronize;


import org.junit.Test;

public class Demo {
	@Test
	public void main(String args[]) {
		Counter counter = new Counter();
		Thread thread1 = new Thread(counter, "A");
		Thread thread2 = new Thread(counter, "B");
		thread1.start();
		thread2.start();
	}

	//非synchronized代码块，未对count进行读写操作，所以可以不用synchronized
	public void printCount() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + " count:" + i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class Counter implements Runnable {
		private int count;

		public Counter() {
			count = 0;
		}

		public void countAdd() {
			synchronized (this) {
				for (int i = 0; i < 5; i++) {
					try {
						System.out.println(Thread.currentThread().getName() + ":" + (count++));
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			if (threadName.equals("A")) {
				countAdd();
			} else if (threadName.equals("B")) {
				printCount();
			}
		}
	}
}

class ClassName {
	public void method() {
		synchronized(ClassName.class) {
			// todo
		}
	}
}

/**
 * 同步线程
 */
class SyncThread implements Runnable {
	private static int count;

	public SyncThread() {
		count = 0;
	}

	public static void method() {
		synchronized(SyncThread.class) {
			for (int i = 0; i < 5; i ++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void run() {
		method();
	}
}
/**
 * 银行账户类
 */
class Account {
	String name;
	float amount;

	public Account(String name, float amount) {
		this.name = name;
		this.amount = amount;
	}
	//存钱
	public  void deposit(float amt) {
		amount += amt;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//取钱
	public  void withdraw(float amt) {
		amount -= amt;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public float getBalance() {
		return amount;
	}
}

/**
 * 账户操作类
 */
class AccountOperator implements Runnable{
	private Account account;
	public AccountOperator(Account account) {
		this.account = account;
	}

	public void run() {
		synchronized (account) {
			account.deposit(500);
			account.withdraw(500);
			System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
		}
	}
}

class Demo00{

	//public static final Object signal = new Object(); // 线程间通信变量
	//将account改为Demo00.signal也能实现线程同步
	@Test
	public void main(String args[]){
		Account account = new Account("zhang san", 10000.0f);
		AccountOperator accountOperator = new AccountOperator(account);

		final int THREAD_NUM = 5;
		Thread threads[] = new Thread[THREAD_NUM];
		for (int i = 0; i < THREAD_NUM; i ++) {
			threads[i] = new Thread(accountOperator, "Thread" + i);
			threads[i].start();
		}
	}
}
