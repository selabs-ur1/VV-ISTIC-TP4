package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {

    @Property
    boolean testAbsoluteValueOfPositiveNumbers(@ForAll @IntRange(min = Integer.MIN_VALUE + 1) int number) {
        // Vérifie que la valeur absolue de tous les nombres est toujours positive ou égale à 0
        return Math.abs(number) >= 0;
    }

    @Property
    boolean checkIfValidRomanNumeralsAreAccepted(@ForAll("validRomans") String roman) {
        // Vérifie que les chiffres romains valides sont bien reconnus par la méthode de validation
        return RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Property
    boolean checkIfInvalidRomanNumeralsAreRejected(@ForAll("invalidRomans") String roman) {
        // Vérifie que les chiffres romains invalides sont rejetés correctement
        return !RomanNumeraUtils.isValidRomanNumeral(roman);
    }

    @Property
    boolean validateRomanNumeralConversion(@ForAll("validRomanPairs") RomanNumeralPair pair) {
        // Vérifie que les chiffres romains sont bien convertis en leurs valeurs respectives
        String roman = pair.roman;
        int expectedValue = pair.value;
        return RomanNumeraUtils.parseRomanNumeral(roman) == expectedValue;
    }

    @Property
    boolean testRomanNumeralConversionForValidNumbers(@ForAll @IntRange(min = 1, max = 3999) int number) {
        // Vérifie que la conversion d'un nombre en chiffre romain est correcte
        String roman = RomanNumeraUtils.toRomanNumeral(number);
        return RomanNumeraUtils.parseRomanNumeral(roman) == number;
    }

    @Property
    boolean testExceptionForInvalidNumbers(@ForAll("outOfRangeNumbers") int number) {
        // Vérifie que les nombres invalides (en dehors de la plage) lèvent bien une exception
        try {
            RomanNumeraUtils.toRomanNumeral(number);
            return false; // Doit lancer une exception
        } catch (IllegalArgumentException e) {
            return e.getMessage().contains("out of range");
        }
    }

    @Provide
    Arbitrary<String> validRomans() {
        return Arbitraries.of(
                "I", "IV", "VIII", "IX", "XIV", "XVI", "XIX", "XCIX", "CV", "MI", "MMCCLXXXIX", "MMMCMXCIX"
        );
    }

    @Provide
    Arbitrary<String> invalidRomans() {
        return Arbitraries.of(
                "IIII", "VV", "XXXX", "LL", "CCCC", "DD", "MMMM", "IC", "IL", "VX", "XD", "", "A", "123"
        );
    }

    @Provide
    Arbitrary<RomanNumeralPair> validRomanPairs() {
        return Arbitraries.of(
                new RomanNumeralPair("I", 1),
                new RomanNumeralPair("IV", 4),
                new RomanNumeralPair("VIII", 8),
                new RomanNumeralPair("IX", 9),
                new RomanNumeralPair("XIV", 14),
                new RomanNumeralPair("XVI", 16),
                new RomanNumeralPair("XIX", 19),
                new RomanNumeralPair("XCIX", 99),
                new RomanNumeralPair("CV", 105),
                new RomanNumeralPair("MI", 1001),
                new RomanNumeralPair("MMCCLXXXIX", 2289),
                new RomanNumeralPair("MMMCMXCIX", 3999)
        );
    }

    @Provide
    Arbitrary<Integer> outOfRangeNumbers() {
        return Arbitraries.integers().filter(n -> n <= 0 || n > 3999);
    }

    static class RomanNumeralPair {
        final String roman;
        final int value;

        RomanNumeralPair(String roman, int value) {
            this.roman = roman;
            this.value = value;
        }
    }
}
