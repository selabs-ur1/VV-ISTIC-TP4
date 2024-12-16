package fr.istic.vv;

import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {
    // Faux car INTEGER_MIN_VALUE ne remplit pas les conditions
//    @Property
//    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
//        return Math.abs(anInteger) >= 0;
//    }

    @Provide
    Arbitrary<Integer[]> integerArrays() {
        return Arbitraries.integers()
                .array(Integer[].class)
                .ofMinSize(1).ofMaxSize(1_000);
    }

    @Property
    boolean all_sorting_sorted_correctly(@ForAll("integerArrays") Integer[] array) {
        Comparator<Integer> comparator = Integer::compareTo; // Il n'est pas dit que nous devions implémenter le comparateur donc autant prendre
        // celui de java pour éviter des bugs supplémentaire

        Integer[] bubbleSorted = Sorting.bubblesort(array.clone(), comparator);
        Integer[] quickSorted = Sorting.quicksort(array.clone(), comparator);
        Integer[] mergeSorted = Sorting.mergesort(array.clone(), comparator);

        Arrays.sort(array, comparator);

        return Arrays.equals(array, bubbleSorted) &&
               Arrays.equals(array, quickSorted) &&
               Arrays.equals(array, mergeSorted);

    }
}
