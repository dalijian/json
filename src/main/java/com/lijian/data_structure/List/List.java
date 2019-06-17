package com.lijian.data_structure.List;

public interface List {
     int getSize();

     boolean isEmpty();

    boolean contains(Object object);

    int indexOf(Object object);

    void insert(int i, Object object) throws  IndexOutOfBoundsException;

    boolean insertBefore(Object object, Object o);

    boolean insertAfter(Object o, Object o2);

    Object remove(int i) throws OutOfBoundaryException;

    boolean remove(Object i);

    Object replace(int i, Object object) throws OutOfBoundaryException;

    Object get(int i) throws OutOfBoundaryException;


}
