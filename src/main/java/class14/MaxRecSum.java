package class14;

import java.util.Arrays;
import java.util.Random;

public class MaxRecSum {
    public int maxRecSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int[] tmp = new int[m];
            for (int j = i; j < n; j++) {
                for (int k = 0; k < m; k++)
                    tmp[k] += matrix[j][k];
                max = Math.max(max, subMaxSum(tmp));
            }
        }
        return max;
    }

    private int subMaxSum(int[] tmp) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i : tmp) {
            sum += i;
            max = Math.max(sum, max);
            if (sum < 0) sum = 0;
        }
        return max;
    }

    public int maxRecSum2(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int[][] presum = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                presum[i][j] = matrix[i - 1][j - 1] + presum[i][j - 1] + presum[i - 1][j] - presum[i - 1][j - 1];
            }
        }
        for (int y1 = 1; y1 <= n; y1++) {
            for (int x1 = 1; x1 <= m; x1++) {
                for (int y2 = y1; y2 <= n; y2++) {
                    for (int x2 = x1; x2 <= m; x2++) {
                        max = Math.max(max, presum[y2][x2] - presum[y1 - 1][x2] - presum[y2][x1 - 1] +
                                presum[y1 - 1][x1 - 1]);
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaxRecSum maxRecSum = new MaxRecSum();
        Random r = new Random();
        final int LIMIT = 100;
        int test = 0;
        while (test++ < 10) {
            int l = r.nextInt(LIMIT);
            int h = r.nextInt(LIMIT);
            int[][] input = new int[h][l];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < l; j++) {
                    input[i][j] = -50 + r.nextInt(100);
                }
            }
            if (maxRecSum.maxRecSum(input) != maxRecSum.maxRecSum2(input))
                System.out.println("error");
        }



    }
}
