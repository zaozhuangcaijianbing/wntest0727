package com.wn.datastructure.binarysearch;

//二分查找
public class BinarySearchTest {
    //1、给定一个n个元素有序的（升序）整型数组nums和一个目标值target写一个函数搜索nums中的target，
    // 如果目标值存在返回下标，否则返回-1。
    //递归和迭代
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mIndex = (left + right) / 2;
            if (nums[mIndex] == target) {
                return mIndex;
            } else if (nums[mIndex] < target) {
                left = mIndex + 1;
            } else if (nums[mIndex] > target) {
                right = mIndex - 1;
            }
        }

        return -1;
    }


    //2、x的平方根 计算并返回 x 的平方根，其中 x 是非负整数。
    public int mySqrt(int x) {
        if (x == 1) {
            return 1;
        }
        long left = 0;
        long right = x;
        while (left <= right) {
            long middle = ( left +  right)/2 ;
            long sqrt = middle * middle;
            long sqrtPlus = (middle + 1) * (middle + 1);

            if (sqrt <= x && sqrtPlus > x) {
                return (int)middle;
            }

            if (sqrt > x) {
                right = middle - 1;
            }
            if (sqrt < x) {
                left = middle + 1;
            }
        }
        return -1;
    }




    public static void main(String[] args) {
        BinarySearchTest test = new BinarySearchTest();
//        test.search(new int[]{1},2);
        System.out.println(test.mySqrt(2147395599));
    }
}
