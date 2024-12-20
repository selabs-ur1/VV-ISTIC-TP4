package fr.istic.vv;

import net.jqwik.api.*;

import java.util.Set;

public class RomanNumeralTest {

    @Provide
    Arbitrary<Integer> validRomanNumbers() {
        return Arbitraries.integers().between(1, 3999);
    }

    @Provide
    Arbitrary<Integer> invalidRomanNumbers() {
        return Arbitraries.integers().filter(i -> i <= 0 || i > 3999);
    }

    @Provide
    Arbitrary<String> validRomanStrings() {
        Set<String> examples = Set.of("I", "IV", "IX", "X", "XL", "XC", "CD", "CM",
                "MMMDCCCLXXXVIII",
                "MCMXCIX",
                "MMXXIII");
        return Arbitraries.of(examples);
    }

    @Provide
    Arbitrary<String> invalidRomanStrings() {
        Set<String> examples = Set.of("IIII", "VV", "LL", "DD", "IC", "XM", "MCMXCIIV", "IXX",
                "ABC", "VX", "LC", "");
        return Arbitraries.of(examples);
    }

    @Property
    boolean roundTripConversion(@ForAll("validRomanNumbers") int n) {
        String roman = RomanNumeraUtils.toRomanNumeral(n);
        int parsed = RomanNumeraUtils.parseRomanNumeral(roman);
        return parsed == n;
    }

    @Property
    boolean validNumbersGenerateValidRomans(@ForAll("validRomanNumbers") int n) {
        String roman = RomanNumeraUtils.toRomanNumeral(n);
        return RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Property
    boolean invalidNumbersGenerateInvalidRomans(@ForAll("invalidRomanNumbers") int n) {
        String roman = RomanNumeraUtils.toRomanNumeral(n);
        return !RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Property
    boolean validRomansAreParsedCorrectly(@ForAll("validRomanStrings") String roman) {
        boolean valid = RomanNumeraUtils.isValidRomanNumeral(roman);
        int parsed = RomanNumeraUtils.parseRomanNumeral(roman);
        return valid && parsed > 0 && parsed <= 3999;
    }

    @Property
    boolean invalidRomansAreDetected(@ForAll("invalidRomanStrings") String roman) {
        return !RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Property
    boolean parseInvalidRomansReturnsZero(@ForAll("invalidRomanStrings") String roman) {
        int parsed = RomanNumeraUtils.parseRomanNumeral(roman);
        return parsed == 0;
    }
}