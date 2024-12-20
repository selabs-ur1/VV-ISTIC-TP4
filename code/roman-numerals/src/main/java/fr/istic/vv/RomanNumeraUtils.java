package fr.istic.vv;

import java.util.Map;

public class RomanNumeraUtils {

    // Les symboles romains valides
    final static String ROMAN_LETTER = "MDCLXVI";

    /**
     * Vérifie la validité d'un chiffre romain.
     * Un chiffre romain est valide si les caractères sont valides et respectent les règles de répétition (ex: 'I' peut être répété jusqu'à 3 fois, etc.).
     *
     * @param value Le chiffre romain à vérifier.
     * @return true si le chiffre est valide, false sinon.
     */
    public static boolean isValidRomanNumeral(String value) {
        // Vérifier que la chaîne n'est pas vide
        if (value == null || value.isEmpty()) {
            return false;
        }

        // Vérification des règles de répétition des symboles
        int iCount = 0, xCount = 0, cCount = 0, mCount = 0, dCount = 0, lCount = 0, vCount = 0;
        char previousChar = '\0'; // Variable pour garder une trace du caractère précédent

        // Vérification des symboles valides et des répétitions
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);

            // Vérifier que chaque caractère est un symbole romain valide
            if (!ROMAN_LETTER.contains(String.valueOf(c))) {
                return false;
            }

            // Vérification des répétitions des symboles
            if (c == previousChar) {
                // Incrémenter le compteur de répétition du caractère actuel
                switch (c) {
                    case 'I':
                        iCount++;
                        break;
                    case 'X':
                        xCount++;
                        break;
                    case 'C':
                        cCount++;
                        break;
                    case 'M':
                        mCount++;
                        break;
                    case 'D':
                        dCount++;
                        break;
                    case 'L':
                        lCount++;
                        break;
                    case 'V':
                        vCount++;
                        break;
                }
            } else {
                // Réinitialiser les compteurs lorsque le caractère change
                previousChar = c;
                switch (c) {
                    case 'I':
                        iCount = 1;
                        break;
                    case 'X':
                        xCount = 1;
                        break;
                    case 'C':
                        cCount = 1;
                        break;
                    case 'M':
                        mCount = 1;
                        break;
                    case 'D':
                        dCount = 1;
                        break;
                    case 'L':
                        lCount = 1;
                        break;
                    case 'V':
                        vCount = 1;
                        break;
                }
            }

            if (iCount > 3 || xCount > 3 || cCount > 3 || mCount > 3 || dCount > 1 || lCount > 1 || vCount > 1) {
                System.out.println(iCount + " " + xCount + " " + cCount + " " + mCount + " " + dCount + " " + lCount + " " + vCount);
                System.out.println(value);
                return false;
            }
        }
        return true;
    }


    /**
     * Parse un chiffre romain valide en sa valeur entière correspondante.
     * Si le chiffre romain est invalide, retourne -1.
     *
     * @param numeral Le chiffre romain à parser.
     * @return La valeur entière correspondante au chiffre romain, ou -1 si invalide.
     */
    public static int parseRomanNumeral(String numeral) {
        // Vérification de la validité du chiffre romain
        if (!isValidRomanNumeral(numeral)) {
            return -1; // Retourner -1 si le chiffre romain est invalide
        }

        // Mapping des symboles romains aux valeurs correspondantes
        final Map<Character, Integer> romanToInteger = Map.of(
                'I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000
        );

        // Initialisation de la variable pour stocker le total du nombre romain
        int total = 0, previousValue = 0;

        // Parcours du chiffre romain de droite à gauche
        for (int i = numeral.length() - 1; i >= 0; i--) {
            // Obtention de la valeur entière du caractère romain courant
            int currentValue = romanToInteger.get(numeral.charAt(i));

            // Si la valeur courante est inférieure à la valeur précédente, on soustrait
            if (currentValue < previousValue) {
                total -= currentValue;
            } else {
                // Sinon, on additionne la valeur courante
                total += currentValue;
            }

            // Mise à jour de la valeur précédente pour le prochain itération
            previousValue = currentValue;
        }

        return total; // Retourne le total calculé
    }


    /**
     * Convertit un nombre entier en un chiffre romain.
     * Le nombre doit être compris entre 1 et 3999 inclus.
     *
     * @param number Le nombre entier à convertir en chiffre romain.
     * @return Le chiffre romain correspondant au nombre, ou une chaîne vide si le nombre est invalide.
     */
    public static String toRomanNumeral(int number) {
        // Vérification si le nombre est dans la plage valide pour les chiffres romains
        if (number <= 0 || number > 3999) {
            return "";  // Retourne une chaîne vide si le nombre est invalide
        }

        // Les symboles romains et leurs valeurs correspondantes
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder result = new StringBuilder();  // StringBuilder pour accumuler le résultat

        // Conversion du nombre entier en chiffre romain
        for (int i = 0; i < values.length && number > 0; i++) {
            // Tant que le nombre est supérieur ou égal à la valeur romaine courante
            while (number >= values[i]) {
                result.append(numerals[i]);  // Ajouter le symbole romain au résultat
                number -= values[i];         // Soustraire la valeur de la conversion
            }
        }
        return result.toString();  // Retourne la chaîne contenant le chiffre romain
    }


}
