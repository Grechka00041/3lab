public class CountRPN {
    private Stack stack;
    private String input;

    public CountRPN(String input){
        this.input = input;
    }

    public int count(){
        stack = new Stack(12);
        char ch;
        int i;
        int num1, num2;
        int answer;
        for(i = 0; i < input.length(); i++){
            ch = input.charAt(i);
            stack.displayStack("ch");
            if (ch >= '0' && ch <= '9') {
                stack.push((int)(ch-'0'));
            }
            else{
                num2 = stack.pop();
                num1 = stack.pop();
                switch (ch){
                    case '+':
                        answer = num1 + num2;
                        break;
                    case '-':
                        answer = num1 - num2;
                        break;
                    case '*':
                        answer = num1*num2;
                        break;
                    case '/':
                        answer = num1/num2;
                        break;
                    default:
                        answer = 0;
                }
                stack.push(answer);
            }
        }
        answer = stack.pop();
        return answer;
    }
}
