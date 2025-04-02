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

    public static void doOperation(MyStack<Integer> numbers, char operand) {
        int num1 = numbers.pop();
        int num2 = numbers.pop();
        int result = switch (operand) {
            case '+' -> num1 + num2;
            case '-' -> num2 - num1;
            case '*' -> num1 * num2;
            case '/' -> {
                if (num1 == 0) throw new ArithmeticException("Деление на ноль");
                yield num2 / num1;
            }
            default -> throw new IllegalArgumentException("Неизвестный оператор: " + operand);
        };
        numbers.push(result);
    }

    public static void doOperationSPN(MyStack<Integer> numbers, char operand) {
        int num1 = numbers.pop();
        int num2 = numbers.pop();
        int result = switch (operand) {
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
