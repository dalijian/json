package com.lijian.data_structure.node;

public class DLNode implements Node {
    private Object element;
    private DLNode pre;
    private DLNode next;

    public DLNode(Object element, DLNode pre, DLNode next) {
        this.element = element;
        this.pre = pre;
        this.next = next;
    }

    public DLNode() {
    }

    @Override
    public Object getData() {
        return element;
    }

    @Override
    public void setData(Object object) {
  this.element =object;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public DLNode getPre() {
        return pre;
    }

    public void setPre(DLNode pre) {
        this.pre = pre;
    }

    public DLNode getNext() {
        return next;
    }

    public void setNext(DLNode next) {
        this.next = next;
    }
}
