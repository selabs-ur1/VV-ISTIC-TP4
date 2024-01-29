package fr.istic.vv;
import net.jqwik.api.*;

public class RomanNumeralTest {

    @Provide
    Arbitrary<Integer> number() {
        return Arbitraries.integers().between(1, 3999);
    }

    @Provide
    Arbitrary<String> romanNumerals() {
        return Arbitraries.strings().withChars('I', 'V', 'X', 'L', 'C', 'D', 'M').ofMinLength(1).ofMaxLength(15).filter(RomanNumeralUtils::isValidRomanNumeral);
    }

    @Property
    boolean isValidRomanNumeral(@ForAll("romanNumerals") String numeral) {
        return RomanNumeralUtils.isValidRomanNumeral(numeral);
    }

    @Property
    boolean parseRomanNumeral(@ForAll("number") int number) {
        String romanNumeral = RomanNumeralUtils.toRomanNumeral(number);
        int parsedNumber = RomanNumeralUtils.parseRomanNumeral(romanNumeral);
        return parsedNumber == number;
    }

    @Property
    boolean toRomanNumeral(@ForAll("romanNumerals") String numeral) {
        int number = RomanNumeralUtils.parseRomanNumeral(numeral);
        String romanNumeral = RomanNumeralUtils.toRomanNumeral(number);
        return romanNumeral.equals(numeral);
    }
}
