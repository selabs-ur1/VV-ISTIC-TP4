package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {

    //Test valid Roman numeral
    @Property
    boolean allValidRomanNumeralsShouldBeValid(@ForAll("validRomanNumerals") String validRomanNumeral) {
        return RomanNumeraUtils.isValidRomanNumeral(validRomanNumeral);
    }

    //Test invalid roman numeral
    @Property
    boolean invalidRomanNumeralsShouldBeInvalid(@ForAll("invalidRomanNumerals") String invalidRomanNumeral) {
        return !RomanNumeraUtils.isValidRomanNumeral(invalidRomanNumeral);
    }

    //Property that test roman to int then int to roman input/output should be the same
    @Property
    boolean parsingAndConvertingShouldBeConsistent(@ForAll("validRomanNumerals") String validRomanNumeral) {
        int parsedValue = RomanNumeraUtils.parseRomanNumeral(validRomanNumeral);
        String convertedNumeral = RomanNumeraUtils.toRomanNumeral(parsedValue);
        return validRomanNumeral.equals(convertedNumeral);
    }

    /**
     * This method allow me to find a bug when the over number was not well parsed
     * Example 4 - > IIII instead of IV
     */
    @Property
    boolean toRomanNumeralShouldProduceValidNumerals(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String romanNumeral = RomanNumeraUtils.toRomanNumeral(number);
        return RomanNumeraUtils.isValidRomanNumeral(romanNumeral);
    }

    //Test the throw of an exception when invalid Roman numeral occur
    @Property
    boolean parsingInvalidRomanNumeralShouldThrowException(@ForAll("invalidRomanNumerals") String invalidRomanNumeral) {
        // Expecting an exception to be thrown
        try {
            RomanNumeraUtils.parseRomanNumeral(invalidRomanNumeral);
            return false; // Fail if no exception is thrown
        } catch (IllegalArgumentException e) {
            return true; // Success if an exception is thrown
        }
    }

    //Provide valid Roman numeral to the property
    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M");
    }

    //Provide invalid Roman numeral to the property
    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.strings().ofMinLength(1);
    }
}
