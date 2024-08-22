package com.test.string.calculator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    private void negativeNumberCheck(List<Integer> numbers) {
        List<Integer> negativeNumbers = numbers.stream().filter(number -> number < 0)
                .toList();
        if(!negativeNumbers.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Negatives not allowed: ");
            negativeNumbers.forEach(
                    negativeNumber -> {
                        stringBuilder.append(negativeNumber);
                        stringBuilder.append(",");
                    }
            );
            String errorMessage = stringBuilder.toString();
            if(errorMessage.charAt(errorMessage.length() - 1) == ',') {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            throw new StringCalculatorException(errorMessage);
        }
    }

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
            List<Integer> numberList = new LinkedList<>();
            Arrays.stream(tokens).forEach(number -> numberList.add(Integer.parseInt(number)));
            negativeNumberCheck(numberList);
            return numberList
                    .stream()
                    .filter(number -> number <= 1000)
                    .mapToInt(Integer::intValue).sum();
        }
    }
}
