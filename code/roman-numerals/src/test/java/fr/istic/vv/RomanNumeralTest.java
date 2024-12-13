package fr.istic.vv;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {

    // propriété pour la conversion de roman -> int
    @Property
    boolean romanToIntConversion(@ForAll("validRomanNumerals") String roman) {
        int result = RomanNumeraUtils.parseRomanNumeral(roman);
        String reconstructedRoman = RomanNumeraUtils.toRomanNumeral(result);
        return reconstructedRoman.equals(roman);
    }

    // propriété pour la conversion de int -> roman
    @Property
    boolean intToRomanConversion(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String roman = RomanNumeraUtils.toRomanNumeral(number);
        int reconstructedNumber = RomanNumeraUtils.parseRomanNumeral(roman);
        return number == reconstructedNumber;
    }

    // propriété pour la vérification de nombre romain valide
    @Property
    boolean romanNumeralValidation(@ForAll("validRomanNumerals") String validRoman) {
        return RomanNumeraUtils.isValidRomanNumeral(validRoman);
    }

    @Property
    boolean invalidRomanNumeralsAreRejected(@ForAll("invalidRomanNumerals") String roman) {
        return !RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XX", "XXX", "XL", "L", "LX",
                "LXX", "LXXX", "XC", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM", "M", "MM", "MMM",
                "MCMXCIX", "MMXXI");
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.of("AAA3", "123", "IIII", "VV", "XXXX", "LL", "CCCC", "DD", "MMMM", "IC", "IL", "IM", "VX",
                "XD", "XM", "ID", "VD", "LC", "XCXC","IXIX","XLXL","XCXC","CDCD","CMCM");
    }

}
