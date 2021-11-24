package reversepolishnotation.calculator;

import reversepolishnotation.converter.ExpressionConverter;
import reversepolishnotation.converter.ExpressionConverterImp;
import reversepolishnotation.tokenizer.ExpressionTokenizer;

import java.util.Stack;

/**
 * Class that provides method for calculating expression.
 * Implements ExpressionCalculator interface
 */
public class ExpressionCalculatorImp implements ExpressionCalculator {
    /**
     * Converter from infix-notation expression to reverse polish notation
     */
    private ExpressionConverter expressionConverter;

    /**
     * Creates new expression calculator
     */
    public ExpressionCalculatorImp() {
        expressionConverter = new ExpressionConverterImp();
    }

    /**
     * Calculates value of the expression
     * @param expression expression to be calculated
     * @return value of the expression
     */
    public double calculateExpression(String expression) {
        String polishNotationExpression = expressionConverter.convertToReversePolishNotation(expression);
        String[] tokenizedExpression = polishNotationExpression.split(" ");
        Stack<Double> stack = new Stack<>();
        for (String token : tokenizedExpression) {
            if (!ExpressionTokenizer.isOperator(token)) {
                Double operand = Double.parseDouble(token);
                stack.push(operand);
            } else {
                Double rightOperand = stack.pop();
                Double leftOperand = stack.pop();
                switch (token) {
                    case "+" -> stack.push(leftOperand + rightOperand);
                    case "-" -> stack.push(leftOperand - rightOperand);
                    case "*" -> stack.push(leftOperand * rightOperand);
                    case "/" -> {
                        if (rightOperand.equals(0.0)) {
                            throw new ArithmeticException("Division by zero");
                        }
                        stack.push(leftOperand / rightOperand);
                    }
                    case "^" -> stack.push(Math.pow(leftOperand, rightOperand));
                }
            }
        }
        return stack.pop();
    }
}
