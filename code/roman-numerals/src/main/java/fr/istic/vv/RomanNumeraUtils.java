package fr.istic.vv;

import java.util.regex.Pattern;

public class RomanNumeraUtils {

        public static boolean isValidRomanNumeral(String value) {
                return Pattern.matches(
                        "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$",
                        value
                );
        }

        public static int parseRomanNumeral(String numeral) {
                char currentChar;
                int result = 0;

                if (!isValidRomanNumeral(numeral)){
                        throw new IllegalArgumentException();
                }

                for (int i=0; i<numeral.length() ; i++){

                        currentChar = numeral.charAt(i);

                        //current character == I, check what char comes next
                        if(currentChar == 'I' && i<numeral.length()-1){

                                if(numeral.charAt(i+1) == 'V'){
                                        result += symbolValue('V') - symbolValue('I');
                                        i++;
                                }

                                else if(numeral.charAt(i+1) == 'X'){
                                        result += symbolValue('X') - symbolValue('I');
                                        i++;
                                }

                                else
                                        result += symbolValue('I');
                        }

                        //current character == X, check what char comes next
                        else if(currentChar == 'X' && i<numeral.length()-1){

                                if(numeral.charAt(i+1) == 'L'){
                                        result += symbolValue('L') - symbolValue('X');
                                        i++;
                                }

                                else if(numeral.charAt(i+1) == 'C'){
                                        result += symbolValue('C') - symbolValue('X');
                                        i++;
                                }

                                else
                                        result += symbolValue('X');
                        }

                        //current character == C, check what char comes next
                        else if(currentChar == 'C' && i<numeral.length()-1){
                                if(numeral.charAt(i+1) == 'D'){
                                        result += symbolValue('D') - symbolValue('C');
                                        i++;
                                }
                                else if(numeral.charAt(i+1) == 'M'){
                                        result += symbolValue('M') - symbolValue('C');
                                        i++;
                                }
                                else
                                        result += symbolValue('C');
                        }

                        else
                                result += symbolValue(currentChar);
                }
                return result;
        }

        public static int symbolValue(char s){
                // can't use switch to compile
                if (s == 'I') return 1;
                else if (s == 'V') return 5;
                else if (s == 'X') return 10;
                else if (s == 'L') return 50;
                else if (s == 'C') return 100;
                else if (s == 'D') return 500;
                else if (s == 'M') return 1000;
                else return 0;
        }

        public static String toRomanNumeral(int number) {
                // Following the rules to form a roman numeral, the biggest seams to be 3999 (MMMCMXCIX)
                if (number <= 0 || number > 3999) {
                        throw new IllegalArgumentException();
                }

                String[] thousands = {"", "M", "MM", "MMM"};
                String[] hundreds = {"", "C", "CC", "CCC", "CD", "D",
                        "DC", "DCC", "DCCC", "CM"};
                String[] tens = {"", "X", "XX", "XXX", "XL", "L",
                        "LX", "LXX", "LXXX", "XC"};
                String[] ones = {"", "I", "II", "III", "IV", "V",
                        "VI", "VII", "VIII", "IX"};

                return thousands[number / 1000] + hundreds[(number % 1000) / 100]
                        + tens[(number % 100) / 10] + ones[number % 10];
        }

}
