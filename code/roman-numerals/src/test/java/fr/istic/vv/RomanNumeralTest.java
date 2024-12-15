package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.Arrays;
import java.util.List;

public class RomanNumeralTest {
    static List<Character> romanNumeral = Arrays.asList('M','D','C', 'L', 'X', 'V','I');
    static List<Character> canBeRepeated = Arrays.asList('M', 'C', 'X', 'I');

    @Property
    boolean isARomanNumeral(@ForAll @IntRange(min = 65, max = 90) int c){
        char number = (char) c;
        if (romanNumeral.contains(number)){
            return RomanNumeralUtils.isValidRomanNumeral(String.valueOf(number));
        }
        else {
            return !RomanNumeralUtils.isValidRomanNumeral(String.valueOf(number));
        }
    }

    @Property
    boolean isAValidRomanNumeral(@ForAll("validRoman") String number){
        return RomanNumeralUtils.isValidRomanNumeral(String.valueOf(number));
    }

    @Property
    boolean isANonValidRomanNumeral(@ForAll("nonValidRoman") String number){
        return !RomanNumeralUtils.isValidRomanNumeral(String.valueOf(number));
    }

    @Property
    boolean isARomanValidRepeated3Times(@ForAll("romanNumeral") char r){
        String repeated = String.valueOf(r).repeat(3);
        System.out.println(repeated);
        if (canBeRepeated.contains(r)){
            System.out.println(repeated);
            return RomanNumeralUtils.isValidRomanNumeral(repeated);
        }
        else {
            return !RomanNumeralUtils.isValidRomanNumeral(repeated);
        }
    }

    @Property
    boolean isARomanValidRepeatedMore(@ForAll("romanNumeral") char r){
        String repeated = String.valueOf(r).repeat(4);
        return !RomanNumeralUtils.isValidRomanNumeral(repeated);
    }

    @Property
    boolean isARomanValidSubstracted(@ForAll("validSubstracted") String numeral){
        System.out.println(numeral);
        return RomanNumeralUtils.isValidRomanNumeral(numeral);
    }

    @Property
    boolean isARomanInvalidSubstracted(@ForAll("nonValidSubstracted") String numeral){
        return !RomanNumeralUtils.isValidRomanNumeral(numeral);
    }

    @Property
    boolean convertIntegersToRomanNumerals(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String roman = RomanNumeralUtils.toRomanNumeral(number);
        System.out.println(roman);
        System.out.println(RomanNumeralUtils.parseRomanNumeral(roman));
        return number == RomanNumeralUtils.parseRomanNumeral(roman);
    }

    @Property
    boolean toRomanNumeralProducesValidRomanNumerals(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String roman = RomanNumeralUtils.toRomanNumeral(number);
        return RomanNumeralUtils.isValidRomanNumeral(roman);
    }

    @Provide
    Arbitrary<Character> romanNumeral() {
        return Arbitraries.of(romanNumeral);
    }

    @Provide
    Arbitrary<String> validSubstracted(){
        return Arbitraries.of(
                "IV",
                "IX",
                "XC",
                "XL",
                "CD",
                "CM"
        );
    }

    @Provide
    Arbitrary<String> nonValidSubstracted(){
        return Arbitraries.of(
                "IC",
                        "IM",
                        "IIX"
        );
    }

    @Provide
    Arbitrary<String> nonValidRoman(){
        return Arbitraries.of("CLXXXIIIIII", "IIII");
    }

    @Provide
    Arbitrary<String> validRoman(){
        return Arbitraries.of(
                "I",
                "IV",
                "VIII",
                "IX",
                "XIV",
                "XVI",
                "XIX",
                "XCIX",
                "CV",
                "MI",
                "MMCCLXXXIX"
        );
    }


}
