package com.wn.datastructure.linkedlist;


import scala.Char;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LinkedListTest1 {

    //2、给定一个链表，判断链表中是否有环。****
    //为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        ListNode slow = head.next;
        ListNode fast = slow.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    //3、给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null ****
    //可以考虑成一个数学问题
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null)
                return null;
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //4、找到两个单链表相交的起始节点。
    //优化？？
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode a = headA;
        while (a != null) {
            ListNode b = headB;
            while (b != null) {
                if (a == b) {
                    return a;
                }
                b = b.next;
            }
            a = a.next;
        }
        return null;
    }


    //5、删除链表的倒数第N个节点***
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        if (n <= 0) {
            return head;
        }

        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            if (fast != null) {
                fast = fast.next;
            } else {
                if (i == n - 1) {
                    head = head.next;
                    return head;
                } else {
                    return head;
                }

            }
        }

        if (fast == null) {
            return head.next;
        }

        ListNode slow = head;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return head;
    }

    //6、反转一个单链表***
    // 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr!=null){
            ListNode tempNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNode;
        }
        return prev;
    }


    //7、删除链表中等于给定值 val 的所有节点。递归和迭代***
    //增加虚拟节点
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val)
                prev.next = prev.next.next;
            else
                prev = prev.next;
        }
        return dummyHead.next;
    }

    //8、奇偶链表***
    // 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // head 为奇链表头结点，o 为奇链表尾节点
        ListNode o = head;
        // p 为偶链表头结点
        ListNode p = head.next;
        // e 为偶链表尾节点
        ListNode e = p;
        while (o.next != null && e.next != null) {
            o.next = e.next;
            o = o.next;
            e.next = o.next;
            e = e.next;
        }
        o.next = p;
        return head;
    }

    //9、请判断一个链表是否为回文链表。****
    public boolean isPalindrome(ListNode head) {
        return false;
    }


    /**
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
//    public ListNode reverseList(ListNode head) {
//
//    }

    /**
     * 1
     * 99
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = l1;
        int jin = 0;
        while(l1!=null || l2!=null){
            int a1= l1 == null ? 0:l1.val;
            int a2 = l2 == null ? 0:l2.val;

            int sum = jin + a1 + a2;
            jin = sum / 10;
            sum = sum % 10;

            l1.val = sum;

            if(l1.next == null && ((l2 != null && l2.next != null) || jin > 0)){
                l1.next = new ListNode(0);
            }

            l1 = l1.next;
            if(l2 != null){
                l2 = l2.next;
            }

        }
        return result;
    }

    /**
     * 输入: "babcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while(prev.next != null && prev.next.next != null){
            ListNode tmp = prev.next;
            ListNode tmp2 = prev.next.next.next;

            prev.next = prev.next.next;
            prev.next.next = tmp;
            tmp.next = tmp2;

            prev = prev.next.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        ListNode a6 = new ListNode(6);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a6;
        LinkedListTest1 test = new LinkedListTest1();
        test.swapPairs(a1);
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

class DoublyListNode {
    int val;
    DoublyListNode next;
    DoublyListNode prev;

    DoublyListNode(int x) {
        val = x;
    }
}

