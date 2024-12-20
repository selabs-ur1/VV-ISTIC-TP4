package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min=Integer.MIN_VALUE +1) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean validRomanNumeralsAreRecognized(@ForAll("validRomanNumerals") String roman) {
        return RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Property
    boolean invalidRomanNumeralsAreRejected(@ForAll("invalidRomanNumerals") String roman) {
        return !RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Property
    boolean validRomanNumeralsParseCorrectly(@ForAll("validRomanNumeralPairs") RomanNumeralPair pair) {
        String roman = pair.roman;
        int expectedValue = pair.value;
        return RomanNumeraUtils.parseRomanNumeral(roman) == expectedValue;
    }

    @Property
    boolean validNumbersConvertToRomanNumerals(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String roman = RomanNumeraUtils.toRomanNumeral(number);
        return RomanNumeraUtils.parseRomanNumeral(roman) == number;
    }

    @Property
    boolean invalidNumbersThrowException(@ForAll("outOfRangeNumbers") int number) {
        try {
            RomanNumeraUtils.toRomanNumeral(number);
            return false;
        } catch (IllegalArgumentException e) {
            return e.getMessage().contains("out of range");
        }
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of(
                "I", "IV", "VIII", "IX", "XIV", "XVI", "XIX", "XCIX", "CV", "MI", "MMCCLXXXIX", "MMMCMXCIX"
        );
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.of(
                "IIII", "VV", "XXXX", "LL", "CCCC", "DD", "MMMM", "IC", "IL", "VX", "XD", "", "A", "123"
        );
    }

    @Provide
    Arbitrary<RomanNumeralPair> validRomanNumeralPairs() {
        return Arbitraries.of(
                new RomanNumeralPair("I", 1),
                new RomanNumeralPair("IV", 4),
                new RomanNumeralPair("VIII", 8),
                new RomanNumeralPair("IX", 9),
                new RomanNumeralPair("XIV", 14),
                new RomanNumeralPair("XVI", 16),
                new RomanNumeralPair("XIX", 19),
                new RomanNumeralPair("XCIX", 99),
                new RomanNumeralPair("CV", 105),
                new RomanNumeralPair("MI", 1001),
                new RomanNumeralPair("MMCCLXXXIX", 2289),
                new RomanNumeralPair("MMMCMXCIX", 3999)
        );
    }

    @Provide
    Arbitrary<Integer> outOfRangeNumbers() {
        return Arbitraries.integers().filter(n -> n <= 0 || n > 3999);
    }

    static class RomanNumeralPair {
        final String roman;
        final int value;

        RomanNumeralPair(String roman, int value) {
            this.roman = roman;
            this.value = value;
        }
    }
}