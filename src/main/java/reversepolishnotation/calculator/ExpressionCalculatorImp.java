package reversepolishnotation.calculator;

import reversepolishnotation.converter.ExpressionConverter;
import reversepolishnotation.converter.ExpressionConverterImp;
import reversepolishnotation.tokenizer.ExpressionTokenizer;

import java.util.List;
import java.util.Set;
import java.util.Stack;

public class ExpressionCalculatorImp implements ExpressionCalculator {
    private static final Set<String> operations = Set.of("+", "-", "/", "*", "^");
    private ExpressionConverter expressionConverter;

    public ExpressionCalculatorImp() {
        expressionConverter = new ExpressionConverterImp();
    }

    public double calculateExpression(String expression) {
        String polishNotationExpression = expressionConverter.convertToReversePolishNotation(expression);
        List<String> tokenizedExpression = ExpressionTokenizer.tokenizeExpression(polishNotationExpression);
        Stack<Double> stack = new Stack<>();
        for (String token : tokenizedExpression) {
            if (!operations.contains(token)) {
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
