package fr.istic.vv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumeraUtils {

        public static boolean isValidRomanNumeral(String value) {
                if (value == null) return false;
                if (value.isEmpty()) return false;

                long repeatV = value.chars().filter(c -> c == 'V').count();
                long repeatL = value.chars().filter(c -> c == 'L').count();
                long repeatD = value.chars().filter(c -> c == 'D').count();

                if (repeatV > 1 || repeatL > 1 || repeatD > 1) return false;

                Pattern pattern = Pattern.compile("[IXCM]{4,}");
                Matcher matcher = pattern.matcher(value);

                if (matcher.find()) return false;

                return true;
        }

        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) return -1;

                return 0;
        }

        public static String toRomanNumeral(int number) {
                return "";
        }

}
