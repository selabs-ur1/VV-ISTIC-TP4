package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumeralTest {
        @Provide
        Arbitrary<String> stringWithOnlyRomanCharacters() {
                return Arbitraries.strings().withChars("IVXLCDM").ofMinLength(1).ofMaxLength(15);
        }

        @Property
        boolean nullStringIsNotValid() {
                return !RomanNumeraUtils.isValidRomanNumeral(null);
        }

        @Property
        boolean emptyStringIsNotValid(String value) {
                return !RomanNumeraUtils.isValidRomanNumeral("");
        }

        @Property
        boolean onlyListedCharsAreValid(@ForAll("stringWithOnlyRomanCharacters") String value) {
                return RomanNumeraUtils.isValidRomanNumeral(value);
        }

        @Property
        boolean charsDontRepeat(@ForAll("stringWithOnlyRomanCharacters") String value) {
                boolean methodResult = RomanNumeraUtils.isValidRomanNumeral(value);

                long repeatV = value.chars().filter(c -> c == 'V').count();
                long repeatL = value.chars().filter(c -> c == 'L').count();
                long repeatD = value.chars().filter(c -> c == 'D').count();

                // Si la chaîne n'est pas valide, elle peut contenir n'importe quoi.
                // Si elle est valide, elle doit respecter la règle (càd ne pas avoir de duplicatats).
                return !methodResult || (repeatV <= 1 && repeatL <= 1 && repeatD <= 1);
        }

        @Property boolean charsDontRepeatMoreThanThreeTimes(@ForAll("stringWithOnlyRomanCharacters") String value) {
                boolean methodResult = RomanNumeraUtils.isValidRomanNumeral(value);

                Pattern pattern = Pattern.compile("[IXCM]{4,}");
                Matcher matcher = pattern.matcher(value);

                // Si la chaîne n'est pas valide, elle peut contenir n'importe quoi.
                // Si elle est valide, elle doit respecter la règle (càd ne pas trouver le pattern regex).
                return !methodResult || !matcher.find();
        }

        @Property boolean charsAreInRightOrder(@ForAll("stringWithOnlyRomanCharacters") String value) {
                boolean methodResult = RomanNumeraUtils.isValidRomanNumeral(value);

                Pattern pattern = Pattern.compile("L.V|D.V|D.L");
                Matcher matcher = pattern.matcher(value);

                return !methodResult || !matcher.find();
        }
}
