package rpn;

public class RPNFacade {
    public int calculate(String expression){
        return RPN.calculate(expression);
    }

    public boolean validate(String expression){
        return RPN.checkRPN(expression);
    }

    public String transform(String expression){
        return RPN.toRPN(expression);
    }
}
