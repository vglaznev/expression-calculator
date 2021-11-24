package reversepolishnotation.tokenizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTokenizerTest {

    @Test
    void isNumeric() {
        assertTrue(ExpressionTokenizer.isNumeric("301"));
        assertTrue(ExpressionTokenizer.isNumeric("3.23"));
        assertTrue(ExpressionTokenizer.isNumeric("-4.21"));
        assertFalse(ExpressionTokenizer.isNumeric("-."));
        assertFalse(ExpressionTokenizer.isNumeric("word"));

    }

    @Test
    void isOperation() {
        assertTrue(ExpressionTokenizer.isOperator("+"));
        assertTrue(ExpressionTokenizer.isOperator("-"));
        assertTrue(ExpressionTokenizer.isOperator("/"));
        assertTrue(ExpressionTokenizer.isOperator("*"));
        assertTrue(ExpressionTokenizer.isOperator("^"));
        assertFalse(ExpressionTokenizer.isOperator("3"));
        assertFalse(ExpressionTokenizer.isOperator(null));
    }

    @Test
    void isBracket() {
        assertTrue(ExpressionTokenizer.isBracket("("));
        assertTrue(ExpressionTokenizer.isBracket(")"));
        assertFalse(ExpressionTokenizer.isBracket("-"));
        assertFalse(ExpressionTokenizer.isBracket(null));
    }

    @Test
    void tokenizeExpression() {
        assertArrayEquals(new String[]{"3", "-", "1", "+", "2"},ExpressionTokenizer.tokenizeExpression("3-1+2"));
        assertArrayEquals(new String[]{"3", "^","(","-", "1", ")", "/", "2"},ExpressionTokenizer.tokenizeExpression("3^(-1)/2"));
        assertThrows(IllegalArgumentException.class,()->ExpressionTokenizer.tokenizeExpression(null));
    }
}