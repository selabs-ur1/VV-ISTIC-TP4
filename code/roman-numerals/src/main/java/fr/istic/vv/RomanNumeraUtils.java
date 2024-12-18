package fr.istic.vv;

public class RomanNumeraUtils {

    /**
     * Any natural number between 0 and 3999 can be represented in *roman numerals*
     * using the following rules:
     * <p>
     * 1. Only symbols *M*, *D*, *C*, *X*, *V*, *I* can be used. Their values are
     * shown below:
     * <p>
     * | M | D | C | L | X | V | I |
     * |------|-----|-----|----|----|---|---|
     * | 1000 | 500 | 100 | 50 | 10 | 5 | 1 |
     * <p>
     * 2. Symbols M, C, X, I can be repeated consecutively up to three times.
     * 3. Symbols D, L and V can not be repeated.
     * 4. When a symbol of lower value of appears to the right of a symbol of equal
     * or higher value, all symbol values are added.
     * 5. When a symbols of lower value appears to the left of a symbols of higher
     * value, the lower value is subtracted from the higher value.
     * Only symbols C, X, V can be subtracted. Each symbol can be subtracted only
     * once. The subtracted symbol must be one fifth or one tenth of the larger.
     *
     * @param value
     * @return
     */
    public static boolean isValidRomanNumeral(String value) {
        boolean valid = value.length() > 0;
        int i = 0;
        int cpt = 0;
        int cptSub = 0;
        char lastChar = ' ';
        while (i < value.length() && valid) {
            char c = value.charAt(i);
            cpt = c == lastChar ? cpt + 1 : 1;
            int cVal = numeralValueOf(c);
            int lastCharVal = numeralValueOf(lastChar);
            cptSub = (lastCharVal > 0 && cVal > lastCharVal) ? cptSub + 1 : 0;

            if (lastCharVal > 0 && lastCharVal < cVal) {
                valid = cptSub == 1 &&
                        (((c == 'M' || c == 'C' || c == 'X') && (lastCharVal * 10 == cVal))
                                || ((c == 'D' || c == 'L' || c == 'V') && (lastCharVal * 5 == cVal)));
            }
            if (c == 'C' || c == 'X' || c == 'M' || c == 'I') {
                valid &= cpt <= 3;
            } else if (c == 'V' || c == 'D' || c == 'L') {
                valid &= cpt == 1;
            } else {
                valid = false;
            }

            lastChar = c;
            i++;
        }
        return valid;
    }

    /**
     * parse Roman Numeral String to integer
     *
     * @param numeral
     * @return
     */
    public static int parseRomanNumeral(String numeral) {
        if (!isValidRomanNumeral(numeral)) {
            return -1;
        }
        int value = 0;
        int i = 0;
        int cpt = 0;
        int cptSub = 0;
        int cVal = -1;
        int lastCharVal = -1;
        while (i < numeral.length()) {
            lastCharVal = cVal;
            cVal = numeralValueOf(numeral.charAt(i));
            cpt = cVal == lastCharVal ? cpt + 1 : 1;

            if (lastCharVal > 0 && cVal > lastCharVal) {
                cVal = cVal - lastCharVal;
            }

            value += cVal;

            i++;
        }
        return value;
    }

    /**
     * get latin numeral String from integer
     *
     * @param number
     * @return
     */
    public static String toRomanNumeral(int number) {
        int multiplier = 1000;
        String numeral = "";

        while (number > 0) {
            int r = number % multiplier;
            int d = (number - r) / multiplier;
            if (d == 4) {
                numeral += strValueOf(multiplier) + strValueOf(multiplier * 5);
            } else if (d == 9) {
                numeral += strValueOf(multiplier) + strValueOf(multiplier * 10);
            } else if (d != 0) {
                if (d >= 5) {
                    numeral += strValueOf(multiplier * 5);
                }
                for (int i = 0; i < d % 5; i++) {
                    numeral += strValueOf(multiplier);
                }
            }
            multiplier /= 10;
            number = r;
        }
        return numeral;
    }

    /***
     * | M | D | C | L | X | V | I |
     * |------|-----|-----|----|----|---|---|
     * | 1000 | 500 | 100 | 50 | 10 | 5 | 1 |
     *
     * @param c
     * @return
     */
    private static int numeralValueOf(char c) {
        int ret = -1;

        switch (c) {
            case 'M':
                ret = 1000;
                break;
            case 'D':
                ret = 500;
                break;
            case 'C':
                ret = 100;
                break;
            case 'L':
                ret = 50;
                break;
            case 'X':
                ret = 10;
                break;
            case 'V':
                ret = 5;
                break;
            case 'I':
                ret = 1;
                break;
        }
        return ret;
    }

    /***
     * | M | D | C | L | X | V | I |
     * | 1000 | 500 | 100 | 50 | 10 | 5 | 1 |
     * |------|-----|-----|----|----|---|---|
     *
     * @param n
     * @return
     */
    private static String strValueOf(int n) {
        String ret = " ";

        switch (n) {
            case 1000:
                ret = "M";
                break;
            case 500:
                ret = "D";
                break;
            case 100:
                ret = "C";
                break;
            case 50:
                ret = "L";
                break;
            case 10:
                ret = "X";
                break;
            case 5:
                ret = "V";
                break;
            case 1:
                ret = "I";
                break;
        }
        return ret;
    }
}
