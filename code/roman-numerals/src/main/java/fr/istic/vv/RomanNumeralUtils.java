package fr.istic.vv;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RomanNumeralUtils {

        /**
         * Nombres romains et leurs valeurs
         */
        private static final Map<Character, Integer> symbols = Map.of(
                'M', 1000,
                'D', 500,
                'C', 100,
                'L', 50,
                'X', 10,
                'V', 5,
                'I', 1
        );

        // Permet d'avoir une map qui garde l'ordre d'insertion.
        /**
         * Nombres avec soustractions pour permettre de convertir en nombre romain
         */
        private static final Map<String, Integer> romansNumbers = new LinkedHashMap<>();

        static {
                romansNumbers.put("M", 1000);
                romansNumbers.put("CM", 900);
                romansNumbers.put("D", 500);
                romansNumbers.put("CD", 400);
                romansNumbers.put("C", 100);
                romansNumbers.put("XC", 90);
                romansNumbers.put("L", 50);
                romansNumbers.put("XL", 40);
                romansNumbers.put("X", 10);
                romansNumbers.put("IX", 9);
                romansNumbers.put("V", 5);
                romansNumbers.put("IV", 4);
                romansNumbers.put("I", 1);
        }

        /**
         * Caractères pouvant être répétés 3 fois
         */
        private static final List<Character> canBeRepeated = Arrays.asList('M', 'C', 'X', 'I' );

        /**
         * Nombre pouvant se voir soutraits pour donner de nouvelles valeurs
         */
        private static final List<Character> canBeSubstracted = Arrays.asList('D', 'L', 'C', 'X', 'V', 'M');

        /**
         * Permet de déterminer si la chaine de caractère est un nombre valide.
         * @param value La chaine de caractère
         * @return Un booléen à vrai si la chaine représente un nombre romain valide.
         */
        public static boolean isValidRomanNumeral(String value) {
                // Le dernier symbole vu
                char lastSymbol = 0;
                // Le nombre de fois où le symbole a été répété
                int repeated = 1;
                // Le plus petit symbole que l'on peut voir (hors soustraction).
                char smallest = 0;
                // On lit notre chaine de charactère à l'envers pour ne pas avoir à faire des rollbacks.
                for (int i = value.length()-1; i >= 0; i--){
                        // On récupère notre symbole actuel
                        char c = value.charAt(i);

                        // S'il s'agit bien d'un symbole romain et qu'il peut bien apparaître ici.
                        if (isSymbol(c) && (canBeSubstractedBy(lastSymbol, c) || isLegit(c, smallest))){
                                // Si nous ne pouvons pas le soustraire, c'est qu'il s'agit d'un nombre plus grand,
                                // nous devons donc redresser notre minimum.
                                if (!canBeSubstractedBy(lastSymbol, c)){
                                        smallest = c;
                                }
                                // Gestion des répétitions
                                if (lastSymbol == c){
                                       repeated++;
                                       if (!canBeRepeated(c) || repeated > 3){
                                               return false;
                                       }
                                }
                                else {
                                        // On met à jour notre dernier symbole vu aprè le traitement pour la prochaine boucle.
                                        lastSymbol = c;
                                        // On remet à 0 le compteur de répétition
                                        repeated = 1;
                                }
                        }
                        else {
                                return false;
                        }
                }
                return true;
        }

        /**
         * Permet de savoir si un nombre peut apparaître ici (à combiner avec le test de soustraction car
         * test seulement si le nouveau caractère lu a bien une valeur plus petite que
         * le précédent que nous avons considéré).
         * @param c Le nouveau caractère lu
         * @param smallest Le plus petit que nous lu pour le moment. (Analyse à l'envers)
         * @return Si le caractère peu être présent à ce stade, hors soustraction.
         */
        private static boolean isLegit(char c, char smallest) {
                if (smallest == 0){
                        return true;
                }
                else {
                        return symbols.get(c) >= symbols.get(smallest);
                }
        }

        /**
         * Convertit un chiffre romain en nombre arabe.
         * @param numeral La chaine de caractère de nombre romain.
         * @return L'équivalent arabe. (Entier
         */
        public static int parseRomanNumeral(String numeral) {
                int number = 0;
                char lastSeen = 0;
                // On vérifie dans un premier temps que la chaine est bien valide.
                if (isValidRomanNumeral(numeral)) {
                        for (int i = numeral.length()-1; i >= 0; i--){
                               char c = numeral.charAt(i);
                               // On va chercher sa valeur
                               int toAdd = symbols.get(c);
                               // S'il s'agit d'une soustraction, on change le signe
                               if (lastSeen != 0 && (symbols.get(lastSeen) > symbols.get(c))){
                                       toAdd = -toAdd;
                               } else {
                                       lastSeen = c;
                               }
                               number = number + toAdd;
                        }
                }
                return number;
        }

        /**
         * Convertit un entier en chaine représentant l'équivalent romain.
         * @param number L'entier à convertir
         * @return L'équivalent romain.
         */
        public static String toRomanNumeral(int number) {
                // On vérifie que le nombre est convertible
                if (number <= 0 || number > 3999){
                        throw new IllegalArgumentException("Veuillez entrer un nombre entre 0 et 3999");
                }

                StringBuilder result = new StringBuilder();
                for (Map.Entry<String, Integer> entry : romansNumbers.entrySet()) {
                        int v = entry.getValue();
                        String n = entry.getKey();

                        // Tant que x est supérieur ou égal à la valeur actuelle v, on ajoute le chiffre romain correspondant
                        while (number >= v) {
                                result.append(n);
                                number -= v;
                        }
                }

                return result.toString();
        }

        /**
         * Permet de déterminer si le caractère c peut être répété 3 fois ou non
         * @param c Le caractère que l'on souhaite répéter
         * @return S'il peut être répété
         */
        private static boolean canBeRepeated(char c){
                return canBeRepeated.contains(c);
        }

        /**
         * Permet de déterminer si nous pouvons effectuer une soustraction entre les caractères.
         * @param substracted Le premier caractère.
         * @param substraction Ce qu'on souhaite lui soustraire
         * @return S'il s'agit bien d'une soustraction
         */
        private static boolean canBeSubstractedBy(char substracted, char substraction){
                return canBeSubstracted(substracted)
                        && (
                                symbols.get(substracted)/10 == symbols.get(substraction)
                                        || symbols.get(substracted)/5 == symbols.get(substraction)
                        );
        }

        /**
         * Retourne si un caractère fait partit de ceux que l'on peut soustraire.
         * @param substracted Le caractère à évaluer
         * @return Si nous pouvons le soustraire par un autre.
         */
        private static boolean canBeSubstracted(char substracted){
                return canBeSubstracted.contains(substracted);
        }

        /**
         * @param c Le caractère possiblement romain
         * @return Si le caractère est un romain ou non.
         */
        private static boolean isSymbol(char c){
                return symbols.containsKey(c);
        }
    
}
