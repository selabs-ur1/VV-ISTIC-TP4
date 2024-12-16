package fr.istic.vv;

import java.util.Objects;
import java.util.Optional;

public class RomanNumeraUtils {

    public static final int MAX_NUMERAL_VALUE = 3999;

    public static boolean isValidRomanNumeral(String value) {
        Objects.requireNonNull(value);

        final String acceptedDigits = "MDCLXVI";    // Accepted symbols.
        final String noRepeatables = "DLV";         // Non-repeatable digits.
        final String substractables = "LCXV";

        // Counter of consecutive digits.
        int consecutive = 1;
        Optional<Character> prevDigit = Optional.empty();

        for (char digit : value.toCharArray()) {

            // Invalid character
            if (acceptedDigits.indexOf(digit) == -1)
                return false;

            // Reset the consecutive counter if the digit is different of the previous.
            if (prevDigit.isPresent() && prevDigit.get() != digit)
                consecutive = 1;

            // Check if the current number of successive digits is correct.
            if ((noRepeatables.indexOf(digit) != -1 && consecutive > 1) || consecutive > 3)
                return false;   // No more than one successive occurence of D, L, or V.

            // Ensure that only substractable digits admit an inferior digit before them.
            // Such digit must be 1/5 or 1/10 of the next digit.
            if (prevDigit.isPresent()) {
                char previous = prevDigit.get();

                int prevValue = parseRomanDigit(previous);
                int nextValue = parseRomanDigit(digit);
                if (prevValue < nextValue && (substractables.indexOf(digit) == -1 &&
                        (nextValue != 5 * prevValue && nextValue != 10 * prevValue)))
                    return false;
            }

            consecutive++;
            prevDigit = Optional.of(digit);
        }

        return true;
    }

    public static int parseRomanNumeral(String numeral) {
        Objects.requireNonNull(numeral);

        // Check the validity of the numeral.
        if (!isValidRomanNumeral(numeral))
            throw new NumberFormatException(
                    String.format("%s is not a valid roman numeral", numeral));

        int result = 0;

        // Previous number, possibly consumed by being subtracted from another digit.
        Optional<Integer> prevValueOpt = Optional.empty();

        // Iterating over the characters of the numeral.
        for (char current : numeral.toCharArray()) {
            int currValue = parseRomanDigit(current);


            if (prevValueOpt.isEmpty())
                // If no previous : fill it.
                prevValueOpt = Optional.of(currValue);
            else {
                // If previous : check it can be subtracted.
                // No need to check if the number is already substractable, due to the fact
                // we are sure that numeral is already valid.
                int prevValue = prevValueOpt.get();
                if (prevValue < currValue) {
                    result += currValue - prevValue;
                    prevValueOpt = Optional.empty();
                } else {
                    result += prevValue;
                    prevValueOpt = Optional.of(currValue);
                }
            }
        }

        // Adding the previous value if we reached the end without finding a substractable
        // number.
        result += prevValueOpt.orElse(0);

        return result;
    }

    public static String toRomanNumeral(int number) {
        if (number < 0 || number > RomanNumeraUtils.MAX_NUMERAL_VALUE)
            throw new NumberFormatException(
                    String.format("number should be included between 0 and number %d.",
                            MAX_NUMERAL_VALUE));

        final String digits = "MDCLXVI";

        StringBuilder builder = new StringBuilder();

        // Dividing from 10 to 10 number in order to retrieve units.
        int unit = 1000;
        int digitIdx = 0;
        int n = number;
        while (n != 0) {
            int currDigitVal = n / unit;

            // Current's digit associated to the value.
            char currDigit = digits.charAt(digitIdx);

            // Next bigger digit, useful if we need to do a subtraction.
            int supDigit = digitIdx - 1;

            if (currDigitVal == 9) {
                // 9th unit : write currDigit, then next of the next digit (e.g. IX = 9).
                builder.append(currDigit)
                        .append(digits.charAt(supDigit - 1));
            } else if (currDigitVal >= 5) {
                // Units from 5 to 8 : write next digit, then currDigit (e.g. VI = 6...)
                builder.append(digits.charAt(supDigit))
                        .append(String.valueOf(currDigit)
                                .repeat(currDigitVal - 5));
            } else if (currDigitVal > 3) {
                // 4-th unit : currDigit, then next digit (e.g. IV = 4).
                builder.append(currDigit)
                        .append(digits.charAt(supDigit));
            } else {
                // Units inferior to 3 : only repeat currDigit (e.g. I, II, III...)
                builder.append(String.valueOf(currDigit)
                        .repeat(currDigitVal));
            }

            n = n % unit;
            unit /= 10;
            digitIdx += 2;
        }

        return builder.toString();
    }

    private static int parseRomanDigit(char digit) {
        switch (digit) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new IllegalArgumentException(String.format("invalid digit %c", digit));
        }
    }

}
