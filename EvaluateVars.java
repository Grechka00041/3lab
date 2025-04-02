package utils;

import java.util.Scanner;


public class EvaluateVars {
    public static String evaluate(String expression, Scanner scanner){
        StringBuilder result = new StringBuilder();
        char ch;
        StringBuilder variable = new StringBuilder();
        for (int i = 0; i < expression.length(); i++){
            ch = expression.charAt(i);
            if (Character.isLetter(ch)){
                variable = new StringBuilder();
                while( i < expression.length() && Character.isLetterOrDigit(expression.charAt(i))){
                        variable.append(expression.charAt(i));
                        i++;
                }
                i--;
                System.out.print("Введите значение для переменной " + variable.toString() + ": ");
                String value = scanner.nextLine();
                result.append(value);
            }
            else {
                    result.append(ch);
            }
        }
        return result.toString();
    }
}
