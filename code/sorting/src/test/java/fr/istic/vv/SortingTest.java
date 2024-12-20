package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.jupiter.api.Assertions;

public class SortingTest {

    @Property
    void differentialFuzzingTest(@ForAll @Size(max = 100) Integer[] array) {
        Integer[] inputArray = array.clone();

        Integer[] sortedByMergeSort = Sorting.mergesort(inputArray.clone(), Comparator.naturalOrder());
        Integer[] sortedByQuickSort = Sorting.quicksort(inputArray.clone(), Comparator.naturalOrder());
        Integer[] sortedByBubbleSort = Sorting.bubblesort(inputArray.clone(), Comparator.naturalOrder());

        Arrays.sort(inputArray);

        Assertions.assertArrayEquals(inputArray, sortedByMergeSort, "MergeSort failed");
        Assertions.assertArrayEquals(inputArray, sortedByQuickSort, "QuickSort failed");
        Assertions.assertArrayEquals(inputArray, sortedByBubbleSort, "BubbleSort failed");
    }

    @Provide
    Arbitrary<Integer[]> arrays() {
        return Arbitraries.integers().array(Integer[].class).ofMinSize(0).ofMaxSize(100);
    }
}