package com.vime.Test;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class FirstTest {


    /**************************************************1****************************************************************/

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


    /**************************************************2****************************************************************/



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
        while (l1.next != null || l2.next != null) {
            l1 = l1.next == null ? zeroNode : l1.next;
            l2 = l2.next == null ? zeroNode : l2.next;
            ListNode nextNode = new ListNode((l1.val + l2.val + UpValue) % 10);
            curNode.next = nextNode;
            curNode = nextNode;
            UpValue = (l1.val + l2.val + UpValue) / 10;
        }
        if (UpValue > 0) {
            ListNode nextNode = new ListNode(UpValue);
            curNode.next = nextNode;
        }
        return ret;
    }


    /***************************************************3***************************************************************/



    /*
3. 无重复字符的最长子串
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
    * */
    @Test
    public void test3() {
        System.out.println(lengthOfLongestSubstring("tmmzuxt"));//dvdfd
    }

    public int lengthOfLongestSubstring(String s) {
        char[] array = s.toCharArray();
        int ret = 0;
        Set<Character> retSet = new HashSet<>();
        int curmax = 0;
        int startIndex = 0;
        for (int i = 0; i < array.length; ) {
            if (retSet.contains(array[i])) {
                retSet.clear();
                curmax = 0;

                //System.out.print("break at index:" + i + " before start:" + startIndex);

                i = startIndex + 1;
                startIndex++;
                //System.out.println(" new start:" + startIndex);

            } else {
                retSet.add(array[i]);
                curmax++;
                i++;
            }
            ret = curmax > ret ? curmax : ret;
        }
        return ret;
    }


    /*********************************************4*********************************************************************/

    /**
     * 4. 寻找两个正序数组的中位数
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
     * <p>
     * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * <p>
     * 你可以假设 nums1 和 nums2 不会同时为空。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * nums1 = [1, 3]
     * nums2 = [2]
     * <p>
     * 则中位数是 2.0
     * 示例 2:
     * <p>
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * <p>
     * 则中位数是 (2 + 3)/2 = 2.5
     * <p>
     * TODO 每一次找下下标为 k - k/2,  每次排除k/2个元素
     */
    @Test
    public void test4() {
    }
    /*public double findMedianSortedArrays(int[] nums1, int[] nums2) {

    }*/


    /****************************************************5**************************************************************/

    /**
     * 5. 最长回文子串
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * <p>
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     */


    @Test
    public void test5() {
        System.out.println(longestPalindrome("babadada"));
    }

    public String longestPalindrome(String s) {
        StringBuffer s_builder = new StringBuffer(s);
        if(s.equals(s_builder.reverse().toString())) return  s;
        char[] array = s.toCharArray();
        int length = array.length;

        int retLength = 0;
        String retStr = String.valueOf(array[0]);

        for (int i = 0; i < length; i++) {
            int j = length - 1;
            for (; j > i; j--) {
                if (array[i] == array[j]) {
                    String newStr = s.substring(i, j + 1);
                    StringBuffer sb = new StringBuffer(newStr);
                    if (newStr.equals(sb.reverse().toString())) {
                        if (newStr.length() > retLength) {
                            retLength = newStr.length();
                            retStr = newStr;
                            break;
                        }
                    }
                }
            }
            //if(retLength == j + 1 - i) break;
        }
        return retStr;
    }
}






























