##锁非this对象具有一定的优点，如果在一个类中有多个synchronized方法，
1. 使用同步的代码块锁非this对象，则synchronized（非this)代码块中的程序与同步方法是异步的，
    不予其他锁this同步方法争抢this锁，提高运行效率


    package com.lijian.thread.chapter2.synBlockString;
    
    public class Service {
    
        private String usernameParam;
        private String passwordParam;
        String anyString = new String();   //对象属性
    
        public void setUsernameParam(String usernameParam, String passwordParam) {
             
            synchronized (anyString) {
                System.out.println("线程名称为" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入同步快");
                usernameParam=usernameParam;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passwordParam=passwordParam;
                System.out.println("线程名称为：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开同步快");
    
            }
        }
    }

##对象监视器必须是同一个对象，如果不是同一个对象监视器，运行的结果就是异步调用了

    package com.lijian.thread.chapter2.synBlockString;
    
    public class Service {
    
        private String usernameParam;
        private String passwordParam;
    
    
        public void setUsernameParam(String usernameParam, String passwordParam) {
             String anyString = new String();  //局部变量
            synchronized (anyString) {
                System.out.println("线程名称为" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入同步快");
                usernameParam=usernameParam;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passwordParam=passwordParam;
                System.out.println("线程名称为：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开同步快");
    
            }
        }
    }
