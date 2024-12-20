package fr.istic.vv;

import net.jqwik.api.*;

public class RomanNumeralTest {

    @Provide
    Arbitrary<String> maxLengthRomain() {
        return Arbitraries.strings().ofMinLength(1).ofMaxLength(2).withChars('X','I');
    }

    @Provide
    Arbitrary<Integer> allNumber() {
        return Arbitraries.integers().between(1, 3999);
    }

    // @Property
    // boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min=1,max=3999) int anInteger) {
    //     return RomanNumeraUtils.isValidRomanNumeral(RomanNumeraUtils.toRomanNumeral(anInteger));
    // }

    @Property
    boolean allNumbers(@ForAll("maxLengthRomain") String anInteger) {
        return RomanNumeraUtils.isValidRomanNumeral(anInteger);
    }

    @Property
    boolean allIntegersAreGood(@ForAll("allNumber") int anInteger) {
        return RomanNumeraUtils.isValidRomanNumeral(RomanNumeraUtils.toRomanNumeral(anInteger));
    }

}
