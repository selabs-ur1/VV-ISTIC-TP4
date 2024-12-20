package fr.istic.vv;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import java.util.Comparator;
import java.util.List;

public class SortingTest {

    private static final Comparator<Integer> INT_COMPARATOR = Integer::compareTo;

    /**
     * @param array For any array of integers...
     * @return <code>Sorting.bubblesort(array, INT_COMPARATOR)</code> is sorted.
     */
    @Property
    boolean bubbleIsSorted(@ForAll List<Integer> array) {
        List<Integer> sorted = Sorting.bubblesort(array, INT_COMPARATOR);

        for (int i = 1; i < sorted.size(); i++)
            if (INT_COMPARATOR.compare(sorted.get(i - 1), sorted.get(i)) > 0)
                return false;

        return true;
    }

    /**
     * @param array For any array of integers...
     * @return <code>Sorting.bubblesort(array, INT_COMPARATOR)</code> contains all elements from
     * <code>array</code>.
     */
    @Property
    boolean bubbleContainsAll(@ForAll List<Integer> array) {
        return array.containsAll(Sorting.bubblesort(array, INT_COMPARATOR));
    }

    /**
     * @param array For any array of integers...
     * @return <code>Sorting.bubblesort(array, INT_COMPARATOR)</code> has the same size as
     * <code>array</code>.
     */
    @Property
    boolean bubbleSameSize(@ForAll List<Integer> array) {
        return Sorting.bubblesort(array, INT_COMPARATOR)
                .size() == array.size();
    }

    /**
     * @param array For any array of integers...
     * @return <code>Sorting.quicksort(array, INT_COMPARATOR)</code> is sorted.
     */
    @Property
    boolean quickIsSorted(@ForAll List<Integer> array) {
        List<Integer> sorted = Sorting.quicksort(array, INT_COMPARATOR);

        for (int i = 1; i < sorted.size(); i++)
            if (INT_COMPARATOR.compare(sorted.get(i - 1), sorted.get(i)) > 0)
                return false;

        return true;
    }

    /**
     * @param array For any array of integers...
     * @return <code>Sorting.quicksort(array, INT_COMPARATOR)</code> contains all elements from
     * <code>array</code>.
     */
    @Property
    boolean quickContainsAll(@ForAll List<Integer> array) {
        return array.containsAll(Sorting.quicksort(array, INT_COMPARATOR));
    }

    /**
     * @param array For any array of integers...
     * @return <code>Sorting.quicksort(array, INT_COMPARATOR)</code> has the same size as
     * <code>array</code>.
     */
    @Property
    boolean quickSameSize(@ForAll List<Integer> array) {
        return Sorting.quicksort(array, INT_COMPARATOR)
                .size() == array.size();
    }

    /**
     * @param array For any array of integers...
     * @return <code>Sorting.mergesort(array, INT_COMPARATOR)</code> is sorted.
     */
    @Property
    boolean mergeIsSorted(@ForAll List<Integer> array) {
        List<Integer> sorted = Sorting.mergesort(array, INT_COMPARATOR);

        for (int i = 1; i < sorted.size(); i++)
            if (INT_COMPARATOR.compare(sorted.get(i - 1), sorted.get(i)) > 0)
                return false;

        return true;
    }

    /**
     * @param array For any array of integers...
     * @return <code>Sorting.mergesort(array, INT_COMPARATOR)</code> contains all elements from
     * <code>array</code>.
     */
    @Property
    boolean mergeContainsAll(@ForAll List<Integer> array) {
        return array.containsAll(Sorting.mergesort(array, INT_COMPARATOR));
    }

    /**
     * @param array For any array of integers...
     * @return <code>Sorting.mergesort(array, INT_COMPARATOR)</code> has the same size as
     * <code>array</code>.
     */
    @Property
    boolean mergeSameSize(@ForAll List<Integer> array) {
        return Sorting.mergesort(array, INT_COMPARATOR)
                .size() == array.size();
    }

}