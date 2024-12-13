package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {

    Comparator<Integer> comparator = Integer::compareTo;
    Sorting sorting = new Sorting();

    @Property(tries = 1)
    void testDifferentialSorting(@ForAll("arrays") Integer[] array) {
        //Regarder les retours de methode de trie car probleme.
        System.out.println("initial :" + Arrays.toString(array));
        Integer[] bubbleSorted = sorting.bubblesort(array.clone(), comparator);
        System.out.println("bubble :" +Arrays.toString(bubbleSorted));
        Integer[] quickSorted = sorting.quicksort(array.clone(), comparator);
       // System.out.println("quick :" + Arrays.toString(quickSorted));
        Integer[] mergeSorted = sorting.mergesort(array.clone(), comparator);
        //System.out.println("merge :" +Arrays.toString(mergeSorted));

        assert Arrays.equals(bubbleSorted, quickSorted) : "Bug: Results differ between bubble sort and quick sort";
        assert Arrays.equals(bubbleSorted, mergeSorted) : "Bug: Results differ between bubble sort and merge sort";
        assert Arrays.equals(quickSorted, mergeSorted) : "Bug: Results differ between quick sort and merge sort";

        assert isSorted(bubbleSorted) : "Bug: Bubble sort did not return a sorted array";
        assert isSorted(quickSorted) : "Bug: Quick sort did not return a sorted array";
        assert isSorted(mergeSorted) : "Bug: Merge sort did not return a sorted array";
    }

    @Provide
    Arbitrary<Integer[]> arrays() {
        return Arbitraries.integers().array(Integer[].class).ofSize(10);
    }

    boolean isSorted(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i+1]) {
                return false;
            }
        }
        return true;
    }
}