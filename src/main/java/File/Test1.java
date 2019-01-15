package File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream out = null;
        try (FileInputStream in = new FileInputStream("a.txt")){
            out = new FileOutputStream("b.txt");
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
                out.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
        }
    }
}
