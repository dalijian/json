package com.lijian.algorithms.link;



public class DoubleLink<T> {
    
    private class Node<T> {
        /**
         * 节点值
         */
    
        private T vlaue;
        /**
         * 前一个节点
         */
        
        private Node<T> prev;
        /**
         * 后一个节点
         */
        
        private Node<T> prex;
        
        public Node(T value,Node<T> prev,Node<T> prex){
            this.vlaue = value;
            this.prev = prev;
            this.prex = prex;
        }
    }
    /**
     * 链表长度
     */
    private int size ;
    /**
     * 头节点
     */
    private Node<T> head;
    public DoubleLink(){
        /**
         * 头结点不存储值 并且头结点初始化时 就一个头结点。
         * 所以头结点的前后节点都是自己
         * 并且这个链表的长度为0；
         */
        head = new Node<>(null, null, null);
        head.prev = head.prex ;
        head = head.prex;
        size = 0;
    }
     public int getSize(){
         return this.size;
     }
     /**
      * 判断链表的长度是否为空
      */
     public boolean isEmplty(){
         return size == 0;
     }
    /**
     * 判断索引是否超出范围
     */
     public void checkIndex(int index){
         if(index<0||index>=size){
             throw new IndexOutOfBoundsException();
         }
         return;
     }
     /**
      * 通过索引获取链表当中的节点
      * 
      */
     public Node<T> getNode(int index){
         /**
          * 检查该索引是否超出范围
          */
         checkIndex(index);
         /**
          * 当索引的值小于该链表长度的一半时，那么从链表的头结点开始向后找是最快的
          */
         if(index<size/2){
             Node<T> cur = head.prex;
             for(int i=0;i<index;i++){
                 cur = cur.prex;
             }
             return cur;
         }
         /**
          * 当索引值位于链表的后半段时，则从链表的另端开始找是最快的
          */
         /**
          * 此
          */
         Node<T> cur = head.prev;
         int newIndex = size - (index+1);
         for(int i=0;i<newIndex;i++){
             cur = cur.prev;
         }
         return cur;
     }
     /**
      * 获取节点当中的值
      */
     public T getValue(Node<T> cur){
         return cur.vlaue;
     }
     /**
      * 获取第一个节点的值
      */
     public T getFirst(){
         return getValue(getNode(0));
     }
     /**
      * 获取最后一个节点的值
      */
     public T getLast(){
         return getValue(getNode(size-1));
     }
     /**
      * 插入节点
      */
     public void insert(int index,T value){
         //如果这次插入时 链表是空的
         if(index==0){
             //这个节点的
             Node<T> cur = new Node<T>(value, head, head.prex);
             head.prex.prev = cur;
             head.prex = cur;
             size++;
             return;
         }
         /**
          * 先根据给出的插入位置 找到该链表原来在此位置的节点
          */
         Node<T> node = getNode(index);
         /**
          *放置的位置的前一个节点就是原节点的前置节点 而后节点就是原节点
          */
         Node<T> cur = new Node<T>(value,node.prev,node);
         /**
          * 现将该位置也就是 原节点的前节点的后节点 赋值成为新节点
          * 然后将新节点的后置节点的值赋值成为原节点
          */
         node.prev.prex = cur;
         node.prev = cur;
         size++;
     }
     /**
      * 向表头插入数据
      */
     public void insertTo(T Value)
     {
         insert(0,Value);
     }
     /**
      * 将元素插入到链表的尾部
      */
     public void insertTotatil(T vlaue){
         Node<T> cur = new Node<>(vlaue,head.prev, head);
        //head.prev 代表原来的尾部节点
         //遵循两个原则 一 新插入节点的前一个节点的后一个节点为新节点。新节点的后一个节点的前一个节点是新节点
         head.prev.prex = cur;
         head.prev = cur;
         size++;
     }
    /**
     * 删除节点的方法
     */
     public void del(int index){
         checkIndex(index);
         Node<T> cur = getNode(index);
         //记住此时的指针还没断开 赋值以后才相当于断开 
         cur.prev.prex = cur.prex;
         cur.prex.prev = cur.prev;
         size--;
         cur = null;
         return;
     }
    /**
     * 删除第一个节点
     */
    public void  delFirst(){
        del(0);
    }
    /**
     * 删除最后一个节点
     */
    public void delLast(){
        del(size-1);
    }

    public static void main(String[] args) {
        DoubleLink<String> doubleLink = new DoubleLink<>();
        doubleLink.insert(1, "first");
        doubleLink.insert(2, "second");

    }
}