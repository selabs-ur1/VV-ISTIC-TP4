package fr.istic.vv;

import java.util.Arrays;
import java.util.Comparator;
import static fr.istic.vv.Sorting.*;

import net.jqwik.api.*;

public class SortingTest {

    @Property
    boolean testQuicksort(@ForAll Integer[] array) {
        Integer[] copy = Arrays.copyOf(array, array.length);
        Arrays.sort(copy);
        quicksort(array, Comparator.naturalOrder());
        return Arrays.equals(array, copy);
    }

    @Property
    boolean testMergesort(@ForAll Integer[] array) {
        Integer[] copy = Arrays.copyOf(array, array.length);
        Arrays.sort(copy);
        mergesort(array, Comparator.naturalOrder());
        return Arrays.equals(array, copy);
    }

    @Property
    boolean testBubblesort(@ForAll Integer[] array) {
        Integer[] copy = Arrays.copyOf(array, array.length);
        Arrays.sort(copy);
        bubblesort(array, Comparator.naturalOrder());
        return Arrays.equals(array, copy);
    }
}