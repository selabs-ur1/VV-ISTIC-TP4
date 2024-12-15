package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Comparator;
import java.util.List;

public class SortingTest {

    private static final Comparator<Integer> INT_COMPARATOR = Integer::compareTo;

    @Property
    boolean bubbleIsSorted(@ForAll List<Integer> array) {
        List<Integer> sorted = Sorting.bubblesort(array, INT_COMPARATOR);

        for (int i = 1; i < sorted.size(); i++)
            if (INT_COMPARATOR.compare(sorted.get(i - 1), sorted.get(i)) > 0)
                return false;

        return true;
    }

    @Property
    boolean bubbleContainsAll(@ForAll List<Integer> array) {
        return array.containsAll(Sorting.bubblesort(array, INT_COMPARATOR));
    }

    @Property
    boolean bubbleSameSize(@ForAll List<Integer> array) {
        return Sorting.bubblesort(array, INT_COMPARATOR).size() == array.size();
    }

    @Property
    boolean quickIsSorted(@ForAll List<Integer> array) {
        List<Integer> sorted = Sorting.quicksort(array, INT_COMPARATOR);

        for (int i = 1; i < sorted.size(); i++)
            if (INT_COMPARATOR.compare(sorted.get(i - 1), sorted.get(i)) > 0)
                return false;

        return true;
    }

    @Property
    boolean quickContainsAll(@ForAll List<Integer> array) {
        return array.containsAll(Sorting.quicksort(array, INT_COMPARATOR));
    }

    @Property
    boolean quickSameSize(@ForAll List<Integer> array) {
        return Sorting.quicksort(array, INT_COMPARATOR).size() == array.size();
    }

}