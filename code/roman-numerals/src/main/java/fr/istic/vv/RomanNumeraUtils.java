package fr.istic.vv;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeraUtils {

        private static final Map<String, Integer> romanValues = new LinkedHashMap<>();

        static {
                romanValues.put("I", 1);
                romanValues.put("IV", 4);
                romanValues.put("V", 5);
                romanValues.put("IX", 9);
                romanValues.put("X", 10);
                romanValues.put("XL", 40);
                romanValues.put("L", 50);
                romanValues.put("XC", 90);
                romanValues.put("C", 100);
                romanValues.put("CD", 400);
                romanValues.put("D", 500);
                romanValues.put("CM", 900);
                romanValues.put("M", 1000);
        }

        public static boolean isValidRomanNumeral(String value) {
                return value.matches("^[IVXLCDM]+$");
        }

        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) {
                        throw new IllegalArgumentException("Invalid Roman numeral");
                }

                int result = 0;
                int prevValue = 0;

                for (int i = numeral.length() - 1; i >= 0; i--) {
                        String currentSymbol = String.valueOf(numeral.charAt(i));
                        int currentValue = romanValues.get(currentSymbol);

                        if (currentValue < prevValue) {
                                result -= currentValue;
                        } else {
                                result += currentValue;
                        }

                        prevValue = currentValue;
                }

                return result;
        }

        public static String toRomanNumeral(int number) {
                if (number <= 0 || number >= 4000) {
                        throw new IllegalArgumentException("Number out of range for Roman numerals");
                }

                StringBuilder result = new StringBuilder();

                for (Map.Entry<String, Integer> entry : romanValues.entrySet()) {
                        String symbol = entry.getKey();
                        int value = entry.getValue();

                        while (number >= value) {
                                result.append(symbol);
                                number -= value;
                        }


                        SubtractiveNotation(result, number, value, 'I', 'V', 'X');
                        SubtractiveNotation(result, number, value, 'X', 'L', 'C');
                        SubtractiveNotation(result, number, value, 'C', 'D', 'M');
                }

                return result.toString();
        }

        private static void SubtractiveNotation(StringBuilder result, int number, int value,
                                                char unit, char midUnit, char nextUnit) {
                int midValue = romanValues.getOrDefault(String.valueOf(midUnit), 0);
                int nextValue = romanValues.getOrDefault(String.valueOf(nextUnit), 0);

                if (number + midValue >= nextValue && midValue > 0 && number >= value - midValue) {
                        result.append(unit).append(nextUnit);
                } else if (number >= value) {
                        result.append(unit);
                }
        }

        public static void main(String[] args) {
                // Example usage
                System.out.println(isValidRomanNumeral("XIV")); // true
                System.out.println(parseRomanNumeral("IV")); // 14
                System.out.println(toRomanNumeral(4)); // IV
        }
}
