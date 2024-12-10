package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {
    @Property
    boolean validRomanNumeralsAreParsedCorrectly(@ForAll("validRomanNumerals") String roman) {
        int parsedValue = RomanNumeraUtils.parseRomanNumeral(roman);
        String convertedBack = RomanNumeraUtils.toRomanNumeral(parsedValue);
        return roman.equals(convertedBack);
    }

    @Property
    boolean invalidRomanNumeralsAreRejected(@ForAll("invalidRomanNumerals") String roman) {
        return !RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Property
    boolean validNumbersAreConvertedToRomanNumerals(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String roman = RomanNumeraUtils.toRomanNumeral(number);
        return RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XX", "XXX", "XL", "L", "LX",
                "LXX", "LXXX", "XC", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM", "M", "MM", "MMM",
                "MCMXCIX", "MMXXI");
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.of("IIII", "VV", "XXXX", "LL", "CCCC", "DD", "MMMM", "IC", "IL", "IM", "VX", "XD", "XM",
                "ID", "VD", "LC");
    }
}
