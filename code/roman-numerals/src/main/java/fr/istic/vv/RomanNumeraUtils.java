package fr.istic.vv;

import java.util.List;

public class RomanNumeraUtils {

        static List<String> roman = List.of("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I");
        static List<Integer> values = List.of(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);

        public static void main(String[] args) {
                System.out.println(parseRomanNumeral("IV"));
                System.out.println(toRomanNumeral(4));
        }
    
        public static boolean isValidRomanNumeral(String value) {
                if(value == null || value.isEmpty()) return false;
                if(!value.matches("^[MDCLXVI]+$")) return false;

                if(value.contains("MMMM") || value.contains("CCCC") || value.contains("XXXX") || value.contains("IIII")) return false;
                if(value.contains("DD") || value.contains("LL") || value.contains("VV")) return false;

                return true;
        }
    
        public static int parseRomanNumeral(String numeral) {
                int result = 0;

                for (int i = 0; i < numeral.length(); i++) {
                        int current = values.get(roman.indexOf(""+ numeral.charAt(i)));
                        if(i < numeral.length() - 1) {
                                int next = values.get(roman.indexOf(""+ numeral.charAt(i + 1)));
                                if(current >= next) {
                                        result += current;
                                } else {
                                        result += (next - current);
                                        i++;
                                }
                        } else {
                                result += current;
                        }
                }
                return result;
        }
    
        public static String toRomanNumeral(int number) {
                String numeral = "";
                for (Integer value : values) {
                        String numeralChar = roman.get(values.indexOf(value));

                        while (number >= value) {
                                numeral += numeralChar;
                                number -= value;
                        }
                }
                return numeral;
        }
    
}
