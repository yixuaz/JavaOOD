package class14;

public class matchSquare {
    static class Point {
        boolean hasRight;//0 is t
        boolean hasBottom;

        public Point(boolean hasRight, boolean hasBottom) {
            this.hasRight = hasRight;
            this.hasBottom = hasBottom;
        }
    }

    public static void main(String[] args) {
        String[] matrix = new String[]{"-----",
                                       "| |  ",
                                        "---  ",
                                        "||   ",
                                        "|    ",
                                        "___  ",};
        Point[][] input = new Point[matrix.length][matrix[0].length()];
        int y = 0;
        for (String s : matrix) {
            int x = 0;
            for (char c : s.toCharArray()){
                if (c == '-') {
                    if ((y > 0 && input[y - 1][x].hasBottom)
                            || (y < input.length -1 && matrix[y + 1].charAt(x) == '|'))
                        input[y][x] = new Point(true,true);
                    else
                        input[y][x] = new Point(true,false);
                }
                else if (c == '|') {
                    if ((y > 0 && input[y - 1][x].hasRight)
                            || (y < input.length -1 && matrix[y + 1].charAt(x) == '-'))
                        input[y][x] = new Point(true,true);
                    else
                        input[y][x] = new Point(false,true);
                }else {
                    input[y][x] = new Point(false,false);
                }
                x++;
            }
            y++;
        }
        int[][] right = new int[input.length][input[0].length];
        int[][] bottom = new int[input.length][input[0].length];
        for (int i = 0; i < input[0].length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (input[j][i].hasBottom)
                    bottom[j][i] = (j == 0 ? 0 : bottom[j - 1][i]) + 1;
            }
        }
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j].hasRight)
                    right[i][j] = (j == 0 ? 0 : right[i][j - 1]) + 1;
            }
        }
        int max = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                int maxLen = Math.min(i, j);
                for (int k = maxLen; k >= 1; k--) {
                    if (right[i][j] >= k && bottom[i][j] >= k
                            && right[i - k][j] >= k && bottom[i][j - k] >= k) {
                        max = Math.max(max, k);
                    }
                }
            }
        }
        System.out.println("max Square len : " + max);
    }
}
