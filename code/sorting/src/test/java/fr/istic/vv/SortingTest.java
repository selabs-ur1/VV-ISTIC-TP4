package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {

    private static final Comparator<Integer> INT_COMPARATOR = Integer::compareTo;

    @Property
    boolean isSorted(@ForAll Integer[] array) {
        Integer[] sorted = Sorting.bubblesort(array, INT_COMPARATOR);

        for (int i = 1; i < sorted.length; i++)
            if (INT_COMPARATOR.compare(sorted[i - 1], sorted[i]) > 0)
                return false;

        return true;
    }

    @Property
    boolean containsAll(@ForAll Integer[] array) {
        Integer[] sorted = Sorting.bubblesort(array, INT_COMPARATOR);

        return Arrays.stream(sorted).allMatch(element -> Arrays.asList(array).contains(element));
    }

    @Property
    boolean sameSize(@ForAll Integer[] array) {
        return Sorting.bubblesort(array, INT_COMPARATOR).length == array.length;
    }
}