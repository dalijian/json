package com.lijian.reactor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyEventSource {

        private List<MyEventListener> listeners;

        public MyEventSource() {
            this.listeners = new ArrayList<>();
        }

        public void register(MyEventListener listener) {    // 1
            listeners.add(listener);
        }// 1 注册 监听器

        public void newEvent(MyEvent event) {
            for (MyEventListener listener :
                    listeners) {
                listener.onNewEvent(event);     // 2 向 监听器 发出新 事件
            }
        }

        public void eventStopped() {
            for (MyEventListener listener :
                    listeners) {
                listener.onEventStopped();      // 3 告诉监听器事件源已停止
            }
        }


    public static class MyEvent {   // 4 事件类
        private Date timeStemp;
        private String message;

        public MyEvent(Date timeStemp, String message) {
            this.timeStemp = timeStemp;
            this.message = message;
        }

        public MyEvent() {
        }

        public Date getTimeStemp() {
            return timeStemp;
        }

        public void setTimeStemp(Date timeStemp) {
            this.timeStemp = timeStemp;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "MyEvent{" +
                    "timeStemp=" + timeStemp +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
    }