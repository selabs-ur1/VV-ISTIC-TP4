package fr.istic.vv;

import java.util.Map;
import java.util.LinkedHashMap;

public class RomanNumeraUtils {

        private static final Map<Character, Integer> SYMBOLS = Map.of(
                'M', 1000, 'D', 500, 'C', 100, 'L', 50, 'X', 10, 'V', 5, 'I', 1
        );

        private static final Map<String, Integer> NUMERALS = new LinkedHashMap<>();
        static {
                NUMERALS.put("M", 1000);
                NUMERALS.put("CM", 900);
                NUMERALS.put("D", 500);
                NUMERALS.put("CD", 400);
                NUMERALS.put("C", 100);
                NUMERALS.put("XC", 90);
                NUMERALS.put("L", 50);
                NUMERALS.put("XL", 40);
                NUMERALS.put("X", 10);
                NUMERALS.put("IX", 9);
                NUMERALS.put("V", 5);
                NUMERALS.put("IV", 4);
                NUMERALS.put("I", 1);
        }

        public static boolean isValidRomanNumeral(String value) {
                if (value == null || value.isEmpty()) return false;

                return value.matches("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
        }

        public static int parseRomanNumeral(String numeral) {
                if (numeral == null || numeral.isEmpty()) {
                        throw new IllegalArgumentException("Roman numeral cannot be null or empty");
                }

                if (!isValidRomanNumeral(numeral)) {
                        throw new IllegalArgumentException("Invalid Roman numeral: " + numeral);
                }

                int result = 0;
                int previousValue = 0;

                for (int i = numeral.length() - 1; i >= 0; i--) {
                        char currentChar = numeral.charAt(i);
                        int currentValue = SYMBOLS.get(currentChar);

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
                if (number <= 0 || number > 3999) {
                        throw new IllegalArgumentException("Number out of range (must be between 1 and 3999)");
                }

                StringBuilder result = new StringBuilder();

                for (Map.Entry<String, Integer> entry : NUMERALS.entrySet()) {
                        while (number >= entry.getValue()) {
                                result.append(entry.getKey());
                                number -= entry.getValue();
                        }
                }

                return result.toString();
        }
}
