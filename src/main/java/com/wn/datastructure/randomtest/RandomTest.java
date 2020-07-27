package com.wn.datastructure.randomtest;

import java.util.*;

public class RandomTest {

    //1给定一个二叉树，判断它是否是高度平衡的二叉树。****
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        return Math.abs(depth(root.left) - depth(root.right)) <= 1
                && isBalanced(root.left) && isBalanced(root.right);
    }

    private int depth(TreeNode node) {
        if (node == null)
            return 0;

        return Math.max(depth(node.left), depth(node.right)) + 1;
    }


    //2给定两个二叉树，编写一个函数来检验它们是否相同。
    //如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;

        if (p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right))
            return true;
        else
            return false;
    }

    //3合并二叉树
    // 你需要将他们合并为一个新的二叉树。
    // 合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，
    // 否则不为 NULL 的节点将直接作为新二叉树的节点
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;

        TreeNode treeNode = new TreeNode(t1.val + t2.val);
        treeNode.left = mergeTrees(t1.left, t2.left);
        treeNode.right = mergeTrees(t1.right, t2.right);
        return treeNode;
    }

    //4二叉搜索树的范围和***
    // 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        }
        if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        }
        return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }

    //5翻转二叉树***
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    //6链表的中间结点
    public ListNode middleNode(ListNode head) {
        //暴力法
        int size = 0;
        ListNode tmp = head;
        while (tmp != null) {
            size++;
            tmp = tmp.next;
        }

        int middle = size / 2 + 1;
        int a = 1;
        while (true) {
            if (a == middle) {
                return head;
            }
            head = head.next;
            a++;
        }
    }

    //7回文数
    // 你能不将整数转为字符串来解决这个问题吗？
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10)
            return true;

        int a = x;
        List<Integer> list = new ArrayList<>();
        for (; ; ) {
            int tmp = a / 10;
            int yu = a % 10;
            a = tmp;
            list.add(yu);
            if (a == 0) {
                break;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (i >= list.size() - 1 - i) {
                break;
            }
            int left = list.get(i);
            int right = list.get(list.size() - 1 - i);
            if (left != right) {
                return false;
            }
        }

        return true;
    }


    //8搜索插入位置
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }
        return nums.length;
    }


    //9最后一个单词的长度
    public int lengthOfLastWord(String s) {

        char[] chars = s.toCharArray();

        int sum = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (' ' == chars[i] && sum == 0) {
                continue;
            }
            if (' ' == chars[i]) {
                return sum;
            }
            sum++;
        }
        return sum;
    }

    //10删除排序链表中的重复元素
    // 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode current = head;
        ListNode next = head.next;
        while (current != null && next != null) {
            if (current.val != next.val) {
                current = current.next;
                next = next.next;
            } else {
                next = next.next;
                current.next = next;
            }
        }
        return head;
    }

    //11二叉树的层次遍历 II
    //给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> upList = new ArrayList<>();
        if (root == null)
            return upList;


        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int size = 0;
        while ((size = queue.size()) > 0) {
            List<Integer> thisLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                thisLevel.add(poll.val);
                if (poll.left != null)
                    queue.offer(poll.left);
                if (poll.right != null)
                    queue.offer(poll.right);
            }
            upList.add(thisLevel);
        }

        List<List<Integer>> returnList = new ArrayList<>();
        for (int i = 0; i < upList.size(); i++) {
            returnList.add(upList.get(upList.size() - 1 - i));
        }
        return returnList;

    }


    //12将有序数组转换为二叉搜索树***
    // 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, start, mid - 1);
        root.right = build(nums, mid + 1, end);
        return root;
    }


    //13二叉树的最小深度 bfs
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;
        int min = 1;
        while ((size = queue.size()) > 0) {
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if(poll.left == null && poll.right == null)
                    return min;

                if(poll.left != null)
                    queue.offer(poll.left);

                if(poll.right != null)
                    queue.offer(poll.right);
            }
            min++;
        }
        return min;
    }

    //14路径总和 ****
    public boolean hasPathSum(TreeNode root, int sum) {
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while(!stack.isEmpty()){

        }

        return false;
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int length = s.length();
        int max = 0;
        int i = 0;
        int j = 0;
        while(j < length){
            boolean result = set.contains(s.charAt(j));
            if(result){
                max = Math.max(max,set.size());
                set.remove(s.charAt(i));
                i++;
            }else{
                set.add(s.charAt(j));
                j++;
            }
        }
        max = Math.max(max,set.size());
        return max;
    }


    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
                stack.push(s.charAt(i));
            }else{
                if(stack.isEmpty()){
                    return false;
                }else{
                    Character pop = stack.pop();
                    if((s.charAt(i) == ')' && pop != '(') || (s.charAt(i) == ']' && pop != '[') || (s.charAt(i) == '}' && pop != '{')){
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        lengthOfLongestSubstring(" ");
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(6);
        ListNode a6 = new ListNode(6);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a6;

        RandomTest test = new RandomTest();
//        test.middleNode(a1);
        System.out.println(test.sortedArrayToBST(new int[]{1, 2, 3, 4, 5}));
    }
}
