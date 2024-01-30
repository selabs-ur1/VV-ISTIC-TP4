package fr.istic.vv;
import net.jqwik.api.*;
import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean sortingIsConsistent(
            @ForAll("randomArrays") Integer[] array,
            @ForAll("comparators") Comparator<Integer> comparator
    ) {
        Integer[] bubbleSorted = Sorting.bubblesort(Arrays.copyOf(array, array.length), comparator);
        Integer[] quickSorted = Sorting.quicksort(Arrays.copyOf(array, array.length), comparator);
        Integer[] mergeSorted = Sorting.mergesort(Arrays.copyOf(array, array.length), comparator);
        return Arrays.equals(bubbleSorted, quickSorted) && Arrays.equals(quickSorted, mergeSorted);
    }

    @Provide
    Arbitrary<Integer[]> randomArrays() {
        return Arbitraries.integers()
                .list()
                .ofMinSize(0)
                .ofMaxSize(1000)
                .map(list -> list.toArray(new Integer[0]));
    }

    @Provide
    Arbitrary<Comparator<Integer>> comparators() {
        return Arbitraries.of(Comparator.naturalOrder(), Comparator.reverseOrder());
    }
}
