package com.lijian.concurrent.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Delayed {

    /**
     * 延迟时间
     */
    private final long delayTime;
    /**
     * 到期时间
     */
    private final long expire;
    /**
     * 数据
     */
    private String data;

    public DelayedTask(long delayTime, String data) {
        this.delayTime = delayTime;
        this.data = data;
        // 过期时间为：当前系统时间+延迟时间
        this.expire = System.currentTimeMillis() + delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DelayedElement{");
        sb.append("delay=").append(delayTime);
        sb.append(", expire=").append(expire);
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

