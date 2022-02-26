package com.lijian.design_pattern.action.visitor;

import java.util.Iterator;
import java.util.ArrayList;

public class Directory extends Entry {
    private String name;                    // 鏂囦欢澶瑰悕瀛�
    private ArrayList<Entry> dir = new ArrayList();      // 鐩綍鏉＄洰闆嗗悎

    public Directory(String name) {         // 鏋勯�犲嚱鏁�
        this.name = name;
    }

    public String getName() {               // 鑾峰彇鍚嶅瓧
        return name;
    }

    public int getSize() {                  // 鑾峰彇澶у皬
        int size = 0;
        Iterator it = dir.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            size += entry.getSize();
        }
        return size;
    }

    public Entry add(Entry entry) {         // 澧炲姞鐩綍鏉＄洰
        dir.add(entry);
        return this;
    }

    public Iterator iterator() {      // 鐢熸垚Iterator
        return dir.iterator();
    }

    @Override
    public void accept(Visitor v) {         // 鎺ュ彈璁块棶鑰呯殑璁块棶
        v.visit(this);
        for (Entry entry : dir) {
            entry.accept(v);

        }
    }
}