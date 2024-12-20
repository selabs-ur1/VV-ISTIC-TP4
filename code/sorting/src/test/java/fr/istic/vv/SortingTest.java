package fr.istic.vv;

import net.jqwik.api.*;
import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {

    private Comparator<Integer> cmp = Comparator.naturalOrder();

    private Integer[] refSort(Integer[] arr) {
        Integer[] copy = arr.clone();
        Arrays.sort(copy, cmp);
        return copy;
    }

    @Property
    boolean bubbleSortIsCorrect(@ForAll("arrays") Integer[] arr) {
        Integer[] expected = refSort(arr);
        Integer[] result = Sorting.bubblesort(arr, cmp);
        return Arrays.equals(expected, result);
    }

    @Property
    boolean quickSortIsCorrect(@ForAll("arrays") Integer[] arr) {
        Integer[] expected = refSort(arr);
        Integer[] result = Sorting.quicksort(arr, cmp);
        return Arrays.equals(expected, result);
    }

    @Property
    boolean mergeSortIsCorrect(@ForAll("arrays") Integer[] arr) {
        Integer[] expected = refSort(arr);
        Integer[] result = Sorting.mergesort(arr, cmp);
        return Arrays.equals(expected, result);
    }

    @Provide
    Arbitrary<Integer[]> arrays() {
        return Arbitraries.integers().between(-1000, 1000)
                .array(Integer[].class)
                .ofMinSize(0).ofMaxSize(100);
    }

    @Property
    boolean sortedArraysAreValid(@ForAll("arrays") Integer[] arr) {
        Integer[] bubble = Sorting.bubblesort(arr, cmp);
        if (!isSorted(bubble, cmp)) return false;

        Integer[] quick = Sorting.quicksort(arr, cmp);
        if (!isSorted(quick, cmp)) return false;

        Integer[] merge = Sorting.mergesort(arr, cmp);
        if (!isSorted(merge, cmp)) return false;

        return true;
    }

    private boolean isSorted(Integer[] arr, Comparator<Integer> cmp) {
        for (int i = 1; i < arr.length; i++) {
            if (cmp.compare(arr[i - 1], arr[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}