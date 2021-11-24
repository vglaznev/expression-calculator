package reversepolishnotation.converter;

import reversepolishnotation.tokenizer.ExpressionTokenizer;
import reversepolishnotation.validator.ExpressionValidator;
import reversepolishnotation.validator.ValidatorResponse;

import java.util.Map;
import java.util.Stack;

/**
 * Class that provides methods for converting infix-notation expression to reverse polish notation expression
 */
public class ExpressionConverterImp implements ExpressionConverter {
    /**
     * Map that pairs operations to their priority
     */
    private static final Map<String, Integer> priorityOfOperations = Map.of("+", 3, "-", 3, "/", 2, "*", 2, "^", 1);

    /**
     *Transforms unary minuses to binary by adding zeros before them
     * @param expression expression to be processed
     * @return expression without unary minuses
     */
    private String processUnaryMinus(String expression) {
        expression = expression.replaceFirst("^-", "0-");
        expression = expression.replaceAll("(\\( *)(-)", "$10$2");
        return expression;
    }

    /**
     * Inspects expression for something bad occurrences.
     * @throws  IllegalArgumentException if they occurred.
     * @param expression expression to be checked
     */
    private void validateExpression(String[] expression) {
        ValidatorResponse response;
        String errorMessage = null;
        if ((response = ExpressionValidator.checkBrackets(expression)).getStatusCode() == ValidatorResponse.StatusCode.ERROR) {
            errorMessage = response.getMessage();
        } else if ((response = ExpressionValidator.checkForBadSequence(expression)).getStatusCode() == ValidatorResponse.StatusCode.ERROR) {
            errorMessage = response.getMessage();
        } else if ((response = ExpressionValidator.checkForIllegalToken(expression)).getStatusCode() == ValidatorResponse.StatusCode.ERROR) {
            errorMessage = response.getMessage();
        }
        if (errorMessage != null) throw new IllegalArgumentException(errorMessage);
    }

    /**
     * Convert expression from infix-notation to reverse polish notation
     * @throws IllegalArgumentException if expression is null
     * @param expression expression to be converted
     * @return expression in reverse polish notation
     */
    public String convertToReversePolishNotation(String expression) {
        if(expression == null){
            throw new IllegalArgumentException("Expression is null");
        }
        expression = processUnaryMinus(expression);
        String[] tokenizedExpression = ExpressionTokenizer.tokenizeExpression(expression);
        validateExpression(tokenizedExpression);
        Stack<String> operations = new Stack<>();
        StringBuilder reversePolishNotationExpression = new StringBuilder();
        String previousToken = null;
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
            previousToken = token;
        }
        while (!operations.isEmpty()) {
            reversePolishNotationExpression.append(operations.pop()).append(" ");
        }
        return reversePolishNotationExpression.toString();
    }
}
