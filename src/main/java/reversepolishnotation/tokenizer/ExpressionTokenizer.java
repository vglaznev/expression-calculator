package reversepolishnotation.tokenizer;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;


/**
 * Utility class that provides methods for splitting expressions into tokens and determining numerical operands, supported operators, brackets
 */
public class ExpressionTokenizer {
    /**
     * Regular-expression for splitting expressions
     */
    private static final String regexSplit = "(?<=[-+*/^() ])|(?=[-+*/^() ])";
    /**
     * Regular-expression for determining number
     */
    private static final Pattern numberPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    /**
     * Set for determining operators
     */
    private static final Set<String> operators = Set.of("+", "-", "/", "*", "^");
    /**
     * Set for determining brackets
     */
    private static final Set<String> brackets = Set.of("(", ")");

    /**
     * Checks if string represents a number
     * @param number string representations of number
     * @return true if input string represents a number; false otherwise
     */
    public static boolean isNumeric(String number) {
        if (number == null) {
            return false;
        }
        return numberPattern.matcher(number).matches();
    }

    /**
     * Checks if string represents an operator
     * @param operation string representations an operator
     * @return true if input string represents an operator; false otherwise
     */
    public static boolean isOperator(String operation){
        if (operation == null) {
            return false;
        }
        return operators.contains(operation);
    }

    /**
     * Checks if string represents a bracket
     * @param bracket string representations a bracket
     * @return true if input string represents a bracket; false otherwise
     */
    public static boolean isBracket(String bracket){
        if (bracket == null) {
            return false;
        }
        return brackets.contains(bracket);
    }

    /**
     * Split expression into numerical operands, supported operators, brackets\
     * @throws IllegalArgumentException if expression is null
     * @param expression expression to be split
     * @return array of tokens
     */
    public static String[] tokenizeExpression(String expression) {
        if(expression == null){
            throw new IllegalArgumentException("Expression is null");
        }
        return Arrays.stream(expression.replaceAll("\\s+", " ").split(regexSplit))
                .filter(x -> !x.equals(" "))
                .toArray(String[]::new);
    }

}
