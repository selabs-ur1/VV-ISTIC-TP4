package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.assertj.core.api.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RomanNumeralTest {
    private static final ArrayList<Character> ROMAN_NUMERALS = new ArrayList<>(
            Arrays.asList('M', 'D', 'C', 'L', 'X', 'V', 'I')
    );

    @Property
    void testValidRomanNumerals(@ForAll("validRomanNumerals") String roman) {
        assertThat(RomanNumeralUtils.isValidRomanNumeral(roman)).isTrue();
    }

    @Property
    void testInvalidRomanNumerals(@ForAll("invalidRomanNumerals") String roman) {
        assertThat(RomanNumeralUtils.isValidRomanNumeral(roman)).isFalse();
    }

    @Property
    void testRomanToInteger(@ForAll("romanToValue") SimpleEntry<String, Integer> romanToValue){
        assertThat(RomanNumeralUtils.parseRomanNumeral(romanToValue.getKey())).isEqualTo(romanToValue.getValue());
    }


    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min=Integer.MIN_VALUE +1) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean incorrectIfNonRomanNumerals(@ForAll String roman){
        for(char c:roman.toCharArray()){
            if(!ROMAN_NUMERALS.contains(c)){
                return false;
            }
        }
        return true;
    }



    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XVII", "XXIV", "XXXVI", "XL", "XLI", "XLIX", "L", "LXXI", "XC", "XCV", "C", "CVI", "CLXII", "CD", "CDII", "D", "DIX", "CM", "CMVI", "M", "MD", "MMCCLXXXIX");
    }

    @Provide
    Arbitrary<String> invalidRomanNumerals() {
        return Arbitraries.of("IIII", "VV", "XXXX", "LL", "DD", "IC", "IL", "VC", "MMMM", "XIIII");
    }

    @Provide
    Arbitrary<SimpleEntry<String, Integer>> romanToValue(){
        return Arbitraries.of(
                new SimpleEntry<>("I", 1),
                new SimpleEntry<>("IV", 4),
                new SimpleEntry<>("V", 5),
                new SimpleEntry<>("IX", 9),
                new SimpleEntry<>("X", 10),
                new SimpleEntry<>("XL", 40),
                new SimpleEntry<>("L", 50),
                new SimpleEntry<>("XC", 90),
                new SimpleEntry<>("C", 100),
                new SimpleEntry<>("CD", 400),
                new SimpleEntry<>("D", 500),
                new SimpleEntry<>("CM", 900),
                new SimpleEntry<>("CMXIV", 914),
                new SimpleEntry<>("M", 1000),
                new SimpleEntry<>("MMXXV", 2025),
                new SimpleEntry<>("MMCCLXXXIX", 2289)
        );
    }

    @Provide
    Arbitrary<Integer> validIntegerRange() {
        return Arbitraries.integers().between(1, 3999);
    }
}
