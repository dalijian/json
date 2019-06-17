package com.lijian.concurrent.balking;

import com.lijian.concurrent.copy_on_write.Router;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RouterTable {

    ConcurrentHashMap<String, CopyOnWriteArraySet<Router>> rt = new ConcurrentHashMap<>();

    volatile  boolean changed;

    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    public void startLocalSaver(){
        ses.scheduleWithFixedDelay(() -> {
            autoSave();
        }, 1, 1, TimeUnit.MINUTES);

    }
    void autoSave(){
        if (!changed) {
            return;
        }
        changed = false;
        this.save2Local();

    }

    private void save2Local() {

    }

    public void remove(Router router) {
        Set<Router> set = rt.get(router.getIface());
        if (set != null) {
            set.remove(router);
            changed =true;
        }
    }

    public void add(Router router) {
        Set<Router> set = rt.computeIfAbsent(router.getIface(),r->
            new CopyOnWriteArraySet<>());
        set.add(router);

        changed=true;
    }

}
