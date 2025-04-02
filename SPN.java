package spn;

import utils.MyStack;

import static utils.ExpressionsUtils.*;


public abstract class SPN {

    public static String toSPN(String input){
        char ch;
        MyStack<Character> stack = new MyStack<>();
        StringBuilder output = new StringBuilder();

        for(int i = input.length() - 1; i >= 0; i--) {
            ch = input.charAt(i);
            if (Character.isWhitespace(ch)) continue;
            else if (Character.isDigit(ch)){
                StringBuilder num = new StringBuilder();
                while (i >= 0 && (Character.isDigit(input.charAt(i)))) {
                    //num.insert(0, ch);
                    num.append(input.charAt(i));
                    i--;
                }
                i++;
                //num.reverse();
                output.append(num);
                output.append(" ");
                System.out.println("Стэк сейчас:" + stack);
                System.out.println("Текущая строка: " + output);
            }
            if (ch == ')') {
                stack.push(ch);
                System.out.println("Стэк сейчас:" + stack);
                System.out.println("Текущая строка: " + output);
            }
            else if (ch == '(') {
                    while (!stack.isEmpty() && stack.peek() != ')') {
                        output.append(stack.pop()).append(" ");
                        System.out.println("Стэк сейчас:" + stack);
                        System.out.println("Текущая строка: " + output);
                    }
                    stack.pop();
            }
            else if (isOperator(ch)){
                while (!stack.isEmpty() && getPriority(stack.peek()) > getPriority(ch)) {
                    output.append(stack.pop()).append(" ");
                    System.out.println("Стэк сейчас:" + stack);
                    System.out.println("Текущая строка: " + output);
                }
                stack.push(ch);
            }

        }
        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(" ");
            System.out.println("Стэк сейчас:" + stack);
            System.out.println("Текущая строка: " + output);
        }

        output.reverse();
        System.out.println("Стэк сейчас:" + stack);
        System.out.println("Текущая строка: " + output);
        return output.toString();


    }

    public static int calculate(String spn) {
        int answer;
        MyStack<Integer> stack = new MyStack<>();
        char ch;
        for (int i = spn.length() - 1; i >= 0; i--) {
            ch = spn.charAt(i);
            if (Character.isWhitespace(ch)) {
                continue;
            }
            if (Character.isDigit(ch)) {
                StringBuilder num = new StringBuilder();
                while (i >= 0 && (Character.isDigit(spn.charAt(i)))) {
                    num.append(spn.charAt(i));
                    i--;
                }
                i++;
                num.reverse();
                stack.push((Integer.valueOf(String.valueOf(num))));
                System.out.println("Стэк сейчас:" + stack);
                System.out.println("Текущая строка: " + spn);
            }
            else{
                doOperationSPN(stack, ch);
                System.out.println("Стэк сейчас:" + stack);
                System.out.println("Текущая строка: " + spn);
            }
        }
        answer = stack.pop();
        System.out.println("Стэк сейчас:" + stack);
        System.out.println("Текущая строка: " + spn);
        return answer;
    }

    public static boolean checkSPN(String expression) {
        MyStack<Integer> stack = new MyStack<>();
        char ch;
        for (int i = expression.length() - 1; i >= 0; i--) {
            ch = expression.charAt(i);
            if(Character.isWhitespace(ch)){
                continue;
            }
            if (Character.isDigit(ch)) {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)))) {
                    num.append(expression.charAt(i--));
                }
                i++;
                stack.push(1);
            } else if (isOperator(ch)) {
                if (stack.size() < 2) {
                    return false;
                }
                stack.pop();
                stack.pop();
                stack.push(1);
            } else {
                return false;
            }
        }
        return stack.size() == 1;
    }

}
