package fr.istic.vv;

import java.util.regex.*;

public class RomanNumeraUtils {

        //Adding transitional SYMBOLS like VI to avoid algorithm complexity
        protected static String[] romanNumerals = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        protected static int[] integerValues = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };


        /**
         * Use regex to valid Roman numerals
         * Thanks ! https://regex101.com/
         * @param value to valid
         * @return true if the roman numeral is valid
         */
        public static boolean isValidRomanNumeral(String value) {
                String pattern = "^[IVXLCDM]+$";
                return Pattern.matches(pattern, value);
        }

        /**
         * Parse roman string to numeral value
         * @param numeral the roman number
         * @return an int corresponding to the roman numeral
         * @throws IllegalArgumentException exception if Roman numeral is not valid
         */
        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) {
                        throw new IllegalArgumentException("Invalid Roman numeral: " + numeral);
                }

                int result = 0;
                int index = 0;
                for (int i = 0; i < romanNumerals.length; i++) {
                        while (numeral.startsWith(romanNumerals[i], index)) {
                                result += integerValues[i];
                                index += romanNumerals[i].length();
                        }
                }
                return result;
        }

        /**
         * Parse a number to a Roman numeral String
         * @param number to parse
         * @return the String corresponding to roman number
         * @throws IllegalArgumentException if number is < 0 or > 3999
         */
        public static String toRomanNumeral(int number) {
                if (number <= 0 || number > 3999) {
                        throw new IllegalArgumentException("Number out of range for Roman numerals: " + number);
                }

                StringBuilder result = new StringBuilder();

                for (int i = 0; i < romanNumerals.length; i++) {
                        while (number >= integerValues[i]) {
                                result.append(romanNumerals[i]);
                                number -= integerValues[i];
                        }
                }

                return result.toString();
        }
}


