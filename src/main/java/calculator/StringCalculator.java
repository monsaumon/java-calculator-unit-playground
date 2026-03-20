package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {
    int sumString(String string) {
        // 구분자 리스트 생성
        int customSeparatorEndIndex = -1;
        List<String> separators = new ArrayList<>(Arrays.asList(",",":"));
        if (string.startsWith("//")) {
            customSeparatorEndIndex = string.indexOf("\n");
            if (customSeparatorEndIndex == -1) throw new RuntimeException("Missing '\\n' after custom separators");
            for (int i = 2; i < customSeparatorEndIndex; i++) {
                separators.add(Character.toString(string.charAt(i)));
            }
        }

        // 정규표현식 메타 문자가 있다면 앞에 이스케이프 삽입
        List<String> regexMetaSymbols = Arrays.asList("\\","^","$",".","|","[","]","(",")","*","+","?","{","}");
        for (String regexMetaSymbol : regexMetaSymbols) {
            int regexMetaSymbolIndex;
            regexMetaSymbolIndex = separators.indexOf(regexMetaSymbol);
            if (regexMetaSymbolIndex != -1) {
                separators.set(regexMetaSymbolIndex, "\\"+regexMetaSymbol);
            }
        }

        // 구분자 기준으로 split하고 sum
        String[] splitString = string.substring(customSeparatorEndIndex+1).split("[" + String.join("|", separators) + "]");
        int sum = 0;
        for (String num: splitString) {
            try{
                int parsedNum = Integer.parseInt(num);
                if (parsedNum < 0) throw new RuntimeException("Do not input negative numbers");
                sum += parsedNum;
            } catch (NumberFormatException e) {
                throw new RuntimeException("Please input only numbers and separators");
            }
        }

        return sum;
    }
}
