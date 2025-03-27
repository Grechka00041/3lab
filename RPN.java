public class RPN {
    private Stack2 stack;
    private String input;
    private String output;


    public RPN(String input){
        this.input = input;
        stack = new Stack2();
    }

    public String toRPN(){
        for(int i=0; i < input.length(); i++){
            char ch = input.charAt(i);
            switch (ch){
                case '+':
                case '-':
                    getOperator(ch, 1);
                    break;
                case '/':
                case '*':
                    getOperator(ch, 2);
                    break;
                case '(':
                    stack.push(ch);
                    break;
                case ')':
                    while (!stack.isEmpty()){
                        int topChar = stack.pop();
                        if(topChar == '('){
                            break;
                        }
                        else{
                            output += topChar;
                        }
                    }
                    break;
                default:
                    output += ch;
                    break;
            }

        }
        while (!stack.isEmpty()){
            output = output + stack.pop();
        }
        return output;
    }

    public void getOperator(char operator, int priority){
        while(!stack.isEmpty()){
            int top = stack.pop();
            if(top == '('){
                stack.push(top);
                break;
            }
            else{
                int newPriority;
                if(top == '-' || top == '+'){
                    newPriority = 1;
                }
                else{
                    newPriority = 2;
                }
                if(priority < newPriority){
                    stack.push(top);
                    break;
                }
                else{
                    output += top;
                }
            }
            stack.push(operator);
        }
    }
}
