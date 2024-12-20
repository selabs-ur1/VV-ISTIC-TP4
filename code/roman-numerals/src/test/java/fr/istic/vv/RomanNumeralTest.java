package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralTest {

    private static final Pattern ROMAN_NUMERAL_PATTERN =
            Pattern.compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min=Integer.MIN_VALUE +1) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean validRomanNumeralsAreAccepted(@ForAll("validRomanNumerals") String roman) {
        return RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Property
    boolean invalidRomanNumeralsAreRejected(@ForAll("invalidRomanNumerals") String invalidRoman) {
        return !RomanNumeraUtils.isValidRomanNumeral(invalidRoman);
    }

    @Property
    boolean convertIntToRoman(@ForAll("validNumbers") int number) {
        String roman = RomanNumeraUtils.toRomanNumeral(number);
        assertThat(roman).isNotNull();
        assertThat(roman).matches("^[MDCLXVI]+$");
        return RomanNumeraUtils.parseRomanNumeral(roman) == number;
    }

    @Property
    boolean convertRomanToInt(@ForAll("validRomanNumerals") String roman) {
        int number = RomanNumeraUtils.parseRomanNumeral(roman);
        assertThat(number).isGreaterThan(0).isLessThanOrEqualTo(3999);
        return RomanNumeraUtils.toRomanNumeral(number).equals(roman);
    }

    @Provide
    Arbitrary<Integer> validNumbers() {
        return Arbitraries.integers().between(1, 3999);
    }

    @Provide
    Arbitrary<String> validRomanNumerals(String input) {
        return Arbitraries.of(
                "I", "IV", "IX", "XL", "XC", "CD", "CM",
                "II", "III", "VI", "XII", "XX", "XXX", "L", "C", "D", "M", "MMM"
        );
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.of(
                "IIII", "VV", "LL", "DD", "MMMM", "IC", "IM", "VX",
                "", "A", "123", "XIIII", "MMMMM", "IIV"
        );
    }
}
