package com.wn.datastructure.bst;

import java.util.*;

//对于二叉搜索树，我们可以通过中序遍历得到一个递增的有序序列。
// 因此，中序遍历是二叉搜索树中最常用的遍历方法。
public class BSTTest {

    //1、给定一个二叉树，判断其是否是一个有效的二叉搜索树***
    //递归和迭代
//    public boolean isValidBST(TreeNode root) {
//        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
//    }
//
//    public boolean isValidBST(TreeNode root, long min, long max) {
//        if (root == null)
//            return true;
//        if (root.val >= max || root.val <= min)
//            return false;
//        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
//    }

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && root.val <= pre.val)
                return false;
            pre = root;
            root = root.right;
        }
        return true;
    }


    //2、二叉搜索树搜索
    // 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
    //迭代
//    public TreeNode searchBST(TreeNode root, int val) {
//        while(root!=null){
//            if (root.val == val) {
//                return root;
//            } else if (root.val > val) {
//                root = root.left;
//            } else if (root.val < val) {
//                root = root.right;
//            }
//        }
//        return null;
//    }

    //递归
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val)
            return root;

        if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }


    //3、二叉搜索树中插入元素
    //迭代
//    public TreeNode insertIntoBST(TreeNode root, int val) {
//        TreeNode tmp = root;
//
//        while (true) {
//            if (tmp.val > val) {
//                if (tmp.left != null) {
//                    tmp = tmp.left;
//                } else {
//                    tmp.left = new TreeNode(val);
//                    return root;
//                }
//            }
//
//            if (tmp.val < val) {
//                if (tmp.right != null) {
//                    tmp = tmp.right;
//                } else {
//                    tmp.right = new TreeNode(val);
//                    return root;
//                }
//            }
//        }
//    }

    //递归
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        else
            root.left = insertIntoBST(root.left, val);
        return root;
    }

    /**
     * 1
     * 2   3
     * 4  5
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode pop = stack.pop();
            result.add(pop.val);
            root = pop.right;
        }

        return result;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < cost.length + 1; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }

        return dp[cost.length];
    }


    public int waysToStep(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n == 3)
            return 4;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        for (int i = 3; i < n; i++) {
            long sum = (long) dp[i - 1] + (long) dp[i - 2] + (long) dp[i - 3];
            if (sum >= 1000000007)
                dp[i] = (int) (sum % 1000000007);
        }
        return dp[n - 1];
    }

    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
            max = max > dp[i] ? max : dp[i];
        }
        return max;
    }


    //nums = [0,0,1,1,1,2,2,3,3,4],
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
        }

        return i + 1;
    }


    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = map.get(nums[i]);
            if (count == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], ++count);
                if (count > nums.length / 2) {
                    return nums[i];
                }
            }
        }
        return -1;
    }


    public static String longestPalindrome(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + result.length(); j < s.length(); j++) {
                if (isPalindrome(s, i, j)) {
                    if (j - i + 1 > result.length())
                        result = s.substring(i, j + 1);
                }
            }
        }

        return result;
    }

    private static boolean isPalindrome(String s, int begin, int end) {
        int middle = begin + (end - begin) / 2;
        int i = begin;
        int j = end;
        while (j > i) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int length = length1 + length2;
        int yu = length % 2;
        int middle = length / 2;

        int a = 0;
        int b = 0;

        int sum = 0;
        for (int i = 0; i <= middle; i++) {
            if (i == middle - 1 && yu == 0) {
                if (a >= length1) {
                    sum += nums2[b];
                } else if (b >= length2) {
                    sum += nums1[a];
                } else if (nums1[a] > nums2[b]) {
                    sum += nums2[b];
                } else {
                    sum += nums1[a];
                }
            } else if (i == middle && yu == 0) {
                if (a >= length1) {
                    sum += nums2[b];
                } else if (b >= length2) {
                    sum += nums1[a];
                } else if (nums1[a] > nums2[b]) {
                    sum += nums2[b];
                } else {
                    sum += nums1[a];
                }
                return ((double) sum) / 2;
            } else if (i == middle && yu == 1) {
                if (a >= length1) {
                    return nums2[b];
                } else if (b >= length2) {
                    return nums1[a];
                } else if (nums1[a] > nums2[b]) {
                    return nums2[b];
                } else {
                    return nums1[a];
                }
            }


            if (nums1[a] > nums2[b]) {
                if (b < length2) {
                    b++;
                } else {
                    a++;
                }

            } else {
                if (a < length1) {
                    a++;
                } else {
                    b++;
                }
            }
        }

        return 0;
    }


    public static int myAtoi(String str) {
        int sign = 1;
        int result = 0;
        char first = ' ';
        for (int i = 0; i < str.length(); i++) {
            if (first == ' ') {
                if (str.charAt(i) == ' ') {
                    continue;
                } else if (str.charAt(i) == '-') {
                    first = '-';
                    sign = -1;
                } else if (str.charAt(i) == '+') {
                    first = '+';
                } else if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                    first = str.charAt(i);
                    result = str.charAt(i) - '0';
                } else {
                    return 0;
                }
            } else {
                if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                    if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && (str.charAt(i) - '0') > Integer.MAX_VALUE % 10)) {
                        return Integer.MAX_VALUE;
                    }

                    if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && (str.charAt(i) - '0') > -(Integer.MIN_VALUE % 10))) {
                        return Integer.MIN_VALUE;
                    }

                    result = result * 10 + sign * (str.charAt(i) - '0');
                } else {
                    break;
                }
            }
        }


        return result;
    }


    public static int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        int length = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int begin = i + 1;
            int end = length - 1;
            while (begin < end) {
                int sum = nums[i] + nums[begin] + nums[end];
                if (sum == 0) {
                    List<Integer> list = new ArrayList();
                    list.add(nums[i]);
                    list.add(nums[begin]);
                    list.add(nums[end]);
                    result.add(list);

                    while (begin < end && nums[begin] == nums[begin + 1]) {
                        begin++;
                    }
                    while (begin < end && nums[end] == nums[end - 1]) {
                        end--;
                    }
                    begin++;
                    end--;
                } else if (sum > 0) {
                    end--;
                } else {
                    begin++;
                }

            }

        }
        return result;
    }

//    private String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
//    public List<String> letterCombinations(String digits) {
//        List<String> list = new ArrayList<>();
//        for(int i=0;i<digits.length();i++){
//            int index = digits.charAt(i)- '0';
//            String str = map[index];
//            list = convert(list,str);
//        }
//
//        return list;
//    }
//
//    public List<String> convert(List<String> list,String str){
//        List<String> result = new ArrayList<>();
//        for(int i=0;i<str.length();i++){
//            if(list.size() == 0){
//                result.add("" + str.charAt(i));
//            }else{
//                for(int j=0;j<list.size();j++){
//                    result.add(list.get(j) + str.charAt(i));
//                }
//            }
//
//        }
//        return result;
//
//    }


    /**
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * <p>
     * 输入: [1,0,0,3,12]
     * 输出: [1,3,12,0,0]
     */
    public static void moveZeroes(int[] nums) {
        int zeroIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && zeroIndex == -1) {
                zeroIndex = i;
            } else if (nums[i] != 0 && zeroIndex > -1) {
                nums[zeroIndex] = nums[i];
                nums[i] = 0;
                zeroIndex++;
            }
        }
    }


    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     * <p>
     * 示例 1:
     * <p>
     * 输入: coins = [1, 2, 5], amount = 11
     * 输出: 3
     * 解释: 11 = 5 + 5 + 1
     * 示例 2:
     * <p>
     * 输入: coins = [2], amount = 3
     * 输出: -1
     * <p>
     * 输入: coins = [2, 3, 5], amount = 14
     */
    int min = Integer.MAX_VALUE;
    Map<Integer, Integer> map = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        help(0, coins, amount);

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private void help(int count, int[] coins, int amount) {
        for (int i = 0; i < coins.length; i++) {
            if (amount > 0) {
                help(count + 1, coins, amount - coins[i]);
            } else if (amount == 0) {
                if (count < min) {
                    min = count;
                }
            } else {
                return;
            }
        }
    }

    public static int[] searchRange(int[] nums, int target) {
        int begin = 0;
        int end = nums.length - 1;
        int[] result = new int[2];
        while (end > begin) {
            int middle = (begin + end) / 2;
            if (nums[middle] == target) {

                for (int i = middle; i >= 0; i--) {
                    if (nums[i] != target) {
                        result[0] = i + 1;
                        break;
                    }
                }

                for (int i = middle; i < nums.length; i++) {
                    if (nums[i] != target) {
                        result[1] = i - 1;
                        break;
                    }
                }
            } else if (nums[middle] > target) {
                end = middle;
            } else {
                begin = middle;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        BSTTest test = new BSTTest();

        int result = test.coinChange(new int[]{1, 2, 5}, 11);
        System.out.println(result);
//        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
//        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
//        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{3, 4}));
//        System.out.println(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
//        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(1 << 31 - 1);
//        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(1 << 31);
//
//        BSTTest test = new BSTTest();
//        TreeNode e = new TreeNode(5);
//        TreeNode d = new TreeNode(4);
//        TreeNode c = new TreeNode(3);
//        TreeNode b = new TreeNode(2);
//        TreeNode a = new TreeNode(1);
//        a.right = c;
//        a.left = b;
//        b.left = d;
//        c.right = e;
//
//        test.minDepth(a);
    }


    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        int depth = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left == null && poll.right == null)
                    return depth;

                if (poll.left != null)

                    queue.add(poll.left);
                if (poll.right != null)
                    queue.add(poll.right);
            }
            depth++;
        }
        return depth;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;

        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }


        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

}

