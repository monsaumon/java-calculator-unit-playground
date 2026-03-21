package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {
    private int customSeparatorEndIndex;
    private List<String> separators;

    // 각 과정을 순차적으로 실행
    public int sumString(String input) {
        getSeparators(input);
        String regex = generateRegexFromSeparators();
        return splitAndSum(input, regex);
    }

    // 구분자 리스트 생성
    private void getSeparators(String input) {
        // default separators
        separators = new ArrayList<>(Arrays.asList(",",":"));

        // custom separators
        customSeparatorEndIndex = -1;
        if (input.startsWith("//")) {
            customSeparatorEndIndex = input.indexOf("\n");
            if (customSeparatorEndIndex == -1) {
                throw new RuntimeException("Missing '\\n' after custom separators");
            }
            for (int i = 2; i < customSeparatorEndIndex; i++) {
                separators.add(Character.toString(input.charAt(i)));
            }
        }
    }

    // 구분자 리스트로부터 정규표현식 생성
    private String generateRegexFromSeparators() {
        // custom separator 중 정규표현식 메타 문자가 있다면 앞에 이스케이프 삽입
        List<String> regexMetaSymbols = Arrays.asList("\\","^","$",".","|","[","]","(",")","*","+","?","{","}");
        for (String regexMetaSymbol : regexMetaSymbols) {
            int regexMetaSymbolIndex = separators.indexOf(regexMetaSymbol);
            if (regexMetaSymbolIndex != -1) {
                separators.set(regexMetaSymbolIndex, "\\"+regexMetaSymbol);
            }
        }

        // 정규표현식 생성 및 반환
        return "[" + String.join("|", separators) + "]";
    }

    // 구분자(정규표현식) 기준으로 문자열을 자르고 숫자 총합 구하기
    private int splitAndSum(String input, String regex) {
        // custom separator 지정 부분 잘라내기
        String numString = input.substring(customSeparatorEndIndex + 1);

        // 정규표현식 기준으로 나누기
        String[] splitString = numString.split(regex);

        // 분할된 숫자 총합 구하기
        int sum = 0;
        for (String str: splitString) {
            try{
                int num = Integer.parseInt(str);
                if (num < 0) throw new RuntimeException("Do not input negative numbers");
                sum += num;
            } catch (NumberFormatException e) {
                throw new RuntimeException("Please input only numbers and separators");
            }
        }

        return sum;
    }
}
