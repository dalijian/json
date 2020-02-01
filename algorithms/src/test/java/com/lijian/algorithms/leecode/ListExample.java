package com.lijian.algorithms.leecode;

import org.junit.Test;


//链表
public class ListExample {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 向右 旋转 链表
    // 将 链表 闭合 成 环
//    找到新的尾部，第 (n - k % n - 1) 个节点 ，新的链表头是第 (n - k % n) 个节点。
//    断开环 new_tail.next = None，并返回新的链表头 new_head


    @Test
    public void rotateRightTest() {

        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        ListNode result = rotateRight(listNode, 2);
        while (result.next != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        // base cases
        if (head == null) return null;
        if (head.next == null) return head;

        // close the linked list into the ring
        ListNode old_tail = head;
        // 链表 长度
        int n;
        for (n = 1; old_tail.next != null; n++)
            old_tail = old_tail.next;
        // 链表 闭合
        old_tail.next = head;

        // find new tail : (n - k % n - 1)th node
        // and new head : (n - k % n)th node
        ListNode new_tail = head;
        for (int i = 0; i < n - k % n - 1; i++)
            new_tail = new_tail.next;
        ListNode new_head = new_tail.next;

        // break the ring
        new_tail.next = null;

        return new_head;
    }
//83. 删除排序链表中的重复元素

    @Test
    public void deleteDuplicatesTest() {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(3);
        ListNode result = deleteDuplicates(listNode);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        while (head.next != null && head.val == head.next.val) {
            head = head.next;
        }
        ListNode temp = head;
        temp.next = deleteDuplicates(head.next);

        return temp;
    }


    @Test
    public void deleteDuplicates2Test() {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(3);
        ListNode result = deleteDuplicates2(listNode);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    //    82. 删除排序链表中的重复元素 II
    public ListNode deleteDuplicates2(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        if (head.next != null && head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates2(head.next);
        } else {
            head.next = deleteDuplicates2(head.next);
        }
        return head;

    }


    public ListNode deleteDuplicates21(ListNode head) {
        if (head == null) return head;
        if (head.next != null && head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates21(head.next);
        } else head.next = deleteDuplicates21(head.next);
        return head;
    }

    public ListNode partition(ListNode head, int x) {



        return new ListNode(0);
    }


    @Test
    public void reverseBetweenTest(){

        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
//        listNode.next.next = new ListNode(3);
//        listNode.next.next.next = new ListNode(4);
//        listNode.next.next.next.next = new ListNode(5);
//        listNode.next.next.next.next.next=new ListNode(6);

        ListNode result = reverseBetween2(listNode, 1, 2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }


//    92. 反转链表 II
    public ListNode reverseBetween(ListNode head, int m, int n) {

        return head;
    }


    public ListNode reverseList(ListNode node) {
        ListNode pre = null;
        ListNode next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;  // 第一个 node  拿到 当前值 ，  将 之前的  node 设置 成 现在 的 next 值，
            pre = node;        // 第一 次   pre   等于 当前值，                                    ，将
            node = next;
        }
        return pre;
    }


    // Object level variables since we need the changes
    // to persist across recursive calls and Java is pass by value.
    private boolean stop;
    private ListNode left;

    public void recurseAndReverse(ListNode right, int m, int n) {

        // base case. Don't proceed any further
        if (n == 1) {
            return;
        }

        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1) {
            this.left = this.left.next;
        }

        // Recurse with m and n reduced.
        this.recurseAndReverse(right, m - 1, n - 1);

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (this.left == right || right.next == this.left) {
            this.stop = true;
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            this.left = this.left.next;
        }
    }
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse(head, m, n);
        return head;
    }


    @Test
    public void reverseList2Test(){
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        ListNode result = reverseList2(listNode);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
    //206. 反转链表-递归
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList2(head.next);
        //1->2->3, 2->3->2, 2->null,3->2
        head.next.next = head;
        head.next = null;
        return p;
    }


//92. 反转链表 II 迭代法
    public ListNode reverseBetween21(ListNode head, int m, int n) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Move the two pointers until they reach the proper starting point
        // in the list.
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;

        // Iteratively reverse the nodes until n becomes 0.
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        // Adjust the final connections as explained in the algorithm
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }

        tail.next = cur;
        return head;
    }
}
