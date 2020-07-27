package com.wn.datastructure.hash;


import java.util.*;

public class HashTest {
    //3、给定一个整数数组，判断是否存在重复元素。
    public boolean containsDuplicate(int[] nums) {
        Set set = new HashSet();
        for (int e : nums) {
            if (set.contains(e)) {
                return true;
            }
            set.add(e);
        }
        return false;
    }

    //4、给定一个非空整数数组，除了某个元素只出现一次以外，***
    // 其余每个元素均出现两次。找出那个只出现了一次的元素。
    //你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
    public int singleNumber(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }

    //5、***给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
    public int[] twoSum(int[] nums, int target) {
        //使用HashMap标记已经遍历过的元素及其索引.
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //得到target和当前元素的差.
            int sub = target - nums[i];
            //判断这个差值是否之前已经被加入map中，如果是，则直接返回最终结果.
            if (map.containsKey(sub)) {
                return new int[]{map.get(sub), i};
            } else {
                //如果否，则将当前元素加入到map中.
                map.put(nums[i], i);
            }
        }
        //若此时返回，则返回2个元素的空数组.
        return new int[2];
    }

    //6、给定两个字符串 s 和 t，判断它们是否是同构的。
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        if (chars.length != chart.length) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            char first = chars[i];
            char second = chart[i];
            if (map.get(first) == null) {
                if (map.containsValue(second)) {
                    return false;
                }
                map.put(first, second);
            } else {
                char value = map.get(first);
                if (value != second) {
                    return false;
                }
            }
        }
        return true;

    }


    private List<List<String>> output = new ArrayList<>();

    // 用于标记是否被列方向的皇后被攻击
    int[] rows;
    // 用于标记是否被主对角线方向的皇后攻击
    int[] mains;
    // 用于标记是否被次对角线方向的皇后攻击
    int[] secondary;
    // 用于存储皇后放置的位置
    int[] queens;

    int n;

    public List<List<String>> solveNQueens(int n) {
        // 初始化
        rows = new int[n];
        mains = new int[2 * n - 1];
        secondary = new int[2 * n - 1];
        queens = new int[n];
        this.n = n;

        // 从第一行开始回溯求解 N 皇后
        backtrack(0);

        return output;
    }

    // 在一行中放置一个皇后
    private void backtrack(int row) {
        if (row >= n) return;
        // 分别尝试在 row 行中的每一列中放置皇后
        for (int col = 0; col < n; col++) {
            // 判断当前放置的皇后不被其他皇后的攻击
            if (isNotUnderAttack(row, col)) {
                // 选择，在当前的位置上放置皇后
                placeQueen(row, col);
                // 当当前行是最后一行，则找到了一个解决方案
                if (row == n - 1) addSolution();
                // 在下一行中放置皇后
                backtrack(row + 1);
                // 撤销，回溯，即将当前位置的皇后去掉
                removeQueen(row, col);
            }
        }
    }

    // 判断 row 行，col 列这个位置有没有被其他方向的皇后攻击
    private boolean isNotUnderAttack(int row, int col) {
        // 判断的逻辑是：
        //      1. 当前位置的这一列方向没有皇后攻击
        //      2. 当前位置的主对角线方向没有皇后攻击
        //      3. 当前位置的次对角线方向没有皇后攻击
        int res = rows[col] + mains[row - col + n - 1] + secondary[row + col];
        // 如果三个方向都没有攻击的话，则 res = 0，即当前位置不被任何的皇后攻击
        return res == 0;
    }

    // 在指定的位置上放置皇后
    private void placeQueen(int row, int col) {
        // 在 row 行，col 列 放置皇后
        queens[row] = col;
        // 当前位置的列方向已经有皇后了
        rows[col] = 1;
        // 当前位置的主对角线方向已经有皇后了
        mains[row - col + n - 1] = 1;
        // 当前位置的次对角线方向已经有皇后了
        secondary[row + col] = 1;
    }

    // 移除指定位置上的皇后
    private void removeQueen(int row, int col) {
        // 移除 row 行上的皇后
        queens[row] = 0;
        // 当前位置的列方向没有皇后了
        rows[col] = 0;
        // 当前位置的主对角线方向没有皇后了
        mains[row - col + n - 1] = 0;
        // 当前位置的次对角线方向没有皇后了
        secondary[row + col] = 0;
    }

    /**
     * 将满足条件的皇后位置放入output中
     */
    public void addSolution() {
        List<String> solution = new ArrayList<String>();
        for (int i = 0; i < n; ++i) {
            int col = queens[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < col; ++j) sb.append(".");
            sb.append("Q");
            for (int j = 0; j < n - col - 1; ++j) sb.append(".");
            solution.add(sb.toString());
        }
        output.add(solution);
    }


    public static void main(String[] args) {
        HashTest test = new HashTest();
        System.out.println(test.solveNQueens(8));
//        System.out.println(test.isIsomorphic("aa", "ab"));
    }
}
