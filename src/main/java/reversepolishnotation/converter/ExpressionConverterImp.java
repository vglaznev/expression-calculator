package reversepolishnotation.converter;

import reversepolishnotation.tokenizer.ExpressionTokenizer;

import java.util.*;

public class ExpressionConverterImp implements ExpressionConverter {
    private static final Map<String, Integer> priorityOfOperations = Map.of("+", 3, "-", 3, "/", 2, "*", 2, "^", 1);

    public String convertToReversePolishNotation(String expression) {
        List<String> tokenizedExpression = ExpressionTokenizer.tokenizeExpression(expression);
        Stack<String> operations = new Stack<>();
        StringBuilder reversePolishNotationExpression = new StringBuilder();
        for (String token : tokenizedExpression) {
            if (ExpressionTokenizer.isNumeric(token)) {
                reversePolishNotationExpression.append(token).append(" ");
            } else if (token.equals("(")) {
                operations.push(token);
            } else if (token.equals(")")) {
                while (!operations.peek().equals("(")) {
                    reversePolishNotationExpression.append(operations.pop()).append(" ");
                }
                operations.pop();
            } else if (priorityOfOperations.containsKey(token)) {
                while (!operations.isEmpty() && !operations.peek().equals("(") && priorityOfOperations.get(operations.peek()) <= priorityOfOperations.get(token)) {
                    reversePolishNotationExpression.append(operations.pop()).append(" ");
                }
                operations.push(token);
            }
        }
        while (!operations.isEmpty()) {
            reversePolishNotationExpression.append(operations.pop()).append(" ");
        }
        return reversePolishNotationExpression.toString();
    }
}
