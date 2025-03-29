public class CheckExpression {
    public static boolean validateNormalExp(String expression){
        MyStack<Character> stack = new MyStack<>();
        boolean lastIsOperand = false;
        for(int i = 0; i < expression.length(); i++){
            char ch = expression.charAt(i);

            if(Character.isWhitespace(ch)){
                continue;
            }

            if(Character.isDigit(ch)){
                lastIsOperand = false;
                while(i < expression.length() && Character.isDigit(expression.charAt(i))){
                    i++;
                }

            }

            else if(ch == '('){
                stack.push(ch);
                lastIsOperand = true;
            }
            else if(ch == ')'){
                if(stack.isEmpty() || stack.pop() != '('){
                    return false;
                }
                lastIsOperand = false;
            }
            else if (isOperator(ch)) {
                if (lastIsOperand) {
                    return false; // Два оператора подряд
                }
                lastIsOperand = true;
            } else {
                return false; // Недопустимый символ
            }
        }
        return stack.isEmpty() && !lastIsOperand;

    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateSPN(String expression) {
        String[] elems = expression.split(" ");
        int operandCount = 0;

        // Идём справа налево
        for (int i = elems.length - 1; i >= 0; i--) {
            String elem = elems[i];

            if (isNumber(elem)) {
                operandCount++;
            } else if (isOperator(elem.charAt(0))) {
                if (operandCount < 2) {
                    return false;
                }
                operandCount--; // Оператор заменяет 2 операнда на 1
            } else {
                return false; // Недопустимый токен
            }
        }

        return operandCount == 1;
    }

    public static boolean validateRPN(String expression) {
        String[] elems = expression.split(" ");
        int operandCount = 0;

        for (String elem : elems) {
            if (isNumber(elem)) {
                operandCount++;
            } else if (isOperator(elem.charAt(0))) {
                if (operandCount < 2) {
                    return false;
                }
                operandCount--; // Оператор заменяет 2 операнда на 1
            } else {
                return false; // Недопустимый токен
            }
        }

        return operandCount == 1;
    }
}
