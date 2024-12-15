package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min = Integer.MIN_VALUE + 1) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean validRomanNumerals(@ForAll("validRomanNumerals") String numeral) {
        return RomanNumeraUtils.isValidRomanNumeral(numeral);
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.strings().withChars('I', 'V', 'X', 'L', 'C', 'D', 'M').ofMinLength(1).ofMaxLength(15)
                .filter(s -> s.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$"));
    }

    @Property
    boolean invalidRomanNumerals(@ForAll("invalidRomanNumerals") String numeral) {
        return !RomanNumeraUtils.isValidRomanNumeral(numeral);
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.strings().withCharRange('A', 'Z').filter(s -> !s.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$"));
    }

    @Property
    boolean parseRomanNumeral(@ForAll("validRomanNumerals") String numeral) {
        int result = RomanNumeraUtils.parseRomanNumeral(numeral);
        return result >= 0;
    }

    @Property
    boolean toRomanNumeral(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String result = RomanNumeraUtils.toRomanNumeral(number);
        return RomanNumeraUtils.isValidRomanNumeral(result);
    }
}