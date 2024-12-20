package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {

    @Property
    boolean roundTripConversion(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String roman = RomanNumeralUtils.toRomanNumeral(number);
        int backToNumber = RomanNumeralUtils.parseRomanNumeral(roman);
        return number == backToNumber;
    }

    @Property
    boolean noRepeatingDLVSymbols(@ForAll String input) {
        if (input.isEmpty()) {
            // Si l'entrée est vide, elle est invalide, donc le test doit retourner true.
            try {
                RomanNumeralUtils.isValidRomanNumeral(input);
                return false; // Si aucune exception n'est levée, c'est une erreur.
            } catch (IllegalArgumentException e) {
                return true; // Exception attendue pour une chaîne vide.
            }
        }

        // Vérifie que les symboles D, L et V ne se répètent pas
        boolean containsRepeatedDLV = input.matches(".*D{2,}.*|.*L{2,}.*|.*V{2,}.*");
        return !containsRepeatedDLV || RomanNumeralUtils.isValidRomanNumeral(input);
    }

    @Property
    boolean noMoreThanThreeRepeats(@ForAll("randomInputs") String input) {
        // Vérifie que les symboles M, C, X, I ne se répètent pas plus de trois fois consécutivement
        boolean containsMoreThanThreeM = input.matches(".*M{4,}.*");
        boolean containsMoreThanThreeC = input.matches(".*C{4,}.*");
        boolean containsMoreThanThreeX = input.matches(".*X{4,}.*");
        boolean containsMoreThanThreeI = input.matches(".*I{4,}.*");

        try {
            // Si un des symboles se répète plus de trois fois consécutivement, la chaîne doit être invalide
            boolean isValid = RomanNumeralUtils.isValidRomanNumeral(input);
            return !input.isEmpty() && !containsMoreThanThreeM && !containsMoreThanThreeC &&
                    !containsMoreThanThreeX && !containsMoreThanThreeI || !isValid;

        }catch(IllegalArgumentException e) {
            return true;
        }
            }

    @Property
    boolean validRomanNumeralForNumber(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String roman = RomanNumeralUtils.toRomanNumeral(number);
        return RomanNumeralUtils.isValidRomanNumeral(roman); // Vérifie que le nombre romain généré est valide
    }

    @Property
    boolean parseRomanNumeralHandlesInvalidInput(@ForAll("romanNumerals") String romanNumeral) {
        if (!RomanNumeralUtils.isValidRomanNumeral(romanNumeral)) {
            try {
                RomanNumeralUtils.parseRomanNumeral(romanNumeral);
                return false; // Si la méthode ne lance pas d'exception, c'est un échec
            } catch (Exception e) {
                return true; // Si une exception est lancée, cela signifie que l'entrée invalide est correctement gérée
            }
        }
        return true; // Si la chaîne est valide, pas de problème
    }

    @Property
    boolean toRomanNumeralInvalidRange(@ForAll @IntRange(min = Integer.MIN_VALUE, max = 0) int number) {
        try {
            RomanNumeralUtils.toRomanNumeral(number);
            return false; // Si la méthode ne lance pas d'exception, c'est un échec
        } catch (Exception e) {
            return true; // Si une exception est lancée pour une entrée invalide, c'est un succès
        }
    }

    // Générateur de chaînes aléatoires pour tester les cas valides et invalides
    @Provide
    Arbitrary<String> randomInputs() {
        return Arbitraries.strings()
                .withChars("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890")
                .ofMinLength(0)
                .ofMaxLength(30);
    }

    // Générateur de nombres romains valides
    @Provide
    Arbitrary<String> romanNumerals() {
        return Arbitraries.strings().withChars("IVXLCDM").ofMinLength(1).ofMaxLength(15);
    }
}
