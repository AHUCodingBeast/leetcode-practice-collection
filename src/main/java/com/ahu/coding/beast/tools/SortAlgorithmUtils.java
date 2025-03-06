package com.ahu.coding.beast.tools;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jianzhang
 * 2025/01/17/下午3:11
 * <p>
 * 练习下五大排序的写法
 * 快速排序、归并排序、插入排序、冒泡排序、选择排序
 */
public class SortAlgorithmUtils {


    /**
     * 选择排序
     * 核心：每一轮排序会将未排序部分的最小元素放到已排序部分的末尾。
     *
     * @param nums 目标数组
     * @return 排序后的数组
     */
    public static int[] selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = nums[i];
            int minIndex = i;
            // 寻找未排序部分（从 i + 1 到 n - 1）的最小元素的索引 minIndex。
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    minIndex = j;
                }
            }
            int t = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = t;
        }
        return nums;
    }


    /**
     * 冒泡排序： 每一轮最大的元素都会直接排到数组最后
     *
     * @param nums 目标数组
     * @return 排序后的数组
     */
    public static int[] bubblingSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                // 前一个数比后一个数大就下沉
                if (nums[j - 1] > nums[j]) {
                    int t = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = t;
                }
            }
        }
        return nums;
    }

    /**
     * 插入排序，把每次要考察的数字插入到一个有序数组里，注意数组的元素移动代码编写
     * 12, 1, 7, 6, 19, 5, 22
     * 12  1
     *
     * @param nums 目标数组
     * @return 排序后的数组
     */
    public static int[] insertSort(int[] nums) {
        if (nums == null) {
            return null;
        }
        // [0-j] 是有序的，需要考虑把nums[i] 塞进这个有序序列里面
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            int j = i - 1;
            while (j >= 0 && cur < nums[j]) {
                // 移动数组中的元素而不是互换元素
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = cur;
        }
        return nums;
    }

    // 考察递归程序编写能力
    public static int[] quickSort(int[] nums) {
        if (nums == null) {
            return null;
        }
        return quickSortMethod(0, nums.length - 1, nums);
    }

    public static int[] quickSortMethod(int left, int right, int[] nums) {

        if (left >= right) {
            return nums;
        }

        int pivot = nums[right];
        int i = left;
        int j = right;

        while (i < j) {
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            if (i < j && nums[i] > pivot) {
                nums[j] = nums[i];
                j--;
            }
            while (i < j && nums[j] > pivot) {
                j--;
            }
            if (i < j && nums[j] < pivot) {
                nums[i] = nums[j];
                i++;
            }
        }

        nums[i] = pivot;
        quickSortMethod(left, i - 1, nums);
        quickSortMethod(i + 1, right, nums);
        return nums;

    }


    public static int[] mergeSort(int[] nums, int low, int high) {
        if (nums == null) {
            return nums;
        }
        if (high <= low) {
            return nums;
        }
        int middle = (high + low) / 2;
        mergeSort(nums, low, middle);
        mergeSort(nums, middle + 1, high);
        // 合并low->mid 与 mid+1 -> high 两部分
        merge(nums, low, middle, high);
        return nums;
    }


    public static void merge(int[] nums, int low, int mid, int high) {
        int[] res = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= high) {
            if (nums[i] > nums[j]) {
                res[k++] = nums[j++];
            } else {
                res[k++] = nums[i++];
            }
        }

        while (i <= mid) {
            res[k++] = nums[i++];
        }

        while (j <= high) {
            res[k++] = nums[j++];
        }

        // 覆盖回去 low -> high  0 -> res.length
        int k2 = 0;
        int i2 = low;
        while (k2 < res.length) {
            nums[i2++] = res[k2++];
        }


    }


    public static int[] sortFromLarge2Small(int[] nums, Function<int[], int[]> baseFunc) {
        int[] apply = baseFunc.apply(nums);
        int i = 0;
        int j = apply.length - 1;
        while (i < j) {
            int t = apply[i];
            apply[i] = apply[j];
            apply[j] = t;
            i++;
            j--;
        }
        return apply;
    }

    public static void print(int nums[]) {
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        int[] nums = {12, 1, 7, 6, 19, 5, 22};
        //  print(quickSort(new int[]{12, 1, 7, 6, 19, 5, 22}));

        print(mergeSort(nums, 0, nums.length - 1));

    }


}
