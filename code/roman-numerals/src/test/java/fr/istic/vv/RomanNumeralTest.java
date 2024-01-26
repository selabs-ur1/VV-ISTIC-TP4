package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.*;


public class RomanNumeralTest {
    /**
     * False, see heap
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }
     */

    @Property
    boolean validNumeral(@ForAll @IntRange(min = 1, max = 3999) int number){
        return RomanNumeralUtils.isValidRomanNumeral(RomanNumeralUtils.toRomanNumeral(number));
    }

    @Property
    boolean sameForDoubleParse(@ForAll @IntRange(min = 1, max = 3999) int number){
        return number == RomanNumeralUtils.parseRomanNumeral(RomanNumeralUtils.toRomanNumeral(number));
    }
}
