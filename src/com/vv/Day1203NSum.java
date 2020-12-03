package com.vv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字节题库
 * 18. N数之和
 * <p>
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ShenZhenWei
 * @date 2020/12/3
 */
class Day1203NSum {
    public static void main(String[] args) {
        int[] a = new int[]{1, 0, -1, 0, -2, 2};
        List<List<Integer>> list = fourSum(a, 0);
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.get(i).size(); k++) {
                System.out.print(list.get(i).get(k));
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        return nSum(nums, 4, 0, target);
    }

    public static List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //递归结束条件
        int sz = nums.length;
        if (n < 2) {
            return result;
        }
        if (n == 2) {
            // twoSum
            result = twoSum(nums, start, target);
        } else {
            //当n>2 时,递归计算 (n-1)Sum的结果
            for (int i = start; i < nums.length; i++) {
                List<List<Integer>> lists = nSum(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> data : lists) {
                    data.add(nums[i]);
                    result.add(data);
                }

                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }

        }
        return result;
    }

    public static List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //1.排序
        Arrays.sort(nums);
        //2.定义两个指针  low heigh
        int low = start, heigh = nums.length - 1;

        while (low < heigh) {
            int sum = nums[low] + nums[heigh];
            //3.定义两个临时变量用来存储  当前两个指针的值  left 跟 right
            int left = nums[low], right = nums[heigh];
            if (sum < target) {
                //受4的启发 跳过所有重复的元素
                while (low < heigh && nums[low] == left) {
                    low++;
                }
            } else if (sum > target) {
                //受4的启发 跳过所有重复的元素
                while (low < heigh && nums[heigh] == right) {
                    heigh--;
                }
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(left);
                list.add(right);
                result.add(list);
                //4.跳过所有重复的元素
                while (low < heigh && nums[low] == left) {
                    low++;
                }
                while (low < heigh && nums[heigh] == right) {
                    heigh--;
                }
            }
        }
        return result;
    }

}
