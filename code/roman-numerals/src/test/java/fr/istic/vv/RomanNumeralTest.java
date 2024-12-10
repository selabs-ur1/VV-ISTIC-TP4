package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import fr.istic.vv.RomanNumeraUtils;


public class RomanNumeralTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min=Integer.MIN_VALUE +1) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    void validRomanNumeralsShouldPassValidation(@ForAll("validRomanNumerals") String numeral) {
        Assertions.assertTrue(RomanNumeraUtils.isValidRomanNumeral(numeral));
    }

    @Property
    void invalidRomanNumeralsShouldFailValidation(@ForAll("invalidRomanNumerals") String numeral) {
        Assertions.assertFalse(RomanNumeraUtils.isValidRomanNumeral(numeral));
    }

    @Property
    void parseRomanNumeralShouldReturnCorrectValue(@ForAll("romanToIntegerCases") Map.Entry<String, Integer> caseEntry) {
        Assertions.assertEquals(caseEntry.getValue(), RomanNumeraUtils.parseRomanNumeral(caseEntry.getKey()));
    }

    @Property
    void toRomanNumeralShouldReturnCorrectString(@ForAll("integerToRomanCases") Map.Entry<Integer, String> caseEntry) {
        Assertions.assertEquals(caseEntry.getValue(), RomanNumeraUtils.toRomanNumeral(caseEntry.getKey()));
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of("I", "IV", "VIII", "IX", "XIV", "XVI", "XCIX", "CV", "MI", "MMCCLXXXIX");
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.of("IIII", "VV", "MMMM", "IC", "VX", "IL", "IXX", "XIIII");
    }

    @Provide
    Arbitrary<Map.Entry<String, Integer>> romanToIntegerCases() {
        Map<String, Integer> cases = Map.of(
                "I", 1,
                "IV", 4,
                "VIII", 8,
                "IX", 9,
                "XIV", 14,
                "XVI", 16,
                "XCIX", 99,
                "CV", 105,
                "MI", 1001,
                "MMCCLXXXIX", 2289
        );
        return Arbitraries.of(cases.entrySet());
    }

    @Provide
    Arbitrary<Map.Entry<Integer, String>> integerToRomanCases() {
        Map<Integer, String> cases = Map.of(
                1, "I",
                4, "IV",
                8, "VIII",
                9, "IX",
                14, "XIV",
                16, "XVI",
                99, "XCIX",
                105, "CV",
                1001, "MI",
                2289, "MMCCLXXXIX"
        );
        return Arbitraries.of(cases.entrySet());
    }
}
