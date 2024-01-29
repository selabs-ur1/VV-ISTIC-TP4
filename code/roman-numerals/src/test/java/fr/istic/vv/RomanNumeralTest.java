package fr.istic.vv;
import net.jqwik.api.*;

public class RomanNumeralTest {

    /*
    Bug trouvé avec les tests : 4 était transformé en IIII au lieu de IV
     */

    @Property
    void parseAndToAreInverseOperations(@ForAll("number") int number) {
        assert number > 0 && number < 4000;

        String romanNumeral = RomanNumeraUtils.toRomanNumeral(number);
        int parsed = RomanNumeraUtils.parseRomanNumeral(romanNumeral);

        assert parsed == number;
    }

    @Property
    void parseValidAndToProvidesSameNumeral(@ForAll("validRomanNumerals") String numeral) {
        int parsed = RomanNumeraUtils.parseRomanNumeral(numeral);
        String numeralFromParsed = RomanNumeraUtils.toRomanNumeral(parsed);
        assert numeral.equals(numeralFromParsed);
    }

    @Property
    void isValidNumeralReturnsTrueOnValidNumerals(@ForAll("validRomanNumerals") String numeral) {
        assert RomanNumeraUtils.isValidRomanNumeral(numeral);
    }

    @Property
    void isValidNumeralReturnsFalseOnInvalidNumerals(@ForAll("invalidRomanNumerals") String numeral) {
        assert !RomanNumeraUtils.isValidRomanNumeral(numeral);
    }


    @Provide
    Arbitrary<Integer> number() {
        return Arbitraries.integers().between(1, 3999);
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of("I", "IV", "VIII", "IX", "XIV", "XVI", "XIX", "XCIX", "CV", "MI", "MMCCLXXXIX");
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.of("MMMM", "nfi", "fenp", "pkoa", "pak", "IIIIII", "DD", "LL", "Ma");
    }

}
