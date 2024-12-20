package fr.istic.vv;

import java.util.Map;

public class RomanNumeralUtils {
    
        public static boolean isValidRomanNumeral(String value) {
                return value.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
        }

        private static final Map<Character, Integer> ROMAN_TO_INT = Map.of('M', 1000,
                'D', 500, 'C', 100, 'L', 50, 'X', 10, 'V', 5, 'I', 1);

        public static int parseRomanNumeral(String numeral) {//we suppose the input always valid
                int size = numeral.length();
                int res = 0;
                int last = 1000;
                for(int i = 0; i < size; i++){
                        char c = numeral.charAt(i);
                        int value = ROMAN_TO_INT.get(c);
                        if(last >= value){
                                res += value;
                        } else res += value - 2*last;
                        last = value;
                }
                return res;
        }

        public static String toRomanNumeral(int number) {
                if(number >= 4000) return "MMMDCCCLXXXVIII";
                else if (number <= 0) return "";
                String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
                int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < values.length; i++) {
                        while(number >= values[i]) {
                                result.append(romanSymbols[i]);
                                number -= values[i];
                        }
                }
                return result.toString();
        }
    
}
