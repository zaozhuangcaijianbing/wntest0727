package com.wn.datastructure.qq;

import java.util.ArrayList;
import java.util.List;

public class QQTest {

    //1最长回文子串
    // 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int max = 0;
        String result = "";
        for (int i = 0; i < chars.length; i++) {
            int tmpMax = 1;
            for (int j = i + 1; j < chars.length; j++) {
                if ((2 * i - j) < 0) {
                    break;
                }
                if (chars[j] == chars[2 * i - j]) {
                    tmpMax = tmpMax + 2;
                } else {
                    break;
                }
            }
            if (tmpMax > max) {
                max = tmpMax;
                result = s.substring(i - max / 2, i + max / 2 + 1);
            }
        }


        for (int i = 0; i < chars.length; i++) {
            int tmpMax = 0;
            for (int j = i; j < chars.length; j++) {
                if ((2 * i - j - 1) < 0) {
                    break;
                }
                if (chars[j] == chars[2 * i - j - 1]) {
                    tmpMax = tmpMax + 2;
                } else {
                    break;
                }
            }
            if (tmpMax > max) {
                max = tmpMax;
                result = s.substring(i - max / 2, i + max / 2);
            }


        }
        return result;
    }

    //2旋转链表
    // 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;
        ListNode c = head;
        int size = 1;
        while (c.next != null) {
            size++;
            c = c.next;
        }

        int move = k % size;

        ListNode nowHead = head;
        for (int i = 0; i < move; i++) {
            ListNode tmp = nowHead;
            ListNode current = tmp;
            while (current.next != null && current.next.next != null) {
                current = current.next;
            }

            nowHead = current.next;
            current.next = null;
            nowHead.next = tmp;
        }

        return nowHead;
    }


    //3盛最多水的容器
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int minHeight = Math.min(height[i], height[j]);
                int size = minHeight * (j - i);
                if (size > max) {
                    max = size;
                }
            }
        }
        return max;
    }


    //4螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> returnList = new ArrayList<>();
        int y = matrix.length;
        if (y == 0) {
            return returnList;
        }
        int x = matrix[0].length;
        int loopCount = Math.min(y, x) / 2 + Math.min(y, x) % 2;
        int size = x * y;

        for (int loop = 0; loop < loopCount; loop++) {
            for (int i = loop; i < x - loop; i++) {
                returnList.add(matrix[loop][i]);
            }
            if (size == returnList.size()) {
                return returnList;
            }

            for (int i = loop + 1; i < y - loop; i++) {
                returnList.add(matrix[i][x - 1 - loop]);
            }
            if (size == returnList.size()) {
                return returnList;
            }

            for (int i = x - 2 - loop; i >= loop; i--) {
                returnList.add(matrix[y - 1 - loop][i]);
            }
            if (size == returnList.size()) {
                return returnList;
            }

            for (int i = y - 2 - loop; i >= loop + 1; i--) {
                returnList.add(matrix[i][loop]);
            }
            if (size == returnList.size()) {
                return returnList;
            }
        }

        return returnList;
    }

    //5反转链表
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return head;
        ListNode current = head;
        while (current.next != null) {
            ListNode newNode = current.next;
            current.next = current.next.next;
            newNode.next = head;
            head = newNode;
        }

        return head;
    }

    //6两数相加，不要使用两数相加，然后放入链表的做法
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


    public static void main(String[] args) {

        QQTest test = new QQTest();
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(5);
        ListNode a5 = new ListNode(6);
        ListNode a6 = new ListNode(4);
        a1.next = a2;
        a2.next = a3;

        a4.next = a5;
        a5.next = a6;
//        ListNode listNode = test.rotateRight(a1, 1);
        System.out.println(test.addTwoNumbers(a1, a4));
    }
}
