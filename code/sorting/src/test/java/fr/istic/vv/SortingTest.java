package fr.istic.vv;

import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean bubbleSortSortsArray(@ForAll Integer[] array) {
        Integer[] sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);
        Integer[] result = Sorting.bubblesort(array, Comparator.naturalOrder());
        return Arrays.equals(result, sortedArray);
    }

    @Property
    boolean quickSortSortsArray(@ForAll Integer[] array) {
        Integer[] sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);
        Integer[] result = Sorting.quicksort(array, Comparator.naturalOrder());
        return Arrays.equals(result, sortedArray);
    }

    @Property
    boolean mergeSortSortsArray(@ForAll Integer[] array) {
        Integer[] sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);
        Integer[] result = Sorting.mergesort(array, Comparator.naturalOrder());
        return Arrays.equals(result, sortedArray);
    }
}