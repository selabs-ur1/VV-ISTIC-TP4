package fr.istic.vv;


import java.util.Map;

public class RomanNumeralUtils {
        private static final Map<Character, Integer> romanNumeralMap = Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000
        );

        private static final String romanNumeralRegex = "M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})";
    
        public static boolean isValidRomanNumeral(String value) {
                // check parameter
                return value != null && value.matches(romanNumeralRegex);
        }
    
        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) {
                        return 0;
                }

                int n = numeral.length();
                int result = romanNumeralMap.get(numeral.charAt(n - 1));
                for (int i = n - 2; i >= 0; i--) {
                        if (romanNumeralMap.get(numeral.charAt(i)) >= romanNumeralMap.get(numeral.charAt(i + 1))) {
                                result += romanNumeralMap.get(numeral.charAt(i));
                        } else {
                                result -= romanNumeralMap.get(numeral.charAt(i));
                        }
                }
                return result;
        }
    
        public static String toRomanNumeral(int number) {
                if (number < 1 || number > 3999) {
                        return "";
                }

                String[] thousands = {"", "M", "MM", "MMM"};
                String[] hundreds = {"", "C", "CC", "CCC", "CD", "D",
                        "DC", "DCC", "DCCC", "CM"};
                String[] tens = {"", "X", "XX", "XXX", "XL", "L",
                        "LX", "LXX", "LXXX", "XC"};
                String[] ones = {"", "I", "II", "III", "IV", "V",
                        "VI", "VII", "VIII", "IX"};

                return thousands[number / 1000] +
                        hundreds[(number % 1000) / 100] +
                        tens[(number % 100) / 10] +
                        ones[number % 10];
        }

        public static void main(String[] args) {
                System.out.println(isValidRomanNumeral("IL"));
                System.out.println(toRomanNumeral(49));
                System.out.println(parseRomanNumeral("IL"));
        }
}
