package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {
    @Property
    void allSortingAlgorithmsProduceSameResult(@ForAll @Size(min = 1, max = 100) Integer[] inputArray) {
        Comparator<Integer> comparator = Comparator.naturalOrder();

        Integer[] bubbleSorted = Sorting.bubblesort(Arrays.copyOf(inputArray, inputArray.length), comparator);
        Integer[] quickSorted = Sorting.quicksort(Arrays.copyOf(inputArray, inputArray.length), comparator);
        Integer[] mergeSorted = Sorting.mergesort(Arrays.copyOf(inputArray, inputArray.length), comparator);

        assert Arrays.equals(bubbleSorted, quickSorted) && Arrays.equals(quickSorted, mergeSorted);
    }
}