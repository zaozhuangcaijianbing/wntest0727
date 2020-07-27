package com.wn.datastructure.queueandstack;

import java.util.Stack;

public class QueueAndStackTest {





    //1岛屿数量 BFS解法 ****
    //给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
    // 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
    public int numIslands(char[][] grid) {
        if (grid.length == 0)
            return 0;
        int col = grid[0].length;
        int row = grid.length;
        int nums = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    nums++;
                    bfs(i, j, grid);
                }
            }
        }
        return nums;
    }

    public void bfs(int x, int y, char[][] grid) {
        if (x < 0 || x >= grid.length)
            return;
        if (y < 0 || y >= grid[x].length)
            return;
        if (grid[x][y] == '1') {
            grid[x][y] = '0';
            bfs(x + 1, y, grid);
            bfs(x, y + 1, grid);
            bfs(x - 1, y, grid);
            bfs(x, y - 1, grid);
        }
    }


    //4、给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                char peek = stack.peek();
                if ((peek == '(' && c == ')') || (peek == '{' && c == '}') || (peek == '[' && c == ']')) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        if (stack.size() == 0) {
            return true;
        } else {
            return false;
        }
    }


    //5、根据每日 气温 列表，请重新生成一个列表，****
    // 对应位置的输入是你需要再等待多久温度才会升高的天数。如果之后都不会升高，请输入 0 来代替。
    //例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int len = T.length;
        int[] res = new int[len];
        for (int index = 0; index < len; index++) {
            while (!stack.isEmpty() && T[index] > T[stack.peek()]) {
                int tmp = stack.pop();
                res[tmp] = index - tmp;
            }
            stack.push(index);
        }
        return res;
    }

    public static void main(String[] args) {
        QueueAndStackTest test = new QueueAndStackTest();
//        System.out.println(test.isValid("()"));
//        char[][] grid = new char[][]{
//                {'1','1','0','0','0'},
//                {'1','1','0','0','0'},
//                {'0','0','1','0','0'},
//                {'0','0','0','1','1'}};
        char[][] grid = new char[][]{
                {'1','1','1'},
                {'0','1','0'},
                {'1','1','1'}};
        System.out.println(test.numIslands(grid));
    }
}
