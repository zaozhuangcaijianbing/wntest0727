package com.wn.datastructure.arrayandstring;

import com.wn.datastructure.bst.TreeNode;

import java.util.*;

public class Test1 {

    public List<String> readBinaryWatch(int num) {
        List<String> res = new LinkedList<>();
        //直接遍历  0:00 -> 12:00   每个时间有多少1
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (count1(i) + count1(j) == num)
                    res.add(i + ":" + (j < 10 ? "0" + j : j));
            }
        }
        return res;
    }




    /**
     * 计算二进制中1的个数
     *
     * @param n
     * @return
     */
    int count1(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }


    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    //1、寻找数组的中心索引，数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
    public int pivotIndex(int[] nums) {
        if (nums == null)
            return -1;

        int length = nums.length;
        if (length <= 2) {
            return -1;
        }


        for (int i = 0; i < length; i++) {
            int left = 0;
            int right = 0;
            for (int j = 0; j < i; j++) {
                left += nums[j];
            }

            for (int j = i + 1; j < length; j++) {
                right += nums[j];
            }

            if (left == right) {
                return i;
            }
        }

        return -1;
    }


    //2、在一个给定的数组nums中，总是存在一个最大元素 。
    //查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
    public int dominantIndex(int[] nums) {
        if (nums == null)
            return -1;

        int length = nums.length;
        if (length == 0) {
            return -1;
        }

        if (length == 1) {
            return 0;
        }


        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            if (j != maxIndex && nums[j] * 2 > max) {
                return -1;
            }
        }

        return maxIndex;
    }


    //3、加一
    // 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
    public int[] plusOne(int[] digits) {
        if (digits == null)
            return null;

        int length = digits.length;
        if (length == 0) {
            return null;
        }

        int plus = 1;
        for (int i = length - 1; i >= 0; i--) {
            if (plus == 1) {
                if (digits[i] < 9) {
                    digits[i] += plus;
                    plus = 0;
                } else {
                    digits[i] = 0;
                    plus = 1;
                }
            }
        }

        if (plus == 1) {
            int[] result = new int[length + 1];
            result[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                result[i + 1] = digits[i];
            }
            return result;
        }
        return digits;
    }

    //4、对角线遍历***
    // 给定一个含有 M x N 个元素的矩阵（M 行，N 列），
    // 请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[]{};
        }
        int length = 0;
        int y = matrix.length;
        int x = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                length++;
            }
        }

        int[] result = new int[length];
        int index = 0;
        //遍历次数，遍历次数=横纵坐标和
        int degree = x + y - 1;
        boolean flag = false;
        for (int i = 0; i < degree; i++) {
            if (flag) {
                for (int j = 0; j <= i; j++) {
                    if (i - j >= x || j >= y)
                        continue;
                    result[index] = matrix[j][i - j];
                    index++;
                }
                flag = false;
            } else {
                for (int j = i; j >= 0; j--) {
                    if (i - j >= x || j >= y)
                        continue;
                    result[index] = matrix[j][i - j];
                    index++;
                }

                flag = true;
            }

        }
        return result;
    }

    //5、螺旋矩阵***
    //给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
    public List<Integer> spiralOrder(int[][] matrix) {
        return null;
    }

    //6、杨辉三角
    public List<List<Integer>> generate(int numRows) {
        if (numRows < 1)
            return new ArrayList<>();
        List<List<Integer>> returnList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> singleLineList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    singleLineList.add(1);
                } else {
                    singleLineList.add(returnList.get(i - 1).get(j - 1) + returnList.get(i - 1).get(j));
                }
            }
            returnList.add(singleLineList);
        }

        return returnList;
    }


    //7、二进制求和
    public String addBinary(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
        int length;
        if (aLength > bLength) {
            length = aLength;
        } else {
            length = bLength;
        }

        List<Integer> charArray = new ArrayList<Integer>();
        int plus = 0;
        for (int i = 1; i <= length; i++) {
            int first = 0;
            int second = 0;
            if (aLength - i >= 0) {
                first = Integer.parseInt("" + a.charAt(aLength - i));
            }

            if (bLength - i >= 0) {
                second = Integer.parseInt("" + b.charAt(bLength - i));
            }

            int tmp = first + second + plus;
            if (tmp <= 1) {
                plus = 0;
                charArray.add(tmp);
            } else if (tmp == 2) {
                plus = 1;
                charArray.add(0);
            } else if (tmp == 3) {
                plus = 1;
                charArray.add(1);
            }
        }

        if (plus == 1) {
            charArray.add(1);
        }

        int size = charArray.size();
        StringBuffer result = new StringBuffer();
        for (int i = size - 1; i >= 0; i--) {
            result.append(charArray.get(i));
        }

        return result.toString();
    }


    //8、实现 strStr() 函数。
    //给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        if (needle.length() == 0) {
            return 0;
        }

        char[] sourceArray = haystack.toCharArray();

        char[] target = needle.toCharArray();

        for (int i = 0; i < sourceArray.length; i++) {
            if (sourceArray.length - i < target.length) {
                return -1;
            }

            for (int j = 0; j < target.length; j++) {
                if (target[j] != sourceArray[i + j]) {
                    break;
                }

                if (j == target.length - 1) {
                    return i;
                }
            }

        }

        return -1;
    }

    //9、最长公共前缀，编写一个函数来查找字符串数组中的最长公共前缀。
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; ; i++) {

            if (i >= strs[0].length()) {
                return stringBuffer.toString();
            }

            char one = strs[0].charAt(i);


            for (int j = 1; j < strs.length; j++) {
                String line = strs[j];

                if (i >= strs[j].length()) {
                    return stringBuffer.toString();
                }

                if (one != line.charAt(i)) {
                    return stringBuffer.toString();
                }

                if (j == strs.length - 1) {
                    stringBuffer.append(one);
                }
            }
        }
    }

    //10、翻转字符串
    public void reverseString(char[] s) {
        int begin = 0;
        int end = s.length - 1;
        for (int i = 0; ; i++) {
            if (begin + i >= end - i) {
                break;
            }
            char left = s[begin + i];
            char right = s[end - i];
            s[end - i] = left;
            s[begin + i] = right;
        }
    }

    //11、数组拆分***
    //不使用排序的话？？
    //给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            result += nums[i];
        }
        return result;
    }

    //12、两数之和 II - 输入有序数组
    //输入: numbers = [2, 7, 11, 15], target = 9
    //输出: [1,2]
    //解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int size = numbers[i] + numbers[j];
                if (target == size) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }

        return null;
    }

    //13、给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
    //不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
    public int removeElement(int[] nums, int val) {

        int length = nums.length;

        int emptyIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                if (emptyIndex == -1) {
                    emptyIndex = i;
                }
                length--;
            } else {
                if (emptyIndex > -1) {
                    nums[emptyIndex] = nums[i];
                    emptyIndex++;
                }
            }
        }

        return length;
    }

    //14、给定一个二进制数组， 计算其中最大连续1的个数。
    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;

        int thisPeriod = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                thisPeriod++;
            } else {
                if (thisPeriod > result) {
                    result = thisPeriod;
                }
                thisPeriod = 0;
            }

            if (i == nums.length - 1) {
                if (thisPeriod > result) {
                    result = thisPeriod;
                }
            }
        }

        return result;
    }

    //15、长度最小的子数组***
    public int minSubArrayLen(int s, int[] nums) {
        int result = 0;

        return result;
    }


    //16、给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
    //输入: [-1,-100,3,99] 和 k = 2
    //输出: [3,99,-1,-100]
    //解释:
    //向右旋转 1 步: [99,-1,-100,3]
    //向右旋转 2 步: [3,99,-1,-100]
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        k %= length;
        for (int i = 0; i < k; i++) {
            int now = 0;
            int last = 0;
            for (int j = 0; j < length; j++) {
                now = nums[j];
                if (j == 0) {
                    nums[j] = nums[length - 1];
                } else {
                    nums[j] = last;
                }
                last = now;
            }
        }
    }


    /**
     * 输入：n = 3
     * 输出：[
     *        "((()))",
     *        "(()())",
     *        "(())()",
     *        "()(())",
     *        "()()()"
     *      ]
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        helpGenerate(0,0,n,result,"");
        return result;
    }



    private void helpGenerate(int left,int right,int n,List<String> list,String s){
        if(left == n && right == n){
            list.add(s);
            return;
        }

        if(left < n){
            helpGenerate(left+1,right,n,list,s + "(");
        }

        if(right < left){
            helpGenerate(left,right+1,n,list,s + ")");
        }
    }


    public static void main(String[] args) {

        Test1 test1 = new Test1();
        int[] param = {9, 9, 9, 9, 9};
//        System.out.println(test1.pivotIndex(param));
//        System.out.println(test1.dominantIndex(param));
//        System.out.println(Arrays.toString(test1.plusOne(param)));

//        int[][] a = new int[2][];
//        a[0] = new int[]{};
//        a[1] = new int[]{};

//        System.out.println(Arrays.toString(test1.findDiagonalOrder(a)));

//        System.out.println(test1.generate(5));

//        System.out.println(test1.addBinary("111","11"));

//        System.out.println(test1.strStr("", ""));
//
//        System.out.println("".indexOf(""));

//        System.out.println(test1.longestCommonPrefix(new String[]{"aca", "cca"}));
//        char[] paramArr = "abcde".toCharArray();
//        test1.reverseString(paramArr);
//        System.out.println(Arrays.toString(paramArr));

//        System.out.println(test1.arrayPairSum(new int[]{1, 2, 3, 4}));

//        System.out.println(Arrays.toString(test1.twoSum(new int[]{1, 2, 3, 3}, 5)));

//        System.out.println(test1.removeElement(new int[]{2, 2, 3, 3}, 2));

//        System.out.println(test1.findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1}));
        int[] array = new int[]{1, 2, 3, 4, 5};
        test1.rotate(array, 11);
        System.out.println(Arrays.toString(array));
    }
}
