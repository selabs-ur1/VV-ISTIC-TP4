package fr.istic.vv;

import java.util.Arrays;
import java.util.List;

public class RomanNumeraUtils {
    
        private static List<Character> validCharacters = Arrays.asList(new Character[]{'M', 'D', 'C', 'L', 'X', 'V', 'I'});
        private static List<String> illegalGroups = Arrays.asList(new String[]{"MMMM", "CCCC", "XXXX", "IIII"});
        private static List<String> cannotBeRepeated = Arrays.asList(new String[]{"DD", "LL", "VV"});

        public static boolean isValidRomanNumeral(String value) {
                return hasOnlyValidChars(value) &&
                hasNoGroupsOfFourConsecutives(value) &&
                hasNoIllegallyRepeatingSymbols(value);
        }
    
        public static int parseRomanNumeral(String numeral) { return 0; }
    
        public static String toRomanNumeral(int number) { return ""; }

        private static boolean hasOnlyValidChars(String value) {
                char[] cArray = value.toUpperCase().toCharArray();
                for(char c: cArray) {
                        if (!validCharacters.contains(c)) return false;
                }
                return true;
        }

        private static boolean hasNoGroupsOfFourConsecutives(String value) {
                return illegalGroups.stream().noneMatch((g) -> { return value.contains(g); });
        }

        private static boolean hasNoIllegallyRepeatingSymbols(String value) {
                return cannotBeRepeated.stream().noneMatch((c) -> { return value.contains(c); });
        }
    
}
