package fr.istic.vv;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;

public class RomanNumeralTest {

    /**
     * Provides valid roman numbers.
     *
     * @return An <code>Arbitrary</code> object used to provide valid roman numbers.
     */
    @Provide
    @SuppressWarnings("unused")
    // actually used
    Arbitrary<String> romanNumeralProvider() {
        return Arbitraries.strings()
                .filter(RomanNumeraUtils::isValidRomanNumeral);
    }

    /**
     * @param numeral For any given numeral...
     * @return <code>RomanNumeraUtils.parseRomanNumeral(numeral)</code> is between <code>0</code>
     * and <code>3999</code>.
     */
    @Property
    boolean parseRomanNumeralNumeralAlwaysInRange(
            @ForAll("romanNumeralProvider") String numeral) {
        int value = RomanNumeraUtils.parseRomanNumeral(numeral);
        return value >= 0 && value <= RomanNumeraUtils.MAX_NUMERAL_VALUE;
    }

    /**
     * @param numeral For any given roman number numeral...
     * @return <code>numeral</code> is not valid
     * <==> <code>RomanNumeraUtils.parseRomanNumeral(numeral)</code> throws a number format
     * exception.
     */
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

    /**
     * @param n For any given integer in range...
     * @return <code>parseRomanNumeral(toRomanNumeral(n)) == n</code>
     */
    @Property
    boolean parseRomanNumeralIsomorphism(
            @ForAll @IntRange(max = RomanNumeraUtils.MAX_NUMERAL_VALUE)
            int n) {
        return RomanNumeraUtils.parseRomanNumeral(RomanNumeraUtils.toRomanNumeral(n)) == n;
    }

    /**
     * @param n For any negative integer...
     * @return <code>RomanNumeraUtils.toRomanNumeral(n)</code> is not defined.
     */
    @Property
    boolean toRomanNumeralUndefinedUnderZero(@ForAll @Negative int n) {
        try {
            RomanNumeraUtils.toRomanNumeral(n);
        } catch (IllegalArgumentException e) {
            return true;
        }

        return false;
    }

    /**
     * @param n For any integer greater than <code>3999</code>...
     * @return <code>RomanNumeraUtils.toRomanNumeral(n)</code> is not defined.
     */
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

    /**
     * @param n For any integer in range...
     * @return <code>RomanNumeraUtils.toRomanNumeral(n)</code> is always a valid roman numeral.
     */
    @Property
    boolean toRomanNumeralAlwaysValid(
            @ForAll @IntRange(max = RomanNumeraUtils.MAX_NUMERAL_VALUE) int n) {
        return RomanNumeraUtils.isValidRomanNumeral(RomanNumeraUtils.toRomanNumeral(n));
    }

    /**
     * @param numeral For any given integer in range...
     * @return <code>toRomanNumeral(parseRomanNumeral(numeral)).equals(numeral)</code>
     */
    @Property
    boolean toRomanNumeralIsomorphism(
            @ForAll("romanNumeralProvider") String numeral) {
        return RomanNumeraUtils.toRomanNumeral(RomanNumeraUtils.parseRomanNumeral(numeral))
                .equals(numeral);
    }
}
