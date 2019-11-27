package com.lijian.algorithms.link;



public class LinkedListDLNode implements LinkedList {

    private int size;

    private DLNode headNode;

    private DLNode tailNode;

    public LinkedListDLNode() {
        this.size = 0;
        this.headNode = new DLNode();

        this.tailNode = new DLNode();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {

        return this.size==0;
    }

    @Override
    public Node first()  {
        return headNode;
    }

    @Override
    public Node last()  {
        return tailNode;
    }

    @Override
    public Node getNext(Node p) throws InvalidNodeException, OutOfBoundaryException {
        //寻找到p 节点
        Node nextNode=null;
        while ((nextNode =headNode.getNext() )!= null) {
            if (nextNode.equals(p)) {
                return ((DLNode) nextNode).getNext();
            }
        }
        return null;
    }

    @Override
    public Node getPre(Node p) throws InvalidNodeException, OutOfBoundaryException {
        //寻找到p 节点
        Node nextNode=null;
        while ((nextNode =headNode.getNext() )!= null) {
            if (nextNode.equals(p)) {
                return ((DLNode) nextNode).getPre();
            }
        }
        return null;
    }

    @Override
    public Node insertFirst(Object e) {
        DLNode dlNode = new DLNode();
        dlNode.setData(e);
        dlNode.setPre(this.headNode);
        dlNode.setNext(this.headNode.getNext());
        this.headNode.setNext(dlNode);
        return dlNode;
    }

    @Override
    public Node insertLast(Object e) {

        DLNode dlNode = new DLNode();
        dlNode.setData(e);
        dlNode.setNext(this.tailNode);
        dlNode.setPre(this.tailNode.getPre());
        this.headNode.setPre(dlNode);
        return dlNode;
    }

    @Override
    public Node insertAfter(Node p, Object e) throws InvalidNodeException, OutOfBoundaryException {

        Node next = getNext(p);
        DLNode dlNode = new DLNode();
        dlNode.setPre((DLNode) p);
        dlNode.setData(e);
        dlNode.setNext((DLNode) next);
        ((DLNode) next).setPre(dlNode);
        return dlNode;
    }

    @Override
    public Node insertBefore(Node p, Object e) throws InvalidNodeException, OutOfBoundaryException {

        Node pre = getPre(p);
        DLNode dlNode = new DLNode();
        dlNode.setPre((DLNode) pre);
        dlNode.setNext((DLNode) p);
        dlNode.setData(e);
        ((DLNode) pre).setNext(dlNode);
        ((DLNode) p).setPre(dlNode);
        return dlNode;
    }

    @Override
    public Object remove(Node p) throws InvalidNodeException {

       DLNode pre= ((DLNode)p).getPre();
       DLNode next = ((DLNode)p).getNext();

       pre.setNext(next);
       next.setPre(pre);

        return p;
    }

    @Override
    public Object removeFirst() throws OutOfBoundaryException {
        return null;
    }

    @Override
    public Object removeLast() throws OutOfBoundaryException {
        return null;
    }

    @Override
    public Object replace(Node p, Object e) throws InvalidNodeException {
        return null;
    }

    @Override
    public Iterator elements() {
        return null;
    }
    //辅助方法，判断结点 p 是否合法，如合法转换为 DLNode
    protected DLNode checkPosition(Node p) throws InvalidNodeException {
        if (p==null)
            throw new InvalidNodeException("错误： p 为空。 ");
        if (p==headNode)
            throw new InvalidNodeException("错误： p 指向头节点，非法。 ");
        if (p==tailNode)
            throw new InvalidNodeException("错误： p 指向尾结点，非法。 ");
        DLNode node = (DLNode)p;
        return node;
    }

    public static void main(String[] args) {
        LinkedListDLNode linkedListDLNode = new LinkedListDLNode();

    }
}
