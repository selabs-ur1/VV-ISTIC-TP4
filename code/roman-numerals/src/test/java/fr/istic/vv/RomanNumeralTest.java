package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;

import static fr.istic.vv.RomanNumeraUtils.*;

public class RomanNumeralTest {
    @Property
    boolean toRomanNumeralProvideValidNumeral(@ForAll("positiveBelow3999") int anInteger){
        return isValidRomanNumeral(toRomanNumeral(anInteger));
    }

    @Property
    boolean integerToRomanNumeralToIntegerShouldBeTheSame(@ForAll("positiveBelow3999") int anInteger){
        return anInteger == parseRomanNumeral(toRomanNumeral(anInteger));
    }

    @Provide
    Arbitrary<Integer> positiveBelow3999() {
        return Arbitraries.integers().filter(v -> v>=0 && v<=3999);
    }

}
