package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import static org.junit.jupiter.api.Assertions.*;


public class RomanNumeralTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min=Integer.MIN_VALUE +1) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }


    @Provide
    Arbitrary<String> validRomanNumerals1() {
        return Arbitraries.strings().withChars('M', 'D', 'C', 'L', 'X', 'V', 'I')
                .filter(this::isValidRomanNumerals2)
                .filter(this::isValidRomanNumerals3);
    }
    private boolean isValidRomanNumerals2(String s) {
        String regex = "^M{0,3}(CM|CD|D?C{0,3})?(XC|XL|L?X{0,3})?(IX|IV|V?I{0,3})?$";
        return s.matches(regex);
    }
    private boolean isValidRomanNumerals3(String s) {
        return !s.isEmpty();
    }

    @Property
    void validRomanNumeralOnlyHasAllowedCharacters(@ForAll("validRomanNumerals1") String numeral) {
        assertTrue(RomanNumeraUtils.isValidRomanNumeral(numeral));
    }


    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.strings()
                .withChars('A', 'B', 'Z', '1', '2', '!', '@');
    }
    @Property
    void invalidRomanNumeralHasDisallowedCharacters(@ForAll("invalidRomanNumerals") String numeral) {
        assertFalse(RomanNumeraUtils.isValidRomanNumeral(numeral));
    }

    @Property
    void parseValidRomanNumeralReturnsPositiveInteger(@ForAll("validRomanNumerals1") String numeral) {
        int result = RomanNumeraUtils.parseRomanNumeral(numeral);
        assertTrue(result > 0);
    }

    @Property
    void parseInvalidRomanNumeralThrowsException(@ForAll("invalidRomanNumerals") String numeral) {
        assertThrows(IllegalArgumentException.class,
                () -> RomanNumeraUtils.parseRomanNumeral(numeral));
    }

    @Property
    void toRomanNumeralProducesValidRomanString(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String numeral = RomanNumeraUtils.toRomanNumeral(number);
        assertTrue(RomanNumeraUtils.isValidRomanNumeral(numeral));
    }

    @Property
    void toRomanNumeralThrowsExceptionForInvalidNumbers(@ForAll @IntRange(min = -100, max = 0) int number) {
        assertThrows(IllegalArgumentException.class,
                () -> RomanNumeraUtils.toRomanNumeral(number));
    }


    @Property
    void bidirectionalConversionIsConsistent(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String numeral = RomanNumeraUtils.toRomanNumeral(number);
        int result = RomanNumeraUtils.parseRomanNumeral(numeral);
        assertEquals(number, result);
    }

}
