package fr.istic.vv;

import javax.print.attribute.HashAttributeSet;
import java.util.HashMap;
import java.util.Stack;

public class RomanNumeraUtils {
        String regex1  = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        String regex2 = "^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$";
        public static boolean isValidRomanNumeral(String value) {
                if (value == null || value.isEmpty()) return  false;
                String symbols = "MDCLXVI"; // dans l'ordre decroissant
                int[] values = {1000,500,100,50,10,5,1};

                //tous les symbols sont romain
                for(char c : value.toCharArray()){
                        if(symbols.indexOf(c) < 0) return false;
                }
                // pas de repetition pour D,L et V
                for (char c : "DLV".toCharArray()) {
                        if (value.indexOf(c) != value.lastIndexOf(c)) {
                                return false;
                        }
                }

                //trois repetition au max pour M,C,X,I
                for(char c : "MCXI".toCharArray()){
                      String quatreRep = ""+c+c+c+c;
                      if(value.contains(quatreRep)) return false;
                }


                // ordre soustraction
                boolean result = true;
                Stack<Character> usedChar = new Stack<>();
                Stack<Character> usedCharAdd = new Stack<>();


                for (int i = 0; i < value.length() - 1; i++) {
                        char current = value.charAt(i);
                        char next = value.charAt(i+1);

                        if(!usedChar.isEmpty()){
                                if(symbols.indexOf(next) <= symbols.indexOf(usedChar.pop())){
                                        return false;
                                }
                        }
                        if(!usedCharAdd.isEmpty()){
                                if(symbols.indexOf(next) < symbols.indexOf(usedCharAdd.pop())){
                                        return false;
                                }
                        }
                        // le plus grand symbol doit etre à droite pour la soustraction
                         if (symbols.indexOf(next) < symbols.indexOf(current)) {
                                /**
                                 * I --> V || X
                                 * X --> L || C
                                 * C --> D || M
                                 * **/
                                if( !(
                                        (current == 'I' && (next == 'V' || next == 'X')) ||
                                        (current == 'X' && (next == 'L' || next == 'C')) ||
                                        (current == 'C' && (next == 'D' || next == 'M'))
                                )){
                                        return false;
                                }

                                boolean consecutiveSubtraction = (i > 0 && value.charAt(i - 1) == current);





                                if( !consecutiveSubtraction){

                                        usedChar.push(current);

                                }

                                if(consecutiveSubtraction){
                                        return  false;
                                }

                        }
                        else {
                                usedCharAdd.push(current);
                        }

                }



                        return true;
        }

        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) {
                        throw new IllegalArgumentException(numeral + " : doit être un nombre romain valide");
                }

                String symbols = "IVXLCDM";
                int[] values = {1, 5, 10, 50, 100, 500, 1000};

                int result = 0;
                int prevValue = 0;

                for (int i = numeral.length() - 1; i >= 0; i--) {
                        char currentSymbol = numeral.charAt(i);
                        int currentIndex = symbols.indexOf(currentSymbol);
                        int currentValue = values[currentIndex];

                        if (currentValue < prevValue) {
                                result -= currentValue;
                        } else {
                                result += currentValue;
                        }

                        prevValue = currentValue;
                }

                return result;
        }


        public static String toRomanNumeral(int number) {
                if (number < 1 || number > 3999) {
                        throw new IllegalArgumentException("Number must be between 1 and 3999 (inclusive)");
                }

                String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
                int[] symbolValues = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

                StringBuilder romanNumeral = new StringBuilder();

                for (int i = romanSymbols.length - 1; i >= 0; i--) {
                        while (number >= symbolValues[i]) {
                                romanNumeral.append(romanSymbols[i]);
                                number -= symbolValues[i];
                        }
                }

                return romanNumeral.toString();
        }

        public static void main(String[] args) {
                System.out.println(isValidRomanNumeral("MMMM"));
                System.out.println(("MMMM".matches("^(?=[MDCLXVI])M{0,3}(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$")));

        }


}
