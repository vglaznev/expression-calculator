package reversepolishnotation.validator;

import org.junit.jupiter.api.Test;
import reversepolishnotation.tokenizer.ExpressionTokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionValidatorTest {

    @Test
    void checkBrackets() {
        assertEquals(ValidatorResponse.StatusCode.OKAY, ExpressionValidator.checkBrackets(ExpressionTokenizer.tokenizeExpression("-(1-(3+2)*4)")).getStatusCode());
        assertEquals(ValidatorResponse.StatusCode.ERROR, ExpressionValidator.checkBrackets(ExpressionTokenizer.tokenizeExpression("-(1-(3+2)*4")).getStatusCode());
        assertEquals(ValidatorResponse.StatusCode.ERROR, ExpressionValidator.checkBrackets(ExpressionTokenizer.tokenizeExpression("-1-(3+2)*4)")).getStatusCode());
    }

    @Test
    void checkForIllegalToken() {
        assertEquals(ValidatorResponse.StatusCode.OKAY, ExpressionValidator.checkForIllegalToken(ExpressionTokenizer.tokenizeExpression("-(1-(3+2)*4)")).getStatusCode());
        assertEquals(ValidatorResponse.StatusCode.ERROR, ExpressionValidator.checkForIllegalToken(ExpressionTokenizer.tokenizeExpression("-(1-(something3+2)*4)")).getStatusCode());
    }

    @Test
    void checkForBadSequence() {
        assertEquals(ValidatorResponse.StatusCode.OKAY, ExpressionValidator.checkForBadSequence(ExpressionTokenizer.tokenizeExpression("-(1-(3+2)*4)")).getStatusCode());
        assertEquals(ValidatorResponse.StatusCode.ERROR, ExpressionValidator.checkForBadSequence(ExpressionTokenizer.tokenizeExpression("--(3+2)*4)")).getStatusCode());
        assertEquals(ValidatorResponse.StatusCode.ERROR, ExpressionValidator.checkForBadSequence(ExpressionTokenizer.tokenizeExpression("-(1-(3 2)*4)")).getStatusCode());
        assertEquals(ValidatorResponse.StatusCode.ERROR, ExpressionValidator.checkForBadSequence(ExpressionTokenizer.tokenizeExpression("-(1-()*4)")).getStatusCode());
    }
}