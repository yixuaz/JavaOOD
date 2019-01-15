package File;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test4 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("a.txt")))){
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

}
class MyBufferedReader {
    private FileInputStream in;
    private StringBuffer buffer;

    public MyBufferedReader(FileInputStream in) {
        this.in = in;
        buffer = new StringBuffer();
    }

    public String nextLine() throws IOException {
        while (true) {
            int c = in.read();
            if (c == -1 || c == '\n') break;
            buffer.append((char) c);
        }
        String output = buffer.toString();
        buffer = new StringBuffer();
        return output;
    }
}
