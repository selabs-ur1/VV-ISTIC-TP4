package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortingTest {
    @Property
    void testAllSort(@ForAll @Size(min=1) Integer[] array) {
        Comparator<Integer> comparator = Integer::compare;

        Integer[] originalArray = Arrays.copyOf(array, array.length);
        Arrays.sort(originalArray, comparator);

        assertArrayEquals(originalArray, Sorting.quicksort(array,comparator));
        assertArrayEquals(originalArray, Sorting.mergesort(array,comparator));
        assertArrayEquals(originalArray, Sorting.bubblesort(array,comparator));
    }

    @Property
    void testBubbleSort(@ForAll @Size(min=1) Integer[] array) {
        Comparator<Integer> comparator = Integer::compare;

        Integer[] originalArray = Arrays.copyOf(array, array.length);
        Arrays.sort(originalArray, comparator);

        assertArrayEquals(originalArray, Sorting.bubblesort(array,comparator));
    }

    @Property
    void testMergeSort(@ForAll @Size(min=1) Integer[] array) {
        Comparator<Integer> comparator = Integer::compare;

        Integer[] originalArray = Arrays.copyOf(array, array.length);
        Arrays.sort(originalArray, comparator);

        assertArrayEquals(originalArray, Sorting.mergesort(array,comparator));
    }

    @Property
    void testQuickSort(@ForAll @Size(min=1) Integer[] array) {
        Comparator<Integer> comparator = Integer::compare;

        Integer[] originalArray = Arrays.copyOf(array, array.length);
        Arrays.sort(originalArray, comparator);

        assertArrayEquals(originalArray, Sorting.quicksort(array,comparator));
    }

}