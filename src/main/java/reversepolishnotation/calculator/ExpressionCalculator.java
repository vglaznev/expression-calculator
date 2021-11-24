package reversepolishnotation.calculator;

/**
 *Interface that provides behaviour of calculator
 */
public interface ExpressionCalculator {
     /**
      *Calculates value of the expression
      * @param expression expression to be calculated
      * @return value of the expression
      */
     double calculateExpression(String expression);
}
