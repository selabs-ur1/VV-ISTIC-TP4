package fr.istic.vv;

import net.jqwik.api.*;

import java.util.Set;

public class RomanNumeralTest {

    @Provide
    Arbitrary<Integer> romanRangeIntegers() {
        return Arbitraries.integers().between(1, 3999);
    }

    @Provide
    Arbitrary<Integer> outsideRomanRangeIntegers() {
        return Arbitraries.integers().filter(i -> i <= 0 || i > 3999);
    }

    @Provide
    Arbitrary<String> someValidRomanNumerals() {
        Set<String> examples = Set.of("I", "IV", "IX", "X", "XL", "XC", "CD", "CM",
                "MMMDCCCLXXXVIII",
                "MCMXCIX",
                "MMXXIII");
        return Arbitraries.of(examples);
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        Set<String> examples = Set.of("IIII", "VV", "LL", "DD", "IC", "XM", "MCMXCIIV", "IXX",
                "ABC", "VX", "LC", "");
        return Arbitraries.of(examples);
    }

    @Property
    boolean roundTripProperty(@ForAll("romanRangeIntegers") int n) {
        String roman = RomanNumeraUtils.toRomanNumeral(n);
        int parsed = RomanNumeraUtils.parseRomanNumeral(roman);
        return parsed == n;
    }

    @Property
    boolean generatedRomanIsValid(@ForAll("romanRangeIntegers") int n) {
        String roman = RomanNumeraUtils.toRomanNumeral(n);
        return RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Property
    boolean outsideRangeIsInvalid(@ForAll("outsideRomanRangeIntegers") int n) {
        String roman = RomanNumeraUtils.toRomanNumeral(n);
        return !RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Property
    boolean knownValidRomansParseCorrectly(@ForAll("someValidRomanNumerals") String roman) {
        boolean valid = RomanNumeraUtils.isValidRomanNumeral(roman);
        int parsed = RomanNumeraUtils.parseRomanNumeral(roman);
        return valid && parsed > 0 && parsed <= 3999;
    }

    @Property
    boolean knownInvalidRomansAreInvalid(@ForAll("invalidRomanNumerals") String roman) {
        return !RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Property
    boolean parseInvalidRomans(@ForAll("invalidRomanNumerals") String roman) {
        int parsed = RomanNumeraUtils.parseRomanNumeral(roman);
        return parsed == 0;
    }
}