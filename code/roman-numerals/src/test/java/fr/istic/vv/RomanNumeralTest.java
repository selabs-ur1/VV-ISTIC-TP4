package fr.istic.vv;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of(
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C", "CC", "CCC", "CD",
            "D", "DC", "DCC", "DCCC", "CM", "M", "MM", "MMM"
        );
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.of(
            "IIII", "VV", "XXXX", "LL", "CCCC", "DD", "MMMM", // Trop de répétitions
            "IC", "IL", "ID", "IM", "XD", "XM", "IL", "IC", "ID", "IM", // Mauvais ordre de caractères
            "A", "B", "Z", "Q" // Lettres non valides
        );
    }

    @Property
    boolean testIsValidRomanNumeral(@ForAll("validRomanNumerals") String validRomanNumeral) {
        return RomanNumeraUtils.isValidRomanNumeral(validRomanNumeral);
    }

    @Property
    boolean testIsInvalidRomanNumeral(@ForAll("invalidRomanNumerals") String invalidRomanNumeral) {
        return !RomanNumeraUtils.isValidRomanNumeral(invalidRomanNumeral);
    }

    @Property
    void testParseRomanNumeralThrows(@ForAll("invalidRomanNumerals") String invalidRomanNumeral) {
        try {
            RomanNumeraUtils.parseRomanNumeral(invalidRomanNumeral);
            throw new AssertionError("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Property
    void testParseArabicThrowsOutOfRangeNegative(@ForAll @IntRange(min = Integer.MIN_VALUE, max = 0) int arabic) {
        try {
            RomanNumeraUtils.toRomanNumeral(arabic);
            throw new AssertionError("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Property
    void testParseArabicThrowsOutOfRangePositive(@ForAll @IntRange(min = 4000, max = Integer.MAX_VALUE) int arabic) {
        try {
            RomanNumeraUtils.toRomanNumeral(arabic);
            throw new AssertionError("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
        try {
            RomanNumeraUtils.toRomanNumeral(arabic);
            throw new AssertionError("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }
    
    @Property
    void testParseRomanNumeralToArabicThenToRomanNumeral(@ForAll("validRomanNumerals") String validRomanNumeral) {
        int arabic = RomanNumeraUtils.parseRomanNumeral(validRomanNumeral);
        String roman = RomanNumeraUtils.toRomanNumeral(arabic);
        Assume.that(RomanNumeraUtils.isValidRomanNumeral(roman));
        Assume.that(!roman.isEmpty());
        Assume.that(roman.length() <= 15);
        Assume.that(arabic > 0);
        Assume.that(arabic < 4000);
        Assume.that(roman.equals(validRomanNumeral));
    }

    @Property
    void testParseToArabicToRomanNumeralThenToArabic(@ForAll @IntRange(min = 1, max = 3999) int arabic) {
        String roman = RomanNumeraUtils.toRomanNumeral(arabic);
        Assume.that(RomanNumeraUtils.isValidRomanNumeral(roman));
        Assume.that(!roman.isEmpty());
        Assume.that(roman.length() <= 15);
        Assume.that(arabic > 0);
        Assume.that(arabic < 4000);
        int parsedArabic = RomanNumeraUtils.parseRomanNumeral(roman);
        Assume.that(parsedArabic == arabic);
    }
}
