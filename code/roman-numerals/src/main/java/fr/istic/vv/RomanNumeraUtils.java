package fr.istic.vv;

import java.util.*;

public class RomanNumeraUtils {

    private static final Map<Character, Integer> ROMAN_TO_INT_MAP = new HashMap<>();
    private static final Map<Integer, String> INT_TO_ROMAN_MAP = new LinkedHashMap<>();

    static {
        ROMAN_TO_INT_MAP.put('I', 1);
        ROMAN_TO_INT_MAP.put('V', 5);
        ROMAN_TO_INT_MAP.put('X', 10);
        ROMAN_TO_INT_MAP.put('L', 50);
        ROMAN_TO_INT_MAP.put('C', 100);
        ROMAN_TO_INT_MAP.put('D', 500);
        ROMAN_TO_INT_MAP.put('M', 1000);

        INT_TO_ROMAN_MAP.put(1000, "M");
        INT_TO_ROMAN_MAP.put(900, "CM");
        INT_TO_ROMAN_MAP.put(500, "D");
        INT_TO_ROMAN_MAP.put(400, "CD");
        INT_TO_ROMAN_MAP.put(100, "C");
        INT_TO_ROMAN_MAP.put(90, "XC");
        INT_TO_ROMAN_MAP.put(50, "L");
        INT_TO_ROMAN_MAP.put(40, "XL");
        INT_TO_ROMAN_MAP.put(10, "X");
        INT_TO_ROMAN_MAP.put(9, "IX");
        INT_TO_ROMAN_MAP.put(5, "V");
        INT_TO_ROMAN_MAP.put(4, "IV");
        INT_TO_ROMAN_MAP.put(1, "I");
    }

    public static boolean isValidRomanNumeral(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        String validSymbols = "IVXLCDM";
        for (char c : input.toCharArray()) {
            if (validSymbols.indexOf(c) == -1) {
                return false; // Invalid symbol
            }
        }

        int prevValue = 0;
        int repeatCount = 1;

        for (int i = input.length() - 1; i >= 0; i--) {
            char currentChar = input.charAt(i);
            int currentValue = ROMAN_TO_INT_MAP.get(currentChar);

            // Rule 1: If a smaller value appears before a larger value, it must be subtractive
            if (currentValue < prevValue) {
                if (!isValidSubtractivePair(currentChar, input.charAt(i + 1))) {
                    return false;
                }
            } else {
                // Rule 2: Check for excessive repetition
                if (currentValue == prevValue) {
                    repeatCount++;
                    if (repeatCount > getMaxRepetition(currentChar)) {
                        return false;
                    }
                } else {
                    repeatCount = 1; // Reset counter for a new numeral
                }
            }

            prevValue = currentValue;
        }

        return true;
    }

    private static boolean isValidSubtractivePair(char smaller, char larger) {
        return (smaller == 'I' && (larger == 'V' || larger == 'X')) ||
                (smaller == 'X' && (larger == 'L' || larger == 'C')) ||
                (smaller == 'C' && (larger == 'D' || larger == 'M'));
    }

    private static int getMaxRepetition(char c) {
        // I, X, C, M can appear at most 3 times consecutively
        // V, L, D can appear only once
        if (c == 'I' || c == 'X' || c == 'C' || c == 'M') return 3;
        return 1;
    }

    public static void main(String[] args) {
        System.out.println(isValidRomanNumeral("XIV"));   // true
        System.out.println(isValidRomanNumeral("MMMM"));  // false
        System.out.println(isValidRomanNumeral("IIII"));  // false
        System.out.println(isValidRomanNumeral("IX"));    // true
        System.out.println(isValidRomanNumeral("IC"));    // false
    }

    public static int parseRomanNumeral(String numeral) {
        if (!isValidRomanNumeral(numeral)) {
            throw new IllegalArgumentException("Invalid Roman numeral");
        }

        int total = 0;
        int previousValue = 0;
        char[] charArray = numeral.toCharArray();

        for (char c : charArray) {
            Integer currentValue = ROMAN_TO_INT_MAP.get(c);
            if (currentValue <= previousValue) {
                total += currentValue;
            } else {
                total += currentValue - 2*previousValue;
            }

            previousValue = currentValue;
        }

        return total;
    }

    public static String toRomanNumeral(int number) {
        if (number <= 0 || number > 3999) {
            throw new IllegalArgumentException("Le nombre doit être compris entre 1 et 3999.");
        }

        StringBuilder roman = new StringBuilder();

        for (Map.Entry<Integer, String> entry : INT_TO_ROMAN_MAP.entrySet()) {
            while (number >= entry.getKey()) {
                roman.append(entry.getValue());
                number -= entry.getKey();
            }
        }

        return roman.toString();
    }

}
