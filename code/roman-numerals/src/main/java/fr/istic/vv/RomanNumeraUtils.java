package fr.istic.vv;

import java.util.ArrayList;
import java.util.List;

public class RomanNumeralUtils {
    public enum Symbole {
        M, CM, D, CD, C, XC, L,XL, X, IX, V, IV, I;

        public int symbToInt() {
            switch (this) {
                case M: return 1000;
                case CM: return 900;
                case D: return 500;
                case CD: return 400;
                case C: return 100;
                case XC: return 90;
                case L: return 50;
                case XL: return 40;
                case X: return 10;
                case IX: return 9;
                case V: return 5;
                case IV: return 4;
                case I: return 1;
                default: throw new IllegalArgumentException("Symbole inconnu : " + this);
            }
        }

        public static List<Symbole> stringToListRomanSymb(String value) {
            if (value == null || value.isEmpty()) {
                throw new IllegalArgumentException("La chaîne de caractères est nulle ou vide.");
            }

            List<Symbole> res = new ArrayList<>();
            for (char c : value.toCharArray()) {
                switch (c) {
                    case 'M': res.add(Symbole.M); break;
                    case 'D': res.add(Symbole.D); break;
                    case 'C': res.add(Symbole.C); break;
                    case 'L': res.add(Symbole.L); break;
                    case 'X': res.add(Symbole.X); break;
                    case 'V': res.add(Symbole.V); break;
                    case 'I': res.add(Symbole.I); break;
                    default: throw new IllegalArgumentException("Caractère invalide dans la chaîne : " + c);
                }
            }
            return res;
        }
    }


    /**
     * Vérifie si une chaîne représentant un nombre romain est valide selon les règles des chiffres romains.
     *
     * @param value la chaîne représentant un nombre romain à valider.
     * @return true si le nombre romain est valide, false sinon.
     */
    public static boolean isValidRomanNumeral(String value) {
        List<Symbole> romanNumeral = Symbole.stringToListRomanSymb(value);
        return !isMoreThanThreeConsecutiveSymbolsMCXI(romanNumeral)
                && !isSymbolDLVRepeated(romanNumeral)
                && isValidFormat(romanNumeral);
    }

    /**
     * Convertit une chaîne représentant un nombre romain valide en sa valeur entière correspondante.
     *
     * @param numeral la chaîne représentant un nombre romain à convertir.
     * @return la valeur entière correspondant au nombre romain.
     */
    public static int parseRomanNumeral(String numeral) {
        List<Symbole> romanNumeral = Symbole.stringToListRomanSymb(numeral);
        int result = 0;

        for (int i = 0; i < romanNumeral.size(); i++) {
            int current = romanNumeral.get(i).symbToInt();
            if (i < romanNumeral.size() - 1 && current < romanNumeral.get(i + 1).symbToInt()) {
                result -= current;
            } else {
                result += current;
            }
        }
        return result;
    }

    /**
     * Convertit un nombre entier en sa représentation sous forme de nombre romain.
     *
     * @param number le nombre entier à convertir en nombre romain.
     * @return la représentation sous forme de nombre romain du nombre entier.
     * @throws IllegalArgumentException si le nombre n'est pas compris entre 1 et 3999.
     */
    public static String toRomanNumeral(int number){
        if( 0>= number || number > 3999){
            throw new IllegalArgumentException("Le nombre doit être compris entre 1 et 3999.");
        }
        List<Symbole> lresult = toRomanNumeralList(number, new ArrayList<Symbole>());
        String result = "";
        for(Symbole sym : lresult){
            result += sym.toString();
        }
        return result;
    }

    /**
     * Vérifie si un symbole particulier est répété plus d'une fois dans la liste de symboles romains.
     *
     * @param romanNumeral la liste de symboles représentant un nombre romain.
     * @param sym le symbole à vérifier.
     * @return true si le symbole est répété plus d'une fois, false sinon.
     */
    private static boolean isSymbolRepeated(List<Symbole> romanNumeral, Symbole sym) {
        int count = 0;
        for (Symbole r : romanNumeral) {
            if (r == sym) {
                count++;
                if (count > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Vérifie si un des symboles D, L ou V est répété dans la liste de symboles romains.
     *
     * @param romanNumeral la liste de symboles représentant un nombre romain.
     * @return true si l'un des symboles D, L ou V est répété, false sinon.
     */
    private static boolean isSymbolDLVRepeated(List<Symbole> romanNumeral) {
        return isSymbolRepeated(romanNumeral,Symbole.D)
                || isSymbolRepeated(romanNumeral,Symbole.L)
                || isSymbolRepeated(romanNumeral,Symbole.V);
    }

    /**
     * Vérifie si un symbole particulier apparaît plus de trois fois de manière consécutive dans la liste de symboles romains.
     *
     * @param romanNumeral la liste de symboles représentant un nombre romain.
     * @param sym le symbole à vérifier pour une répétition consécutive.
     * @return true si le symbole apparaît plus de trois fois consécutivement, false sinon.
     */
    private static boolean isMoreThanThreeConsecutiveSymbols(List<Symbole> romanNumeral, Symbole sym) {
        int count = 0;

        for (Symbole r: romanNumeral ) {
            if (r == sym) {
                count++;
                if (count > 3) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    /**
     * Vérifie si un des symboles M, C, X ou I est répété plus de trois fois de manière consécutive.
     *
     * @param romanNumeral la liste de symboles représentant un nombre romain.
     * @return true si l'un des symboles M, C, X ou I est répété plus de trois fois consécutivement, false sinon.
     */
    private static boolean isMoreThanThreeConsecutiveSymbolsMCXI(List<Symbole> romanNumeral) {
        return isMoreThanThreeConsecutiveSymbols(romanNumeral,Symbole.M)
                || isMoreThanThreeConsecutiveSymbols(romanNumeral,Symbole.C)
                || isMoreThanThreeConsecutiveSymbols(romanNumeral,Symbole.X)
                || isMoreThanThreeConsecutiveSymbols(romanNumeral,Symbole.I);
    }

    /**
     * Valide le format d'un nombre romain en fonction des règles de soustraction correctes.
     *
     * @param romanNumeral la liste de symboles représentant un nombre romain.
     * @return true si le format est valide, false sinon.
     */
    private static boolean isValidFormat(List<Symbole> romanNumeral){
        for (int i = 0; i < romanNumeral.size() - 1; i++) {
            int current = romanNumeral.get(i).symbToInt();
            int next = romanNumeral.get(i + 1).symbToInt();

            if (current < next) {
                if (!isValidSubtraction(romanNumeral.get(i), romanNumeral.get(i + 1))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Vérifie si la règle de soustraction est valide entre deux symboles romains.
     *
     * @param low le symbole romain inférieur.
     * @param high le symbole romain supérieur.
     * @return true si la soustraction est valide, false sinon.
     */
    private static boolean isValidSubtraction(Symbole low, Symbole high) {
        if (low == Symbole.I && (high == Symbole.V || high == Symbole.X)) return true;
        if (low == Symbole.X && (high == Symbole.L || high == Symbole.C)) return true;
        if (low == Symbole.C && (high == Symbole.D || high == Symbole.M)) return true;
        return false;
    }

    /**
     * Convertit un nombre entier en une liste de symboles romains.
     *
     * PS : Méthode récursive : pour décomposer l'entier en ses composants romains en fonction des valeurs disponibles.
     *
     * @param number le nombre entier à convertir.
     * @param symboles la liste de symboles romains à remplir.
     * @return une liste de symboles romains correspondant au nombre entier.
     */
    private static List<Symbole> toRomanNumeralList(int number, List<Symbole> symboles) {
        Symbole[] symbolesRomiens = {
                Symbole.M, Symbole.CM, Symbole.D, Symbole.CD,
                Symbole.C, Symbole.XC, Symbole.L, Symbole.XL,
                Symbole.X, Symbole.IX, Symbole.V, Symbole.IV, Symbole.I
        };
        int[] valeurs = {1000, 900, 500, 400,
                100,  90 , 50 , 40 ,
                10,   9,   5,    4 , 1};

        if (number == 0) {
            return symboles;
        }

        for (int i = 0; i < valeurs.length; i++) {
            if (number >= valeurs[i]) {
                symboles.add(symbolesRomiens[i]);
                return toRomanNumeralList((number - symbolesRomiens[i].symbToInt()), symboles);
            }
        }

        return symboles;
    }

}
