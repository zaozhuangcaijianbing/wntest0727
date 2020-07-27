package com.wn.datastructure.tree;

import java.util.*;

public class TreeTest {

    //1、给定一个二叉树，返回它的前序遍历。****
    //进阶: 递归算法很简单，你可以通过迭代算法完成吗？
    public List<Integer> preorderTraversal(TreeNode root) {
        //1.1递归解法
        List<Integer> result = new ArrayList<Integer>();
        preorderTraversal(root, result);
        return result;

        //1.2非递归解法  基于栈
//        List<Integer> list = new ArrayList<>();
//        if(root == null){
//            return list;
//        }
//
//        Stack<TreeNode> stack = new Stack<>();
//        //先压根结点
//        stack.add(root);
//
//        while (!stack.isEmpty()) {
//            root = stack.pop();
//            list.add(root.val);
//            //在压右子树
//            if (root.right != null) {
//                stack.push(root.right);
//            }
//            //再压左子树
//            if (root.left != null) {
//                stack.push(root.left);
//            }
//            //这样出栈顺序就能做到根左右
//        }
//
//        return list;

    }

    private void preorderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            result.add(root.val);
            preorderTraversal(root.left, result);
            preorderTraversal(root.right, result);
        }
    }

    //2、给定一个二叉树，返回它的中序遍历。****
    //进阶: 递归算法很简单，你可以通过迭代算法完成吗？
    //2.1、递归
//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> res = new LinkedList<>();
//        in(root, res);
//        return res;
//    }
//
//    public void in(TreeNode root, List<Integer> res) {
//        if (root != null) {
//            in(root.left, res);
//            res.add(root.val);
//            in(root.right, res);
//        }
//    }

    //2.2、迭代 基于栈****
    //1将节点左子树全部压栈
    //2如果栈不为空则弹出栈顶元素并将其加入遍历结果，指向当前节点右子树
    //3若当前节点和栈均为空，则结束遍历；否则继续循环上述操作
    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//        Stack<TreeNode> stack = new Stack<>();
//        TreeNode cur = root;
//        while (cur != null || !stack.isEmpty()) {
//            if (cur != null) {
//                stack.push(cur);
//                cur = cur.left;
//            } else {
//                cur = stack.pop();
//                list.add(cur.val);
//                cur = cur.right;
//            }
//        }
//        return list;

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;

    }


    //3、后序遍历 递归
//    public List<Integer> postorderTraversal(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//        post(root, list);
//        return list;
//    }
//
//    private void post(TreeNode node, List<Integer> list) {
//        if (node != null) {
//            post(node.left, list);
//            post(node.right, list);
//            list.add(node.val);
//        }
//    }

    //3、后序遍历 迭代*****
    public List<Integer> postorderTraversal(TreeNode root) {
        return null;
    }


    //4、BFS广度优先遍历 使用队列 ****
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


    //5、二叉树的最大深度 递归和迭代****
    public int maxDepth(TreeNode root) {

        //4.1 递归
//        if (root == null) return 0;
//        int maxDepth = Math.max(maxDepth(root.left), maxDepth(root.right));
//        return maxDepth + 1;

        //4.2 迭代 DFS或者BFS
        // 广度优先搜索： 广度优先搜索最后到达的叶子节点的是最大深度。
        // 深度优先搜索：记录各节点的深度，通过访问到的叶子节点的深度更新max，直到遍历完成。
        if (root == null) {
            return 0;
        }
        int max = 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int levelSize = q.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode treeNode = q.poll();
                if (treeNode.left != null) {
                    q.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    q.add(treeNode.right);
                }
            }
            max++;
        }
        return max;
    }

    //6、给定一个二叉树，检查它是否是镜像对称的。****？？
    //递归和迭代,迭代类似BFS
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }

    //7、路径总和
    // 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
    public boolean hasPathSum(TreeNode root, int sum) {
        return false;
    }

    public TreeNode invertTree(TreeNode root) {
        help(root);
        return root;
    }

    public void help(TreeNode root) {
        if (root == null)
            return;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
    }

    //实现 pow(x, n) ，即计算 x 的 n 次幂函数。
    public double myPow(double x, int n) {
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return half(x, n);
    }

    private double half(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double half = half(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }

    }

    /**
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     * [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        result.add(first);
        help(result, first, nums, 0);
        return result;
    }

    public void help(List<List<Integer>> list, List<Integer> line, int[] nums, int count) {
        if (count == nums.length) {
            return;
        }
        List<Integer> newList = new ArrayList<>(line);
        newList.add(nums[count]);
        list.add(newList);
        help(list, newList, nums, count + 1);

        help(list, line, nums, count + 1);
    }


    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validate(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }


    public static void main(String[] args) {
        TreeTest test = new TreeTest();
        TreeNode d = new TreeNode(4);
        TreeNode c = new TreeNode(3);
        TreeNode b = new TreeNode(2);
        TreeNode a = new TreeNode(1);
        c.left = d;
        b.right = c;
        a.left = b;
        TreeNode e = new TreeNode(5);
        TreeNode f = new TreeNode(6);
        TreeNode g = new TreeNode(7);
        TreeNode h = new TreeNode(8);
        TreeNode k = new TreeNode(9);

        g.left = h;
        g.right = k;
        f.left = g;
        e.right = f;
        a.right = e;

        System.out.println(test.maxDepth(a));
    }
}

