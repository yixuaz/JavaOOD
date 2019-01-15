package File;

import java.io.File;

public class Test6 {
    public static void main(String[] args) {
        String dirname = "F:/";
        File d = new File(dirname);
        String[] path = d.list();
        for (String p : path) {
            System.out.println(p);
        }
    }
}
