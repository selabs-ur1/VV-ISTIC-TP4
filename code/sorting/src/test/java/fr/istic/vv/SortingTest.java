package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {

    private static <T> boolean isSorted(T[] array, Comparator<T> comparator) {
        for (int i = 1; i < array.length; i++) {
            if (comparator.compare(array[i - 1], array[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    @Property
    boolean bubbleSortWorksCorrectly(@ForAll int[] array) {
        Integer[] inputArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Comparator<Integer> comparator = Integer::compareTo;

        Integer[] sortedArray = Sorting.bubbleSort(inputArray, comparator);

        return isSorted(sortedArray, comparator);
    }

    @Property
    boolean quickSortWorksCorrectly(@ForAll int[] array) {
        Integer[] inputArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Comparator<Integer> comparator = Integer::compareTo;

        Sorting.quickSort(inputArray, comparator);

        return isSorted(inputArray, comparator);
    }

    @Property
    boolean mergeSortWorksCorrectly(@ForAll int[] array) {
        Integer[] inputArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Comparator<Integer> comparator = Integer::compareTo;

        Integer[] sortedArray = Sorting.mergeSort(inputArray, comparator);

        return isSorted(sortedArray, comparator);
    }

    @Property
    boolean allSortsReturnTheSameResult(@ForAll int[] array) {
        Integer[] inputArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Comparator<Integer> comparator = Integer::compareTo;

        Integer[] bubbleSorted = Sorting.bubbleSort(inputArray.clone(), comparator);
        Integer[] quickSorted = inputArray.clone();
        Sorting.quickSort(quickSorted, comparator);
        Integer[] mergeSorted = Sorting.mergeSort(inputArray.clone(), comparator);

        return Arrays.equals(bubbleSorted, quickSorted) && Arrays.equals(bubbleSorted, mergeSorted);
    }
}