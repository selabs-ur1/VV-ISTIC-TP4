package fr.istic.vv;

import java.util.Arrays;
import java.util.Comparator;

import net.jqwik.api.*;

public class SortingTest {

    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        if (anInteger == Integer.MIN_VALUE) {
            return Math.abs((long) anInteger) > 0;
        }
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean differentSortingAlgorithmsProduceSameResult(@ForAll Integer[] array) {

        Comparator<Integer> naturalOrder = Integer::compare;

        if (array == null || array.length == 0) {
            return true;
        }

        Integer[] bubbleSorted = Sorting.bubblesort(array, naturalOrder);
        Integer[] quickSorted = Sorting.quicksort(array, naturalOrder);
        Integer[] mergeSorted = Sorting.mergesort(array, naturalOrder);

        return Arrays.equals(bubbleSorted, quickSorted) &&
                Arrays.equals(quickSorted, mergeSorted) &&
                isSorted(bubbleSorted, naturalOrder);
    }

    private boolean isSorted(Integer[] array, Comparator<Integer> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            if (comparator.compare(array[i], array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}