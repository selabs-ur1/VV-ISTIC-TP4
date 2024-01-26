package fr.istic.vv;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class RomanNumeralUtils {

        private static final HashMap<String, Integer> romanValues = new LinkedHashMap<>();

        static {
                romanValues.put("M", 1000);
                romanValues.put("CM", 900);
                romanValues.put("D", 500);
                romanValues.put("CD", 400);
                romanValues.put("C", 100);
                romanValues.put("XC", 90);
                romanValues.put("L", 50);
                romanValues.put("XL", 40);
                romanValues.put("X", 10);
                romanValues.put("IX", 9);
                romanValues.put("V", 5);
                romanValues.put("IV", 4);
                romanValues.put("I", 1);
        }

        public static boolean isValidRomanNumeral(String value) {
            return value.matches("^M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
        }

        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) {
                        throw new IllegalArgumentException("Invalid Roman numeral");
                }
                if (numeral.isEmpty()){
                        throw new IllegalArgumentException("Empty Roman numeral");
                }

                int result = 0;
                int prevValue = 0;

                for (int i = numeral.length() - 1; i >= 0; i--) {
                        int currentValue = romanValues.get(numeral.substring(i, i+1));

                        if (currentValue < prevValue) {
                                result -= currentValue;
                        } else {
                                result += currentValue;
                                prevValue = currentValue;
                        }
                }

                return result;
        }

        public static String toRomanNumeral(int number) {
                if (number <= 0 || number >= 4000) {
                        throw new IllegalArgumentException("Number out of range for Roman numerals");
                }

                StringBuilder romanNumeral = new StringBuilder();

                for (String romanSymbol : romanValues.keySet()) {
                        int value = romanValues.get(romanSymbol);

                        while (number >= value) {
                                romanNumeral.append(romanSymbol);
                                number -= value;
                        }
                }

                return romanNumeral.toString();


        }

        public static void main(String[] args) {
                for (String romanSymbol : romanValues.keySet()) {
                        System.out.println(romanSymbol);
                }
        }
}
