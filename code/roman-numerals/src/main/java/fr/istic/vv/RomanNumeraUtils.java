package fr.istic.vv;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RomanNumeraUtils {

        private static final Pattern VALID_ROMAN_PATTERN = Pattern.compile(
                        "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

  private static final Map<Character, Integer> ROMAN_VALUES = new LinkedHashMap<>();
    static {
        ROMAN_VALUES.put('I', 1);
        ROMAN_VALUES.put('V', 5);
        ROMAN_VALUES.put('X', 10);
        ROMAN_VALUES.put('L', 50);
        ROMAN_VALUES.put('C', 100);
        ROMAN_VALUES.put('D', 500);
        ROMAN_VALUES.put('M', 1000);
    }

        public static boolean isValidRomanNumeral(String value) {
                if (value == null || value.isEmpty()) {
                        return false;
                }

                if (!VALID_ROMAN_PATTERN.matcher(value).matches()) {
                        return false;
                }

                if (hasInvalidRepetition(value)) {
                        return false;
                }

                return true;
        }

        /**
         * Check for invalid symbol repetition
         * 
         * @param value Roman numeral string
         * @return true if there's invalid repetition, false otherwise
         */
        private static boolean hasInvalidRepetition(String value) {
                if (value.contains("DD") || value.contains("LL") || value.contains("VV")) {
                        return true;
                }

                return value.contains("MMMM") ||
                                value.contains("CCCC") ||
                                value.contains("XXXX") ||
                                value.contains("IIII");
        }

        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) {
                        throw new IllegalArgumentException("Invalid Roman numeral");
                }

                int total = 0;
                int prevValue = 0;

                for (int i = numeral.length() - 1; i >= 0; i--) {
                        int currentValue = ROMAN_VALUES.get(numeral.charAt(i));

                        if (currentValue >= prevValue) {
                                total += currentValue;
                        }
                        else {
                                total -= currentValue;
                        }

                        prevValue = currentValue;
                }

                return total;
        }



    /**
     * Converts an integer to its Roman numeral representation
     * @param number integer to convert (1-3999)
     * @return Roman numeral string
     * @throws IllegalArgumentException if number is out of range
     */
    public static String toRomanNumeral(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Number out of range (1-3999)");
        }
        
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder roman = new StringBuilder();
        
        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                roman.append(symbols[i]);
                number -= values[i];
            }
        }
        
        return roman.toString();
    }

}
