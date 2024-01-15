package fr.istic.vv;

import java.security.InvalidParameterException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RomanNumeraUtils {

        static Map<String,Integer> valueMap = new LinkedHashMap<>() {{
                put("M", 1000);
                put("CM",900);
                put("D", 500);
                put("CD",400);
                put("C", 100);
                put("XC",90);
                put("L", 50);
                put("XL",40);
                put("X", 10);
                put("IX",9);
                put("V", 5);
                put("IV",4);
                put("I", 1);
        }};

        public static boolean isValidRomanNumeral(String value) {
                //Regex chiffre romain de 0 à 3999
                String regex = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
                return Pattern.matches(regex, value);
        }
    
        public static int parseRomanNumeral(String numeral) throws InvalidParameterException{
                if(!isValidRomanNumeral(numeral)){
                       throw new InvalidParameterException("String is not a valid roman numeral");
                }
                int value = 0;

                for(int i=0; i<numeral.length(); i++){
                        int v1 = valueMap.get(String.valueOf(numeral.charAt(i)));

                        if(i+1<numeral.length()){
                                int v2 = valueMap.get(String.valueOf(numeral.charAt(i+1)));
                                if(v2>v1){
                                        v1 = v2 - v1;
                                        i++;
                                }
                        }
                        value += v1;
                }
                return value;
        }
    
        public static String toRomanNumeral(int number) throws InvalidParameterException {
                if(number<0 || number > 3999){
                        throw new InvalidParameterException("Incorrect value");
                }

                StringBuilder numeral = new StringBuilder();
                int tmp = number;

                for(Map.Entry<String,Integer> entry : valueMap.entrySet()){
                        int nb = tmp/entry.getValue();
                        tmp -= nb*entry.getValue();
                        numeral.append(entry.getKey().repeat(nb));
                }
                return numeral.toString();
        }
    
}
