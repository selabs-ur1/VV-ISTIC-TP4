package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min=Integer.MIN_VALUE +1) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }


    @Property
    boolean convertAndParse_Reversibility(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String romanNumeral = RomanNumeraUtils.toRomanNumeral(number);
        
        int parsedNumber = RomanNumeraUtils.parseRomanNumeral(romanNumeral);
        
        return number == parsedNumber;
    }

    @Property
    boolean validateRomanNumerals(@ForAll("validRomanNumerals") String numeral) {
        return RomanNumeraUtils.isValidRomanNumeral(numeral);
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        // Provide a set of valid Roman numerals for testing
        return Arbitraries.of(
            "I", "IV", "V", "IX", "X", 
            "XIV", "XIX", "XXIV", 
            "CDXCIX", "MCMXCIX", 
            "MMMDCCCLXXXVIII"
        );
    }

    @Property
    boolean convertNumberToRomanNumeral(@ForAll @IntRange(min = 1, max = 3999) int number) {
        try {
            String romanNumeral = RomanNumeraUtils.toRomanNumeral(number);
            
            return RomanNumeraUtils.isValidRomanNumeral(romanNumeral);
        } catch (Exception e) {
            return false;
        }
    }
}
