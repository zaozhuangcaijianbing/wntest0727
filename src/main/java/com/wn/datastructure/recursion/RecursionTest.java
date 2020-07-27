package com.wn.datastructure.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//递归，虽然简洁但会有重复计算，浪费资源
public class RecursionTest {
    //0、以相反的顺序打印字符串。
    private static void printReverse(char[] str) {
        helper(0, str);
    }

    private static void helper(int index, char[] str) {
        if (str == null || index >= str.length) {
            return;
        }
        helper(index + 1, str);
        System.out.print(str[index]);
    }


    //1、翻转字符串
    public void reverseString(char[] s) {
        swap(s, 0, s.length - 1);
    }

    private void swap(char[] s, int begin, int end) {
        if (begin >= end) {
            return;
        }
        char left = s[begin];
        char right = s[end];
        s[begin] = right;
        s[end] = left;
        swap(s, begin + 1, end - 1);
    }

    //2、给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。****
    //给定 1->2->3->4, 你应该返回 2->1->4->3.
    public ListNode swapPairs(ListNode head) {
        //1、递归在中间，匪夷所思
//        if (head == null || head.next == null) {
//            return head;
//        }

//        ListNode next = head.next;
//        head.next = swapPairs(next.next);
//        next.next = head;
//        return next;

        //2
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tmp = head.next;
        head.next = tmp.next;
        tmp.next = head;
        head.next = swapPairs(head.next);
        return tmp;
    }

    //3、杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> returnList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            returnList.add(getRow(i));
        }
        return returnList;
    }


    //4、给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
    public List<Integer> getRow(int rowIndex) {
        List<Integer> single = new ArrayList();
        single.add(1);

        if (rowIndex == 0)
            return single;

        List<Integer> last = getRow(rowIndex - 1);
        for (int i = 1; i < last.size(); i++)
            single.add(last.get(i - 1) + last.get(i));
        single.add(1);
        return single;

    }


    //5、反转单链表****
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


    //6、斐波那契数
    public int fib(int N) {
        if (N == 0)
            return 0;
        if (N == 1)
            return 1;

        Integer v = cache.get(N);
        if (v != null)
            return v;
        Integer now = fib(N - 1) + fib(N - 2);
        cache.put(N, now);
        return now;
    }

    Map<Integer, Integer> cache = new HashMap<>();

    //7、假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    //每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2)
            return 2;
        Integer v = cache.get(n);
        if (v != null)
            return v;

        Integer now = climbStairs(n - 1) + climbStairs(n - 2);
        cache.put(n, now);
        return now;
    }

    //8、二叉树最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);
            return Math.max(left_height, right_height) + 1;
        }
    }

    //9、实现 pow(x, n) ，即计算 x 的 n 次幂函数。
    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        return fastPow(x, N);
    }

    //10、合并两个有序链表***
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
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
//        a5.next = a6;
        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(2);
        ListNode b3 = new ListNode(3);
        ListNode b4 = new ListNode(4);
        b1.next = b2;
        b2.next = b3;
        b3.next = b4;
        RecursionTest test = new RecursionTest();
//        System.out.println(test.reverseList(a1));
//        ListNode r = test.mergeTwoLists(a1, b1);
//        System.out.println(r);

        ListNode listNode = test.swapPairs(a1);
        System.out.println(listNode);

//        printReverse("123".toCharArray());
    }
}