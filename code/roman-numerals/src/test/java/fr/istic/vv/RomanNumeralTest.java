package fr.istic.vv;
import net.jqwik.api.*;

public class RomanNumeralTest {


    @Property
    boolean isValidRomanNumeral(@ForAll("validRomanNumerals") String numeral) {
       return RomanNumeraUtils.isValidRomanNumeral(numeral);
    }

    @Property
    boolean parseRomanNumeral(@ForAll("validRomanNumerals") String numeral) {
        int numParse = RomanNumeraUtils.parseRomanNumeral(numeral);

        String numParseToNumRoman = RomanNumeraUtils.toRomanNumeral(numParse);
        System.out.println(numeral + " " + numParse + " " + numParseToNumRoman);
        return numeral.equals(numParseToNumRoman);
    }


    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M");
    }


    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.strings().withChars(':', 'A', 'c', '2', 'Z', '<', 'U', '7', 'P').ofMinLength(1);
    }
}
