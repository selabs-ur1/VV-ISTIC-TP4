package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {
    @Provide
    Arbitrary<Integer[]> randomIntArray() {
        return Arbitraries.integers().array(Integer[].class).ofMinSize(1).ofMaxSize(100);
    }

    @Property
    boolean bubbleSort(@ForAll("randomIntArray") Integer[] array) {
        Integer[] bubbleSorted = Sorting.bubblesort(array, Comparator.naturalOrder());
        Integer[] quickSorted = Sorting.quicksort(array, Comparator.naturalOrder());
        Integer[] mergedSorted = Sorting.quicksort(array, Comparator.naturalOrder());

        return Arrays.equals(bubbleSorted, quickSorted) && Arrays.equals(bubbleSorted, mergedSorted);
    }
}