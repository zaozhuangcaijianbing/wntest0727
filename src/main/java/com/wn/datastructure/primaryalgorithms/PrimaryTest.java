package com.wn.datastructure.primaryalgorithms;

import java.util.*;

public class PrimaryTest {
    //1从排序数组中删除重复项
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int lastIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[lastIndex]) {
                if (lastIndex < i - 1) {
                    nums[lastIndex + 1] = nums[i];
                }
                lastIndex++;
            }
        }

        return lastIndex + 1;
    }


    //2买卖股票的最佳时机
    // 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
    public int maxProfit(int[] prices) {
        int buyIndex = -1;
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1] && buyIndex == -1) {
                buyIndex = i - 1;
            }
            if (prices[i] < prices[i - 1] && buyIndex != -1) {
                int add = prices[i - 1] - prices[buyIndex];
                sum += add;
                buyIndex = -1;
            }

            if (i == prices.length - 1 && buyIndex != -1) {
                int add = prices[i] - prices[buyIndex];
                sum += add;
            }
        }
        return sum;
    }

    //3两个数组的交集
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    //4 移动零
    // 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    public void moveZeroes(int[] nums) {
        int zeroBeginIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && zeroBeginIndex == -1) {
                zeroBeginIndex = i;
            }

            if (nums[i] != 0 && zeroBeginIndex > -1) {
                nums[zeroBeginIndex] = nums[i];
                nums[i] = 0;
                zeroBeginIndex += 1;
            }
        }
    }

    //5有效的数独
    // 判断一个 9x9 的数独是否有效
    public boolean isValidSudoku(char[][] board) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ('.' != board[i][j]) {
                    if (set.contains(board[i][j])) {
                        return false;
                    } else {
                        set.add(board[i][j]);
                    }
                }
            }
            set = new HashSet<>();
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ('.' != board[j][i]) {
                    if (set.contains(board[j][i])) {
                        return false;
                    } else {
                        set.add(board[j][i]);
                    }
                }

            }
            set = new HashSet<>();
        }


        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board[i].length; j += 3) {
                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 3; n++) {
                        if ('.' != board[i + m][j + n]) {
                            if (set.contains(board[i + m][j + n])) {
                                return false;
                            } else {
                                set.add(board[i + m][j + n]);
                            }
                        }

                    }
                }
                set = new HashSet<>();
            }
        }

        return true;
    }


    //6旋转图像****
    public void rotate(int[][] matrix) {

    }


    //7 整数反转
    // 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    //8字符串中的第一个唯一字符.
    public int firstUniqChar(String s) {
        if (s.length() == 1) {
            return 0;
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (chars[i] == chars[j] && i != j) {
                    break;
                }

                if (j == chars.length - 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    //9有效的字母异位词
    // 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        char s1[] = s.toCharArray();
        char t1[] = t.toCharArray();
        int count[] = new int[26];
        for (int i = 0; i < t1.length; i++) {
            count[s1[i] - 'a']++;
            count[t1[i] - 'a']--;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0)
                return false;
        }
        return true;
    }

    // 10验证回文字符串
    // 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        if (chars.length < 2)
            return true;

        int i = 0;
        int j = chars.length - 1;
        while (true) {
            if (i >= j)
                break;
            if (!((chars[i] >= 'a' && chars[i] <= 'z') || (chars[i] >= 'A' && chars[i] <= 'Z') || (chars[i] >= '0' && chars[i] <= '9'))) {
                i++;
                continue;
            }

            if (!((chars[j] >= 'a' && chars[j] <= 'z') || (chars[j] >= 'A' && chars[j] <= 'Z') || (chars[j] >= '0' && chars[j] <= '9'))) {
                j--;
                continue;
            }

            if (Character.toLowerCase(chars[i]) != Character.toLowerCase(chars[j])) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    //11报数
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String last = countAndSay(n - 1);
        char[] chars = last.toCharArray();
        char lastChar = chars[0];
        int size = 1;
        StringBuffer result = new StringBuffer();
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (c == lastChar) {
                size++;
                continue;
            }
            if (c != lastChar) {
                result.append(size).append(lastChar);
                lastChar = c;
                size = 1;
                continue;
            }
        }

        result.append(size).append(lastChar);

        return result.toString();
    }

    //12删除链表中的节点***
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    //13删除链表的倒数第N个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode firstNode = head;
        ListNode secondNode = head;
        for (int i = 0; i < n; i++) {
            secondNode = secondNode.next;
        }

        if (secondNode == null) {
            head = head.next;
            return head;
        }

        while (secondNode.next != null) {
            secondNode = secondNode.next;
            firstNode = firstNode.next;
        }

        firstNode.next = firstNode.next.next;
        return head;

    }

    //14反转链表
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return null;

        ListNode moveNode = head;

        while (moveNode.next != null) {
            ListNode tmp = moveNode.next;
            moveNode.next = moveNode.next.next;

            tmp.next = head;
            head = tmp;

        }

        return head;

//        if (head == null || head.next == null)
//            return head;
//        ListNode p = reverseList(head.next);
//        head.next.next = head;
//        head.next = null;
//        return p;
    }

    //15合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;

        if (l2 == null)
            return l1;

        ListNode newNode = new ListNode(-1);
        ListNode moveNode = newNode;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                moveNode.next = l2;
                return newNode.next;
            }
            if (l2 == null) {
                moveNode.next = l1;
                return newNode.next;
            }

            if (l1.val <= l2.val) {
                moveNode.next = l1;
                l1 = l1.next;
            } else {
                moveNode.next = l2;
                l2 = l2.next;
            }
            moveNode = moveNode.next;
        }

        return newNode.next;
    }

    //16请判断一个链表是否为回文链表。***
    public boolean isPalindrome(ListNode head) {
        //1效率低
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        if (list.size() < 2)
            return true;

        int middle = list.size() / 2;

        for (int i = 0; i < middle; i++) {
            if (!list.get(i).equals(list.get(list.size() - 1 - i))) {
                return false;
            }
        }

        return true;

    }

    //17给定一个链表，判断链表中是否有环。
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = slow.next;

        while (fast != slow) {
            if (fast.next == null || fast.next.next == null || slow.next == null)
                return false;

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }


    //18二叉树的最大深度****
    public int maxDepth(TreeNode root) {
        //1递归
//        if (root == null)
//            return 0;
//
//        int max = Math.max(maxDepth(root.left), maxDepth(root.right));
//        return max + 1;

        //2BFS
        if (root == null)
            return 0;
        int max = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null)
                    queue.offer(poll.left);
                if (poll.right != null)
                    queue.offer(poll.right);
            }

            max++;
        }
        return max;


    }

    //19验证二叉搜索树****
    public boolean isValidBST(TreeNode root) {
        //中序遍历的思想
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode pre = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (pre != null && curr.val <= pre.val) {
                return false;
            }
            pre = curr;
            curr = curr.right;
        }
        return true;
    }

    //20对称二叉树****
    public boolean isSymmetric(TreeNode root) {
        //类似BFS
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;


//        if(root==null) return true;
//        LinkedList<TreeNode> list=new LinkedList<>();
//        list.push(root.left);
//        list.push(root.right);
//        while(list.size()!=0){
//            TreeNode left=list.pop();
//            TreeNode right=list.pop();
//            if(left==null&&right==null){
//                continue;
//            }
//            if(left==null||right==null){
//                return false;
//            }
//            if(left.val!=right.val){
//                return false;
//            }
//            list.push(left.left);
//            list.push(right.right);
//            list.push(right.left);
//            list.push(left.right);
//        }
//        return true;

    }


    //21二叉树的层次遍历***
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int levelSize = q.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode treeNode = q.poll();
                currentLevel.add(treeNode.val);
                if (treeNode.left != null) {
                    q.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    q.add(treeNode.right);
                }
            }
            res.add(currentLevel);
        }
        return res;
    }

    //22将有序数组转换为二叉搜索树???
    public TreeNode sortedArrayToBST(int[] nums) {
        return null;
    }

    //23合并两个有序数组
    //输入:
    //nums1 = [1,2,3,0,0,0], m = 3
    //nums2 = [2,5,6],       n = 3
    //输出: [1,2,2,3,5,6]
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int last = m + n - 1, i = m - 1, j = n - 1; last >= 0; last--) {

            if (i < 0) {
                nums1[last] = nums2[j];
                j--;
                continue;
            }

            if (j < 0) {
                nums1[last] = nums1[i];
                i--;
                continue;
            }

            if (nums1[i] > nums2[j]) {
                nums1[last] = nums1[i];
                i--;
                continue;
            }

            if (nums1[i] <= nums2[j]) {
                nums1[last] = nums2[j];
                j--;
                continue;
            }
        }


    }


    //24爬楼梯 动态规划
    // 到达第 ii 阶的方法总数就是到第 (i-1)(i−1) 阶和第 (i-2)(i−2) 阶的方法数之和。
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    //25买卖股票的最佳时机
    // 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
    public int maxProfit2(int[] prices) {
        //暴力法
//        int max = 0;
//        for (int i = 0; i < prices.length - 1; i++) {
//            for (int j = i + 1; j < prices.length; j++) {
//                int tmp = prices[j] - prices[i];
//                if(tmp > max)
//                    max = tmp;
//            }
//        }
//        return max;

        //动态规划
        if (prices.length < 2) {
            return 0;
        }
        int maxProfit = 0;
        int smallest = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > smallest) {
                maxProfit = prices[i] - smallest > maxProfit ? prices[i] - smallest : maxProfit;
            } else {
                smallest = prices[i];
            }
        }
        return maxProfit;


    }


    //26最大子序和
    //给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    public int maxSubArray(int[] nums) {
        //暴力法
//        int max = nums[0];
//        for (int i = 0; i < nums.length; i++) {
//            int thisMax = nums[i];
//            int tmp = nums[i];
//            for (int j = i + 1; j < nums.length; j++) {
//                tmp+=nums[j];
//                if(tmp > thisMax)
//                    thisMax = tmp;
//            }
//            if(thisMax > max)
//                max = thisMax;
//        }
//        return max;

        //动态规划
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }


    //27打家劫舍****
    // 你是一个专业的小偷，计划偷窃沿街的房屋。
    //每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    public int rob(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return n == 0 ? 0 : nums[0];
        int[] memo = new int[n];
        memo[0] = nums[0];
        memo[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++)
            memo[i] = Math.max(memo[i - 1], nums[i] + memo[i - 2]);
        return memo[n - 1];

    }


    //28Fizz Buzz
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                list.add("FizzBuzz");
            } else if (i % 3 == 0) {
                list.add("Fizz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else {
                list.add("" + i);
            }
        }
        return list;
    }


    //29计数质数****
    public int countPrimes(int n) {
        boolean[] notPrim = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrim[i] == false) {
                count++;
                for (int j = 2; j * i < n; j++) {
                    notPrim[j * i] = true;
                }
            }
        }
        return count;
    }

    //30 3的幂
    public boolean isPowerOfThree(int n) {
        if (n == 0)
            return false;
        if(n==1)
            return true;

        while (true) {
            if (n / 3 == 1 && n % 3 == 0)
                return true;
            if (n % 3 != 0)
                return false;
            n = n / 3;
        }
    }




    public static void main(String[] args) {
        PrimaryTest test = new PrimaryTest();
        test.moveZeroes(new int[]{0, 1, 0, 3, 12});

        char[][] arr = new char[9][9];
        for (int i = 0; i < 9; i++) {
            char[] a = new char[]{'.', '.', '.', '.', '.', '.', '.', '.', '.'};
            arr[i] = a;
        }

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;


        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(2);
        ListNode b3 = new ListNode(3);
        ListNode b4 = new ListNode(4);
        ListNode b5 = new ListNode(5);
        b1.next = b2;
        b2.next = b3;
        b3.next = b4;
//        b4.next = b5;
//        test.merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
//        System.out.println(test.rob(new int[]{5, 1, 1, 5}));
        System.out.println(test.isPowerOfThree(27));
    }

}
