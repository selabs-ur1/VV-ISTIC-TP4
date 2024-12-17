package fr.istic.vv;

public class RomanNumeralUtils {
    
        public static boolean isValidRomanNumeral(String value) {
                if(value == null || value.isEmpty()) {
                        return false;
                }
                return value.matches("M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})");
        }

        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) {
                        throw new IllegalArgumentException("Invalid Roman numeral");
                }

                int result = 0;
                int prevValue = 0;

                for (int i = numeral.length() - 1; i >= 0; i--) {
                        char currentChar = numeral.charAt(i);
                        int currentValue = 0;

                        switch (currentChar) {
                                case 'I': currentValue = 1; break;
                                case 'V': currentValue = 5; break;
                                case 'X': currentValue = 10; break;
                                case 'L': currentValue = 50; break;
                                case 'C': currentValue = 100; break;
                                case 'D': currentValue = 500; break;
                                case 'M': currentValue = 1000; break;
                        }

                        if (currentValue < prevValue) {
                                result -= currentValue;
                        } else {
                                result += currentValue;
                        }

                        prevValue = currentValue;
                }

                return result;
        }


        public static String toRomanNumeral(int number) {
                if (number <= 0 || number >= 4000) {
                        throw new IllegalArgumentException("Not between 1 and 3999");
                }

                String[] thousands = {"", "M", "MM", "MMM"};
                String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
                String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
                String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

                int m = number / 1000;
                int c = (number % 1000) / 100;
                int x = (number % 100) / 10;
                int i = number % 10;

                return thousands[m] + hundreds[c] + tens[x] + ones[i];
        }


}
