package com.wn.datastructure.bytedance;

import java.util.*;

public class ByteDanceTest {

    //1无重复字符的最长子串
    // 输入: "abcabcbb"
    //输出: 3
    //思路：使用滑动窗口
    public int lengthOfLongestSubstring(String s) {

        int max = 0;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; ) {
            for (int j = i; j < chars.length; j++) {
                Integer value = map.get(chars[j]);
                if (value != null) {
                    if (map.size() > max) {
                        max = map.size();
                    }
                    i = value + 1;
                    map.clear();
                    break;
                } else {
                    map.put(chars[j], j);

                }

                if (j == chars.length - 1) {
                    if (map.size() > max) {
                        max = map.size();
                    }
                    map.clear();
                    i++;
                }
            }
        }

        return max;


    }

    //2三数之和 ****
    //给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组
    public List<List<Integer>> threeSum(int[] nums) {
        return null;
    }

    //3岛屿的最大面积****  dfs？？
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length < 1)
            return 0;
        int maxA = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1)
                    maxA = Math.max(maxA, dfs(grid, i, j));
            }
        }
        return maxA;
    }

    private int dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0)
            return 0;
        //计算过的清零
        grid[x][y] = 0;
        return 1 + dfs(grid, x + 1, y) + dfs(grid, x - 1, y) +
                dfs(grid, x, y + 1) + dfs(grid, x, y - 1);
    }


    //4搜索旋转排序数组 你的算法时间复杂度必须是 O(log n) 级别。****
    public int search(int[] nums, int target) {
       return -1;
    }

    //5最长连续递增序列
    public int findLengthOfLCIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int max = 1;
        int thisMax = 1;
        for(int i =1;i<nums.length;i++){
            if(nums[i] > nums[i-1]){
                thisMax++;
                if(i == nums.length-1){
                    if(thisMax > max){
                        max = thisMax;
                    }
                }
            }else{
                if(thisMax > max){
                    max = thisMax;
                }
                thisMax = 1;
            }
        }

        return max;
    }


    //6数组中的第K个最大元素 ***
    public int findKthLargest(int[] nums, int k) {
        //暴力法，使用冒泡排序
//        for(int i=0;i<nums.length - 1;i++){
//            for(int j =0;j<nums.length - 1-i;j++){
//                if(nums[j+1] > nums[j]){
//                    int tmp = nums[j];
//                    nums[j] = nums[j+1];
//                    nums[j+1] = tmp;
//                }
//            }
//        }
//
//        return nums[k-1];

        //二叉小顶堆
//        Queue<Integer> queue = new PriorityQueue<>();
//        for(int num : nums){
//            queue.add(num);
//            //当queue的大小大于k，每次弹出堆顶的最小元素；
//            if(queue.size() > k) queue.poll();
//        }
//        return queue.peek();

        //桶排序
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int num : nums){
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int[] bucket = new int[max-min+1];
        for(int i=0; i<nums.length; i++){
            bucket[nums[i] - min]++;
        }
        int count = 0;
        for(int j=bucket.length-1; j>=0; j--){
            count += bucket[j];
            if(count >= k) return j+min;
        }

        return -1;


    }


    //7第k个排列
    public String getPermutation(int n, int k) {
        return null;
    }



    public static void main(String[] args) {
        ByteDanceTest test = new ByteDanceTest();
//        System.out.println(test.checkInclusion("adc", "dcda"));
        System.out.println(test.findKthLargest(new int[]{3,2,5,1,6,4},2));
    }
}
