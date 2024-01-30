package fr.istic.vv;
import java.util.HashMap;

public class RomanNumeralUtils {

        private static final HashMap<Character,Integer> romanValues = new HashMap<>();

        static {
                romanValues.put('I', 1);
                romanValues.put('V', 5);
                romanValues.put('X', 10);
                romanValues.put('L', 50);
                romanValues.put('C', 100);
                romanValues.put('D', 500);
                romanValues.put('M', 1000);
        }
    
        public static boolean isValidRomanNumeral(String value) {
                return value.matches("^[IVXLCDM]+$") && value.matches(".*[VLD]{2,}.*") && value.matches(".*[MCXI]{4,}.*");
        }
    
        public static int parseRomanNumeral(String numeral) {

                if (!isValidRomanNumeral(numeral)) {
                        return -1;
                }

                int result = 0;
                int preValue = 0;

                for (int i = numeral.length()-1; i >= 0; i--) {

                        int currentValue = romanValues.get(numeral.charAt(i));

                        if (currentValue < preValue) {
                                result -= currentValue;
                        } else {
                                result += currentValue;
                        }

                        preValue = currentValue;
                }
                return result;
        }
    
        public static String toRomanNumeral(int number) {

                if (number <= 0 || number >= 4000) {
                        return "";
                }

                StringBuilder result = new StringBuilder();

                for (char symbol : "IVXLCDM".toCharArray()) {

                        int value = romanValues.get(symbol);

                        while (number >= value) {
                                result.append(symbol);
                                number -= value;
                        }
                }
                return result.toString();
        }
}
