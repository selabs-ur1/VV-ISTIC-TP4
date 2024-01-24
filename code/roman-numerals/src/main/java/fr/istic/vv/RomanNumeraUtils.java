package fr.istic.vv;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

import java.io.IOException;

public class RomanNumeraUtils {
    
    public static boolean isValidRomanNumeral(String value) {

        // contains only valid symbols
        List<Character> symbols = Arrays.asList('I', 'V', 'X', 'L', 'C', 'D', 'M');
        for (Character c : value.toCharArray()) {
            if(!symbols.contains(c)){
                return false;
            }
        }

        // repeat constraint
        List<String> unvalidSequences = Arrays.asList("IIII", "VV", "XXXX", "LL", "CCCC", "DD", "MMMM");
        for (String sequence : unvalidSequences) {
            if(value.contains(sequence)){
                return false;
            }
            
        }

        return true;
    }

    public static int parseRomanNumeral(String numeral) throws IOException {

        if(!isValidRomanNumeral(numeral)){
            throw new IOException("roman numeral is invalid");
        }

        int number = 0;

        List<String> substractSequences = Arrays.asList("CM", "CD", "XC", "XL", "IX", "IV");
        Map<String, Integer> converter = Map.ofEntries(
            entry("M", 1000),
            entry("CM", 900),
            entry("D", 500),
            entry("CD", 400),
            entry("C", 100),
            entry("XC", 90),
            entry("L", 50),
            entry("XL", 40),
            entry("X", 10),
            entry("IX", 9),
            entry("V", 5),
            entry("IV", 4),
            entry("I", 1)
        );

        int i = 0;
        while(i < numeral.length()) {
            if(i+1 < numeral.length()) {
                String sequence = "" + numeral.charAt(i) + numeral.charAt(i+1);
                if(substractSequences.contains(sequence)){
                    number += converter.get(sequence);
                    i+=2;
                    continue;
                }
            }

            number += converter.get(numeral.charAt(i)+"");
            i+=1;
        }

        return number; 
    }

    public static String toRomanNumeral(int number) throws IOException {

        if(number <=  0 || number > 3999) {
            throw new IOException("roman numeral is invalid");
        }

        String romanNumeral = "";

        Map<Integer, String> converter = Map.ofEntries(
            entry(1000, "M"),
            entry(900, "CM"),
            entry(500, "D"),
            entry(400, "CD"),
            entry(100, "C"),
            entry(90, "XC"),
            entry(50, "L"),
            entry(40, "XL"),
            entry(10, "X"),
            entry(9, "IX"),
            entry(5, "V"),
            entry(4, "IV"),
            entry(1, "I")
        );

        List<Integer> values = Arrays.asList(1000, 900, 500, 400, 100, 90, 50, 40 ,10, 9, 5, 4, 1);

        for(int value : values){
            while(number >= value) {
                romanNumeral = romanNumeral + converter.get(value);
                number -= value;
            }
        }
        
        return romanNumeral; 
        
    }

}
