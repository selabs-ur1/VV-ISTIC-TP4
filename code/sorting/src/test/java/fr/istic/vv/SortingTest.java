package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {
    // Why this test ?
    //@Property
    //boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
      //  return Math.abs(anInteger) >= 0;
    //}

    //Fuzing testing on multiple implementation
    @Property
    boolean sortingAlgorithmsProduceEquivalentResults(@ForAll("arrays") Integer[] array) {
        Comparator<Integer> comparator = Comparator.naturalOrder();

        Integer[] sortedBubble = Sorting.bubblesort(array.clone(), comparator);
        Integer[] sortedQuick = Sorting.quicksort(array.clone(), comparator);
        Integer[] sortedMerge = Sorting.mergesort(array.clone(), comparator);

        /* Uncomment to check the result manualy if needed
        System.out.println("Base table : " + Arrays.toString(array));
        System.out.println("Bubble : " + Arrays.toString(sortedBubble));
        System.out.println("Quick : " + Arrays.toString(sortedQuick));
        System.out.println("Merge : " + Arrays.toString(sortedMerge));
        */

        return Arrays.equals(sortedBubble, sortedQuick) && Arrays.equals(sortedBubble, sortedMerge);
    }

    // Provider of an array of Integer each intergers are between 0 and 100
    //The size of the arrays is beetween 0 and 30
    @Provide
    Arbitrary<Integer[]> arrays() {
        Arbitrary<Integer> integerArbitrary = Arbitraries.integers().between(0, 100);
        Arbitrary<Integer[]> arrayArbitrary = integerArbitrary.array(Integer[].class).ofMinSize(0).ofMaxSize(30);
        return arrayArbitrary;
    }

}