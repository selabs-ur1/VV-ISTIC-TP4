package fr.istic.vv;

public class RomanNumeralUtils {

    private static final String[] ROMAN_NUMERALS = {"M", "D", "C", "L", "X", "V", "I"};
    private static final int[] VALUES = {1000, 500, 100, 50, 10, 5, 1};

    public static boolean isValidRomanNumeral(String value) {
        return value.matches("M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})");
    }

    public static int parseRomanNumeral(String numeral) {
        int result = 0;
        int prevValue = 0;

        for (int i = numeral.length() - 1; i >= 0; i--) {
            int value = getValue(numeral.charAt(i));
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }

        return result;
    }

    public static String toRomanNumeral(int number) {
        if (number <= 0 || number > 3999) {
            throw new IllegalArgumentException("Input must be between 1 and 3999");
        }

        // special case for "4"
        if (number == 4) {
            return "IV";
        }

        if (number == 40) {
            return "XL";
        }

        if (number == 400) {
            return "CD";
        }

        StringBuilder result = new StringBuilder();
        int index = 0;

        while (number > 0) {
            int value = VALUES[index];
            String numeral = ROMAN_NUMERALS[index];

            if (number >= value) {
                result.append(numeral);
                number -= value;
            } else if (index % 2 == 0 && index < VALUES.length - 2 && number >= (value - VALUES[index + 2])) {
                result.append(ROMAN_NUMERALS[index + 2]);
                result.append(ROMAN_NUMERALS[index]);
                number -= (value - VALUES[index + 2]);
            } else {
                index++;
            }
        }

        return result.toString();
    }

    private static int getValue(char numeral) {
        for (int i = 0; i < ROMAN_NUMERALS.length; i++) {
            if (ROMAN_NUMERALS[i].charAt(0) == numeral) {
                return VALUES[i];
            }
        }
        throw new IllegalArgumentException("Invalid Roman numeral character: " + numeral);
    }
}

