package com.test.string.calculator;

public class StringCalculator {

    public int add(String numbers) {
        if(numbers.isBlank()) {
            return 0;
        } else {
            String[] tokens = numbers.replace("\n",",").split(",");
            int result = 0;
            for(String token : tokens) {
                result += Integer.parseInt(token);
            }
            return result;
        }
    }
}
