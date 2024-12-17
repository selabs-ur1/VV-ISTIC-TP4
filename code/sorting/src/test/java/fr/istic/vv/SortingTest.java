package fr.istic.vv;
import net.jqwik.api.*;
import java.util.*;

public class SortingTest {

    @Property
    boolean differentialFuzzing(@ForAll("randomIntegerLists") Integer[] originalArray) {
        Integer[] arrayForBubbleSort = Arrays.copyOf(originalArray, originalArray.length);
        Integer[] arrayForQuickSort = Arrays.copyOf(originalArray, originalArray.length);
        Integer[] arrayForMergeSort = Arrays.copyOf(originalArray, originalArray.length);
        
        Integer[] sortedByBubbleSort = Sorting.bubblesort(arrayForBubbleSort, Comparator.naturalOrder());
        Integer[] sortedByQuickSort = Sorting.quicksort(arrayForQuickSort, Comparator.naturalOrder());
        Integer[] sortedByMergeSort = Sorting.mergesort(arrayForMergeSort, Comparator.naturalOrder());

        return Arrays.equals(sortedByBubbleSort, sortedByQuickSort) &&
                Arrays.equals(sortedByQuickSort, sortedByMergeSort);
    }

    @Provide
    Arbitrary<Integer[]> randomIntegerLists() {
        return Arbitraries.integers().between(0, 100).list().ofMaxSize(50).map(list -> list.toArray(new Integer[0]));
    }
}
