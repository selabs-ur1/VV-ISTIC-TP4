package fr.istic.vv;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RomanNumeraUtils {

    public static final Map<Character, Integer> ROMAN_TO_ARABIC = new HashMap<>();
    static {
        ROMAN_TO_ARABIC.put('I', 1);
        ROMAN_TO_ARABIC.put('V', 5);
        ROMAN_TO_ARABIC.put('X', 10);
        ROMAN_TO_ARABIC.put('L', 50);
        ROMAN_TO_ARABIC.put('C', 100);
        ROMAN_TO_ARABIC.put('D', 500);
        ROMAN_TO_ARABIC.put('M', 1000);
    }

    private static final Map<Integer, String> ARABIC_TO_ROMAN = new LinkedHashMap<>();
    static {
        ARABIC_TO_ROMAN.put(1000, "M");
        ARABIC_TO_ROMAN.put(900, "CM");
        ARABIC_TO_ROMAN.put(500, "D");
        ARABIC_TO_ROMAN.put(400, "CD");
        ARABIC_TO_ROMAN.put(100, "C");
        ARABIC_TO_ROMAN.put(90, "XC");
        ARABIC_TO_ROMAN.put(50, "L");
        ARABIC_TO_ROMAN.put(40, "XL");
        ARABIC_TO_ROMAN.put(10, "X");
        ARABIC_TO_ROMAN.put(9, "IX");
        ARABIC_TO_ROMAN.put(5, "V");
        ARABIC_TO_ROMAN.put(4, "IV");
        ARABIC_TO_ROMAN.put(1, "I");
    }

    public static boolean isValidRomanNumeral(String value) {

        if (value == null || value.isEmpty()) {
            return false;
        }

        Map<Character, Integer> characterCount = new HashMap<>();
        for (char c : value.toCharArray()) {
            if (!ROMAN_TO_ARABIC.containsKey(c)) {
                return false;
            }

            characterCount.put(c, characterCount.getOrDefault(c, 0) + 1);
        }

        List<Character> maxThreeTimes = Arrays.asList('M', 'C', 'X', 'I');
        List<Character> maxOnce = Arrays.asList('D', 'L', 'V');

        for (Map.Entry<Character, Integer> entry : characterCount.entrySet()) {

            if (maxThreeTimes.contains(entry.getKey()) && entry.getValue() > 3) {
                return false;
            }

            if (maxOnce.contains(entry.getKey()) && entry.getValue() > 1) {
                return false;
            }
        }
        
        for (int i = 0; i < value.length() - 1; i++) {
            Character current = value.charAt(i);
            Character next = value.charAt(i + 1);

            if (ROMAN_TO_ARABIC.get(current) < ROMAN_TO_ARABIC.get(next)) {
                if (!isValidSubtraction(current, next)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int parseRomanNumeral(String numeral) {
        if (!isValidRomanNumeral(numeral)) {
            throw new IllegalArgumentException("Invalid Roman numeral");
        }

        int result = 0;

        for (int i = 0; i < numeral.length(); i++) {
            int current = ROMAN_TO_ARABIC.get(numeral.charAt(i));
            int next = (i + 1 < numeral.length()) ? ROMAN_TO_ARABIC.get(numeral.charAt(i + 1)) : 0;

            if (current < next) {
                result += next - current;
                i++;
            } else {
                result += current;
            }
        }

        return result;
    }

    public static String toRomanNumeral(int number) {
        if (number <= 0 || number >= 4000) {
            throw new IllegalArgumentException("Number out of range");
        }

        StringBuilder romanNumeral = new StringBuilder();

        for (Map.Entry<Integer, String> entry : ARABIC_TO_ROMAN.entrySet()) {
            while (number >= entry.getKey()) {
                romanNumeral.append(entry.getValue());
                number -= entry.getKey();
            }
        }

        return romanNumeral.toString();
    }

    private static boolean isValidSubtraction(char smaller, char larger) {
        int smallerValue = ROMAN_TO_ARABIC.get(smaller);
        int largerValue = ROMAN_TO_ARABIC.get(larger);

        return (largerValue == 5 * smallerValue || largerValue == 10 * smallerValue);
    }
}