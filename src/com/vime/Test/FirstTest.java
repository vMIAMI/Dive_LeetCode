package com.vime.Test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FirstTest {


    /*
1. 两数之和
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
    *
    * */
    @Test
    public void test1() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] ret = twoSum(nums, target);
    }

    /*
    暴力破解法：时间复杂度 O(n*n) 空间复杂度 O(1)
    另外 放hashmap, containsKey()  时间复杂度 O(n) 空间复杂度 O(1)
    * */

    /*
     * hashMap.containsKey()的时间复杂度，使用指针指向数组引用，时间复杂度为O(1)，未命中时，才回去遍历红黑树，时间复杂度为O(n)，有兴趣的可以取看看 https://blog.csdn.net/qingtian_1993/article/details/80763381 这篇帖子
     * */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] + nums[j] == target) return new int[]{i, j};
        }
        return null;
    }
}
