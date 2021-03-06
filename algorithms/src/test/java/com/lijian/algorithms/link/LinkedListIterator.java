package com.lijian.algorithms.link;

// 链表 迭代器
public class LinkedListIterator implements Iterator {
    private LinkedList list;

    // 当前 元素
    private Node current;

    public LinkedListIterator(LinkedList list) {
        this.list = list;
        if (list.isEmpty()) {
            current =null;

        }else {
            current=list.first();
        }
    }

    @Override
    public void first() {
        current = list.first();
    }

    @Override
    public void next()  {
        try {
            current =  list.getNext(current);
        } catch (InvalidNodeException e) {
            e.printStackTrace();
        } catch (OutOfBoundaryException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isDone() {
        if (current.equals(list.last())) {
            return true;
        }
        return false;
    }

    @Override
    public Object currentItem() {
        return current.getData();
    }
}
