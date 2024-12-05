package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min=Integer.MIN_VALUE +1) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }
}
