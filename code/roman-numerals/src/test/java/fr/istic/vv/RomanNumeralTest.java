package fr.istic.vv;
import jdk.jshell.execution.Util;
import net.jqwik.api.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static fr.istic.vv.RomanNumeraUtils.*;

public class RomanNumeralTest {

    String regex1  = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    String regex2 = "^(?=[MDCLXVI])M{0,3}(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$";

    List<String> all = Utils.readRomanNumeralsFromFile("src/test/java/fr/istic/vv/allRoman.txt");

    public RomanNumeralTest() throws IOException {
    }


    @Property
    boolean isValidAndRegexShouldBeConsistent(@ForAll("validRomanNumerals") String romanNumeral) {

        return isValidRomanNumeral(romanNumeral) == romanNumeral.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

    }
    @Property
    boolean testRandom(@ForAll String randomString){
        return isValidRomanNumeral(randomString) == randomString.matches(regex2);

    }
    @Property
    boolean testRandomNumerals(@ForAll("randomNumerals") String randomNumeral){
        return isValidRomanNumeral(randomNumeral) == randomNumeral.matches(regex2);

    }
    @Property
    boolean parsedValueShouldBeValid(@ForAll("allNumerals") String romanNumeral) {
        int parsedValue = parseRomanNumeral(romanNumeral);
        int realValue = all.indexOf(romanNumeral)+ 1;


        return isValidRomanNumeral(romanNumeral) && parsedValue == realValue;
    }
    @Property
    boolean validIntToRoman(@ForAll("allValues") int value) {
        String parsedValue = toRomanNumeral(value);
        String realRoman = all.get(value-1);


        return isValidRomanNumeral(parsedValue) && parsedValue.equals(realRoman);
    }
    @Provide
    Arbitrary<String> randomNumerals(){
        return Arbitraries.strings().withChars('I', 'V', 'X', 'L', 'C', 'D', 'M');

    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of("I", "V", "X", "L", "C", "D", "M", "II", "III", "IV", "IX", "XIV", "XX", "XXX", "XL", "XC", "CD", "CM", "MMMCMXCIX");
    }

    @Provide
    Arbitrary<String> allNumerals() throws IOException {
        return  Arbitraries.of(all);
    }
    @Provide
    Arbitrary<Integer> allValues() throws IOException {
        return  Arbitraries.integers().between(1,3999);
    }




}
