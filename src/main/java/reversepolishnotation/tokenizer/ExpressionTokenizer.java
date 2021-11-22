package reversepolishnotation.tokenizer;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExpressionTokenizer {
    private static final String regexSplit = "(?<=[()+*/-^])|(?=[()+*/-^])";
    private static final Pattern numberPattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String number) {
        if (number == null) {
            return false;
        }
        return numberPattern.matcher(number).matches();
    }

    private static void compressUnaryMinus(List<String> tokenizedExpression, int index) {
        tokenizedExpression.set(index + 1, "-" + tokenizedExpression.get(index + 1));
        tokenizedExpression.remove(index);
    }

    private static List<String> associateUnaryMinus(List<String> tokenizedExpression) {
        if (tokenizedExpression.size() >= 2 && tokenizedExpression.get(0).equals("-") && isNumeric(tokenizedExpression.get(1))) {
            compressUnaryMinus(tokenizedExpression, 0);
        }
        for (int i = 0; i < tokenizedExpression.size() - 2; i++) {
            if (tokenizedExpression.get(i).equals("(") && tokenizedExpression.get(i + 1).equals("-") && ExpressionTokenizer.isNumeric(tokenizedExpression.get(i + 2))) {
                compressUnaryMinus(tokenizedExpression, i + 1);
                i++;
            }
        }
        return tokenizedExpression;
    }

    public static List<String> tokenizeExpression(String expression) {
        return ExpressionTokenizer.associateUnaryMinus(
                Arrays.stream(expression
                                .replaceAll("\\s+", "")
                                .split(regexSplit))
                        .collect(Collectors.toList())
        );
    }
}
