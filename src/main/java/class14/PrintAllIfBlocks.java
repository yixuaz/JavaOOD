package class14;

import java.util.*;

public class PrintAllIfBlocks {
    public void print(int n) {
        help(0,0,"", n);
    }

    private void help(int left, int right, String s, int limit) {
        if (left == limit && right == limit)
            System.out.println(s);

        if (left < limit) {
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < (left - right); i++)
                sb.append("  ");
            sb.append("if {\n");

            help(left + 1, right, sb.toString(), limit);
        }
        if (right < left) {
            right++;
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < (left - right); i++)
                sb.append("  ");
            sb.append("}\n");

            help(left, right, sb.toString(), limit);
        }
    }


      public class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int key) {
          this.key = key;
        }
      }
    public TreeNode reconstruct(int[] inOrder, int[] levelOrder) {

        return help(inOrder, levelOrder);
    }
    private TreeNode help(int[] in, int[] level) {
        if (in.length == 0) return null;
        Map<Integer, Integer> map = new HashMap<>();
        int len = in.length;
        for (int i = 0; i < len; i++) map.put(in[i], i);
        TreeNode cur = new TreeNode(level[0]);
        int idx = map.get(level[0]);
        int[] left = new int[idx];
        int[] right = new int[in.length - idx - 1];
        int l = 0;
        int r = 0;

        for (int i = 1; i < level.length; i++) {
            if (map.get(level[i]) < idx) left[l++] = level[i];
            else right[r++] = level[i];
        }
        cur.left = help(Arrays.copyOfRange(in, 0, idx),left );
        cur.right = help(Arrays.copyOfRange(in, idx + 1, in.length), right);
        return cur;
    }
    public static void main(String[] args) {
        PrintAllIfBlocks p = new PrintAllIfBlocks();
        p.reconstruct(new int[]{1,6,5,7,4,10,9}, new int[]{4,1,10,5,9,6,7});
    }
}
