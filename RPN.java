public class RPN {

    private static int getPriority(char ch){
        return switch (ch) {
            case '(' -> 1;
            case ')' -> -1;
            case '+', '-' -> 2;
            case '*', '/' -> 3;
            default -> 0;
        };
    }

    public static String toRPN(String input){
        MyStack<Character> stack = new MyStack<>();
        StringBuilder output = new StringBuilder();
        int priority;
        char ch;
        for(int i = 0; i < input.length(); i++){
            ch = input.charAt(i);
            priority = getPriority(ch);

            if (ch == ' ') continue;

            else if (Character.isDigit(ch) || ch == '.'){
                StringBuilder num = new StringBuilder();
                while (i < input.length() && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
                    num.append(input.charAt(i++));
                }
                i--;
                output.append(num);
                System.out.println("Стэк сейчас:" + stack);
                System.out.println("Текущая строка: " + output);
            }
            else if (ch == '('){
                stack.push(input.charAt(i));
                System.out.println("Стэк сейчас:" + stack);
                System.out.println("Текущая строка: " + output);
            }

            else if(ch == ')'){
                while (stack.peek() != '(') {
                    output.append(stack.pop());
                    System.out.println("Стэк сейчас:" + stack);
                    System.out.println("Текущая строка: " + output);
                }
                stack.pop();
                System.out.println("Стэк сейчас:" + stack);
                System.out.println("Текущая строка: " + output);
            }
            else{
                output.append(" ");
                while(!stack.isEmpty() && getPriority(stack.peek()) >= priority){

                    System.out.println("Стэк сейчас:" + stack);
                    System.out.println("Текущая строка: " + output);
                }
                stack.push(ch);
                System.out.println("Стэк сейчас:" + stack);
                System.out.println("Текущая строка: " + output);
            }

        }
        while(!stack.isEmpty()){
            System.out.println("Стэк сейчас:" + stack);
            System.out.println("Текущая строка: " + output);
            output.append(stack.pop());
        }
        System.out.println("Стэк сейчас:" + stack);
        System.out.println("Конец стэка.");
        return output.toString();
    }

    public static double calculate(String rpn) {
        MyStack<Double> stack = new MyStack<>();
        char ch;
        double answer;
        for (int i = 0; i < rpn.length(); i++) {
            ch = rpn.charAt(i);
            if (ch == ' ') {
                continue;
            }
            if (Character.isDigit(ch)) {
                stack.push((double)(ch-'0')); // WTF
                System.out.println("Стэк сейчас:" + stack);
                System.out.println("Текущая строка: " + rpn);
            } else {
                doOperation(stack, ch);
                System.out.println("Стэк сейчас:" + stack);
                System.out.println("Текущая строка: " + rpn);
            }
        }
        answer = stack.pop();
        System.out.println("Стэк сейчас:" + stack);
        System.out.println("Текущая строка: " + rpn);
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
