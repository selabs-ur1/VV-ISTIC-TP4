package fr.istic.vv;

import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeraUtils {

        private static final Map<Character, Integer> ROMAN_INT_MAP = Map.of(
                'I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000
        );

        private static final Map<Integer, String> ROMAN_NUMERAL_MAP = new LinkedHashMap<>();

        static {
                ROMAN_NUMERAL_MAP.put(1000, "M");
                ROMAN_NUMERAL_MAP.put(900, "CM");
                ROMAN_NUMERAL_MAP.put(500, "D");
                ROMAN_NUMERAL_MAP.put(400, "CD");
                ROMAN_NUMERAL_MAP.put(100, "C");
                ROMAN_NUMERAL_MAP.put(90, "XC");
                ROMAN_NUMERAL_MAP.put(50, "L");
                ROMAN_NUMERAL_MAP.put(40, "XL");
                ROMAN_NUMERAL_MAP.put(10, "X");
                ROMAN_NUMERAL_MAP.put(9, "IX");
                ROMAN_NUMERAL_MAP.put(5, "V");
                ROMAN_NUMERAL_MAP.put(4, "IV");
                ROMAN_NUMERAL_MAP.put(1, "I");
        }

        public static boolean isValidRomanNumeral(String value) {
                if (value == null || value.isEmpty()) {
                        return false;
                }

                String romanRules =
                        "M{0,3}" + "(CM|CD|D?C{0,3})" + "(XC|XL|L?X{0,3})" + "(IX|IV|V?I{0,3})";

                return value.matches(romanRules);
        }

        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) {
                        throw new IllegalArgumentException("Le nombre romain n'est pas valide");
                }

                int total = 0;
                int prevValue = 0;

                for (int i = 0; i < numeral.length(); i++) {
                        char currentSymbol = numeral.charAt(i);
                        int currentValue = ROMAN_INT_MAP.get(currentSymbol);

                        if (currentValue > prevValue) {
                                total += currentValue - 2 * prevValue;
                        } else {
                                total += currentValue;
                        }
                        prevValue = currentValue;
                }

                return total;
        }

        public static String toRomanNumeral(int number) {
                if (number <= 0 || number >= 4000) {
                        throw new IllegalArgumentException("Le nombre doit être compris entre 1 et 3999");
                }

                StringBuilder romanNumeral = new StringBuilder();

                for (Map.Entry<Integer, String> entry : ROMAN_NUMERAL_MAP.entrySet()) {
                        int value = entry.getKey();
                        String symbol = entry.getValue();

                        while (number >= value) {
                                romanNumeral.append(symbol);
                                number -= value;
                        }
                }

                return romanNumeral.toString();
        }

}