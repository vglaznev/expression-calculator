package reversepolishnotation.validator;

import reversepolishnotation.tokenizer.ExpressionTokenizer;

import java.util.EmptyStackException;
import java.util.Stack;


/**
 *Utility class that provides methods for checking an expression for correctness
 */
public class ExpressionValidator {
    /**
     * Checks whether the brackets in the expression are consistent
     * @param tokenizedExpression expression that split into operands, operators and brackets
     * @return OKAY-response if brackets match; ERROR-response otherwise
     */
    public static ValidatorResponse checkBrackets(String[] tokenizedExpression) {
        final String errorMessagePattern = "Brackets don't match";
        Stack<String> stack = new Stack<>();
        for (String token : tokenizedExpression) {
            if (token.equals("(")) {
                stack.push("(");
            } else if (token.equals(")")) {
                try {
                    stack.pop();
                } catch (EmptyStackException e) {
                    return ValidatorResponse.of(ValidatorResponse.StatusCode.ERROR, errorMessagePattern);
                }
            }
        }
        return (stack.size() == 0 ? ValidatorResponse.of(ValidatorResponse.StatusCode.OKAY, "Successfully") : ValidatorResponse.of(ValidatorResponse.StatusCode.ERROR, errorMessagePattern));
    }

    /**
     * Checks if expression contain only supported tokens
     * @param tokenizedExpression expression that split into operands, operators and brackets
     * @return OKAY-response if all tokens are only numerical operands, supported operators and brackets; ERROR-response otherwise
     */
    public static ValidatorResponse checkForIllegalToken(String[] tokenizedExpression) {
        final String errorMessagePattern = "Illegal literal in the expression: \"%s\"";
        for (String token : tokenizedExpression) {
            if (!(ExpressionTokenizer.isBracket(token) || ExpressionTokenizer.isNumeric(token) || ExpressionTokenizer.isOperator(token))) {
                return ValidatorResponse.of(ValidatorResponse.StatusCode.ERROR, String.format(errorMessagePattern, token));
            }
        }
        return ValidatorResponse.of(ValidatorResponse.StatusCode.OKAY, "Successfully");
    }

    /**
     * Checks if operators, operands, expressions in brackets are missing
     * @param tokenizedExpression expression that split into operands, operators and brackets
     * @return  OKAY-response if no operators, operands, expressions in brackets are missed, supported operators and brackets; ERROR-response otherwise
     */
    public static ValidatorResponse checkForBadSequence(String[] tokenizedExpression) {
        final String errorMessagePattern = "Bad sequence in the expression: \"%s\" after \"%s\". ";
        String previousToken = null;
        String errorMessage = null;
        for (String token : tokenizedExpression) {
            if (ExpressionTokenizer.isOperator(token) && ExpressionTokenizer.isOperator(previousToken)) {
                errorMessage = "Operands missed.";
            } else if (ExpressionTokenizer.isNumeric(token) && ExpressionTokenizer.isNumeric(previousToken)) {
                errorMessage = "Operation missed.";
            } else if (token.equals(")") && previousToken != null && previousToken.equals("(")) {
                errorMessage = "Expression in brackets missed.";
            }
            if (errorMessage != null) {
                return ValidatorResponse.of(ValidatorResponse.StatusCode.ERROR, String.format(errorMessagePattern, token, previousToken) + errorMessage);
            }
            previousToken = token;
        }
        return ValidatorResponse.of(ValidatorResponse.StatusCode.OKAY, "Successfully");
    }
}
