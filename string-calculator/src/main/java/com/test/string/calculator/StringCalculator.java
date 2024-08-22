package com.test.string.calculator;

public class StringCalculator {

    public int add(String numbers) {
        String delimiter = ",";
        if(numbers.isBlank()) {
            return 0;
        } else {
            if(numbers.charAt(0) == '/' && numbers.charAt(1) == '/') {
               delimiter = numbers.substring(2, numbers.indexOf("\n"));
               numbers = numbers.substring(numbers.indexOf("\n") + 1);
            }
            String[] tokens = numbers.replace("\n",delimiter).split(delimiter);
            int result = 0;
            for(String token : tokens) {
                result += Integer.parseInt(token);
            }
            return result;
        }
    }
}
