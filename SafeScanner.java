package utils;

import java.util.Scanner;

public class SafeScanner {
    public static int scanInt(Scanner scanner){
        int variable = 0;
        boolean isValid = false;
        while(!isValid){
            if (scanner.hasNextInt()) {
                variable = scanner.nextInt();
                isValid = true;
            } else {
                System.out.println("Пожалуйста введите число");
                scanner.next();
            }
        }
        return variable;
    }
}
