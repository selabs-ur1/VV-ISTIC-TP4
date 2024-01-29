package fr.istic.vv;
import net.jqwik.api.*;
import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {

    @Property
    boolean sortingAlgorithmsProduceEquivalentResults(@ForAll("arrays") Integer[] array) {
        Comparator<Integer> comparator = Comparator.naturalOrder();

        Integer[] sortedBubble = Sorting.bubbleSort(array.clone(), comparator);
        Integer[] sortedQuick = Sorting.quickSort(array.clone(), comparator);
        Integer[] sortedMerge = Sorting.mergeSort(array.clone(), comparator);

        return Arrays.equals(sortedBubble, sortedQuick) && Arrays.equals(sortedBubble, sortedMerge);
    }

    // The size of the arrays is between 0 and 30.
    @Provide
    Arbitrary<Integer[]> arrays() {
        Arbitrary<Integer> integerArbitrary = Arbitraries.integers().between(0, 100);
        Arbitrary<Integer[]> arrayArbitrary = integerArbitrary.array(Integer[].class).ofMinSize(0).ofMaxSize(30);
        return arrayArbitrary;
    }
}
