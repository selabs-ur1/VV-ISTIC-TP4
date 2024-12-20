package fr.istic.vv;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeraUtils {

        private static final Map<Character, Integer> romanToIntMap = new HashMap<>();
        static {
                romanToIntMap.put('I', 1);
                romanToIntMap.put('V', 5);
                romanToIntMap.put('X', 10);
                romanToIntMap.put('L', 50);
                romanToIntMap.put('C', 100);
                romanToIntMap.put('D', 500);
                romanToIntMap.put('M', 1000);
        }

        private static final int[] intValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        private static final String[] romanSymbols = {
                "M", "CM", "D", "CD", "C", "XC",
                "L", "XL", "X", "IX", "V", "IV", "I"
        };

        public static boolean isValidRomanNumeral(String value) {
                if (value == null || value.isEmpty()) {
                        return false;
                }

                String regex = "^M{0,3}(CM|CD|D?C{0,3})"
                        + "(XC|XL|L?X{0,3})"
                        + "(IX|IV|V?I{0,3})$";

                return value.matches(regex);
        }

        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) {
                        return 0;
                }

                int total = 0;
                int i = 0;

                while (i < numeral.length()) {
                        char current = numeral.charAt(i);
                        int currentVal = romanToIntMap.get(current);

                        if (i + 1 < numeral.length()) {
                                char next = numeral.charAt(i + 1);
                                int nextVal = romanToIntMap.get(next);

                                if (currentVal < nextVal) {
                                        total += (nextVal - currentVal);
                                        i += 2;
                                        continue;
                                }
                        }

                        total += currentVal;
                        i++;
                }

                return total;
        }


        public static String toRomanNumeral(int number) {
                if (number < 1 || number > 3999) {
                        return "";
                }

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < intValues.length; i++) {
                        while (number >= intValues[i]) {
                                sb.append(romanSymbols[i]);
                                number -= intValues[i];
                        }
                }

                return sb.toString();
        }
}
