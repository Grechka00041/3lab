import java.util.Arrays;
import java.util.Collections;

public class SPN {
    private static int getPriority(char ch){
        return switch (ch) {
            case '(' -> 1;
            case ')' -> -1;
            case '+', '-' -> 2;
            case '*', '/' -> 3;
            default -> 0;
        };
    }
    public static String toSPN(String input){
        char ch;
        MyStack<Character> stack = new MyStack<>();
        StringBuilder output = new StringBuilder();

        for(int i = input.length() - 1; i >= 0; i--) {
            ch = input.charAt(i);
            if (ch == ' ') continue;
            else if (Character.isDigit(ch) || ch == '.'){
                StringBuilder num = new StringBuilder();
                while (i >= 0 && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
                    num.insert(0, ch);
                    i++;
                }
                i--;
                output.append(num);
                output.append(" ");
                System.out.println("Стэк сейчас:" + stack);
                System.out.println("Текущая строка: " + output);
            }
            if (ch == ')') {
                stack.push(ch);
            }
            else if (ch == '(') {
                    while (!stack.isEmpty() && stack.peek() != ')') {
                        output.append(stack.pop()).append(" ");
                    }
                    stack.pop();
            }
            else{
                while (!stack.isEmpty() && getPriority(stack.peek()) > getPriority(ch)) {
                    output.append(stack.pop()).append(" ");
                }
                stack.push(ch);
            }

        }
        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(" ");
        }

        /*String[] tokens = output.toString().trim().split(" ");
        Collections.reverse(Arrays.asList(tokens));
        return String.join(" ", tokens);

         */
        return output.toString();

    }

    public static double calculate(String spn) {
        double answer;
        MyStack<Double> stack = new MyStack<>();
        char ch;
        for (int i = spn.length() - 1; i >= 0; i--) {
            ch = spn.charAt(i);
            if (ch == ' ') {
                continue;
            }
            if (Character.isDigit(ch)) {
                stack.push((double)(ch-'0')); // WTF
                System.out.println("Стэк сейчас:" + stack);
                System.out.println("Текущая строка: " + spn);
            }
            else{
                doOperation(stack, ch);
            }
        }
        answer = stack.pop();
        return answer;
    }

    private static void doOperation(MyStack<Double> numbers, char operand) {
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

}
