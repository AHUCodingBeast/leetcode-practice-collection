package com.ahu.coding.beast.training.phase01;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author jianzhang
 * 2025/02/25/下午3:28
 * <p>
 * 矩阵乘法
 */
public class Question40 {


    public static void main(String[] args) {

        int[][] A = new int[][]{
                {1, 2}, {3, 4}
        };

        int[][] B = new int[][]{
                {5, 6}, {7, 8}
        };

        int[][] result = matrixMultiPly(A, B);

        for (int[] rows : result) {
            System.out.println(Arrays.stream(rows).boxed().map(String::valueOf).collect(Collectors.joining(",")));
        }


    }

    static int[] getMatrixCol(int[][] nums, int col) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                if (j == col) {
                    res[i] = nums[i][j];
                    break;
                }
            }
        }
        return res;
    }

    public static int[][] matrixMultiPly(int[][] matrixA, int[][] matrixB) {

        int rowNumOfA = matrixA.length;
        int colNumOfB = matrixB[0].length;

        int[][] result = new int[rowNumOfA][colNumOfB];

        for (int i = 0; i < matrixA.length; i++) {
            // 获取第i行的所有元素
            int[] o1 = matrixA[i];
            for (int j = 0; j < colNumOfB; j++) {
                // 获取第j列的所有元素
                int[] o2 = getMatrixCol(matrixB, j);
                result[i][j] = multiPly(o1, o2);
            }
        }
        return result;
    }

    public static int multiPly(int[] o1, int[] o2) {
        int res = 0;
        for (int i = 0; i < o1.length; i++) {
            res += (o1[i] * o2[i]);
        }
        return res;
    }

}
