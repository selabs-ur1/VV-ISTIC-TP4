package fr.istic.vv;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.jqwik.api.*;

public class RomanNumeralTest {

    @Provide
	Arbitrary<Integer> inBounds() {
		return Arbitraries.integers().between(1, 3999);
	}

    @Property
    boolean containsOnlyValidSymbols(@ForAll("inBounds") int i) throws IOException{
        List<Character> symbols = Arrays.asList('I', 'V', 'X', 'L', 'C', 'D', 'M');
        String romanNumeral = RomanNumeraUtils.toRomanNumeral(i);
        for (Character c : romanNumeral.toCharArray()) {
            if(!symbols.contains(c)){
                return false;
            }
        }
        return true;
    }

    @Property
    boolean symbolsCorrectlyRepeated(@ForAll("inBounds") int i) throws IOException{
        List<String> unvalidSequences = Arrays.asList("IIII", "VV", "XXXX", "LL", "CCCC", "DD", "MMMM");
        String romanNumeral = RomanNumeraUtils.toRomanNumeral(i);

        for (String sequence : unvalidSequences) {
            if(romanNumeral.contains(sequence)){
                return false;
            }
            
        }
  
        return true;
    }

    @Property
    boolean testIsValidRomanNumeral(@ForAll("inBounds") int i) throws IOException {
        return RomanNumeraUtils.isValidRomanNumeral(RomanNumeraUtils.toRomanNumeral(i));
    }

    @Property
    boolean testParseRomanNumeral(@ForAll("inBounds") int i) throws IOException {
        return i == RomanNumeraUtils.parseRomanNumeral(RomanNumeraUtils.toRomanNumeral(i));
    }
}
