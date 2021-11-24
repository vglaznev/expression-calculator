import reversepolishnotation.calculator.ExpressionCalculator;
import reversepolishnotation.calculator.ExpressionCalculatorImp;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ExpressionCalculator calculator = new ExpressionCalculatorImp();
        System.out.println(calculator.calculateExpression("8/(-10)"));
    }
}
