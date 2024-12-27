package fr.istic.vv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumeralUtils {

        public static boolean isValidRomanNumeral(String value) {
                if (value == null) return false;
                if (value.isEmpty()) return false;

                return value.matches(
                        "^M{0,3}" +
                        "(CM|CD|D?C{0,3})" +
                        "(XC|XL|L?X{0,3})" +
                        "(IX|IV|V?I{0,3})$"
                );
        }

        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) return -1;

                int sum = 0;

                int currentValue = 0;
                int previousValue = 0;

                for (int i = numeral.length() - 1; i >= 0; i--) {

                        switch (numeral.charAt(i)) {
                                case 'I':
                                        currentValue = 1;
                                        break;
                                case 'V':
                                        currentValue = 5;
                                        break;
                                case 'X':
                                        currentValue = 10;
                                        break;
                                case 'L':
                                        currentValue = 50;
                                        break;
                                case 'C':
                                        currentValue = 100;
                                        break;
                                case 'D':
                                        currentValue = 500;
                                        break;
                                case 'M':
                                        currentValue = 1000;
                                        break;
                                default:
                                        break;
                        }

                        if (currentValue < previousValue) {
                                sum -= currentValue;
                        } else {
                                sum += currentValue;
                        }

                        previousValue = currentValue;
                }

                return sum;
        }

        public static String toRomanNumeral(int number) {
                if (number <= 0) return "";
                
                StringBuilder builder = new StringBuilder();

                int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
                String[] romanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

                for (int i = 0; i < values.length; i++) {
                        while (number >= values[i]) {
                                builder.append(romanNumerals[i]);
                                number -= values[i];
                        }
                }

                return builder.toString();
        }
}
