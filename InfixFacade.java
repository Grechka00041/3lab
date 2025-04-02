package infix;

public class InfixFacade {

    public Integer calculate(String expression){
        return Infix.calculate(expression);
    }

    public boolean validate(String expression){
        return Infix.checkInfix(expression);
    }

}
