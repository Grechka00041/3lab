package spn;

public class SPNFacade {

    public Double calculate(String expression){
        return SPN.calculate(expression);
    }

    public boolean validate(String expression){
        return SPN.checkSPN(expression);
    }

    public String transform(String expression){
        return SPN.toSPN(expression);
    }
}
