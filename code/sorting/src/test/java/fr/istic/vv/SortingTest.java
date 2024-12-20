package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortingTest {

    @Property
    boolean allSortingMethodsAgree(@ForAll List<@IntRange(min=-1000, max=1000) Integer> list) {
        Integer[] original = list.toArray(new Integer[0]);
        Integer[] bubble = Sorting.bubblesort(original, Comparator.naturalOrder());
        Integer[] quick = Sorting.quicksort(original, Comparator.naturalOrder());

        Integer[] merge = Sorting.mergesort(original, Comparator.naturalOrder());

        return Arrays.equals(bubble, quick) && Arrays.equals(quick, merge);
    }

    @Property
    boolean allSortedCorrectly(@ForAll List<@IntRange(min=-1000, max=1000) Integer> list) {
        Integer[] original = list.toArray(new Integer[0]);
        Integer[] sorted = Arrays.copyOf(original, original.length);
        Arrays.sort(sorted);

        Integer[] bubble = Sorting.bubblesort(original, Comparator.naturalOrder());

        Integer[] quick = Sorting.quicksort(original, Comparator.naturalOrder());

        Integer[] merge = Sorting.mergesort(original, Comparator.naturalOrder());

        return Arrays.equals(bubble, sorted) && Arrays.equals(quick, sorted) && Arrays.equals(merge, sorted);
    }

}
