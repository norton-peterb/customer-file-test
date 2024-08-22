package com.test.string.calculator;

public class StringCalculator {

    public int add(String numbers) {
        if(numbers.isBlank()) {
            return 0;
        } else if(numbers.contains(",")){
            return Integer.parseInt(numbers.substring(0,numbers.indexOf(",")))
                    + Integer.parseInt(numbers.substring(numbers.indexOf(",") + 1));
        } else {
            return Integer.parseInt(numbers);
        }
    }
}
