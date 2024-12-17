package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {

    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min=Integer.MIN_VALUE +1) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean validRomanNumeralsPass(@ForAll("validRomanNumerals") String romanNumeral) {
        return RomanNumeralUtils.isValidRomanNumeral(romanNumeral);
    }

    @Property
    boolean invalidRomanNumeralsFail(@ForAll("invalidRomanNumerals") String romanNumeral) {
        return !RomanNumeralUtils.isValidRomanNumeral(romanNumeral);
    }

    @Property
    boolean parseAndConvertBack(@ForAll("validRomanNumerals") String romanNumeral) {
        int parsed = RomanNumeralUtils.parseRomanNumeral(romanNumeral);
        String convertedBack = RomanNumeralUtils.toRomanNumeral(parsed);
        return romanNumeral.equals(convertedBack);
    }

    @Property
    boolean generatedRomanNumeralsValid(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String romanNumeral = RomanNumeralUtils.toRomanNumeral(number);
        return RomanNumeralUtils.isValidRomanNumeral(romanNumeral);
    }

    @Property
    boolean parseInvalidRomanNumeralsThrow(@ForAll("invalidRomanNumerals") String romanNumeral) {
        try {
            RomanNumeralUtils.parseRomanNumeral(romanNumeral);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of(
                "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XX", "XL", "L", "XC",
                "C", "CD", "D", "CM", "M", "MM", "MMM", "MMMCMXCIX"
        );
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.of(
                "IIII", "VV", "LL", "DD", "MMMM", "IC", "IL", "XD", "IM", "IVI", "", "ABCD", "1234"
        );
    }
}
