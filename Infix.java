package infix;

import utils.MyStack;

import static utils.ExpressionsUtils.*;

public abstract class Infix {

    public static Integer calculate(String input){
        MyStack<Integer> numbers = new MyStack<>();
        MyStack<Character> operands = new MyStack<>();
        char ch;
        for(int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);

            if (Character.isWhitespace(ch)) {
                continue;
            }

            if (Character.isDigit(ch)) {
                StringBuilder num = new StringBuilder();
                while (i < input.length() && (Character.isDigit(input.charAt(i)) )) {
                    num.append(input.charAt(i++));
                }
                i--;
                numbers.push(Integer.parseInt(num.toString()));
                System.out.println("Стэк чисел:" + numbers);
                System.out.println("Стэк операций: " + operands);
            }
            else{

                if (ch == '(') {
                    operands.push(ch);
                    System.out.println("Стэк чисел:" + numbers);
                    System.out.println("Стэк операций: " + operands);
                }
                if (ch == ')') {
                    while (operands.peek() != '(' && !operands.isEmpty()) {
                        doOperation(numbers, operands.pop());
                        System.out.println("Стэк чисел:" + numbers);
                        System.out.println("Стэк операций: " + operands);
                    }
                    operands.pop();
                    System.out.println("Стэк чисел:" + numbers);
                    System.out.println("Стэк операций: " + operands);
                }
                if(isOperator(ch)){
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

        }
        while (!operands.isEmpty()) {
            doOperation(numbers, operands.pop());
            System.out.println("Стэк чисел:" + numbers);
            System.out.println("Стэк операций: " + operands);
        }
        return numbers.pop();
    }

    public static boolean checkInfix(String expression){
        MyStack<Character> stack = new MyStack<>();
        boolean lastIsOperator = true;
        for(int i = 0; i < expression.length(); i++){
            char ch = expression.charAt(i);
            if(Character.isWhitespace(ch)){
                continue;
            }
            if(Character.isDigit(ch)){
                lastIsOperator = false;
                while(i < expression.length() && Character.isDigit(expression.charAt(i))){
                    i++;
                }
                i--;
            }
            else if(ch == '('){
                stack.push(ch);
                lastIsOperator = true;
            }
            else if(ch == ')'){
                if(stack.isEmpty() || stack.pop() != '('){
                    return false;
                }
                lastIsOperator = false;
            }
            else if (isOperator(ch)) {
                if (lastIsOperator) {
                    return false;
                }
                lastIsOperator = true;
            } else {
                return false;
            }
        }
        return stack.isEmpty() && !lastIsOperator;
    }

}
