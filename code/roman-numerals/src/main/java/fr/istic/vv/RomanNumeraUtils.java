package fr.istic.vv;

public class RomanNumeraUtils {

        public static boolean isValidRomanNumeral(String value) {
                if (value == null || value.isEmpty()) {
                        return false;
                }

                // Check if the string contains only valid characters
                if (!value.matches("^[IVXLCDM]+$")) {
                        return false;
                }

                // Check 3 times M, C, X, I
                if (value.matches(".*[M]{4,}.*") || value.matches(".*[C]{4,}.*") || value.matches(".*[X]{4,}.*")
                                || value.matches(".*[I]{4,}.*")) {
                        return false;
                }

                // Check 2 times D, L, V
                if (value.matches(".*[D]{2,}.*") || value.matches(".*[L]{2,}.*") || value.matches(".*[V]{2,}.*")) {
                        return false;
                }

                // Lorsqu'un symbole de valeur inférieure apparaît à gauche d'un symbole de valeur supérieure, la valeur inférieure est soustraite de la valeur supérieure. Seuls les symboles C, X, V peuvent être soustraits. Chaque symbole ne peut être soustrait qu'une seule fois. Le symbole soustrait doit être un cinquième ou un dixième du plus grand
                if (value.matches(".*(IC|IL|ID|IM|XD|XM|VL|VC|VD|VX|LC).*")) {
                        return false;
                    }

                return true;
        }

        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) {
                        throw new IllegalArgumentException("Invalid Roman numeral");
                }

                int result = 0;
                int previousValue = 0;

                for (int i = numeral.length() - 1; i >= 0; i--) {
                        char c = numeral.charAt(i);
                        int value = romanValue.valueOf(String.valueOf(c)).getValue();

                        if (value < previousValue) {
                                result -= value;
                        } else {
                                result += value;
                        }

                        previousValue = value;
                }

                return result;
        }

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

        private static void appendRomanNumerals(StringBuilder result, int chiffre, char one, char five, char ten) {
                if (chiffre == 9) {
                        result.append(one).append(ten);
                } else if (chiffre >= 5) {
                        result.append(five);
                        for (int j = 0; j < chiffre - 5; j++) {
                                result.append(one);
                        }
                } else if (chiffre == 4) {
                        result.append(one).append(five);
                } else {
                        for (int j = 0; j < chiffre; j++) {
                                result.append(one);
                        }
                }
        }

        public static String toRomanNumeral(int number) {
                if (number < 1 || number > 3999) {
                        throw new IllegalArgumentException("Number must be between 1 and 3999");
                }

                StringBuilder result = new StringBuilder();
                int[] chiffres = Integer.valueOf(number).toString().chars().map(c -> c - '0').toArray();
                int length = chiffres.length;

                for (int i = 0; i < length; i++) {
                        int chiffre = chiffres[i];
                        int puissance = length - i - 1; // cela permet de savoir si on est sur les unités, les dizaines,
                                                        // les centaines ou les milliers

                        if (chiffre == 0) {
                                continue;
                        }

                        switch (puissance) {
                        case 0:
                                appendRomanNumerals(result, chiffre, 'I', 'V', 'X');
                                break;
                        case 1:
                                appendRomanNumerals(result, chiffre, 'X', 'L', 'C');
                                break;
                        case 2:
                                appendRomanNumerals(result, chiffre, 'C', 'D', 'M');
                                break;
                        case 3:
                                for (int j = 0; j < chiffre; j++) {
                                        result.append(romanValue.M);
                                }
                                break;
                        }
                }

                return result.toString();
        }
}