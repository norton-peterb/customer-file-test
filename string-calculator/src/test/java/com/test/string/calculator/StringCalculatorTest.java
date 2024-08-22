package com.test.string.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void test_Step5_NegativeNumbersOne() {
        StringCalculatorException exception =
                assertThrows(StringCalculatorException.class,
                        () -> new StringCalculator().add("-1,2"));
        Assertions.assertEquals("Negatives not allowed: -1",
                exception.getMessage());
    }

    @Test
    void test_Step5_NegativeNumbersMoreThanOne() {
        StringCalculatorException exception =
                assertThrows(StringCalculatorException.class,
                        () -> new StringCalculator().add("2,-4,3,-5"));
        Assertions.assertEquals("Negatives not allowed: -4,-5",
                exception.getMessage());
    }

    @Test
    void test_Step6_BiggerThanAThousand() {
        int result = new StringCalculator().add("1001,2");
        Assertions.assertEquals(2, result);
    }

    @Test
    void test_Step7_DelimiterGreaterThanOneCharacter() {
        int result = new StringCalculator().add("//[|||]\n1|||2|||3");
        Assertions.assertEquals(6, result);
    }

    @Test
    void test_Step8_MultipleDelimiters() {
        int result = new StringCalculator().add("//[|][%]\n1|2%3");
        Assertions.assertEquals(6, result);
    }

    @Test
    void test_Step9_MultipleDelimitersAnyLength() {
        int result = new StringCalculator().add("//[|||][%]\n1|||2%3");
        Assertions.assertEquals(6, result);
    }

}
