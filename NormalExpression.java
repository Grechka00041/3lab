public class NormalExpression {

    private static int getPriority(char ch){
        return switch (ch) {
            case '(' -> 1;
            case ')' -> -1;
            case '+', '-' -> 2;
            case '*', '/' -> 3;
            default -> 0;
        };
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

    public static Double calculate(String input){
        MyStack<Double> numbers = new MyStack<>();
        MyStack<Character> operands = new MyStack<>();
        char ch;
        for(int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);

            if (ch == ' ') {
                continue;
            }

            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder num = new StringBuilder();
                while (i < input.length() && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
                    num.append(input.charAt(i++));
                }
                i--;
                numbers.push(Double.parseDouble(num.toString()));
                System.out.println("Стэк чисел:" + numbers);
                System.out.println("Стэк операций: " + operands);
            } else if (ch == '(') {
                operands.push(ch);
                System.out.println("Стэк чисел:" + numbers);
                System.out.println("Стэк операций: " + operands);
            } else if (ch == ')') {
                while (operands.peek() != '(') {
                    doOperation(numbers, operands.pop());
                    System.out.println("Стэк чисел:" + numbers);
                    System.out.println("Стэк операций: " + operands);
                }
                operands.pop();
                System.out.println("Стэк чисел:" + numbers);
                System.out.println("Стэк операций: " + operands);
            } else {
                while (!operands.isEmpty() && getPriority(operands.peek()) >= getPriority(ch)) {
                    doOperation(numbers, operands.pop());
                    System.out.println("Стэк чисел:" + numbers);
                    System.out.println("Стэк операций: " + operands);
                }
                operands.push(ch);
                System.out.println("Стэк чисел:" + numbers);
                System.out.println("Стэк операций: " + operands);
            }
        }
        while (!operands.isEmpty()) {
            doOperation(numbers, operands.pop());
            System.out.println("Стэк чисел:" + numbers);
            System.out.println("Стэк операций: " + operands);
        }
        return numbers.pop();
    }

}
