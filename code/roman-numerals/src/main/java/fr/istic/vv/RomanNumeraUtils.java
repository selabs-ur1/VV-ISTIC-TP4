package fr.istic.vv;

public class RomanNumeraUtils {

        enum romanValue {
                I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

                private int value;

                romanValue(int value) {
                        this.value = value;
                }

                public int getValue() {
                        return value;
                }
        }

        public static boolean isValidRomanNumeral(String value) {

                if (value == null || value.isEmpty()) {
                        return false;
                }

                // que des characteres autorisés
                if (!value.matches("^[MDCLXVI]+$")) {
                        return false;
                }

                // M, C, X, I au max 3 fois à la suite (si 4 ou plus erreur)
                if (value.matches(".*[M]{4,}.*") || value.matches(".*[C]{4,}.*") || value.matches(".*[X]{4,}.*")
                                || value.matches(".*[I]{4,}.*")) {
                        return false;
                }

                // D, L, V ne peuvent pas être répter plusieurs fois (si 2 ou plus erreur)
                if (value.matches(".*[D]{2,}.*") || value.matches(".*[L]{2,}.*") || value.matches(".*[V]{2,}.*")) {
                        return false;
                }
                // si un symbole inférieur apparait à gauche de C,X,V alors il faut que ce soit
                // ,
                // il faut que le symbole inferieur soit seul on peut enlever soit 1/5 soit 1/10
                // du nombre
                if (value.matches(".*(IM|VM|XM|LM|DM).*")// à gauche de M seulement C possible
                                || value.matches(".*[C]{2,}M.*") // à gauche de M seulement un seul C possible
                                || value.matches(".*(LD|XD|VD|ID).*") // à gauche de D seulement C possible
                                || value.matches(".*[C]{2,}D.*") // à gauche de D seulement un seul C
                                || value.matches(".*(LC|VC|IC).*") // à gauche de C seulement X possible
                                || value.matches(".*[X]{2,}C.*") // à gauche de C seulement un seul X
                                || value.matches(".*(IL|VL).*") // à gauche de L seulement X possible
                                || value.matches(".*[X]{2,}L.*") // à gauche de L seulement un seul X
                                || value.matches(".*(VX).*") // à gauche de X seulement I possible
                                || value.matches(".*[I]{2,}X.*") // à gauche de X seulement un seul I
                                || value.matches(".*[I]{2,}V.*") // à gauche de V seulement un seul I
                ) {
                        return false;
                }

                //  local valide mais globalement non à cause de répétition d'élement à gauche 
                if (value.matches(".*(IVIV|IXIX|XLXL|XCXC|CDCD|CMCM).*")) {
                        return false; 
                    }

                return true;

        }

        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) {
                        throw new IllegalArgumentException("numeral not well formed");
                }

                int res = 0;
                int previousVal = 0;

                for (int i = numeral.length() - 1; i >= 0; i--) {
                        char c = numeral.charAt(i);
                        int val = romanValue.valueOf(String.valueOf(c)).getValue();

                        if (val < previousVal) {
                                res -= val;
                        } else {
                                res += val;
                        }

                        previousVal = val;
                }

                return res;

        }

        public static String toRomanNumeral(int number) {
                // I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

                int numberUpdate = number;
                StringBuilder res = new StringBuilder();
                while (numberUpdate > 0) {
                        if (numberUpdate >= 1000) {
                                res.append("M");
                                numberUpdate -= 1000;
                        } else if (numberUpdate >= 900) {
                                res.append("CM");
                                numberUpdate -= 900;

                        } else if (numberUpdate >= 500) {
                                res.append("D");
                                numberUpdate -= 500;

                        } else if (numberUpdate >= 400) {
                                res.append("CD");
                                numberUpdate -= 400;

                        } else if (numberUpdate >= 100) {
                                res.append("C");
                                numberUpdate -= 100;

                        } else if (numberUpdate >= 90) {
                                res.append("XC");
                                numberUpdate -= 90;

                        } else if (numberUpdate >= 50) {
                                res.append("L");
                                numberUpdate -= 50;

                        } else if (numberUpdate >= 40) {
                                res.append("XL");
                                numberUpdate -= 40;

                        } else if (numberUpdate >= 10) {
                                res.append("X");
                                numberUpdate -= 10;

                        } else if (numberUpdate >= 9) {
                                res.append("IX");
                                numberUpdate -= 9;

                        } else if (numberUpdate >= 5) {
                                res.append("V");
                                numberUpdate -= 5;

                        } else if (numberUpdate >= 4) {
                                res.append("IV");
                                numberUpdate -= 4;

                        } else {
                                res.append("I");
                                numberUpdate -= 1;

                        }

                }

                return res.toString();
        }

}
