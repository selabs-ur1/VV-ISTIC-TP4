package fr.istic.vv;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RomanNumeralUtils {

        private static final Map<Character,Integer> VALUES = new HashMap<>();
        static {
                VALUES.put('I',1);
                VALUES.put('V',5);
                VALUES.put('X',10);
                VALUES.put('L',50);
                VALUES.put('C',100);
                VALUES.put('D',500);
                VALUES.put('M',1000);
        }


        private static final Pattern ROMAN_PATTERN = Pattern.compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

        public static boolean isValidRomanNumeral(String value) {

                if(value == null || value.isEmpty()) return false;

                return ROMAN_PATTERN.matcher(value).matches();
        }

        public static int parseRomanNumeral(String numeral) {
                if(!isValidRomanNumeral(numeral)) {
                        //Si ce n'est pas valide on lance une exeption
                        throw new IllegalArgumentException("Invalid Roman Numeral: " + numeral);
                }
                int result = 0;
                //ON parcourt tous les caracteres
                for (int i = 0; i < numeral.length(); i++) {
                        int value = VALUES.get(numeral.charAt(i));

                        //on regarde si le suivant est plus grand pour voir si on rajoute ou on soustrait
                        if (i+1 < numeral.length()) {

                                int next = VALUES.get(numeral.charAt(i+1));
                                if (value >= next) {

                                        result += value;
                                } else {

                                        result += (next - value);
                                        i++;
                                }
                        } else {
                                result += value;
                        }
                }
                return result;
        }

        public static String toRomanNumeral(int number) {
                //ON verifie l'interval
                if (number < 1 || number > 3999) {

                        throw new IllegalArgumentException("Number out of range (1-3999): " + number);
                }
                

                String[] thousands = {"","M","MM","MMM"};
                String[] hundreds = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
                String[] tens = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
                String[] ones = {"","I","II","III","IV","V","VI","VII","VIII","IX"};


                return thousands[number/1000] +
                        hundreds[(number%1000)/100] +
                        tens[(number%100)/10] +
                        ones[number%10];
        }

}
