package com.lijian.concurrent.threadLocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public  class  ThreadLocalSimpleDateFormat {

    static class SafeDateFormat {
        //初始化设置值
        static final ThreadLocal<DateFormat> tl = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        static DateFormat get() {
            return tl.get();
        }

        DateFormat df = SafeDateFormat.get();

        public DateFormat getDf() {
        return     tl.get();

        }

        public void setDf(DateFormat df) {
            tl.set(df);

        }
    }
}
