package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {

    @Provide
    Arbitrary<Integer[]> integerArrays() {
        return Arbitraries.integers()
                .array(Integer[].class)
                .ofSize(10);
    }

    @Property
    void testSorting(@ForAll("integerArrays") Integer[] originalArray) {
        Comparator<Integer> comparator = Integer::compare;

        Integer[] bubbleSort = Arrays.copyOf(originalArray, originalArray.length);
        Integer[] quickSort = Arrays.copyOf(originalArray, originalArray.length);
        Integer[] mergeSort = Arrays.copyOf(originalArray, originalArray.length);

        bubbleSort = Sorting.bubblesort(bubbleSort, comparator);
        quickSort = Sorting.quicksort(quickSort, comparator);
        mergeSort = Sorting.mergesort(mergeSort, comparator);

        System.out.println("Original: " + Arrays.toString(originalArray) + "\nBubbleSort: " + Arrays.toString(bubbleSort) + "\nQuickSort: " + Arrays.toString(quickSort) + "\nMergeSort: " + Arrays.toString(mergeSort));
        assert Arrays.equals(bubbleSort, quickSort) : "QuickSort et BubbleSort sont différents";
        assert Arrays.equals(bubbleSort, mergeSort) : "MergeSort et BubbleSort sont différents";
    }
}