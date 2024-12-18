package fr.istic.vv;

import net.jqwik.api.*;
import java.util.Set;

public class RomanNumeralTest {

    private static final Set<Character> ROMAN_SYMBOLS = Set.of('M', 'D', 'C', 'L', 'X', 'V', 'I');


    @Provide
    Arbitrary<String> romanNumerals() {
        return Arbitraries.strings()
                .withChars("IVXLCDM")
                .ofMinLength(1)
                .ofMaxLength(15);
    }


    @Property
    boolean testIsValidRomanNumeral(@ForAll("romanNumerals") String romanNumeral) {
        System.out.println("romanNumeral: " + romanNumeral);
        boolean validSymbols = romanNumeral.chars().allMatch(c -> ROMAN_SYMBOLS.contains((char) c));

        boolean repeatedSymbolsValid = !romanNumeral.contains("IIII") &&
                !romanNumeral.contains("XXXX") &&
                !romanNumeral.contains("CCCC") &&
                !romanNumeral.contains("MMMM");

        boolean noRepeatedDLV = !romanNumeral.contains("DD") &&
                !romanNumeral.contains("LL") &&
                !romanNumeral.contains("VV");

        boolean validSubtractions = checkValidSubtractions(romanNumeral);

        boolean inputIsValid = !romanNumeral.isEmpty() && validSymbols && repeatedSymbolsValid && noRepeatedDLV && validSubtractions;
        boolean resultFromMethod = RomanNumeraUtils.isValidRomanNumeral(romanNumeral);

        System.out.println("inputIsValid: " + inputIsValid + " resultFromMethod: " + resultFromMethod);
        return inputIsValid == resultFromMethod;
    }

    private boolean checkValidSubtractions(String romanNumeral) {

        final java.util.Map<Character, Integer> romanValues = java.util.Map.of(
                'I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000
        );

        for (int i = 0; i < romanNumeral.length() - 1; i++) {
            char current = romanNumeral.charAt(i);
            char next = romanNumeral.charAt(i + 1);

            Integer currentValue = romanValues.get(current);
            Integer nextValue = romanValues.get(next);

            if (currentValue == null || nextValue == null) {
                return false;
            }

            if (currentValue < nextValue) {
                if (current != 'I' && current != 'X' && current != 'C') {
                    return false;
                }

                if (!(nextValue == currentValue * 5 || nextValue == currentValue * 10)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Provide
    Arbitrary<Tuple.Tuple2<String, Integer>> romanNumeralPairs() {
        return Arbitraries.of(
                Tuple.of("I", 1),
                Tuple.of("IV", 4),
                Tuple.of("VIII", 8),
                Tuple.of("IX", 9),
                Tuple.of("XIV", 14),
                Tuple.of("XVI", 16),
                Tuple.of("XIX", 19),
                Tuple.of("XCIX", 99),
                Tuple.of("CV", 105),
                Tuple.of("MI", 1001),
                Tuple.of("MMCCLXXXIX", 2289)
        );
    }

    @Property
    void testParseRomanNumeral(@ForAll @From("romanNumeralPairs") Tuple.Tuple2<String, Integer> pair) {
        String numeral = pair.get1();
        int expectedValue = pair.get2();

        checkRomanNumeral(numeral, expectedValue);
    }

    private void checkRomanNumeral(String numeral, int expectedValue) {
        int result = RomanNumeraUtils.parseRomanNumeral(numeral);
        System.out.println("numeral: " + numeral + " expectedValue: " + expectedValue + " result: " + result);
        assert result == expectedValue;
    }

    @Property
    void testToRomanNumeral(@ForAll @From("romanNumeralPairs") Tuple.Tuple2<String, Integer> pair) {
        int numeral = pair.get2();
        String expectedValue = pair.get1();

        checkRomanNumeral(numeral, expectedValue);
    }

    private void checkRomanNumeral(int numeral, String expectedValue) {
        String result = RomanNumeraUtils.toRomanNumeral(numeral);
        System.out.println("numeral: " + numeral + " expectedValue: " + expectedValue + " result: " + result);
        assert result.equals(expectedValue);
    }


}