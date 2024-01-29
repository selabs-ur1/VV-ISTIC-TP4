package fr.istic.vv;

import java.util.regex.Pattern;

public class RomanNumeralUtils {

        private static final String[] SYMBOLES_ROMAINS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        private static final int[] VALEURS_INTEGERS = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        /**
         * Utilisez une expression régulière pour valider les chiffres romains
         *
         * @param valeur à valider
         * @return true si le chiffre romain est valide
         */
        public static boolean estChiffreRomainValide(String valeur) {
                String pattern = "^[IVXLCDM]+$";
                return Pattern.matches(pattern, valeur);
        }

        /**
         * Analyser une chaîne romaine en valeur numérique
         *
         * @param chiffreRomain le chiffre romain
         * @return un entier correspondant au chiffre romain
         * @throws IllegalArgumentException si le chiffre romain n'est pas valide
         */
        public static int convertirChiffreRomainEnNombre(String chiffreRomain) {
                if (!estChiffreRomainValide(chiffreRomain)) {
                        throw new IllegalArgumentException("Chiffre romain invalide : " + chiffreRomain);
                }

                int resultat = 0;
                int index = 0;
                for (int i = 0; i < SYMBOLES_ROMAINS.length; i++) {
                        while (chiffreRomain.startsWith(SYMBOLES_ROMAINS[i], index)) {
                                resultat += VALEURS_INTEGERS[i];
                                index += SYMBOLES_ROMAINS[i].length();
                        }
                }
                return resultat;
        }

        /**
         * Convertir un nombre en une chaîne romaine
         *
         * @param nombre à convertir
         * @return la chaîne correspondant au nombre romain
         * @throws IllegalArgumentException si le nombre est < 0 ou > 3999
         */
        public static String convertirNombreEnChiffreRomain(int nombre) {
                if (nombre <= 0 || nombre > 3999) {
                        throw new IllegalArgumentException("Nombre hors de portée pour les chiffres romains : " + nombre);
                }

                StringBuilder resultat = new StringBuilder();

                for (int i = 0; i < SYMBOLES_ROMAINS.length; i++) {
                        while (nombre >= VALEURS_INTEGERS[i]) {
                                resultat.append(SYMBOLES_ROMAINS[i]);
                                nombre -= VALEURS_INTEGERS[i];
                        }
                }

                return resultat.toString();
        }
}
