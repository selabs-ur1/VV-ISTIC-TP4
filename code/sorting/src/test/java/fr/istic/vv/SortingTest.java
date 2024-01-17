package fr.istic.vv;
import org.junit.jupiter.api.Test;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

public class SortingTest {
	/*
	 * differential fuzzing strategy : on cherche a detecter des divergences dans les 3 implementations
	 */
	
	/*
	 * Cette propriété à permis de trouver un comportement différent pour le tableau vide en entrée
	 */
	/*
	 * Apres verification non, la propriété utilisait equals, sans doute pas appropriée
	 * return bubble.equals(merge) && bubble.equals(quick) && merge.equals(quick);
	 */
	@Property
	boolean allImplShouldGiveTheSameResult(@ForAll("integerArrayGenerator") Integer[] array) {
		Integer[] bubble = Sorting.bubblesort(array, new IntegerComparatorNaturalOrder());
		Integer[] quick = Sorting.quicksort(array, new IntegerComparatorNaturalOrder());
		Integer[] merge = Sorting.mergesort(array, new IntegerComparatorNaturalOrder());
		
		if(bubble.length != quick.length || bubble.length != merge.length || merge.length != quick.length)
			return false;
		
		for(int i=0 ; i<bubble.length ; i++)
			if(bubble[i] != quick[i] || bubble[i] != merge[i] || merge[i] != quick[i])
				return false;
		
		return true;
	}
	
	/*
	 * testSameResultX decoulent de contre exemple trouvé sur allImplShouldGiveTheSameResult
	 * pas vraiment des test, c'etait surtout pour visualiser les différences
	 */
	/*
	@Test
	public void testSameResult1() {
		Integer[] array = {102662, -416, -12105, 7, -3312, 13, -1, -1943, -7, -6, -42102, 7, -2111031316, 167};
		Integer[] bubble = Sorting.bubblesort(array, new IntegerComparatorNaturalOrder());
		Integer[] quick = Sorting.quicksort(array, new IntegerComparatorNaturalOrder());
		Integer[] merge = Sorting.mergesort(array, new IntegerComparatorNaturalOrder());
		System.out.println("\nBUBBLE : ");
		printArr(bubble);
		System.out.println("\nQUICK : ");
		printArr(quick);
		System.out.println("\nMERGE : ");
		printArr(merge);
	}
	
	@Test
	public void testSameResult2() {
		Integer[] array = {};
		Integer[] bubble = Sorting.bubblesort(array, new IntegerComparatorNaturalOrder());
		Integer[] quick = Sorting.quicksort(array, new IntegerComparatorNaturalOrder());
		Integer[] merge = Sorting.mergesort(array, new IntegerComparatorNaturalOrder());
		System.out.println("\nBUBBLE : ");
		printArr(bubble);
		System.out.println("\nQUICK : ");
		printArr(quick);
		System.out.println("\nMERGE : ");
		printArr(merge);
	}
	*/
	/*
	@Test
	public void testSameResult3() {
		Integer[] array = {128,128};
		Integer[] bubble = Sorting.bubblesort(array, new IntegerComparatorNaturalOrder());
		Integer[] quick = Sorting.quicksort(array, new IntegerComparatorNaturalOrder());
		Integer[] merge = Sorting.mergesort(array, new IntegerComparatorNaturalOrder());
		System.out.println("\nBUBBLE : ");
		printArr(bubble);
		System.out.println("\nQUICK : ");
		printArr(quick);
		System.out.println("\nMERGE : ");
		printArr(merge);
	}*/
	
	private void printArr(Integer[]arr) {
		for(int i=0 ; i<arr.length ; i++)
			System.err.print( "|" + arr[i]);
	}
	
	/*
	 * Quelques propriétés pour tester les implementation une à une
	 */
    @Property
    boolean bubbleSortShouldSort(@ForAll("integerArrayGenerator") Integer[] array) {
    	Integer[] sorted = Sorting.bubblesort(array, new IntegerComparatorNaturalOrder());
    	return isSorted(sorted);
    }
    
    /*
     * Apres une premiere execution pour cette propriété, un contre exemple  est directement trouvé :
     * [2147483626, -22]
     * 
     * le probleme venait de notre implementation de comparateur qui ne prenait pas en compte les depassements sur Integer
     * Premier impl naive : 
     	@Override
		public int compare(Integer x1, Integer x2) {
		return x1-x2;
		}
		
		On aurait souhaité compare(2147483626, -22) > 0
		Mais 2147483626 - (-22) = 0 , soit INT_MAX + 1(dépassement)
     */
    @Property
    boolean mergeSortShouldSort(@ForAll("integerArrayGenerator") Integer[] array) {
    	
    	Integer[] sorted = Sorting.mergesort(array, new IntegerComparatorNaturalOrder());
    	return isSorted(sorted);
    }
    
    @Property
    boolean quickSortShouldSort(@ForAll("integerArrayGenerator") Integer[] array) {
    	Integer[] sorted = Sorting.quicksort(array, new IntegerComparatorNaturalOrder());
    	return isSorted(sorted);
    }
   
    
    /*
     * Test pour un contre exemple fourni lors de lexecution de la regle sur quickSort
     * Il y avait une erreur dans organiseAroundPivot
     * Cela se produidait lorsque le pivot etait plus petit que toutes les valeur du sous tableau à partitionner
     */
    /*@Test
    public void erreurPivot() {
    	Integer[] arr = {0, -2, -1};
    	Integer[] sorted = Sorting.quicksort(arr, new IntegerComparatorNaturalOrder());
    	for(int i=0 ; i<sorted.length ; i++)
    		System.err.print(" | " + sorted[i]);  //[0, 1, 0]
    }*/
    /*
     * Apres reflexion notre impl de partitionnement se met a gerer trop de cas particulier, on va regarder la bonne maniere de faire sur wiki
     * il suffisait de penser a faire commencer une place temporaire de pivot a begin-1 ...
     */
    
    @Provide
    Arbitrary<Integer[]> integerArrayGenerator() {
    	
        return Arbitraries.integers().array(Integer[].class).ofMinSize(0).ofMaxSize(20);
    }
    
    /*
     * On se focalise sur des tableau dentiers pour faciliter la generation
     */
    boolean isSorted(Integer[] arr) {
    	if(arr.length <= 1)
    		return true;
    	for(int i=0 ; i<arr.length-1 ; i++)
    		if(arr[i] > arr[i+1])
    			return false;
    	return true;
    }
}