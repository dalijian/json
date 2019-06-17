package com.lijian.data_structure.node;

import com.lijian.data_structure.List.DefaultStrategy;
import com.lijian.data_structure.List.List;
import com.lijian.data_structure.List.OutOfBoundaryException;
import com.lijian.data_structure.List.Strategy;

public class ListSLinked implements List {
    private Strategy strategy;

    private SLNode head;

    private int size;
    public ListSLinked(){
        this(new DefaultStrategy());
    }

    public ListSLinked(Strategy strategy) {
        this.strategy = strategy;
        head = new SLNode();
        size =0;

    }

    private SLNode getPreNode(Object object) {
        SLNode p = head;
        while (p.getNext() != null) {
            if (strategy.equal(p.getNext().getData(), object)) {
                return  p;
            }else {
                p = p.getNext();
            }
        }return  null;
    }

    private SLNode getPreNode(int i) {
        SLNode p =head;
        for (; i > 0; i--) {
            p= p.getNext();
        }
        return p;
    }

    private SLNode getNode(int i) {
        SLNode p =head.getNext();
        for (; i > 0; i--) {
            p=p.getNext();
        }
        return p;
    }
    @Override
    public int getSize() {
       return  size;
    }

    @Override
    public boolean isEmpty() {
        return  size==0;
    }

    @Override
    public boolean contains(Object object) {
        SLNode p =head.getNext();
        while (p != null) {
            if (strategy.equal(p.getData(),object)) {
                return true;
            }else{
                p=p.getNext();
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object object) {
        SLNode p = head.getNext();
        int index =0;
        while (p != null) {
            if (strategy.equal(p.getData(), object)) {
                return index;
            }else{
                index++;
                p=p.getNext();
            }
        }
        return -1;
    }

    @Override
    public void insert(int i, Object object) throws IndexOutOfBoundsException {
        if (i < 0 || i > size) {
            throw new OutOfBoundaryException(" error out of boundary");

        }
        SLNode p = getPreNode(i);
        SLNode insertNode = new SLNode(object,p.getNext());
        p.setNext(insertNode);

        size++;
        return;


    }

    @Override
    public boolean insertBefore(Object object, Object o) {
       return false;
    }

    @Override
    public boolean insertAfter(Object o, Object o2) {
        return false;
    }

    @Override
    public Object remove(int i) throws OutOfBoundaryException {
        return null;
    }

    @Override
    public boolean remove(Object i) {
        return false;
    }

    @Override
    public Object replace(int i, Object object) throws OutOfBoundaryException {
        return null;
    }

    @Override
    public Object get(int i) throws OutOfBoundaryException {
        return null;
    }
}
