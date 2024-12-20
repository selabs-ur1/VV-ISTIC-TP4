package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {

    @Property
    boolean validRomanRoundTrip(@ForAll @IntRange(min=1, max=3999) int number) {
        String roman = RomanNumeralUtils.toRomanNumeral(number);

        int parsed = RomanNumeralUtils.parseRomanNumeral(roman);
        
        return parsed == number && RomanNumeralUtils.isValidRomanNumeral(roman);
    }

    @Property
    boolean parseRomanIsConsistent(@ForAll("validRomans") String roman) {

        int number = RomanNumeralUtils.parseRomanNumeral(roman);

        return number >= 1 && number <= 3999;
    }

    @Provide
    Arbitrary<String> validRomans() {

        return Arbitraries.integers().between(1,3999).map(RomanNumeralUtils::toRomanNumeral);
    }

}
