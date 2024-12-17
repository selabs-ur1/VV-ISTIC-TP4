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
    boolean bubble_sort_proper_result(@ForAll("integerArrays") Integer[] array) {
        Comparator<Integer> comparator = Integer::compareTo; // Il n'est pas dit que nous devions implémenter le comparateur donc autant prendre
        // celui de java pour éviter des bugs supplémentaire

        Integer[] bubbleSorted = Sorting.bubblesort(array.clone(), comparator);

        Arrays.sort(array, comparator);

        return Arrays.equals(array, bubbleSorted);
    }

    @Property
    boolean quick_sort_proper_result(@ForAll("integerArrays") Integer[] array) {
        Comparator<Integer> comparator = Integer::compareTo; // Il n'est pas dit que nous devions implémenter le comparateur donc autant prendre
        // celui de java pour éviter des bugs supplémentaire

        Integer[] quickSorted = Sorting.quicksort(array.clone(), comparator);

        Arrays.sort(array, comparator);

        return Arrays.equals(array, quickSorted);

    }

    @Property
    boolean merge_sort_proper_result(@ForAll("integerArrays") Integer[] array) {
        Comparator<Integer> comparator = Integer::compareTo; // Il n'est pas dit que nous devions implémenter le comparateur donc autant prendre
        // celui de java pour éviter des bugs supplémentaire

        Integer[] mergeSorted = Sorting.mergesort(array.clone(), comparator);

        Arrays.sort(array, comparator);

        return Arrays.equals(array, mergeSorted);

    }
}
