package fr.istic.vv;

import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

public class Test {

    @Property
    boolean sortingIsCorrect(
            @ForAll("arrays") Integer[] array,
            @ForAll("comparators") Comparator<Integer> comparator
    ) {
        Integer[] sortedBubble = Sorting.bubblesort(Arrays.copyOf(array, array.length), comparator);
        Integer[] sortedQuick = Sorting.quicksort(Arrays.copyOf(array, array.length), comparator);
        Integer[] sortedMerge = Sorting.mergesort(Arrays.copyOf(array, array.length), comparator);

        Arrays.sort(array, comparator);

        return Arrays.equals(array, sortedBubble) && Arrays.equals(array, sortedQuick) && Arrays.equals(array, sortedMerge);
    }

    @Provide
    Arbitrary<Integer[]> arrays() {
        return Arbitraries.integers().array(Integer[].class);
    }

    @Provide
    Arbitrary<Comparator<Integer>> comparators() {
        return Arbitraries.of(Comparator.naturalOrder(), Comparator.reverseOrder());
    }
}
