package fr.istic.vv;

import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

class SortingTest {

    @Property
    void testSortingAlgorithms(
            @ForAll("randomIntArrays") Integer[] array
    ) {
        // Test Bubble Sort
        Integer[] bubbleSorted = Sorting.bubblesort(array.clone(), Comparator.naturalOrder());
        assertSorted(bubbleSorted);

        // Test Quick Sort
        Integer[] quickSorted = Sorting.quicksort(array.clone(), Comparator.naturalOrder());
        assertSorted(quickSorted);

        // Test Merge Sort
        Integer[] mergeSorted = Sorting.mergesort(array.clone(), Comparator.naturalOrder());
        assertSorted(mergeSorted);

        // Compare the results of all algorithms
        assert Arrays.equals(bubbleSorted, quickSorted) : "Bubble Sort and Quick Sort differ";
        assert Arrays.equals(bubbleSorted, mergeSorted) : "Bubble Sort and Merge Sort differ";
        assert Arrays.equals(quickSorted, mergeSorted) : "Quick Sort and Merge Sort differ";
    }

    private void assertSorted(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            assert array[i - 1] <= array[i] : "Array is not sorted";
        }
    }

    @Provide
    Arbitrary<Integer[]> randomIntArrays() {
        Arbitrary<Integer> integerArbitrary = Arbitraries.integers().between(1, 1000);
        return integerArbitrary.array(Integer[].class).ofMinSize(2).ofMaxSize(1000);
    }
}
