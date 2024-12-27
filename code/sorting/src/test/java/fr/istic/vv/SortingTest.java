package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.arbitraries.ListArbitrary;

import java.util.*;

public class SortingTest {
    @Provide
    Arbitrary<List<Integer>> randomIntegerList() {
        return Arbitraries.integers().list().ofSize(100);
    }

    @Provide
    Arbitrary<Comparator<Integer>> integerComparator() {
        return Arbitraries.of(
                Comparator.naturalOrder()
        );
    }

    @Property
    boolean listsAreEquivalent(
            @ForAll("randomIntegerList") List<Integer> list,
            @ForAll("integerComparator") Comparator<Integer> integerComparator) {
        Integer[] arrayBubblesort = list.toArray(new Integer[0]);
        Integer[] arrayQuicksort = list.toArray(new Integer[0]);
        Integer[] arrayMergesort = list.toArray(new Integer[0]);

        Sorting.bubblesort(arrayBubblesort, integerComparator);
        Sorting.quicksort(arrayQuicksort, integerComparator);
        Sorting.mergesort(arrayMergesort, integerComparator);

        for (int i = 0; i < arrayBubblesort.length; i++) {
            if (integerComparator.compare(arrayQuicksort[i], arrayBubblesort[i]) != 0) return false;
            if (integerComparator.compare(arrayBubblesort[i], arrayMergesort[i]) != 0) return false;
        }

        return true;
    }

    @Property
    boolean bubblesortedListsAreSorted(
            @ForAll("randomIntegerList") List<Integer> list,
            @ForAll("integerComparator") Comparator<Integer> integerComparator) {
        Integer[] arrayBubblesort = list.toArray(new Integer[0]);

        Sorting.bubblesort(arrayBubblesort, integerComparator);

        for (int i = 1; i < arrayBubblesort.length; i++) {
            if (integerComparator.compare(arrayBubblesort[i - 1], arrayBubblesort[i]) > 0) return false;
        }

        return true;
    }

    @Property
    boolean quicksortedListsAreSorted(
            @ForAll("randomIntegerList") List<Integer> list,
            @ForAll("integerComparator") Comparator<Integer> integerComparator) {
        Integer[] arrayQuicksort = list.toArray(new Integer[0]);

        Sorting.quicksort(arrayQuicksort, integerComparator);

        for (int i = 1; i < arrayQuicksort.length; i++) {
            if (integerComparator.compare(arrayQuicksort[i - 1], arrayQuicksort[i]) > 0) return false;
        }

        return true;
    }

    @Property
    boolean mergesortedListsAreSorted(
            @ForAll("randomIntegerList") List<Integer> list,
            @ForAll("integerComparator") Comparator<Integer> integerComparator) {
        Integer[] arrayMergesort = list.toArray(new Integer[0]);

        Sorting.mergesort(arrayMergesort, integerComparator);

        for (int i = 1; i < arrayMergesort.length; i++) {
            if (integerComparator.compare(arrayMergesort[i - 1], arrayMergesort[i]) > 0) return false;
        }

        return true;
    }

}