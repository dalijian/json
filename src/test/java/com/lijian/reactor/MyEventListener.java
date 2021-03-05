package com.lijian.reactor;

public interface MyEventListener {
        void onNewEvent(MyEventSource.MyEvent event);
        void onEventStopped();
    }