package fr.istic.vv;
import net.jqwik.api.*;
import java.util.Random;

/*
--Seuls les symboles M , D , C , X , V , I peuvent être utilisés. Leurs valeurs sont indiquées ci-dessous :
M	  D	  C	  L	 X	 V	I
1000 500 100 50	 10	 5	1

--Les symboles M, C, X, I peuvent être répétés consécutivement jusqu'à trois fois.

--Les symboles D, L et V ne peuvent pas être répétés.

--Lorsqu'un symbole de valeur inférieure apparaît à droite d'un symbole de valeur égale ou supérieure, toutes les valeurs
des symboles sont additionnées.

--Lorsqu'un symbole de valeur inférieure apparaît à gauche d'un symbole de valeur supérieure, la valeur inférieure est
soustraite de la valeur supérieure. Seuls les symboles C, X, V peuvent être soustraits.
Chaque symbole ne peut être soustrait qu'une seule fois. Le symbole soustrait doit être un cinquième ou un dixième du plus grand.
 */

public class RomanNumeralTest {


    @Property
    boolean simpleOrderSymbols(@ForAll("validSimpleRomanNumerals") String numeralRoman){
        return RomanNumeraUtils.isValidRomanNumeral(numeralRoman);
    }

    @Property
    boolean complexeOrderSymbols(@ForAll("validComplexRomanNumerals") String numeralRoman){
        return RomanNumeraUtils.isValidRomanNumeral(numeralRoman);
    }

    @Provide
    Arbitrary<String> validSimpleRomanNumerals(){
        return Arbitraries.of(generateSimpleNumeralsList());
    }

    @Provide
    Arbitrary<String> validComplexRomanNumerals(){
        return Arbitraries.of(generateComplexeNumeralsList());
    }

    private String generateSimpleNumeralsList(){
        StringBuilder numeralRoman = new StringBuilder();
        Random random = new Random();
        addRomanUnit(numeralRoman, "M", random.nextInt(4));
        addRomanUnit(numeralRoman, "D", random.nextInt(2));
        addRomanUnit(numeralRoman, "C", random.nextInt(4));
        addRomanUnit(numeralRoman, "L", random.nextInt(2));
        addRomanUnit(numeralRoman, "X", random.nextInt(4));
        addRomanUnit(numeralRoman, "V", random.nextInt(2));
        addRomanUnit(numeralRoman, "I", random.nextInt(4));
        return numeralRoman.toString();
    }

    private String generateComplexeNumeralsList(){
        StringBuilder numeralRoman = new StringBuilder();
        Random random = new Random();

        addRomanUnitComplexe(numeralRoman, "M", random.nextInt(4), true);
        int cm = random.nextInt(2);
        addRomanUnitComplexe(numeralRoman, "CM", cm, true);
        int d = random.nextInt(2);
        addRomanUnitComplexe(numeralRoman, "D", d, cm == 0);
        int cd = random.nextInt(2);
        addRomanUnitComplexe(numeralRoman, "CD", cd, d == 0);
        int c = random.nextInt(2);
        addRomanUnitComplexe(numeralRoman, "C", c, cd == 0 && cm == 0);
        int xc = random.nextInt(2);
        addRomanUnitComplexe(numeralRoman, "XC", xc, c == 0);
        int x = random.nextInt(4);
        addRomanUnitComplexe(numeralRoman, "X", x, xc == 0 && c == 0);
        int ix = random.nextInt(2);
        addRomanUnitComplexe(numeralRoman, "IX", ix, x == 0);
        int v = random.nextInt(2);
        addRomanUnitComplexe(numeralRoman, "V", v, ix == 0);
        int i = random.nextInt(4);
        addRomanUnitComplexe(numeralRoman, "I", i, ix == 0 && v == 0);

        return numeralRoman.toString();
    }

    private void addRomanUnit(StringBuilder numeralRoman , String single, int count) {
        for(int i = 0; i < count; i++){
            numeralRoman.append(single);
        }
    }

    private void addRomanUnitComplexe(StringBuilder numeralRoman , String single, int count, boolean insere) {
        if(insere){
            for(int i = 0; i < count; i++){
                numeralRoman.append(single);
            }
        }
    }

}
