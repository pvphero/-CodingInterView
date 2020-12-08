package com.vv;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShenZhenWei
 * @date 2020/12/8
 */
public class Day1208Permutations {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);
    }

    public static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            int len = nums.length;
            //使用动态数组保留所有的可能的全排列
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if (len == 0) {
                return res;
            }
            boolean[] used = new boolean[len];
            List<Integer> path = new ArrayList<Integer>();
            dfs(nums, len, 0, path, used, res);
            System.out.println("dfs执行完毕===>" + res);
            return res;
        }

        public void dfs(int[] nums, int len, int depth,
                        List<Integer> path, boolean[] used,
                        List<List<Integer>> res) {
            System.out.println("递归开始：=>" + res);
            //递归的终止条件
            if (depth == len) {
                res.add(new ArrayList<Integer>(path));
                System.out.println("递归终止：==>" + res);
                return;
            }

            for (int i = 0; i < len; i++) {
                if (!used[i]) {
                    path.add(nums[i]);
                    used[i] = true;
                    System.out.println("回溯之前：===>" + path);
                    dfs(nums, len, depth + 1, path, used, res);
                    //发生回溯
                    used[i] = false;
                    path.remove(path.size() - 1);
                    System.out.println("回溯之后：====>" + path);
                }
            }

        }
    }
}
