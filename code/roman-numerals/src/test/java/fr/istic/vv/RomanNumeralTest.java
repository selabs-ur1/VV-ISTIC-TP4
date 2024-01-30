package fr.istic.vv;
import net.jqwik.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class RomanNumeralTest {

    @Property
    void isValidRomanNumeralShouldNotFail(@ForAll("validRomanNumerals") String validRomanNumeral) {
        assertTrue(RomanNumeralUtils.isValidRomanNumeral(validRomanNumeral));
    }

    @Property
    void parseAndToRomanNumeralShouldBeInverse(@ForAll("validIntegers") int validInteger) {
        assumeTrue(validInteger > 0 && validInteger < 4000); // Ensure valid range
        String romanNumeral = RomanNumeralUtils.toRomanNumeral(validInteger);
        assertEquals(validInteger, RomanNumeralUtils.parseRomanNumeral(romanNumeral));
    }

    @Property
    void parseInvalidRomanNumeralShouldReturnNegativeOne(@ForAll("invalidRomanNumerals") String invalidRomanNumeral) {
        assertEquals(-1, RomanNumeralUtils.parseRomanNumeral(invalidRomanNumeral));
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.strings().withCharRange('I', 'X').ofMinLength(1).ofMaxLength(15);
    }

    @Provide
    Arbitrary<Integer> validIntegers() {
        return Arbitraries.integers().between(1, 3999);
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.strings().ofMinLength(1).ofMaxLength(15).filter(s -> !RomanNumeralUtils.isValidRomanNumeral(s));
    }
}
