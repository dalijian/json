package com.lijian.algorithms.leecode;

import org.junit.Test;

import java.util.stream.IntStream;

public class ArrayExample {

    @Test
    public void plusOneTest() {

        int[] result = plusOne2(new int[]{1, 2, 9});
        IntStream.of(result).boxed().forEach(x -> System.out.println(x));
    }

    //66 加一
    public int[] plusOne(int[] digits) {
        if (digits.length == 0) {
            return new int[0];

        }
        int i = digits.length - 1;
        if (digits[i] != 9) {
            digits[i] = digits[i] + 1;
            return digits;
        }
        if (digits[i] == 9) {
            digits[i] = 0;
            if (i - 1 < 0) {
                int[] temp = new int[digits.length + 1];
                System.arraycopy(digits, 0, temp, 1, digits.length);
                temp[0] = 1;
                temp[1] = 0;
                return temp;
            } else {
                digits[i - 1] = digits[i - 1] + 1;
            }

            i--;
        }

        while (digits[i] == 10) {
            digits[i] = 0;
            if (i - 1 < 0) {
                int[] temp = new int[digits.length + 1];
                System.arraycopy(digits, 0, temp, 1, digits.length);
                temp[0] = 1;
                temp[1] = 0;
                return temp;
            }
            digits[i - 1] = digits[i - 1] + 1;
            i--;
        }

        return digits;
    }


    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }


    @Test
    public void setZerosTest() {

        setZeroes(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}
        });
    }

    // 73. 矩阵 置零
    public void setZeroes(int[][] matrix) {
        int[][] temp = new int[matrix.length][matrix[0].length];

        System.arraycopy(matrix, 0, temp, 0, matrix.length);
        // 为什么 修改 matrix , temp  也会 发生 变化
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                if (temp[i][j] == 0) {
                    for (int k = 0; k < matrix.length; k++) {
                        matrix[i][k] = 0;
                    }
                    for (int k = 0; k < matrix[0].length; k++) {
                        matrix[k][j] = 0;
                    }
                    break;
                }
            }
        }
    }

@Test
public void sortColorsTest(){

    sortColors(new int[]{1,2, 0, 2, 1, 1, 0});
}

    /*
    Edsger W. Dijkstra
  荷兰三色旗问题解
  */
    public void sortColors(int[] nums) {
        // 对于所有 idx < i : nums[idx < i] = 0
        // j是当前考虑元素的下标
        int p0 = 0, curr = 0;
        // 对于所有 idx > k : nums[idx > k] = 2
        int p2 = nums.length - 1;
        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // 交换第 p0个和第curr个元素
                // i++，j++
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            }
            else if (nums[curr] == 2) {
                // 交换第k个和第curr个元素
                // p2--
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            }
            else curr++;
        }
    }
}


