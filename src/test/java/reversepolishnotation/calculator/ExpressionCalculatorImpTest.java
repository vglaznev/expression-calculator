package reversepolishnotation.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionCalculatorImpTest {

    @Test
    void calculateExpression() {
        ExpressionCalculator calculator = new ExpressionCalculatorImp();
        assertAll(
                ()->assertEquals(2, calculator.calculateExpression("-3+5")),
                ()->assertEquals(20.2, calculator.calculateExpression("10^2-8*10+5^(-1)")),
                ()->assertEquals(-4, calculator.calculateExpression("-(16+24)/10+(3-(21-18))"))
        );
        assertThrows(ArithmeticException.class, ()->calculator.calculateExpression("3/0"));
    }
}