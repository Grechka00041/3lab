package rpn;

import utils.MyStack;

import static utils.ExpressionsUtils.*;

public abstract class RPN {

    public static String toRPN(String input){
        MyStack<Character> stack = new MyStack<>();
        StringBuilder output = new StringBuilder();
        int priority;
        char ch;
        for(int i = 0; i < input.length(); i++){
            ch = input.charAt(i);
            priority = getPriority(ch);

            if (ch == ' ') continue;

            else if (Character.isDigit(ch)){
                StringBuilder num = new StringBuilder();
                while (i < input.length() && (Character.isDigit(input.charAt(i)))) {
                    num.append(input.charAt(i++));
                }
                i--;
                output.append(num);
                output.append(" ");
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
                    output.append(" ");
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
                    output.append(stack.pop()).append(" ");
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
            output.append(" ");
        }
        System.out.println("Стэк сейчас:" + stack);
        System.out.println("Конец стэка.");
        return output.toString();
    }

    public static int calculate(String rpn) {
        MyStack<Integer> stack = new MyStack<>();
        char ch;
        int answer;
        for (int i = 0; i < rpn.length(); i++) {
            ch = rpn.charAt(i);
            if (Character.isWhitespace(ch)) {
                continue;
            }
            if (Character.isDigit(ch)) {
                StringBuilder num = new StringBuilder();
                while (i < rpn.length() && (Character.isDigit(rpn.charAt(i)))) {
                    num.append(rpn.charAt(i++));
                }
                i--;
                stack.push((Integer.valueOf(String.valueOf(num))));
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

    public static boolean checkRPN(String expression) {
        MyStack<Integer> stack = new MyStack<>();
        char ch;
        for (int i = 0; i < expression.length(); i++) {
            ch = expression.charAt(i);
            if(Character.isWhitespace(ch)){
                continue;
            }
            if (Character.isDigit(ch)) {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)))) {
                    num.append(expression.charAt(i++));
                }
                i--;
                stack.push(1);
            } else if (isOperator(ch)) {
                if (stack.size() < 2) {
                    return false;
                }
                stack.pop();
                stack.pop();
                stack.push(1);
            }

            else {
                return false;
            }
        }
        return stack.size() == 1 && isOperator(expression.charAt(expression.length()-1));
    }

}
