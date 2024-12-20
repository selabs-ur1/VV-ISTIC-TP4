package fr.istic.vv;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeraUtils {
        private static final Map<Character, Integer> Roman_To_Int = new HashMap<>();
        private static final int Max_Roman = 3999;

        static{
                Roman_To_Int.put('I', 1);
                Roman_To_Int.put('V', 5);
                Roman_To_Int.put('X', 10);
                Roman_To_Int.put('L', 50);
                Roman_To_Int.put('C', 100);
                Roman_To_Int.put('D', 500);
                Roman_To_Int.put('M', 1000);
        }
        public static boolean isValidRomanNumeral(String value) {
                if (value == null || value.isEmpty()){
                        return false;
                }
                String romanThousand = "M{0,3}";//jusqu'a 3000
                String romanHundred = "(CM|CD|D?C{0,3})?";//100 à 900
                String romanTen = "(XC|XL|L?X{0,3})?";//10 à 90
                String romanUnit = "(IX|IV|V?I{0,3})?"; //1 à 9
                String roman = "^" + romanThousand + romanHundred + romanTen + romanUnit + "$";
                return  value.matches(roman);
        }
    
        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)){throw new IllegalArgumentException("Invalid Roman numeral");}
                int result = 0;
                int previousValue = 0;

                for (int i = numeral.length()-1; i >= 0; i--) {
                        char currentChar = numeral.charAt(i);
                        int currentValue = Roman_To_Int.get(currentChar);

                        if(currentValue < previousValue){
                                result -= currentValue;
                        }else{
                                result += currentValue;
                        }
                        previousValue = currentValue;

                }
                return result;
        }

        public static String toRomanNumeral(int number) { //19
                if(number <=0||number > Max_Roman){throw new IllegalArgumentException("number out of range");}
                StringBuilder roman = new StringBuilder();

                int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
                String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

                for (int i = 0; i < values.length; i++) {
                        while (number >= values[i]) {
                                roman.append(symbols[i]);
                                number -= values[i];
                        }
                }

                return roman.toString();
        }

    
}
