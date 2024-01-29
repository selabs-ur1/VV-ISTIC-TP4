package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Comparator;
import java.util.Arrays;

public class SortingTest {
    @Provide
    Arbitrary<Integer[]> arrayArbitrary() {
        Arbitrary<Integer> integerArbitrary = Arbitraries.integers().between(0, 100);
        Arbitrary<Integer[]> arrayArbitrary = integerArbitrary.array(Integer[].class).ofMinSize(0).ofMaxSize(30);
        return arrayArbitrary;
    }

    @Property
    boolean sortingAlgorithmsProduceEquivalentResults(@ForAll("arrayArbitrary") Integer[] array) {
        Comparator<Integer> comparator = Comparator.naturalOrder();

        Integer[] sortedBubble = Sorting.bubblesort(array.clone(), comparator);
        Integer[] sortedQuick = Sorting.quicksort(array.clone(), comparator);
        Integer[] sortedMerge = Sorting.mergesort(array.clone(), comparator);

        return Arrays.equals(sortedBubble, sortedQuick) && Arrays.equals(sortedQuick, sortedMerge);
    }
}