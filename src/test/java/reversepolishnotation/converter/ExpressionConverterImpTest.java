package reversepolishnotation.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionConverterImpTest {

    @Test
    void convertToReversePolishNotation() {
        ExpressionConverter converter = new ExpressionConverterImp();
        assertAll(
                ()->assertEquals("0 1 - 0 1 - 5 + + ", converter.convertToReversePolishNotation("-1+(-1+5)")),
                ()->assertEquals("0 0 1 - 5 + - ", converter.convertToReversePolishNotation("-(-1+5)")),
                ()->assertEquals("6 2 ^ 2 / 0 10 5 * - * 3 + ", converter.convertToReversePolishNotation("6^2/2*(-10*5)+3")),

                ()->assertThrows(IllegalArgumentException.class, () -> converter.convertToReversePolishNotation(null)),
                ()->assertThrows(IllegalArgumentException.class, () -> converter.convertToReversePolishNotation("3 2 + 1")),
                ()->assertThrows(IllegalArgumentException.class, () -> converter.convertToReversePolishNotation("3*() + 1")),
                ()->assertThrows(IllegalArgumentException.class, () -> converter.convertToReversePolishNotation("3 * + 1"))
        );

    }
}