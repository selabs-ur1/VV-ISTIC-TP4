package fr.istic.vv;

import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeraUtils {
        public static final Map<String, Integer> numbers = new LinkedHashMap<String, Integer>() {
                {
                        put("M", 1000);
                        put("CM", 900);
                        put("D", 500);
                        put("CD", 400);
                        put("C", 100);
                        put("XC", 90);
                        put("L", 50);
                        put("XL", 40);
                        put("X", 10);
                        put("IX", 9);
                        put("V", 5);
                        put("IV", 4);
                        put("I", 1);
                }
        };

        public static boolean isValidRomanNumbers(String number) {
                if (!number.matches("[MDCLXVI]*")) {
                        return false;
                }
                String[] repetitions = {
                        "VV", "LL", "DD", "IIII", "XXXX", "CCCC", "MMMM"
                };
                // I, X, C, M cannot be repeated more than 3 times.
                // V, L, D cannot be repeated.
                for (String repetition : repetitions) {
                        if (number.matches(repetition)) {
                                return false;
                        }
                }
                // Check if number contains valid pairs (ie IV, IX, XC, XL, CD, CM)
                if (number.length() > 1) {
                        String currentNumeral = "", nextNumeral = "", pair = "";
                        int currentNumeralValue = 0, nextNumeralValue = 0;

                        int i = 0;
                        while (i < number.length()) {
                                currentNumeral = number.substring(i, i + 1);

                                if (i < number.length() - 1) {
                                        nextNumeral = number.substring(i + 1, i + 2);
                                }

                                currentNumeralValue = numbers.get(currentNumeral);
                                nextNumeralValue = numbers.get(nextNumeral);
                                pair = currentNumeral + nextNumeral;

                                if (nextNumeralValue > currentNumeralValue && !numbers.containsKey(pair)) {
                                        return false;
                                }

                                i++;
                        }
                }

                return true;
        }

        public static int parseRomanNumber(String number) {
                if (number == null || number.isEmpty() || !isValidRomanNumbers(number)) {
                        return 0;
                }
                int res = 0;
                String currentNumeral = "", nextNumeral = "", pair = "";
                int i = 0;
                while (i < number.length()) {
                        currentNumeral = number.substring(i, i + 1);

                        if (i < number.length() - 1) {
                                nextNumeral = number.substring(i + 1, i + 2);
                        }

                        pair = currentNumeral + nextNumeral;
                        if (numbers.containsKey(pair)) {
                                res += numbers.get(pair);
                                i += 2;
                        } else {
                                res += numbers.get(currentNumeral);
                                i += 1;
                        }
                }
                return res;
        }

        public static String toRomanNumber(int number) {
                String res = "";
                if (number <= 0 || number > 3999) {
                        return res;
                }
                for (Map.Entry<String, Integer> entry : numbers.entrySet()) {
                        while (number >= entry.getValue()) {
                                res += entry.getKey();
                                number -= entry.getValue();
                        }
                }

                return res;
        }
}