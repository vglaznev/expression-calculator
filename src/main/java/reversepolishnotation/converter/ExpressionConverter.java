package reversepolishnotation.converter;

/**
 *Interface that provides behaviour of the expression converter
 */
public interface ExpressionConverter {
    /**
     * Convert expression to another notation
     * @param expression expression to be converted
     * @return converted expression
     */
    String convertToReversePolishNotation(String expression);
}
