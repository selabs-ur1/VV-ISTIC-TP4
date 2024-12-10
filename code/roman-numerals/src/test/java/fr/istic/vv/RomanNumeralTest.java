package fr.istic.vv;

import net.jqwik.api.*;

public class RomanNumeralTest {


    @Property
    boolean validRomanNumeralOnlyContainsValidSymbols(@ForAll("validRomanNumerals") String numeral) {
        return RomanNumeraUtils.isValidRomanNumeral(numeral);
    }


    @Property
    boolean parseValidRomanNumeral(@ForAll("validRomanNumerals") String numeral) {
        return RomanNumeraUtils.parseRomanNumeral(numeral) >= 0;
    }

    @Property
    boolean parseInvalidRomanNumeralReturnsZero(@ForAll("invalidRomanNumerals") String numeral) {
        return RomanNumeraUtils.parseRomanNumeral(numeral) == -1;
    }

    @Property
    boolean toRomanNumeralReversibility(@ForAll("validIntegers") int number) {
        return !RomanNumeraUtils.toRomanNumeral(number).isEmpty();
    }

    @Property
    boolean toRomanNumeralReversibilityInvalid(@ForAll("invalidIntegers") int number) {
        return RomanNumeraUtils.toRomanNumeral(number).isEmpty();
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.strings().filter(s -> s.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})?$"));
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.strings().filter(s -> !s.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})?$"));
    }

    @Provide
    Arbitrary<Integer> validIntegers() {
        return Arbitraries.integers().between(1, 3999);
    }

    @Provide
    Arbitrary<Integer> invalidIntegers() {
        return Arbitraries.integers().filter(i -> i < 0 || i > 3999);
    }
}
