package fr.istic.vv;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;

public class RomanNumeralTest {

    /* isValidRomanNumeral tests */

    /* parseRomanNumeral */

    @Provide
    Arbitrary<String> romanNumeralProvider() {
        return Arbitraries.strings()
                .filter(RomanNumeraUtils::isValidRomanNumeral);
    }

    @Property
    boolean parseRomanNumeralNumeralAlwaysInRange(
            @ForAll("romanNumeralProvider") String numeral) {
        int value = RomanNumeraUtils.parseRomanNumeral(numeral);
        return value >= 0 && value <= RomanNumeraUtils.MAX_NUMERAL_VALUE;
    }

    @Property
    boolean parseRomanNumeralUndefinedForAllNonRoman(@ForAll String numeral) {
        boolean isValid = RomanNumeraUtils.isValidRomanNumeral(numeral);

        try {
            RomanNumeraUtils.parseRomanNumeral(numeral);
        } catch (NumberFormatException e) {
            return !isValid;
        }

        return isValid;
    }

    @Property
    boolean parseRomanNumeralIsomorphism(
            @ForAll @IntRange(max = RomanNumeraUtils.MAX_NUMERAL_VALUE) int n) {
        return RomanNumeraUtils.parseRomanNumeral(RomanNumeraUtils.toRomanNumeral(n)) == n;
    }

    @Property
    boolean toRomanNumeralAlwaysRoman(
            @ForAll @IntRange(max = RomanNumeraUtils.MAX_NUMERAL_VALUE) int n) {
        return RomanNumeraUtils.isValidRomanNumeral(RomanNumeraUtils.toRomanNumeral(n));
    }

    @Property
    boolean toRomanNumeralUndefinedUnderZero(@ForAll @Negative int n) {
        try {
            RomanNumeraUtils.toRomanNumeral(n);
        } catch (IllegalArgumentException e) {
            return true;
        }

        return false;
    }

    @Property
    boolean toRomanNumeralUndefinedOverMax(
            @ForAll @IntRange(min = RomanNumeraUtils.MAX_NUMERAL_VALUE + 1) int n) {
        try {
            RomanNumeraUtils.toRomanNumeral(n);
        } catch (IllegalArgumentException e) {
            return true;
        }

        return false;
    }

    @Property
    boolean toRomanNumeralAlwaysValid(
            @ForAll @IntRange(max = RomanNumeraUtils.MAX_NUMERAL_VALUE) int n) {
        return RomanNumeraUtils.isValidRomanNumeral(RomanNumeraUtils.toRomanNumeral(n));
    }

    @Property
    boolean toRomanNumeralIsomorphism(
            @ForAll("romanNumeralProvider") String numeral) {
        return RomanNumeraUtils.toRomanNumeral(RomanNumeraUtils.parseRomanNumeral(numeral))
                .equals(numeral);
    }

    /* toRomanNumeral */
}
