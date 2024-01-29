package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {

    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min = 0, max = Integer.MAX_VALUE) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean invalidRomanNumeralsShouldBeInvalid(@ForAll("invalidRomanNumerals") String invalidRomanNumeral) {
        return !RomanNumeralUtils.estChiffreRomainValide(invalidRomanNumeral);
    }

    @Property
    boolean parsingAndConvertingShouldBeConsistent(@ForAll("validRomanNumerals") String validRomanNumeral) {
        int parsedValue = RomanNumeralUtils.convertirChiffreRomainEnNombre(validRomanNumeral);
        String convertedNumeral = RomanNumeralUtils.convertirNombreEnChiffreRomain(parsedValue);
        return validRomanNumeral.equals(convertedNumeral);
    }

    @Property
    boolean toRomanNumeralShouldProduceValidNumerals(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String romanNumeral = RomanNumeralUtils.convertirNombreEnChiffreRomain(number);
        return RomanNumeralUtils.estChiffreRomainValide(romanNumeral);
    }

    @Property
    boolean parsingInvalidRomanNumeralShouldThrowException(@ForAll("invalidRomanNumerals") String invalidRomanNumeral) {
        // Expecting an exception to be thrown
        try {
            RomanNumeralUtils.convertirChiffreRomainEnNombre(invalidRomanNumeral);
            return false; // Fail if no exception is thrown
        } catch (IllegalArgumentException e) {
            return true; // Success if an exception is thrown
        }
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M");
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.strings().ofMinLength(1);
    }
}
