package fr.istic.vv;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeraUtils {

        public static boolean isValidRomanNumeral(String value) {
                if (value == null || value.isEmpty()) {
                        return false;
                }
                String regex = "^M{0,3}" +
                        "(CM|CD|D?C{0,3})" +
                        "(XC|XL|L?X{0,3})" +
                        "(IX|IV|V?I{0,3})$";
                return value.matches(regex);
        }

        public static int parseRomanNumeral(String numeral) {
                if (numeral == null || numeral.isEmpty()) {
                        throw new IllegalArgumentException("Numeral cannot be null or empty");
                }

                int count = 0;
                int lastValue = 0;

                Map<Character, Integer> romanInteger = new HashMap<>();
                romanInteger.put('I', 1);
                romanInteger.put('V', 5);
                romanInteger.put('X', 10);
                romanInteger.put('L', 50);
                romanInteger.put('C', 100);
                romanInteger.put('D', 500);
                romanInteger.put('M', 1000);

                for (int i = numeral.length() - 1; i >= 0; i--) {
                        char chiffreChar = numeral.charAt(i);

                        Integer currentValue = romanInteger.get(chiffreChar);
                        if (currentValue == null) {
                                throw new IllegalArgumentException("Invalid Roman numeral character: " + chiffreChar);
                        }

                        if (currentValue < lastValue) {
                                count -= currentValue;
                        } else {
                                count += currentValue;
                        }
                        lastValue = currentValue;
                }

                return count;
        }

        public static String toRomanNumeral(int number) {
                if (number <= 0 ) {
                        throw new IllegalArgumentException();
                }

                Map<String, Integer> romanInteger = new LinkedHashMap<>();
                romanInteger.put("M", 1000);
                romanInteger.put("CM", 900);
                romanInteger.put("D", 500);
                romanInteger.put("CD", 400);
                romanInteger.put("C", 100);
                romanInteger.put("XC", 90);
                romanInteger.put("L", 50);
                romanInteger.put("XL", 40);
                romanInteger.put("X", 10);
                romanInteger.put("IX", 9);
                romanInteger.put("V", 5);
                romanInteger.put("IV", 4);
                romanInteger.put("I", 1);

                StringBuilder roman = new StringBuilder();

                for(Map.Entry<String, Integer> entry : romanInteger.entrySet()){
                        while (number >= entry.getValue()) {
                                roman.append(entry.getKey());

                                number -= entry.getValue();
                        }
                }

                return roman.toString();
        }
    
}
