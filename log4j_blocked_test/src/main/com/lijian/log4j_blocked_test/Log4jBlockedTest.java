package com.lijian.log4j_blocked_test;


import org.apache.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Log4jBlockedTest {
    private final static Logger log = Logger.getLogger(Log4jBlockedTest.class);
    //    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,100,30000L,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(20));
    static ExecutorService executorService = Executors.newFixedThreadPool(50, r -> {
        Thread thread = new Thread(r);

        thread.setName("checked_log4j_blocked" + thread.getId());
        return thread;
    });

    public static void main(String[] args) {
        while (true) {
//            for (int i = 0; i < 1000; i++) {
//                Thread thread = new Thread(() -> log.info(Thread.currentThread().getName() + " select sum(fee4)    from outp_rcpt_master ,outp_rcpt_masterxnh where outp_rcpt_masterxnh.reg_idxnh=outp_rcpt_master.reg_idxnh  \n" +
//                        " and  outp_rcpt_master.charge_indicator!=1 and outp_rcpt_master.hospital_id='000220' and outp_rcpt_master.operator_no='00022004' \n" +
//                        " and outp_rcpt_master.charge_datetime <=to_date('2021-12-31 00:00:00','yyyy-mm-dd hh24:mi:ss')\n" +
//                        " and outp_rcpt_master.charge_datetime >=to_date('2021-12-12 00:00:00','yyyy-mm-dd hh24:mi:ss')\n" +
//                        " -- and (outp_rcpt_master.acct_flag='0' or outp_rcpt_master.acct_flag is null) "));
//                thread.start();
//
//            }
            try {
                TimeUnit.MILLISECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.submit(() -> {


                log.info(Thread.currentThread().getName() + " select sum(fee4)    from outp_rcpt_master ,outp_rcpt_masterxnh where outp_rcpt_masterxnh.reg_idxnh=outp_rcpt_master.reg_idxnh  \n" +
                        " and  outp_rcpt_master.charge_indicator!=1 and outp_rcpt_master.hospital_id='000220' and outp_rcpt_master.operator_no='00022004' \n" +
                        " and outp_rcpt_master.charge_datetime <=to_date('2021-12-31 00:00:00','yyyy-mm-dd hh24:mi:ss')\n" +
                        " and outp_rcpt_master.charge_datetime >=to_date('2021-12-12 00:00:00','yyyy-mm-dd hh24:mi:ss')\n" +
                        " -- and (outp_rcpt_master.acct_flag='0' or outp_rcpt_master.acct_flag is null) ");
                log.info(Thread.currentThread().getName() + " select sum(fee4)    from outp_rcpt_master ,outp_rcpt_masterxnh where outp_rcpt_masterxnh.reg_idxnh=outp_rcpt_master.reg_idxnh  \n" +
                        " and  outp_rcpt_master.charge_indicator!=1 and outp_rcpt_master.hospital_id='000220' and outp_rcpt_master.operator_no='00022004' \n" +
                        " and outp_rcpt_master.charge_datetime <=to_date('2021-12-31 00:00:00','yyyy-mm-dd hh24:mi:ss')\n" +
                        " and outp_rcpt_master.charge_datetime >=to_date('2021-12-12 00:00:00','yyyy-mm-dd hh24:mi:ss')\n" +
                        " -- and (outp_rcpt_master.acct_flag='0' or outp_rcpt_master.acct_flag is null) ");
                log.info(Thread.currentThread().getName() + " select sum(fee4)    from outp_rcpt_master ,outp_rcpt_masterxnh where outp_rcpt_masterxnh.reg_idxnh=outp_rcpt_master.reg_idxnh  \n" +
                        " and  outp_rcpt_master.charge_indicator!=1 and outp_rcpt_master.hospital_id='000220' and outp_rcpt_master.operator_no='00022004' \n" +
                        " and outp_rcpt_master.charge_datetime <=to_date('2021-12-31 00:00:00','yyyy-mm-dd hh24:mi:ss')\n" +
                        " and outp_rcpt_master.charge_datetime >=to_date('2021-12-12 00:00:00','yyyy-mm-dd hh24:mi:ss')\n" +
                        " -- and (outp_rcpt_master.acct_flag='0' or outp_rcpt_master.acct_flag is null) ");


            });

        }

    }
}
