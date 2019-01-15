package File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args) throws IOException {
        try (FileReader in = new FileReader("a.txt")){
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }
        }
    }
}
