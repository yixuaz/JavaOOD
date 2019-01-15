package File;

import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test3 {
    public static void main(String[] args) throws IOException {
        try(InputStreamReader cin = new InputStreamReader(System.in)) {
            System.out.println("Enter char, 'q' to quit.");
            StringBuffer userInput = new StringBuffer();
            while (true) {
                char c = (char) cin.read();
                if (c == 'q') break;
                userInput.append(c);
            }
            System.out.println(userInput);
        }
    }
}
