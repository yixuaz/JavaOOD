package File;

import java.util.Scanner;

public class Test5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (line.toLowerCase().equals("exit"))
                break;
            System.out.println("Input text : " + line);
        }
    }
}
