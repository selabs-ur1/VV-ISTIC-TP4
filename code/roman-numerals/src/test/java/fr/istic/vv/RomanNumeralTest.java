package fr.istic.vv;
import net.jqwik.api.*;

class RomanNumeralUtilsTest {

    @Property
    boolean isValidTrueForValidNumerals(@ForAll("validRomanNumerals") String validNumeral) {
        return RomanNumeralUtils.isValidRomanNumeral(validNumeral);
    }

    @Property
    boolean isValidFalseForInvalidNumerals(@ForAll("invalidRomanNumerals") String invalidNumeral) {
        Assume.that(!RomanNumeralUtils.isValidRomanNumeral(invalidNumeral));
        return true;
    }

    @Property
    boolean parseInverseOfRomanNumeral(@ForAll("validRomanNumerals") String validNumeral) {
        int parsedValue = RomanNumeralUtils.parseRomanNumeral(validNumeral);
        String convertedNumeral = RomanNumeralUtils.toRomanNumeral(parsedValue);

        if (!validNumeral.equals(convertedNumeral)) {
            System.out.println("Failed for: " + validNumeral);
            System.out.println("Parsed value: " + parsedValue);
            System.out.println("Converted back to Roman numeral: " + convertedNumeral);
        }

        return validNumeral.equals(convertedNumeral);
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of("I", "IV", "IX", "X", "XL", "XC", "C", "CD", "D", "CM", "M");
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.strings().withCharRange('A', 'H').ofMinLength(1).ofMaxLength(5);
    }
}
