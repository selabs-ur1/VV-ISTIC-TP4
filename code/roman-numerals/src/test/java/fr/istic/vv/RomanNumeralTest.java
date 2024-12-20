package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {

    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min = Integer.MIN_VALUE + 1) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    // Teste que les numéraux romains valides retournent true
    @Property
    boolean validRomanNumeralsReturnTrue(@ForAll("validRomanNumerals") String numeral) {
        // Vérifie que les numéraux romains valides sont bien identifiés comme valides
        return RomanNumeraUtils.isValidRomanNumeral(numeral);
    }

    // Teste que les numéraux romains invalides retournent false
    @Property
    boolean invalidRomanNumeralsReturnFalse(@ForAll("invalidRomanNumerals") String numeral) {
        // Vérifie que les numéraux romains invalides sont bien identifiés comme invalides
        return !RomanNumeraUtils.isValidRomanNumeral(numeral);
    }

    // Teste que l'analyse des numéraux romains valides retourne la bonne valeur entière
    @Property
    boolean parsingValidRomanNumeralsReturnsCorrectValue(
            @ForAll("validRomanNumeralsWithValues") Object[] data
    ) {
        String numeral = (String) data[0];  // La chaîne romaine
        int expectedValue = (int) data[1];  // La valeur entière attendue
        int parsedValue = RomanNumeraUtils.parseRomanNumeral(numeral); // On parse le numéral romain
        return parsedValue == expectedValue;  // Vérifie que la valeur obtenue correspond à la valeur attendue
    }

    // Teste que l'analyse des numéraux romains invalides retourne -1
    @Property
    boolean parsingInvalidRomanNumeralsReturnsMinusOne(
            @ForAll("invalidRomanNumerals") String numeral
    ) {
        int parsedValue = RomanNumeraUtils.parseRomanNumeral(numeral); // On tente de parser un numéral romain invalide
        return parsedValue == -1; // Les numéraux romains invalides doivent retourner -1
    }

    // Teste que la conversion d'un entier en numéral romain fonctionne correctement
    @Property
    boolean integerToRomanNumeralReturnsCorrectRoman(
            @ForAll("numbersWithRomanNumerals") Object[] data
    ) {
        int number = (int) data[0]; // Le nombre entier à convertir
        String expectedRoman = (String) data[1]; // Le numéral romain attendu
        String romanNumeral = RomanNumeraUtils.toRomanNumeral(number); // Conversion du nombre en numéral romain
        return romanNumeral.equals(expectedRoman); // Vérifie que le numéral romain obtenu est correct
    }

    // Teste que les nombres invalides retournent une chaîne vide
    @Property
    boolean invalidNumbersReturnEmptyString(
            @ForAll("invalidNumbers") int number
    ) {
        String romanNumeral = RomanNumeraUtils.toRomanNumeral(number); // Conversion du nombre invalide
        return romanNumeral.isEmpty(); // Les nombres invalides doivent retourner une chaîne vide
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.strings()
                .withChars('I', 'V', 'X', 'L', 'C', 'D', 'M') // Utilisation des caractères valides dans les numéraux romains
                .ofMinLength(1) // Exclut les chaînes vides
                .filter(s -> s.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")); // Filtre pour valider la chaîne selon le format des numéraux romains
    }


    // Fournit un générateur de numéraux romains invalides pour les tests
    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.strings()
                .withChars('A', 'Z') // Génère des caractères dans l'intervalle A-Z
                .ofMinLength(1) // Exclut les chaînes vides
                .filter(s -> !s.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")); // Filtre les chaînes valides selon la regex des numéraux romains
    }

    // Fournit des tableaux de numéraux romains valides avec leurs valeurs attendues
    @Provide
    Arbitrary<Object[]> validRomanNumeralsWithValues() {
        return Arbitraries.of(
                new Object[] { "I", 1 },
                new Object[] { "IV", 4 },
                new Object[] { "IX", 9 },
                new Object[] { "XL", 40 },
                new Object[] { "XC", 90 },
                new Object[] { "CD", 400 },
                new Object[] { "CM", 900 },
                new Object[] { "MMXXIV", 2024 }
        );
    }

    // Fournit des paires de nombres et leurs équivalents en chiffres romains
    @Provide
    Arbitrary<Object[]> numbersWithRomanNumerals() {
        return Arbitraries.of(
                new Object[] { 1, "I" },
                new Object[] { 4, "IV" },
                new Object[] { 9, "IX" },
                new Object[] { 40, "XL" },
                new Object[] { 90, "XC" },
                new Object[] { 3999, "MMMCMXCIX" },
                new Object[] { 2289, "MMCCLXXXIX" },
                new Object[] { 105, "CV" },
                new Object[] { 1001, "MI" },
                new Object[] { 500, "D" }
        );
    }

    // Fournit des nombres hors de la plage des numéraux romains valides (0-3999)
    @Provide
    Arbitrary<Integer> invalidNumbers() {
        return Arbitraries.integers().filter(i -> i > 3999 || i < 0); // Génère des nombres en dehors de la plage valide (0-3999)
    }

}
