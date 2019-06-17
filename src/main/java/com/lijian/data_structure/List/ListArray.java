package com.lijian.data_structure.List;

public class ListArray implements List {

    private final int len =8;
    private Strategy strategy;
    private int size;
    private Object[] elements;
    public ListArray(){
        this(new DefaultStrategy());
    }

    public ListArray(Strategy strategy) {
        this.strategy =strategy;
        size=0;
        elements = new Object[len];
    }
    @Override
    public int getSize() {
        return  elements.length;
    }

    @Override
    public boolean isEmpty() {
        return elements.length==0;
    }

    @Override
    public boolean contains(Object object) {
        for (int i = 0; i < elements.length; i++) {
            if (object.equals(elements[i])) {
                return true;

            }

        }
        return false;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < elements.length; i++) {
            if (object.equals(elements[i])) {
                return i;

            }

        }
        return -1;
    }

    @Override
    public void insert(int i, Object object) throws IndexOutOfBoundsException {

        if (i > elements.length - 1 || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size >= elements.length) {
            expandSpace();

        }
        for (int j = size; j >i ; j--) {
            elements[j] =elements[j-1];

        }
        elements[i] =object;
        size++;
        return;
    }

    private void expandSpace() {
        Object[] a = new Object[elements.length*2];
        for (int i = 0; i < elements.length; i++) {
            a[i] = elements[i];

        }
        elements =a;

    }

    @Override
    public boolean insertBefore(Object o1, Object o2) {
        try {
            int index = indexOf(o1);
            insert(index, o2);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public boolean insertAfter(Object o1, Object o2) {
        try {
            int index = indexOf(o1);
            insert(index+1, o2);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public Object remove(int i) throws OutOfBoundaryException {
        if (i > elements.length - 1 || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object obj = elements[i];

        for (int j = i; j < elements.length; j++) {
            elements[j] = elements[j + 1];
        }
        elements[--size] =null;
        return obj;

    }

    @Override
    public boolean remove(Object i) {
        try {
            int index = indexOf(i);
            remove(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Object replace(int i, Object object) throws OutOfBoundaryException {
        if (i < 0 || i >= size) {
            throw new OutOfBoundaryException("错误，指定的序号越界");

        }
        Object obj = elements[i];
        elements[i] =object;
        return  obj;

    }

    @Override
    public Object get(int i) throws OutOfBoundaryException {
        if (i < 0 || i >= size) {
            throw new OutOfBoundaryException("错误，指定的序号越界");

        }
        return elements[i];
    }
}
