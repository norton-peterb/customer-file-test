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

    @Test
    void test_Step2_ThreeNumbers() {
        int result = new StringCalculator().add("1,2,3");
        Assertions.assertEquals(6, result);
    }

    @Test
    void test_Step2_MoreNumbers() {
        int result = new StringCalculator().add("1,2,3,5,8");
        Assertions.assertEquals(19, result);
    }

    @Test
    void test_Step3_NewLineInNumbers() {
        int result = new StringCalculator().add("1\n2,3");
        Assertions.assertEquals(6, result);
    }

    @Test
    void test_Step4_CustomDelimiter() {
        int result = new StringCalculator().add("//;\n1;2");
        Assertions.assertEquals(3, result);
    }

}
