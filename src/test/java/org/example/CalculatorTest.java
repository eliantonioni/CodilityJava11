package org.example;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }

    @Test
    public void testEvaluateExpression() {
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6, sum);
    }

    @Test
    public void testException() {
        Exception exc = assertThrows(NullPointerException.class, () -> calculator.evaluate(null));
        System.out.println(exc.getMessage());
    }

    @Test
    void testOsName() {
        Assumptions.assumeTrue(System.getProperty("os.name").toUpperCase().contains("MAC"));
        assertTrue(true);
    }

}
