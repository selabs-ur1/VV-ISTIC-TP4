package fr.istic.vv;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RomanNumeraUtils {

	private static final Map<Character, Integer> romanMap = new HashMap<Character, Integer>() {
		{
			put('I', 1);
			put('V', 5);
			put('X', 10);
			put('L', 50);
			put('C', 100);
			put('D', 500);
			put('M', 1000);
		}
	};

	private static final Set<Character> allowedChar = new HashSet<Character>() {
		{
			add('I');
			add('V');
			add('X');
			add('L');
			add('C');
			add('D');
			add('M');
		}
	};

	private static final Set<Character> allowedToRepeat = new HashSet<Character>() {
		{
			add('I');
			add('X');
			add('C');
			add('M');
		}
	};

	private static final Map<Character, Set<Character>> allowedSubtractions = new HashMap<Character, Set<Character>>() {
		{
			put('I', new HashSet<>(Set.of('V', 'X')));
			put('X', new HashSet<>(Set.of('L', 'C')));
			put('C', new HashSet<>(Set.of('D', 'M')));
		}
	};

	private static final int MAX_REP = 3;

	/*
	 * Précondition : arr ne contient que des caractères romains valides
	 */
	private static boolean validSubtractions(char[] arr) {
		char currentValue, rightValue;

		for (int current = 0; current < arr.length - 1; current++) {
			currentValue = arr[current];
			rightValue = arr[current + 1];

			// detection dune soustraction
			if (romanMap.get(currentValue) < romanMap.get(rightValue) && (!allowedSubtractions.containsKey(currentValue)
					|| !allowedSubtractions.get(currentValue).contains(rightValue)))
				return false;
		}

		return true;
	}

	/*
	 * Detection de validité dune chaine, en appliquant nos règles, dans lordre
	 */
	public static boolean isValidRomanNumeral(String value) {
		if(value == null || value.isEmpty())
			return false;
		
		char[] toArr = value.toCharArray();
		
		return containsOnlyAllowedChar(toArr) && validRepetitions(toArr) && validSubtractions(toArr);
	}

	private static boolean containsOnlyAllowedChar(char[] arr) {
		for (int i = 0; i < arr.length; i++)
			if (!allowedChar.contains(arr[i]))
				return false;

		return true;
	}

	/*
	 * Précondition : arr ne contient que des caractères romains valides
	 */
	public static boolean validRepetitions(char[] arr) {
		int current;
		int previous = 0;

		for (current = 0; current < arr.length; current++) {

			if (arr[current] != arr[previous])
				previous = current;
			else if (!allowedToRepeat.contains(arr[current]) || current - previous >= MAX_REP)
				return false;
		}
		return true;
	}

	public static int parseRomanNumeral(String numeral) {

		/*
		 * Verif not null & valid chaine
		 */
		int result = 0;

		char[] numArr = numeral.toCharArray();
		int xCurrent = numArr.length - 1;

		/*
		 * xCurrent > 0 <=> on peut lire a gauche du courant pour decider quoi faire on
		 * a besoin dun caractère de prélecture pour la soustraction
		 */
		while (xCurrent > 0) {
			int currentVal = romanMap.get(numArr[xCurrent]);
			int leftVal = romanMap.get(numArr[xCurrent - 1]);

			if (leftVal >= currentVal) {
				result += currentVal;
				xCurrent--;
			} else {
				result += currentVal - leftVal;
				xCurrent -= 2;
			}
		}
		// Pour le cas ou on arrive sur le dernier caractère, ou chaine de taille 1
		// la boucle ne permet pas de gerer le dernier caractère car elle utilise un
		// caractère de prélecture
		if (xCurrent == 0) {
			result += romanMap.get(numArr[xCurrent]);
		}

		return result;
	}
	
	private static final int[] orderedValues = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
	private static final String[] orderedSymbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

	public static String toRomanNumeral(int number) {
		String result = "";
		if(number <= 0 || number >= 4000)
			return "";
		
		int index = 0;
		while(number > 0 && index < orderedValues.length) {
			if(number >= orderedValues[index]) {
				number -= orderedValues[index];
				result += orderedSymbols[index];
			}
			else
				index++;//number netait pas reprensable avec le symbole courant, on passe au symbole inferieur
		}
		return result;
	}

}
