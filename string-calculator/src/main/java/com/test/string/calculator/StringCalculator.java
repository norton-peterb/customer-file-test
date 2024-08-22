package com.test.string.calculator;

import java.util.*;

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

    private List<String> getDelimiters(String numbers) {
        if(numbers.charAt(0) == '/' && numbers.charAt(1) == '/') {
            List<String> delimiters = new LinkedList<>();
            String delimString = numbers.substring(2, numbers.indexOf('\n'));
            String[] delimTokens = delimString.split("\\[|\\[*]");
            Arrays.stream(delimTokens).forEach(
                    token -> {
                        if(!token.isBlank()) {
                            delimiters.add(token.replace("]", "").replace("[", "")
                                    .replace("|","\\|"));
                        }
                    }
            );
            return delimiters;
        } else {
            return List.of(",");
        }
    }

    private List<String> applyAllDelimiters(String numbers, List<String> delimiter) {
        List<String> startingTokens = applyDelimiter(List.of(numbers), delimiter.get(0));
        if(delimiter.size() > 1) {
            for(int i = 1 ; i < delimiter.size() ; i++) {
                startingTokens = applyDelimiter(startingTokens, delimiter.get(i));
            }
        }
        return startingTokens;
    }

    private List<String> applyDelimiter(List<String> inputTokens, String delimiter) {
        List<String> outputTokens = new LinkedList<>();
        inputTokens.forEach(
                inputToken -> {
                    String[] tokens = inputToken.replace("\n",delimiter).split(delimiter);
                    outputTokens.addAll(Arrays.stream(tokens).toList());
                }
        );
        return outputTokens;
    }

    public int add(String numbers) {
        if(numbers.isBlank()) {
            return 0;
        } else {
            List<String> delimiters = getDelimiters(numbers);
            if(numbers.charAt(0) == '/' && numbers.charAt(1) == '/') {
                numbers = numbers.substring(numbers.indexOf("\n") + 1);
            }
            List<String> tokens = applyAllDelimiters(numbers, delimiters);
            List<Integer> numberList = new LinkedList<>();
            tokens.forEach(number -> numberList.add(Integer.parseInt(number)));
            negativeNumberCheck(numberList);
            return numberList
                    .stream()
                    .filter(number -> number <= 1000)
                    .mapToInt(Integer::intValue).sum();
        }
    }
}
