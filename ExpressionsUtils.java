package utils;

public abstract class ExpressionsUtils {

    public static int getPriority(char ch){
        return switch (ch) {
            case '(' -> 1;
            case ')' -> -1;
            case '+', '-' -> 2;
            case '*', '/' -> 3;
            default -> 0;
        };
    }

    public static void doOperation(MyStack<Double> numbers, char operand) {
        double num1 = numbers.pop();
        double num2 = numbers.pop();
        double result = switch (operand) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> {
                if (num2 == 0) throw new ArithmeticException("Деление на ноль");
                yield num1 / num2;
            }
            default -> throw new IllegalArgumentException("Неизвестный оператор: " + operand);
        };
        numbers.push(result);
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    static boolean isVariable(char ch) {
        return  (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }
}
