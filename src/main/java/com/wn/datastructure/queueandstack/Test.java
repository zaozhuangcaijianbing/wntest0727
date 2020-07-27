package com.wn.datastructure.queueandstack;

import java.util.*;

public class Test {
    static int BFS(Node root, Node target) {
        Queue<Node> queue = new LinkedList<>();  // store all nodes which are waiting to be processed
        int step = 0;       // number of steps neeeded from root to current node
        // initialize
        queue.add(root);
        // BFS
        while (!queue.isEmpty()) {
            step = step + 1;
            // iterate the nodes which are already in the queue
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Node cur = queue.peek();
                if (cur == target) {
                    return step;
                }
                if (cur.children != null && cur.children.size() > 0) {
                    for (Node next : cur.children) {
                        queue.add(next);
                    }
                }

                queue.remove();
            }
        }
        return -1;          // there is no path from root to target
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        return maxarea;
    }


    public static void main2(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node g = new Node(7);
        Node h = new Node(8);

        List<Node> list2 = new ArrayList<>();
        list2.add(b);
        list2.add(c);
        a.children = list2;

        List<Node> list31 = new ArrayList<>();
        list31.add(d);
        list31.add(e);

        b.children = list31;

        List<Node> list32 = new ArrayList<>();
        list32.add(f);
        list32.add(g);
        c.children = list32;

        List<Node> list4 = new ArrayList<>();
        list4.add(h);
        f.children = list4;

        System.out.println(BFS(a, h));

    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                Character pop = stack.pop();
                if ((pop == '(' && c != ')') || (pop == '[' && c != ']') || (pop == '{' && c != '}')) {
                    return false;
                }

            }
        }

        if (stack.size() == 0) {
            return true;
        }
        return false;
    }


    public static int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack();
        for (int i = T.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.isPerfectSquare(10);
    }


    public boolean isPerfectSquare(int num) {
        int left = 0;
        int right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid == num) {
                return true;
            } else if ((long) mid * mid > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }


    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }


    public static int minMutation(String start, String end, String[] bank) {
        char[] chars = new char[]{'A', 'C', 'G', 'T'};
        Set<String> set = new HashSet(Arrays.asList(bank));
        Queue<String> queue = new LinkedList();
        queue.add(start);

        int deep = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (end.equals(poll)) {
                    return deep;
                }

                for (int j = 0; j < poll.length(); j++) {
                    for (int k = 0; k < chars.length; k++) {
                        char[] pollChars = poll.toCharArray();
                        char[] newChars = new char[poll.length()];
                        System.arraycopy(pollChars, 0, newChars, 0, poll.length());
                        newChars[j] = chars[k];
                        String newStr = new String(newChars);
                        if (set.contains(newStr)) {
                            set.remove(newStr);
                            queue.add(newStr);
                        }
                    }
                }
            }

            deep++;
        }
        return -1;
    }


    //数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
    //输入：n = 3
    //输出：[
    //       "((()))",
    //       "(()())",
    //       "(())()",
    //       "()(())",
    //       "()()()"
    //     ]
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }

        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

}
