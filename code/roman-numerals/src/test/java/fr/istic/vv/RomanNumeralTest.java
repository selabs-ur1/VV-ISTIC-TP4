package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;

public class RomanNumeralTest {

    //Provide valid Roman numeral to the property
    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M","XIV", "XCIX", "MMCCLXXXIX");
    }

    //Provide invalid Roman numeral to the property
    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.strings().ofMinLength(1);
    }

    @Property
    boolean testIsValidRomanNumeralsValid(@ForAll("validRomanNumerals") String valid) {
        return RomanNumeraUtils.isValidRomanNumeral(valid);
    }


    @Property
    boolean testIsValidRomanNumeralsInvalid(@ForAll("invalidRomanNumerals") String invalid) {
        return !RomanNumeraUtils.isValidRomanNumeral(invalid);
    }

    @Property
    boolean parsingAndConvertingShouldBeConsistent(@ForAll("validRomanNumerals") String valid) {
        int romanToInt = RomanNumeraUtils.parseRomanNumeral(valid);
        String intToRoman = RomanNumeraUtils.toRomanNumeral(romanToInt);
        return valid.equals(intToRoman);
    }


}
