package utils;

import java.util.Scanner;

import static utils.ExpressionsUtils.isOperator;
import static utils.ExpressionsUtils.isVariable;

public class EvaluateVars {
    public static String evaluate(String expression, Scanner scanner){
        StringBuilder result = new StringBuilder();
        char ch;
        StringBuilder variable = new StringBuilder();
        for (int i = 0; i < expression.length(); i++){
            ch = expression.charAt(i);
            if(Character.isWhitespace(ch) || Character.isDigit(ch)|| isOperator(ch)){
                result.append(ch);
            }
            else{
                variable = new StringBuilder();
                while (i < expression.length() && isVariable(ch)) {
                    variable.append(expression.charAt(i++));
                }
                i--;
                System.out.print("Введите значение для переменной" + variable.toString() + ": ");
                result.append(variable.toString());
            }
        }
        return result.toString();
    }
}
