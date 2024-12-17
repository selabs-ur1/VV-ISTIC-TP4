package fr.istic.vv;

import net.jqwik.api.*;
import java.util.Arrays;
import java.util.Comparator;

class SortingTests {

    @Property
    boolean differentialFuzzingTest(@ForAll("randomArrays") Integer[] array) {
        Comparator<Integer> comparator = Integer::compare;

        // Clone the input array to ensure each algorithm starts with the same data
        Integer[] bubbleSorted = Sorting.bubblesort(array.clone(), comparator);
        Integer[] quickSorted = Sorting.quicksort(array.clone(), comparator);
        Integer[] mergeSorted = Sorting.mergesort(array.clone(), comparator);

        // All three algorithms should produce the same result
        return Arrays.equals(bubbleSorted, quickSorted) && Arrays.equals(quickSorted, mergeSorted);
    }

    @Provide
    Arbitrary<Integer[]> randomArrays() {
        return Arbitraries.integers()
                .between(-1000, 1000) // Random values between -1000 and 1000
                .array(Integer[].class)
                .ofMinSize(1)          // Minimum array size of 1
                .ofMaxSize(100);       // Maximum array size of 100
    }
}
