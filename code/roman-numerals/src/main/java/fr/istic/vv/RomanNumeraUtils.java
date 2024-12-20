package fr.istic.vv;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumeraUtils {

        public static boolean isValidRomanNumeral(String value) {

                Pattern pattern = Pattern.compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$",
                                Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(value);
                return matcher.find();
        }

        public static int parseRomanNumeral(String numeral) {
                if (isValidRomanNumeral(numeral)) {
                        int res = 0;
                        for (int i = 0; i < numeral.length(); i++) {
                                switch (numeral.charAt(i)) {
                                        case 'I': {
                                                if (i == numeral.length() - 1) {
                                                        if (numeral.charAt(i + 1) == 'V') {
                                                                res += 4;
                                                                i++;
                                                                break;

                                                        } else if (numeral.charAt(i + 1) == 'X') {
                                                                res += 9;
                                                                i++;
                                                                break;
                                                        }
                                                }
                                                res += 1;
                                                break;
                                        }
                                        case 'V':
                                                res += 5;
                                                break;
                                        case 'X': {
                                                if (i == numeral.length() - 1) {
                                                        if (numeral.charAt(i + 1) == 'C') {
                                                                res += 90;
                                                                i++;
                                                                break;
                                                        }
                                                }
                                                res += 10;
                                                break;
                                        }
                                        case 'L':
                                                res += 50;
                                                break;
                                        case 'C':
                                                res += 100;
                                                break;
                                        case 'D':
                                                res += 500;
                                                break;
                                        case 'M':
                                                res += 1000;
                                                break;
                                        default:
                                                break;
                                }
                        }
                        return res;
                } else {
                        return -1;
                }
        }

        public static String toRomanNumeral(int number) {
                if (number < 1 || number > 3999) {
                        return "Invalid Roman Number Value";
                }
                String romanValue = "";
                int n = number;
                while (n > 0) {
                        if (n >= 1000) {
                                romanValue += "M";
                                n -= 1000;
                        } else if (n >= 900) {
                                romanValue += "CM";
                                n -= 900;
                        } else if (n >= 500) {
                                romanValue += "D";
                                n -= 500;
                        } else if (n >= 400) {
                                romanValue += "CD";
                                n -= 400;
                        } else if (n >= 100) {
                                romanValue += "C";
                                n -= 100;
                        } else if (n >= 90) {
                                romanValue += "XC";
                                n -= 90;
                        } else if (n >= 50) {
                                romanValue += "L";
                                n -= 50;
                        } else if (n >= 40) {
                                romanValue += "XL";
                                n -= 40;
                        } else if (n >= 10) {
                                romanValue += "X";
                                n -= 10;
                        } else if (n >= 9) {
                                romanValue += "IX";
                                n -= 9;
                        } else if (n >= 5) {
                                romanValue += "V";
                                n -= 5;
                        } else if (n >= 4) {
                                romanValue += "IV";
                                n -= 4;
                        } else if (n >= 1) {
                                romanValue += "I";
                                n -= 1;
                        }
                }
                return romanValue;
        }

}
