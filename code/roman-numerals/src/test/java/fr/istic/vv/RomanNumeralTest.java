package fr.istic.vv;

import org.junit.jupiter.api.Test;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

public class RomanNumeralTest {

	/*private static final Map<String, Integer> testValues = new HashMap<String, Integer>() {
		{
			put("I", 1);
			put("IV", 4);
			put("VIII", 8);
			put("IX", 9);
			put("XIV", 14);
			put("XVI", 16);
			put("XIX", 19);
			put("XCIX", 99);
			put("CV", 105);
			put("MI", 1001);
			put("MMCCLXXXIX", 2289);
		}
	};

	@Test
	public void testConversionStringToInt() {
		for (String str : testValues.keySet())
			assertEquals(testValues.get(str), RomanNumeraUtils.parseRomanNumeral(str));
	}

	@Test
	public void testValidRepetition1() {
		String validRep = "I";
		assertTrue(RomanNumeraUtils.validRepetitions(validRep.toCharArray()));
	}

	@Test
	public void testValidRepetition2() {
		String validRep = "MMCCLXXXIX";
		assertTrue(RomanNumeraUtils.validRepetitions(validRep.toCharArray()));
	}

	@Test
	public void testValidRepetition3() {
		String invalidRep = "DD";
		assertFalse(RomanNumeraUtils.validRepetitions(invalidRep.toCharArray()));
	}

	@Test
	public void testValidRepetition4() {
		String invalidRep = "MMCCLXXXXIX";
		assertFalse(RomanNumeraUtils.validRepetitions(invalidRep.toCharArray()));
	}
	
	@Test
	public void testValidRepetition5() {
		String invalidRep = "MMCCLLXXXIX";
		assertFalse(RomanNumeraUtils.validRepetitions(invalidRep.toCharArray()));
	}*/

	
	/*@Property boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger)
	{ return Math.abs(anInteger) >= 0; }*/
	
	/*
	 * Test pour des chaines completment random, la propriété ne devrait quasiment jamais etre satisfaite
	 */
	/*@Property
    boolean completelyRandomStringSouldAlmostNeverBeValid(@ForAll String numeral) {
		//System.err.println(numeral);
        return !RomanNumeraUtils.isValidRomanNumeral(numeral);
    }*/
	
	/*
	 * Test pour des chaines contenant uniquement les caractère romain, mais dans un ordre aleatoire
	 */
	@Property
    boolean stringContainingRomanCharCouldBeValid(@ForAll("stringWithRomanCharacters") String numeral) {
		boolean valid = RomanNumeraUtils.isValidRomanNumeral(numeral);
	
		Assume.that(valid);//commenter cette ligne pour faire ressortie des exemple de respectant pas le propriété
		
        return valid;
    }
	
	@Provide
	Arbitrary<String> stringWithRomanCharacters(){
		return Arbitraries.strings().ofMinLength(2).ofMaxLength(5).withChars('I','V','X','L','C','D','M');
	}
	
	@Property
	boolean intToRomanToIntShouldGiveTheSame(@ForAll("romanInterval") int value) {
		String toRoman = RomanNumeraUtils.toRomanNumeral(value);
		int toInt = RomanNumeraUtils.parseRomanNumeral(toRoman);
		return value == toInt;
	}
	
	@Provide
	Arbitrary<Integer> romanInterval(){
		return Arbitraries.integers().between(1, 3999);
	}
	
	/*
	 * jqwik a trouvé un contre exemple pour 400 concernant la propriété intToRomanToIntShouldGiveTheSame
	 */
	/*@Test
	public void testFor400() {
		String toRoman = RomanNumeraUtils.toRomanNumeral(400);
		System.err.println(toRoman);
		int backToInt = RomanNumeraUtils.parseRomanNumeral(toRoman);
		System.err.println(backToInt);
	}*/
	
	//Bon cetait une erreur bete, javais écris dans toRomanNumeral 
	/*
	 * if(number <= 0 || number >= 400)
			return "";
			
	Au lieu de 
	if(number <= 0 || number >= 4000)
			return "";
	 */
	
	

	 

}
