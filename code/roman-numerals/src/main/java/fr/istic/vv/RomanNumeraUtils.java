package fr.istic.vv;

import java.util.*;

public class RomanNumeraUtils {

    public static boolean isValidRomanNumeral(String value) {

        if (!(value == null || value.isEmpty())) {

            List<Character> possibleLetters = Arrays.asList('M', 'D', 'C', 'X', 'V', 'I');

            Map<Character, Integer> romanNumerals = new HashMap<>();
            for (int i = 0; i < value.length(); i++) {
                char current = value.charAt(i);

                if (!possibleLetters.contains(current)) {
                    return false;
                }

                romanNumerals.put(current, romanNumerals.getOrDefault(current, 0) + 1);

                if ((romanNumerals.get(current) > 3 && (current == 'I' || current == 'X' || current == 'C' || current == 'M')) || (romanNumerals.get(current) > 1 && (current == 'D' || current == 'L' || current == 'V'))) {
                    return false;
                }

                if (i + 1 < value.length()) {
                    char next = value.charAt(i + 1);
                    if (current == 'I' && (next == 'V' || next == 'X')) {
                        i++;
                    } else if (current == 'X' && (next == 'L' || next == 'C')) {
                        i++;
                    } else if (current == 'C' && (next == 'D' || next == 'M')) {
                        i++;
                    } else if (current == 'V' || current == 'L' || current == 'D') {
                        return false;
                    } else if (current >= next) {

                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }


    public static int parseRomanNumeral(String numeral) {

        if (numeral == null || numeral.isEmpty() || !isValidRomanNumeral(numeral)) {
            return 0;
        }

        Map<Character, Integer> romanToValue = new HashMap<>();
        romanToValue.put('I', 1);
        romanToValue.put('V', 5);
        romanToValue.put('X', 10);
        romanToValue.put('L', 50);
        romanToValue.put('C', 100);
        romanToValue.put('D', 500);
        romanToValue.put('M', 1000);

        int result = 0;

        for (int i = 0; i < numeral.length(); i++) {
            char current = numeral.charAt(i);
            int currentValue = romanToValue.getOrDefault(current, 0);

            if (i + 1 < numeral.length()) {
                char next = numeral.charAt(i + 1);
                int nextValue = romanToValue.getOrDefault(next, 0);

                if (currentValue < nextValue) {
                    result -= currentValue;
                } else {
                    result += currentValue;
                }
            } else {
                result += currentValue;
            }
        }

        return result;
    }


    public static String toRomanNumeral(int number) {

        if (number <= 0 || number > 3999) {
            return "";
        }
        StringBuilder result = new StringBuilder();

        Map<Integer, String> romanNumerals = new LinkedHashMap<>();
        romanNumerals.put(1000, "M");
        romanNumerals.put(900, "CM");
        romanNumerals.put(500, "D");
        romanNumerals.put(400, "CD");
        romanNumerals.put(100, "C");
        romanNumerals.put(90, "XC");
        romanNumerals.put(50, "L");
        romanNumerals.put(40, "XL");
        romanNumerals.put(10, "X");
        romanNumerals.put(9, "IX");
        romanNumerals.put(5, "V");
        romanNumerals.put(4, "IV");
        romanNumerals.put(1, "I");

        for (Map.Entry<Integer, String> entry : romanNumerals.entrySet()) {
            while (number >= entry.getKey()) {
                result.append(entry.getValue());
                number -= entry.getKey();
            }
        }

        return result.toString();
    }


}
