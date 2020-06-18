package com.vime.Test;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (s.equals(s_builder.reverse().toString())) return s;
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

    /****************************************************6**************************************************************/

    /**
     * 6. Z 字形变换
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * <p>
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * <p>
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     * <p>
     * 请你实现这个将字符串进行指定行数变换的函数：
     * <p>
     * string convert(string s, int numRows);
     * 示例 1:
     * <p>
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     * 示例 2:
     * <p>
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     * <p>
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     */
    @Test
    public void test6() {
        System.out.println(convert("123456789123456789", 2));
    }

    public String convert(String s, int numRows) {
        StringBuffer[] ret = new StringBuffer[numRows];
        for (int i = 0; i < numRows; i++) {
            ret[i] = new StringBuffer();
        }
        if (numRows <= 1) return s;
        char[] array = s.toCharArray();
        int appendIndex = 0;
        boolean isDown = false;
        for (int i = 0; i < array.length; i++) {
            ret[appendIndex].append(array[i]);
            if (appendIndex == 0 || appendIndex == numRows - 1) isDown = !isDown;
            appendIndex += isDown ? 1 : -1;
        }
        StringBuffer r = new StringBuffer();
        for (int i = 0; i < numRows; i++) {
            r.append(ret[i]);
            if (i < numRows - 1) r.append('\n');
        }
        return r.toString();
    }


    /****************************************************7**************************************************************/

    /***
     * 7. 整数反转
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *
     * 示例 1:
     *
     * 输入: 123
     * 输出: 321
     *  示例 2:
     *
     * 输入: -123
     * 输出: -321
     * 示例 3:
     *
     * 输入: 120
     * 输出: 21
     */

    @Test
    public void test7() {
        System.out.println(reverse(-2147483412));
    }

    public int reverse(int x) {
        boolean isMinus = false;
        long x0 = x;
        if (x0 < 0) {
            isMinus = true;
            x0 -= 2 * x0;
        }
        //if(x0 > Integer.MAX_VALUE || x0 < Integer.MIN_VALUE) return  0;

        long ret = 0;
        while (x0 >= 10) {
            ret = ret * 10 + (x0 % 10);
            x0 /= 10;
        }
        ret = ret * 10 + (x0 % 10);
        if (isMinus) ret -= 2 * ret;
        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) return 0;
        return (int) ret;
    }


    /****************************************************8**************************************************************/
    /**
     * 8. 字符串转换整数 (atoi)
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     * <p>
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
     * <p>
     * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
     * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
     * <p>
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
     * <p>
     * 提示：
     * <p>
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: "42"
     * 输出: 42
     * 示例 2:
     * <p>
     * 输入: "   -42"
     * 输出: -42
     * 解释: 第一个非空白字符为 '-', 它是一个负号。
     * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
     * 示例 3:
     * <p>
     * 输入: "4193 with words"
     * 输出: 4193
     * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
     * 示例 4:
     * <p>
     * 输入: "words and 987"
     * 输出: 0
     * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     * 因此无法执行有效的转换。
     * 示例 5:
     * <p>
     * 输入: "-91283472332"
     * 输出: -2147483648
     * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     * 因此返回 INT_MIN (−231) 。
     * <p>
     * TODO 敏感词过滤的算法原理之DFA算法
     */

    @Test
    public void test8() {
        /*String s = "0123";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.println((int) chars[i]);
        }*/
        System.out.println(myAtoi("   + 01 2 3"));
    }

    public int myAtoi(String str) {
        char[] array = str.trim().toCharArray();
        if (array.length == 0) return 0;
        boolean isStart = false;
        boolean isMinus = array[0] == '-';
        long ret = 0;
        for (int i = 0; i < array.length; i++) {
            int n = GetCharIntValueByZero(array[i]);
            if (!isStart) {
                if (array[i] == '-' || array[i] == '+') {
                    isStart = true;
                    continue;
                }
                if (n >= 0 && n <= 9) isStart = true;
                else return 0;
            }
            if (isStart) {
                if (n < 0 || n > 9) break;
                ret = ret * 10 + n;
                if (ret > Integer.MAX_VALUE) break;
            }
            if (!isStart && array[i] == '+') isStart = true;
        }
        ret -= isMinus ? 2 * ret : 0;
        if (ret <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
        else if (ret >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return (int) ret;
    }

    public int GetCharIntValueByZero(char c) {
        return (int) c - 48;
    }

    /****************************************************9**************************************************************/
    /**
     * 9. 回文数
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 121
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: -121
     * 输出: false
     * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3:
     * <p>
     * 输入: 10
     * 输出: false
     * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
     * TODO 只反转一半即可
     */
    @Test
    public void test9() {
        System.out.println(isPalindrome(10));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int x0 = x;
        int revase = 0;
        while (x >= 10) {
            revase = revase * 10 + x % 10;
            x /= 10;
        }
        revase = revase * 10 + x % 10;
        return revase == x0;
    }


    /****************************************************10**************************************************************/

    /**
     * 10. 正则表达式匹配
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * <p>
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     * <p>
     * 说明:
     * <p>
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     * 示例 1:
     * <p>
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * 示例 2:
     * <p>
     * 输入:
     * s = "aa"
     * p = "a*"
     * 输出: true
     * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * 示例 3:
     * <p>
     * 输入:
     * s = "ab"
     * p = ".*"
     * 输出: true
     * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     * 示例 4:
     * <p>
     * 输入:
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     * 示例 5:
     * <p>
     * 输入:
     * s = "mississippi"
     * p = "mis*is*p*."
     * 输出: false
     */
    @Test
    public void test10() {

    }

    //动态规划。还不会... 嘤嘤嘤
    public boolean isMatch(String s, String p) {
        Pattern.matches("from", "to");

        return false;
    }
}






























