//package com.lijian.map;
//
//import java.util.Map;
//import java.util.concurrent.locks.LockSupport;
//
//public class MyConcurrentHashMap<K,V> {
//    //最大容量为2的30次方
//    private static final int MAXIMUM_CAPACITY = 1 << 30;
//
//    //默认大小为16
//    private static final int DEFAULT_CAPACITY = 16;
//
//    //默认并发数为16
//    private static final int DEFAULT_CONCURRENCY_LEVEL = 16;
//
//    //负载参数为0.75
//    private static final float LOAD_FACTOR = 0.75f;
//
//    //链表转换红黑树节点数阈值为8
//    static final int TREEIFY_THRESHOLD = 8;
//
//    //红黑树转换链表节点数阈值为6
//    static final int UNTREEIFY_THRESHOLD = 6;
//
//    //链表转换红黑树容量阈值为64（Map容量不到64时，链表转红黑树之前会先扩容）
//    static final int MIN_TREEIFY_CAPACITY = 64;
//
//    //每个cpu强制处理的最小Map容量数
//    private static final int MIN_TRANSFER_STRIDE = 16;
//
//    //生成sizeCtl所使用的bit位数（还不大明白）
//    private static int RESIZE_STAMP_BITS = 16;
//
//    //参与扩容的最大线程数
//    private static final int MAX_RESIZERS = (1 << (32 - RESIZE_STAMP_BITS)) - 1;
//
//    //移位量，把生成戳移位后保存在sizeCtl中当做扩容线程计数的基数，相反方向移位后能够反解出生成
////    戳（抄的）
//    private static final int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;
////    下面是ConcurrentHashMap的重要变量。
//
//    //Map对应的Hash桶数组
//    transient volatile Node<K,V>[] table;
//
//    //扩容时候新建的Hash桶数组，注意transient关键字，该字段不会被序列化
//    private transient volatile Node<K,V>[] nextTable;
//
//    //用于节点计数
//    private transient volatile long baseCount;
//
//    //非常非常非常重要的一个参数，统御全局
//    //sizeCtl = -1，表示有线程正在进行初始化操作，防止多线程同时初始化Map
//    //sizeCtl = -(1 + nThreads)，表示有nThreads个线程正在进行扩容操作
//    //sizeCtl > 0，表示接下来的初始化操作中的Map容量，或者表示初始化/扩容完成后的阈值
//    //sizeCtl = 0，默认值
//    private transient volatile int sizeCtl;
//
//    //用以维护多线程扩容时候的线程安全
//    private transient volatile int transferIndex;
//
//    //节点的静态内部类，键值对存储的地方
//    static class Node<K,V> implements Map.Entry<K,V> {
//        final int hash;
//        final K key;
//        //val值和下一个节点Node<K,V> next都被volatile关键字修饰，保证线程安全
//        volatile V val;
//        volatile Node<K,V> next;
//
//        //初始化方法
//        Node(int hash, K key, V val, Node<K,V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.val = val;
//            this.next = next;
//        }
//
//        public final K getKey()       { return key; }
//        public final V getValue()     { return val; }
//        public final int hashCode()   { return key.hashCode() ^ val.hashCode(); }
//        public final String toString(){ return key + "=" + val; }
//        //为了线程安全setValue不允许调用，会直接抛异常
//        public final V setValue(V value) {
//            throw new UnsupportedOperationException();
//        }
//
//        //重写equals方法
//        public final boolean equals(Object o) {
//            Object k, v, u; Map.Entry<?,?> e;
//            return ((o instanceof Map.Entry) &&
//                    (k = (e = (Map.Entry<?,?>)o).getKey()) != null &&
//                    (v = e.getValue()) != null &&
//                    (k == key || k.equals(key)) &&
//                    (v == (u = val) || v.equals(u)));
//        }
//
//        //用以支持map.get()方法，会在子类中重写
//        Node<K,V> find(int h, Object k) {
//            Node<K,V> e = this;
//            if (k != null) {
//                do {
//                    K ek;
//                    if (e.hash == h &&
//                            ((ek = e.key) == k || (ek != null && k.equals(ek))))
//                        return e;
//                } while ((e = e.next) != null);
//            }
//            return null;
//        }
//    }
//
//    //一个在扩容方法中使用的内部类，用以标记已经处理过的Hash桶
//    static final class ForwardingNode<K,V> extends Node<K,V> {
//        final Node<K,V>[] nextTable;
//
//        //构造方法，ForwardingNode节点的Hash值为MOVED，nextTable指向扩容后的新Map
//        ForwardingNode(Node<K,V>[] tab) {
//            super(MOVED, null, null, null);
//            this.nextTable = tab;
//        }
//
//        //重写了Node中的find方法
//        Node<K,V> find(int h, Object k) {
//            //使用循环，避免多次碰到ForwardingNode导致递归过深
//            outer: for (Node<K,V>[] tab = nextTable;;) {
//                Node<K,V> e; int n;
//                if (k == null || tab == null || (n = tab.length) == 0 ||
//                        (e = tabAt(tab, (n - 1) & h)) == null)
//                    return null;
//                for (;;) {
//                    int eh; K ek;
//                    if ((eh = e.hash) == h &&
//                            ((ek = e.key) == k || (ek != null && k.equals(ek))))
//                        return e;
//                    if (eh < 0) {
//                        //遇到ForwardingNode节点的处理，相当于递归操作
//                        if (e instanceof ForwardingNode) {
//                            tab = ((ForwardingNode<K,V>)e).nextTable;
//                            continue outer;
//                        }
//                        else
//                            return e.find(h, k);
//                    }
//                    if ((e = e.next) == null)
//                        return null;
//                }
//            }
//        }
//    }
//    //树节点的静态内部类，与TreeBin共同提供红黑树功能
//    static final class TreeNode<K,V> extends Node<K,V> {
//        //红黑树的基本参数
//        TreeNode<K,V> parent;
//        TreeNode<K,V> left;
//        TreeNode<K,V> right;
//        //其实还维护着链表指针
//        TreeNode<K,V> prev;
//        boolean red;
//
//        //构造方法
//        TreeNode(int hash, K key, V val, Node<K,V> next,
//                 TreeNode<K,V> parent) {
//            super(hash, key, val, next);
//            this.parent = parent;
//        }
//
//        //重写find方法
//        Node<K,V> find(int h, Object k) {
//            return findTreeNode(h, k, null);
//        }
//
//        //find方法实现，从树的根部开始遍历节点
//        final TreeNode<K,V> findTreeNode(int h, Object k, Class<?> kc) {
//            if (k != null) {
//                TreeNode<K,V> p = this;
//                do  {
//                    int ph, dir; K pk; TreeNode<K,V> q;
//                    TreeNode<K,V> pl = p.left, pr = p.right;
//                    if ((ph = p.hash) > h)
//                        p = pl;
//                    else if (ph < h)
//                        p = pr;
//                    else if ((pk = p.key) == k || (pk != null && k.equals(pk)))
//                        return p;
//                    else if (pl == null)
//                        p = pr;
//                    else if (pr == null)
//                        p = pl;
//                    else if ((kc != null ||
//                            (kc = comparableClassFor(k)) != null) &&
//                            (dir = compareComparables(kc, k, pk)) != 0)
//                        p = (dir < 0) ? pl : pr;
//                        //递归遍历右子树
//                    else if ((q = pr.findTreeNode(h, k, kc)) != null)
//                        return q;
//                    else
//                        p = pl;
//                } while (p != null);
//            }
//            return null;
//        }
//    }
//    //拥有红黑树的根节点，维护着红黑树的读写锁
//    static final class TreeBin<K,V> extends Node<K,V> {
//        TreeNode<K,V> root;
//        volatile TreeNode<K,V> first;
//        volatile Thread waiter;
//        volatile int lockState;
//        //持有写锁状态
//        static final int WRITER = 1;
//        //等待写锁状态
//        static final int WAITER = 2;
//        //持有读锁状态
//        static final int READER = 4;
//
//        // 在hashCode相等并且不是Comparable类时才使用此方法进行判断大小
//        static int tieBreakOrder(Object a, Object b) {
//            int d;
//            if (a == null || b == null ||
//                    (d = a.getClass().getName().
//                            compareTo(b.getClass().getName())) == 0)
//                d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
//                        -1 : 1);
//            return d;
//        }
//
//        //构造方法，根据头节点定义红黑树
//        TreeBin(TreeNode<K,V> b) {
//            super(TREEBIN, null, null, null);
//            this.first = b;
//            TreeNode<K,V> r = null;
//            for (TreeNode<K,V> x = b, next; x != null; x = next) {
//                next = (TreeNode<K,V>)x.next;
//                x.left = x.right = null;
//                if (r == null) {
//                    x.parent = null;
//                    x.red = false;
//                    r = x;
//                }
//                else {
//                    K k = x.key;
//                    int h = x.hash;
//                    Class<?> kc = null;
//                    for (TreeNode<K,V> p = r;;) {
//                        int dir, ph;
//                        K pk = p.key;
//                        if ((ph = p.hash) > h)
//                            dir = -1;
//                        else if (ph < h)
//                            dir = 1;
//                        else if ((kc == null &&
//                                (kc = comparableClassFor(k)) == null) ||
//                                (dir = compareComparables(kc, k, pk)) == 0)
//                            dir = tieBreakOrder(k, pk);
//                        TreeNode<K,V> xp = p;
//                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                            x.parent = xp;
//                            if (dir <= 0)
//                                xp.left = x;
//                            else
//                                xp.right = x;
//                            r = balanceInsertion(r, x);
//                            break;
//                        }
//                    }
//                }
//            }
//            this.root = r;
//            assert checkInvariants(root);
//        }
//
//        //根节点加写锁
//        private final void lockRoot() {
//            if (!U.compareAndSwapInt(this, LOCKSTATE, 0, WRITER))
//                contendedLock(); // offload to separate method
//        }
//
//        //根节点释放写锁
//        private final void unlockRoot() {
//            lockState = 0;
//        }
//
//        //因为ConcurrentHashMap的写方法会给头节点加锁，所以读写锁不用考虑写写竞争的情况，只用考虑读写竞争的情况
//        private final void contendedLock() {
//            boolean waiting = false;
//            for (int s;;) {
//                //没有线程持有读锁时尝试获取写锁
//                if (((s = lockState) & ~WAITER) == 0) {
//                    //没有线程持有写锁时尝试获取写锁
//                    if (U.compareAndSwapInt(this, LOCKSTATE, s, WRITER)) {
//                        //拿到锁后将等待线程清空（等待线程是它自己）
//                        if (waiting)
//                            waiter = null;
//                        return;
//                    }
//                }
//                //有线程持有写锁且本线程状态不为WAITER时
//                else if ((s & WAITER) == 0) {
//                    //尝试占有waiting线程
//                    if (U.compareAndSwapInt(this, LOCKSTATE, s, s | WAITER)) {
//                        waiting = true;
//                        waiter = Thread.currentThread();
//                    }
//                }
//                //有线程持有写锁且本线程状态为WAITER时，堵塞自己
//                else if (waiting)
//                    LockSupport.park(this);
//            }
//        }
//
//        //重写find方法，当写锁被持有时使用链表查询的方法
//        final Node<K,V> find(int h, Object k) {
//            if (k != null) {
//                for (Node<K,V> e = first; e != null; ) {
//                    int s; K ek;
//                    //写锁被持有时使用链表的方法遍历
//                    if (((s = lockState) & (WAITER|WRITER)) != 0) {
//                        if (e.hash == h &&
//                                ((ek = e.key) == k || (ek != null && k.equals(ek))))
//                            return e;
//                        e = e.next;
//                    }
//                    //写锁没被持有时，持有一个读锁，用红黑树的方法遍历
//                    else if (U.compareAndSwapInt(this, LOCKSTATE, s,
//                            s + READER)) {
//                        TreeNode<K,V> r, p;
//                        try {
//                            p = ((r = root) == null ? null :
//                                    r.findTreeNode(h, k, null));
//                        } finally {
//                            Thread w;
//                            //当当前线程持有最后一个读锁的时候通知waiter线程获取写锁
//                            if (U.getAndAddInt(this, LOCKSTATE, -READER) ==
//                                    (READER|WAITER) && (w = waiter) != null)
//                                LockSupport.unpark(w);
//                        }
//                        return p;
//                    }
//                }
//            }
//            return null;
//        }
//
//        //用以实现Map.putVal的树部分
//        final TreeNode<K,V> putTreeVal(int h, K k, V v) {
//            Class<?> kc = null;
//            boolean searched = false;
//            for (TreeNode<K,V> p = root;;) {
//                int dir, ph; K pk;
//                if (p == null) {
//                    first = root = new TreeNode<K,V>(h, k, v, null, null);
//                    break;
//                }
//                else if ((ph = p.hash) > h)
//                    dir = -1;
//                else if (ph < h)
//                    dir = 1;
//                else if ((pk = p.key) == k || (pk != null && k.equals(pk)))
//                    return p;
//                else if ((kc == null &&
//                        (kc = comparableClassFor(k)) == null) ||
//                        (dir = compareComparables(kc, k, pk)) == 0) {
//                    if (!searched) {
//                        TreeNode<K,V> q, ch;
//                        searched = true;
//                        if (((ch = p.left) != null &&
//                                (q = ch.findTreeNode(h, k, kc)) != null) ||
//                                ((ch = p.right) != null &&
//                                        (q = ch.findTreeNode(h, k, kc)) != null))
//                            return q;
//                    }
//                    dir = tieBreakOrder(k, pk);
//                }
//
//                TreeNode<K,V> xp = p;
//                if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                    TreeNode<K,V> x, f = first;
//                    first = x = new TreeNode<K,V>(h, k, v, f, xp);
//                    if (f != null)
//                        f.prev = x;
//                    if (dir <= 0)
//                        xp.left = x;
//                    else
//                        xp.right = x;
//                    //当父节点是黑节点时候，直接挂一个红节点，不用加锁
//                    if (!xp.red)
//                        x.red = true;
//                        //其余时候可能需要旋转红黑树，重新平衡，这里加写锁
//                    else {
//                        lockRoot();
//                        try {
//                            root = balanceInsertion(root, x);
//                        } finally {
//                            unlockRoot();
//                        }
//                    }
//                    break;
//                }
//            }
//            assert checkInvariants(root);
//            return null;
//        }
//
//        //移除红黑树节点
//        final boolean removeTreeNode(TreeNode<K,V> p) {
//            TreeNode<K,V> next = (TreeNode<K,V>)p.next;
//            TreeNode<K,V> pred = p.prev;  // unlink traversal pointers
//            TreeNode<K,V> r, rl;
//            if (pred == null)
//                first = next;
//            else
//                pred.next = next;
//            if (next != null)
//                next.prev = pred;
//            if (first == null) {
//                root = null;
//                return true;
//            }
//            //如果红黑树规模太小，返回True，转换为链表
//            if ((r = root) == null || r.right == null ||
//                    (rl = r.left) == null || rl.left == null)
//                return true;
//            //红黑树规模大时，加写锁，在树上删除节点
//            lockRoot();
//            try {
//                TreeNode<K,V> replacement;
//                TreeNode<K,V> pl = p.left;
//                TreeNode<K,V> pr = p.right;
//                if (pl != null && pr != null) {
//                    TreeNode<K,V> s = pr, sl;
//                    while ((sl = s.left) != null) // find successor
//                        s = sl;
//                    boolean c = s.red; s.red = p.red; p.red = c; // swap colors
//                    TreeNode<K,V> sr = s.right;
//                    TreeNode<K,V> pp = p.parent;
//                    if (s == pr) { // p was s's direct parent
//                        p.parent = s;
//                        s.right = p;
//                    }
//                    else {
//                        TreeNode<K,V> sp = s.parent;
//                        if ((p.parent = sp) != null) {
//                            if (s == sp.left)
//                                sp.left = p;
//                            else
//                                sp.right = p;
//                        }
//                        if ((s.right = pr) != null)
//                            pr.parent = s;
//                    }
//                    p.left = null;
//                    if ((p.right = sr) != null)
//                        sr.parent = p;
//                    if ((s.left = pl) != null)
//                        pl.parent = s;
//                    if ((s.parent = pp) == null)
//                        r = s;
//                    else if (p == pp.left)
//                        pp.left = s;
//                    else
//                        pp.right = s;
//                    if (sr != null)
//                        replacement = sr;
//                    else
//                        replacement = p;
//                }
//                else if (pl != null)
//                    replacement = pl;
//                else if (pr != null)
//                    replacement = pr;
//                else
//                    replacement = p;
//                if (replacement != p) {
//                    TreeNode<K,V> pp = replacement.parent = p.parent;
//                    if (pp == null)
//                        r = replacement;
//                    else if (p == pp.left)
//                        pp.left = replacement;
//                    else
//                        pp.right = replacement;
//                    p.left = p.right = p.parent = null;
//                }
//
//                root = (p.red) ? r : balanceDeletion(r, replacement);
//
//                if (p == replacement) {  // detach pointers
//                    TreeNode<K,V> pp;
//                    if ((pp = p.parent) != null) {
//                        if (p == pp.left)
//                            pp.left = null;
//                        else if (p == pp.right)
//                            pp.right = null;
//                        p.parent = null;
//                    }
//                }
//            } finally {
//                unlockRoot();
//            }
//            assert checkInvariants(root);
//            return false;
//        }
//        //用以方便空Hash桶加锁的占位节点
//        static final class ReservationNode<K,V> extends Node<K,V> {
//            ReservationNode() {
//                super(RESERVED, null, null, null);
//            }
//
//            Node<K,V> find(int h, Object k) {
//                return null;
//            }
//        }
//        //初始化table的方法，保证初始化时线程安全（table其实就是Map的具体实现）
//        private final Node<K,V>[] initTable() {
//            Node<K,V>[] tab; int sc;
//            while ((tab = table) == null || tab.length == 0) {
//                //sizeCtl为负数时说明又其他线程正在初始化table，线程让出cpu
//                if ((sc = sizeCtl) < 0)
//                    Thread.yield();
//                    //使用CAS尝试将 sizeCtl修改为-1，开始初始化table
//                else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
//                    try {
//                        if ((tab = table) == null || tab.length == 0) {
//                            int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
//                            @SuppressWarnings("unchecked")
//                            Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n];
//                            table = tab = nt;
//                            sc = n - (n >>> 2);
//                        }
//                    } finally {
//                        sizeCtl = sc;
//                    }
//                    break;
//                }
//            }
//            return tab;
//        }
//
//        //扩容主要使用的是transfer方法
//        private final void transfer(Node<K,V>[] tab, Node<K,V>[] nextTab) {
//            int n = tab.length, stride;
//            //如果CPU数量过多导致每个线程分到的待处理的Hash桶的数量小于预设值，就将其置为预设值
//            if ((stride = (NCPU > 1) ? (n >>> 3) / NCPU : n) < MIN_TRANSFER_STRIDE)
//                stride = MIN_TRANSFER_STRIDE;
//            //如果没有新的table就创建一个两倍大小的table进行复制
//            //创建新table的线程安全在调用本方法之前用CAS保障
//            if (nextTab == null) {
//                try {
//                    @SuppressWarnings("unchecked")
//                    Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n << 1];
//                    nextTab = nt;
//                } catch (Throwable ex) {      // try to cope with OOME
//                    sizeCtl = Integer.MAX_VALUE;
//                    return;
//                }
//                nextTable = nextTab;
//                transferIndex = n;
//            }
//            int nextn = nextTab.length;
//            //创建ForwardingNode作为标志节点
//            ForwardingNode<K,V> fwd = new ForwardingNode<K,V>(nextTab);
//            //advance作为Hash桶操作完成的标志变量
//            boolean advance = true;
//            //finishing作为扩容完成的标志变量
//            boolean finishing = false;
//            //使用stride计算出该线程需要处理的Hash桶
//            for (int i = 0, bound = 0;;) {
//                Node<K,V> f; int fh;
//                while (advance) {
//                    int nextIndex, nextBound;
//                    if (--i >= bound || finishing)
//                        advance = false;
//                    else if ((nextIndex = transferIndex) <= 0) {
//                        i = -1;
//                        advance = false;
//                    }
//                    else if (U.compareAndSwapInt
//                            (this, TRANSFERINDEX, nextIndex,
//                                    nextBound = (nextIndex > stride ?
//                                            nextIndex - stride : 0))) {
//                        bound = nextBound;
//                        i = nextIndex - 1;
//                        advance = false;
//                    }
//                }
//                if (i < 0 || i >= n || i + n >= nextn) {
//                    int sc;
//                    //如果标志变量为真，则结束线程
//                    if (finishing) {
//                        nextTable = null;
//                        table = nextTab;
//                        sizeCtl = (n << 1) - (n >>> 1);
//                        return;
//                    }
//                    //提交之前再次检查
//                    if (U.compareAndSwapInt(this, SIZECTL, sc = sizeCtl, sc - 1)) {
//                        if ((sc - 2) != resizeStamp(n) << RESIZE_STAMP_SHIFT)
//                            return;
//                        finishing = advance = true;
//                        i = n;
//                    }
//                }
//                //如果Hash桶为空，则将标志节点放置在桶内表示该桶不用再被处理
//                else if ((f = tabAt(tab, i)) == null)
//                    advance = casTabAt(tab, i, null, fwd);
//                    //如果Hash桶内已经有标志节点，说明该桶已被处理，跳过该桶
//                else if ((fh = f.hash) == MOVED)
//                    advance = true;
//                else {
//                    //针对单个Hash桶开始数据的拷贝，首先锁住桶的头节点，保证线程安全
//                    synchronized (f) {
//                        if (tabAt(tab, i) == f) {
//                            //创建两个节点头，用以拆分原Hash桶的数据至两个新Hash桶中
//                            Node<K,V> ln, hn;
//                            //判断头节点的Hash值是否大于0，若小于0可能是树节点，占位节点等
//                            if (fh >= 0) {
//                                //通过fh & n有效地将原Hash桶中的节点分为值为0和1的两类
//                                int runBit = fh & n;
//                                Node<K,V> lastRun = f;
//·                          //遍历找到原链表中最后一段fh & n（runBit）相同的链表节，将其整段插入新的链表中
//                                //lastRun为最后一段fh & n相同的链表节的头节点
//                                for (Node<K,V> p = f.next; p != null; p = p.next) {
//                                    int b = p.hash & n;
//                                    if (b != runBit) {
//                                        runBit = b;
//                                        lastRun = p;
//                                    }
//                                }
//                                //根据runBit判断将这段链表插入哪个新链表
//                                if (runBit == 0) {
//                                    ln = lastRun;
//                                    hn = null;
//                                }
//                                else {
//                                    hn = lastRun;
//                                    ln = null;
//                                }
//                                //将其余节点插入两个新链表中，可以看出新链表相对于老链表来说顺序被倒置了
//                                for (Node<K,V> p = f; p != lastRun; p = p.next) {
//                                    int ph = p.hash; K pk = p.key; V pv = p.val;
//                                    if ((ph & n) == 0)
//                                        ln = new Node<K,V>(ph, pk, pv, ln);
//                                    else
//                                        hn = new Node<K,V>(ph, pk, pv, hn);
//                                }
//                                //将新链表分别插入新表中，将标志节点插入原表中，链表数据拷贝完成
//                                setTabAt(nextTab, i, ln);
//                                setTabAt(nextTab, i + n, hn);
//                                setTabAt(tab, i, fwd);
//                                advance = true;
//                            }
//                            //待处理的Hash桶中的数据为树节点
//                            else if (f instanceof TreeBin) {
//                                TreeBin<K,V> t = (TreeBin<K,V>)f;
//                                //创建lo与hi作为新树的两个根节点
//                                TreeNode<K,V> lo = null, loTail = null;
//                                TreeNode<K,V> hi = null, hiTail = null;
//                                int lc = 0, hc = 0;
//                                for (Node<K,V> e = t.first; e != null; e = e.next) {
//                                    int h = e.hash;
//                                    TreeNode<K,V> p = new TreeNode<K,V>
//                                            (h, e.key, e.val, null, null);
//                                    //同样根据h & n将节点分为两类
//                                    //同时维护树状结构和链表结构
//                                    if ((h & n) == 0) {
//                                        if ((p.prev = loTail) == null)
//                                            lo = p;
//                                        else
//                                            loTail.next = p;
//                                        loTail = p;
//                                        ++lc;
//                                    }
//                                    else {
//                                        if ((p.prev = hiTail) == null)
//                                            hi = p;
//                                        else
//                                            hiTail.next = p;
//                                        hiTail = p;
//                                        ++hc;
//                                    }
//                                }
//                                //如果拆分后的新树节点数小于阈值则转换回链表
//                                ln = (lc <= UNTREEIFY_THRESHOLD) ? untreeify(lo) :
//                                        (hc != 0) ? new TreeBin<K,V>(lo) : t;
//                                hn = (hc <= UNTREEIFY_THRESHOLD) ? untreeify(hi) :
//                                        (lc != 0) ? new TreeBin<K,V>(hi) : t;
//                                //将新链表分别插入新表中，将标志节点插入原表中，红黑树数据拷贝完成
//                                setTabAt(nextTab, i, ln);
//                                setTabAt(nextTab, i + n, hn);
//                                setTabAt(tab, i, fwd);
//                                advance = true;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        final V putVal(K key, V value, boolean onlyIfAbsent) {
//            //ConcurrentHashMap不允许K、V值为NULL的键值对插入Map中
//            if (key == null || value == null) throw new NullPointerException();
//            //将Hash值进行高位与计算，使得高位和低位都参与运算，降低Hash碰撞概率
//            int hash = spread(key.hashCode());
//            int binCount = 0;
//            for (Node<K,V>[] tab = table;;) {
//                Node<K,V> f; int n, i, fh;
//                //如果没有table,则初始化一个
//                if (tab == null || (n = tab.length) == 0)
//                    tab = initTable();
//                    //Hash桶里没有节点时，不加锁直接将节点通过CAS放入
//                else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
//                    if (casTabAt(tab, i, null,
//                            new Node<K,V>(hash, key, value, null)))
//                        break;
//                }
//                //如果发现该Hash桶里有一个标志节点，则帮助扩容
//                else if ((fh = f.hash) == MOVED)
//                    tab = helpTransfer(tab, f);
//                else {
//                    V oldVal = null;
//                    //锁住头节点，开始插入链表
//                    synchronized (f) {
//                        if (tabAt(tab, i) == f) {
//                            if (fh >= 0) {
//                                binCount = 1;
//                                for (Node<K,V> e = f;; ++binCount) {
//                                    K ek;
//                                    if (e.hash == hash &&
//                                            ((ek = e.key) == key ||
//                                                    (ek != null && key.equals(ek)))) {
//                                        oldVal = e.val;
//                                        if (!onlyIfAbsent)
//                                            e.val = value;
//                                        break;
//                                    }
//                                    Node<K,V> pred = e;
//                                    if ((e = e.next) == null) {
//                                        pred.next = new Node<K,V>(hash, key,
//                                                value, null);
//                                        break;
//                                    }
//                                }
//                            }
//                            //红黑树插入节点
//                            else if (f instanceof TreeBin) {
//                                Node<K,V> p;
//                                binCount = 2;
//                                if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
//                                        value)) != null) {
//                                    oldVal = p.val;
//                                    if (!onlyIfAbsent)
//                                        p.val = value;
//                                }
//                            }
//                        }
//                    }
//                    //如果大于阈值，则转为红黑树
//                    if (binCount != 0) {
//                        if (binCount >= TREEIFY_THRESHOLD)
//                            treeifyBin(tab, i);
//                        if (oldVal != null)
//                            return oldVal;
//                        break;
//                    }
//                }
//            }
//            addCount(1L, binCount);
//            return null;
//        }
//}
