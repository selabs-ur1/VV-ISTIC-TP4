package fr.istic.vv;
import net.jqwik.api.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumeralTest {
        @Provide
        Arbitrary<String> stringWithAnyCharacter() {
                return Arbitraries.strings().ofMinLength(1).ofMaxLength(15);
        }

        @Provide
        Arbitrary<String> stringWithOnlyRomanCharacters() {
                return Arbitraries.strings().withChars("IVXLCDM").ofMinLength(1).ofMaxLength(15);
        }

        @Provide
        Arbitrary<Tuple.Tuple2<String, Integer>> romanNumeralsWithValue() {
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

        @Provide
        Arbitrary<Integer> invalidInteger() {
                return Arbitraries.integers().lessOrEqual(0);
        }

        @Provide
        Arbitrary<Integer> validInteger() {
                return Arbitraries.integers().greaterOrEqual(1).lessOrEqual(3999);
        }

        @Property
        boolean nullStringIsNotValid() {
                return !RomanNumeralUtils.isValidRomanNumeral(null);
        }

        @Property
        boolean emptyStringIsNotValid() {
                return !RomanNumeralUtils.isValidRomanNumeral("");
        }

        @Property
        boolean onlyListedCharsAreValid(@ForAll("stringWithAnyCharacter") String value) {
                boolean methodResult = RomanNumeralUtils.isValidRomanNumeral(value);

                Pattern pattern = Pattern.compile("[IVXLCDM]{4,}");
                Matcher matcher = pattern.matcher(value);

                return !methodResult || !matcher.find();
        }

        @Property
        boolean charsDontRepeat(@ForAll("stringWithOnlyRomanCharacters") String value) {
                boolean methodResult = RomanNumeralUtils.isValidRomanNumeral(value);

                long repeatV = value.chars().filter(c -> c == 'V').count();
                long repeatL = value.chars().filter(c -> c == 'L').count();
                long repeatD = value.chars().filter(c -> c == 'D').count();

                // Si la chaîne n'est pas valide, elle peut contenir n'importe quoi.
                // Si elle est valide, elle doit respecter la règle (càd ne pas avoir de duplicatats).
                return !methodResult || (repeatV <= 1 && repeatL <= 1 && repeatD <= 1);
        }

        @Property boolean charsDontRepeatMoreThanThreeTimes(@ForAll("stringWithOnlyRomanCharacters") String value) {
                boolean methodResult = RomanNumeralUtils.isValidRomanNumeral(value);

                Pattern pattern = Pattern.compile("I{4,}|X{4,}|C{4,}|M{4,}");
                Matcher matcher = pattern.matcher(value);

                // Si la chaîne n'est pas valide, elle peut contenir n'importe quoi.
                // Si elle est valide, elle doit respecter la règle (càd ne pas trouver le pattern regex).
                return !methodResult || !matcher.find();
        }

        @Property boolean charsAreInRightOrder(@ForAll("stringWithOnlyRomanCharacters") String value) {
                boolean methodResult = RomanNumeralUtils.isValidRomanNumeral(value);

                Pattern wrongOrderPattern = Pattern.compile("IL|IC|ID|IM|VX|VL|VC|VD|VM|XD|XM|LC|LD|LM|DM");
                Matcher matcher = wrongOrderPattern.matcher(value);

                return !methodResult || !matcher.find();
        }

        @Property boolean handlesInvalidString(@ForAll("stringWithOnlyRomanCharacters") String value) {
                int methodResult = RomanNumeralUtils.parseRomanNumeral(value);

                return methodResult != -1 || !RomanNumeralUtils.isValidRomanNumeral(value);
        }

        @Property boolean givesTheRightInteger(@ForAll("romanNumeralsWithValue") Tuple.Tuple2<String, Integer> value) {
                int methodResult = RomanNumeralUtils.parseRomanNumeral(value.get1());

                return methodResult == value.get2();
        }

        @Property boolean givesTheRightRomanNumeral(@ForAll("romanNumeralsWithValue") Tuple.Tuple2<String, Integer> value) {
                String methodResult = RomanNumeralUtils.toRomanNumeral(value.get2());

                return methodResult.equals(value.get1());
        }

        @Property boolean handlesInvalidInteger(@ForAll("invalidInteger") int value) {
                String methodResult = RomanNumeralUtils.toRomanNumeral(value);

                return methodResult.isEmpty();
        }

        @Property boolean cycleInteger(@ForAll("validInteger") int value) {
                String romanNumeralVersion = RomanNumeralUtils.toRomanNumeral(value);
                int backToIntegerVersion = RomanNumeralUtils.parseRomanNumeral(romanNumeralVersion);

                return backToIntegerVersion == value;
        }

        @Property boolean cycleRomanNumeral(@ForAll("stringWithOnlyRomanCharacters") String value) {
                if (RomanNumeralUtils.isValidRomanNumeral(value)) {
                        int integerVersion = RomanNumeralUtils.parseRomanNumeral(value);
                        String backToRomanNumeralVersion = RomanNumeralUtils.toRomanNumeral(integerVersion);

                        return value.equals(backToRomanNumeralVersion);
                }

                return true;
        }
}
