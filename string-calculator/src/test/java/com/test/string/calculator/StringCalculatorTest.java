package com.test.string.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    @Test
    void test_Step1_ZeroLength() {
        int result = new StringCalculator().add("");
        Assertions.assertEquals(0, result);
    }

    @Test
    void test_Step1_OneNumber() {
        int result = new StringCalculator().add("1");
        Assertions.assertEquals(1, result);
    }

    @Test
    void test_Step1_TwoNumbers() {
        int result = new StringCalculator().add("1,2");
        Assertions.assertEquals(3,result);
    }

}
