package com.vime.Test;

import org.junit.Test;

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




    /******************************************************************************************************************/



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
        ListNode n1 = new ListNode(9);
        ListNode n2 = new ListNode(8);
        n1.next = n2;

        ListNode n3 = new ListNode(1);

        addTwoNumbers(n1, n3);
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode((l1.val + l2.val) % 10);
        ListNode curNode = ret;
        int UpValue = (l1.val + l2.val) / 10;
        ListNode zeroNode = new ListNode(0);
        while (l1.next != null || l2.next != null)
        {
            l1 = l1.next == null ? zeroNode: l1.next;
            l2 = l2.next == null ? zeroNode : l2.next;
            ListNode nextNode = new ListNode((l1.val + l2.val+ UpValue) % 10);
            curNode.next = nextNode;
            curNode = nextNode;
            UpValue = (l1.val + l2.val + UpValue) / 10;
        }
        if (UpValue > 0)
        {
            ListNode nextNode = new ListNode(UpValue);
            curNode.next = nextNode;
        }
        return ret;
    }
}
