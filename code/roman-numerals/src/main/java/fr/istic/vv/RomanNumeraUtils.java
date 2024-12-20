package fr.istic.vv;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeraUtils {


        public static boolean isValidRomanNumeral(String value) {

                if (value == null || value.isEmpty()) {
                        return false;
                }
                Map<Character, Integer> symbols = Map.of(
                        'M', 1000,
                        'D', 500,
                        'C', 100,
                        'L', 50,
                        'X', 10,
                        'V', 5,
                        'I', 1
                );

                int maxRepeat = 1;
                char previousChar = value.charAt(0);
                int previousValue = symbols.getOrDefault(previousChar, -1);

                if (previousValue == -1) {
                        return false;
                }

                for (int i = 1; i < value.length(); i++) {
                        char currentChar = value.charAt(i);
                        int currentValue = symbols.getOrDefault(currentChar, -1);

                        if (currentValue == -1) {
                                return false;
                        }

                        if (currentChar == previousChar) {
                                maxRepeat++;
                                if ((currentChar == 'M' || currentChar == 'C' || currentChar == 'X' || currentChar == 'I') && maxRepeat > 3) {
                                        return false;
                                } else if ((currentChar == 'D' || currentChar == 'L' || currentChar == 'V') && maxRepeat > 1) {
                                        return false;
                                }
                        } else {
                                maxRepeat = 1;
                        }

                        if (currentValue > previousValue) {
                                if (!(previousChar == 'I' && (currentChar == 'V' || currentChar == 'X')) &&
                                        !(previousChar == 'X' && (currentChar == 'L' || currentChar == 'C')) &&
                                        !(previousChar == 'C' && (currentChar == 'D' || currentChar == 'M'))) {
                                        return false;
                                }
                        }

                        previousChar = currentChar;
                        previousValue = currentValue;
                }

                return true;
        }

        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) {
                        throw new IllegalArgumentException("Invalid Roman numeral: " + numeral);
                }

                Map<Character, Integer> symbols = Map.of(
                        'M', 1000,
                        'D', 500,
                        'C', 100,
                        'L', 50,
                        'X', 10,
                        'V', 5,
                        'I', 1
                );

                int result = 0;
                int previousValue = 0;

                for (int i = numeral.length() - 1; i >= 0; i--) {

                        char currentChar = numeral.charAt(i);
                        int currentValue = symbols.get(currentChar);

                        if (currentValue < previousValue) {
                                result -= currentValue;
                        } else {
                                result += currentValue;
                        }

                        previousValue = currentValue;
                }

                return result;
        }

        public static String toRomanNumeral(int number) {
                if (number <= 0 || number > 3999) throw new IllegalArgumentException("Number out of range");
                StringBuilder result = new StringBuilder();

                Map<String, Integer> numerals = getStringIntegerMap();

                for (Map.Entry<String, Integer> numeral : numerals.entrySet()) {
                        while (number >= numeral.getValue()) {
                                result.append(numeral.getKey());
                                number -= numeral.getValue();
                        }
                }

                return result.toString();
        }

        private static Map<String, Integer> getStringIntegerMap() {
                Map<String, Integer> numerals = new LinkedHashMap<>();
                numerals.put("M", 1000);
                numerals.put("CM", 900);
                numerals.put("D", 500);
                numerals.put("CD", 400);
                numerals.put("C", 100);
                numerals.put("XC", 90);
                numerals.put("L", 50);
                numerals.put("XL", 40);
                numerals.put("X", 10);
                numerals.put("IX", 9);
                numerals.put("V", 5);
                numerals.put("IV", 4);
                numerals.put("I", 1);
                return numerals;
        }

}
