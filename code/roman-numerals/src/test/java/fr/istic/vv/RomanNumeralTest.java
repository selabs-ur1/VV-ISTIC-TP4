package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.Set;

public class RomanNumeralTest {

    private static final String MAX_STRING = "MMMDCCCLXXXVIII";
    private static final int MAX_INT= 3999;

    private static final Set<Character> DIGITS = Set.of('M', 'D', 'C', 'L', 'X', 'V', 'I');

    private static boolean isRomanDigit(char c){
        return DIGITS.contains(c);
    }

    @Property
    public boolean cantBeRepeatedDLV(@ForAll @IntRange(max = MAX_INT) int input){
        String res = RomanNumeralUtils.toRomanNumeral(input);
        int d = 0, l = 0, v = 0;
        for(int i = 0; i < res.length(); i++){
            char c = res.charAt(i);
            switch(c){
                case 'D' -> d++;
                case 'L' -> l++;
                case 'V' -> v++;
            }
            if(d > 1 || l > 1 || v > 1)return false;
        }
        return true;
    }

    @Property
    public boolean cantBeRepeated3TimesMCXI(@ForAll @IntRange(max = MAX_INT) int input){
        String res = RomanNumeralUtils.toRomanNumeral(input);
        int m = 0, c = 0, x =0, i = 0;
        char last = 'T';
        for(int j = 0; j < res.length(); j++){
            char ch = res.charAt(j);
            if(last != ch) { m = 0; c = 0; x = 0; i = 0;}
            switch(ch){
                case 'M' -> m++;
                case 'C' -> c++;
                case 'X' -> x++;
                case 'I' -> i++;
            }//LXV
            if(m > 3 || c > 3 || x > 3 || i > 3)return false;
            last = ch;
        }
        return true;
    }

    @Property
    public boolean onlyRomanDigits(@ForAll @IntRange(max = MAX_INT) int input){
        String res = RomanNumeralUtils.toRomanNumeral(input);
        for(int i = 0; i < res.length(); i++){
            if(!isRomanDigit(res.charAt(i)))return false;
        }
        return true;
    }

    @Property
    public boolean romanNumberAlwaysUnderMax(@ForAll @IntRange(max = MAX_INT) int input){
        String res = RomanNumeralUtils.toRomanNumeral(input);
        int size = res.length();
        return size <= MAX_STRING.length();
    }

    //for integer generation

    public Arbitrary<String> romanThreeChars(char c){
        return Arbitraries.strings().ofMaxLength(3).withChars(c);
    }

    public Arbitrary<String> romanOneChar(char c){
        return Arbitraries.strings().ofMaxLength(1).withChars(c);
    }

    @Provide
    public Arbitrary<String> romanString(){
        return Combinators.combine(romanThreeChars('M'),
                romanOneChar('D'),
                romanThreeChars('C'),
                romanOneChar('L'),
                romanThreeChars('X'),
                romanOneChar('V'),
                romanThreeChars('I')).as(
                (m,d,c,l,x,v,i) -> m+d+c+l+x+v+i);
    }

    @Property
    public boolean numberAlwaysInBounds(@ForAll @From("romanString") String input){
        int res = RomanNumeralUtils.parseRomanNumeral(input);
        return res >= 0 && res <= 3999;
    }

    //for isRoman

    @Property
    public boolean oneRomanCharAlwaysRoman(@ForAll @From("romanString") String input){
        return RomanNumeralUtils.isValidRomanNumeral(input);
    }

    @Property
    public boolean fromStringToStringIdentity(@ForAll @From("romanString") String input){
        int integer = RomanNumeralUtils.parseRomanNumeral(input);
        String res = RomanNumeralUtils.toRomanNumeral(integer);
        return res.equals(input);
    }

    @Property
    public boolean fromIntegerToIntegerIdentity(@ForAll @IntRange(max = MAX_INT) int input){
        String romanString = RomanNumeralUtils.toRomanNumeral(input);
        int res = RomanNumeralUtils.parseRomanNumeral(romanString);
        return res == input;
    }

}
