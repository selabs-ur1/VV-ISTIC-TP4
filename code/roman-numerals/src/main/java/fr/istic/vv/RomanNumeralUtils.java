package fr.istic.vv;

import java.util.Map;

public class RomanNumeralUtils {
        private static final Map<Character, Integer> ROMAN_TO_INTEGER = Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000
        );
    
        public static boolean isValidRomanNumeral(String value) { return false; }
    
        public static int parseRomanNumeral(String numeral) {
                System.out.println("================="+numeral);
                int result = ROMAN_TO_INTEGER.get(numeral.charAt(numeral.length() - 1));
                System.out.println(result);
                for(int i = numeral.length()-2; i >= 0; i--){
                        if(ROMAN_TO_INTEGER.get(numeral.charAt(i)) < ROMAN_TO_INTEGER.get(numeral.charAt(i+1))){
                                result -= ROMAN_TO_INTEGER.get(numeral.charAt(i));
                        }
                        else{
                                result += ROMAN_TO_INTEGER.get(numeral.charAt(i));
                        }
                        System.out.println(i+"     "+result);
                }
                return result;
        }
    
        public static String toRomanNumeral(int number) { return ""; }

        private static int getValue(char c){
                switch(c){
                        case 'I': return 1;
                        case 'V': return 5;
                        case 'X': return 10;
                        case 'L': return 50;
                        case 'C': return 100;
                        case 'D': return 500;
                        case 'M': return 1000;
                        default: return 0;
                }
        }

        public static void main(String[] args) {
                parseRomanNumeral("CMXIV");
        }
    
}
