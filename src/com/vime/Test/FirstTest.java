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


    /*
    2. 两数相加
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
    * */

    @Test
    public void test2() {
       /* ListNode n1 = new ListNode(9);
        ListNode n2 = new ListNode(8);
        n1.next = n2;

        ListNode n3 = new ListNode(1);

        addTwoNumbers(n1, n3);*/

        System.out.println(Integer.parseInt("9999999991"));
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1.val == 0) return  l2;
        if(l2.val == 0) return  l1;
        /*ListNode curNode_1 = l1;
        int sum_1 = curNode_1.val;
        while (curNode_1.next != null) {
            sum_1 *= 10;
            sum_1 += curNode_1.next.val * 10;
            curNode_1 = curNode_1.next;
        }

        ListNode curNode_2 = l2;
        int sum_2 = curNode_2.val;
        while (curNode_2.next != null) {
            sum_2 *= 10;
            sum_2 += curNode_2.next.val;
            curNode_2 = curNode_2.next;
        }*/
        StringBuffer s1 = new StringBuffer(String.valueOf(l1.val));
        while (l1.next != null)
        {
            l1 = l1.next;
            s1.append(l1.val);
        }
        long sum_1 = Long.parseLong(s1.reverse().toString());

        StringBuffer s2 = new StringBuffer(String.valueOf(l2.val));
        while (l2.next != null)
        {
            l2 = l2.next;
            s2.append(l2.val);
        }
        long sum_2 = Long.parseLong(s2.reverse().toString());

        long total = sum_1 + sum_2;

        ListNode ret = new ListNode((int)(total % 10));
        ListNode cur = ret;
        while ((total /= 10) > 0) {
            ListNode next = new ListNode((int)(total % 10));
            cur.next = next;
            cur = next;
        }
        return ret;
    }
}
