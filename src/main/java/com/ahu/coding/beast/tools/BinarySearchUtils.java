package com.ahu.coding.beast.tools;

/**
 * @author jianzhang
 * 2025/02/10/上午10:27
 */
public class BinarySearchUtils {
    public static void main(String[] args) {
        int target = 12;
        int nums[] = {1, 2, 3, 4, 10, 12, 34};
        System.out.println(m1(nums, target));
        System.out.println(m2(nums, target));
    }

    public static int m1(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }


   static int m2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定左侧边界
                right = mid - 1;
            }
        }
        // 判断 target 是否存在于 nums 中
        if (left < 0 || left >= nums.length) {
            return -1;
        }
        // 判断一下 nums[left] 是不是 target
        return nums[left] == target ? left : -1;
    }

}
