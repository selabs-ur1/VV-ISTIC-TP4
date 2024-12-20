package fr.istic.vv;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.NotEmpty;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


public class BinaryHeapTest {

    private static final Comparator<Integer> INT_COMPARATOR = Integer::compareTo;

    /**
     * Utility method, used for create heaps from lists.
     * @param list List of number.
     * @return Binary min heap containing integers from <code>list</code>.
     */
    private static BinaryHeap<Integer> heapFromList(List<Integer> list) {
        BinaryHeap<Integer> heap = new BinaryHeap<>(INT_COMPARATOR);
        for (int i : list)
            heap.push(i);

        return heap;
    }

    /**
     * Provides arbitrary, of at least two element, with unique elements integer list.
     * @return The arbitrary list.
     */
    @Provide
    private static Arbitrary<List<Integer>> twoElemList() {
        return Arbitraries.integers()
                .unique()
                .list()
                .ofMinSize(2);
    }

    /**
     * @return Has an empty binary heap a size of 0 ?
     */
    @Example
    boolean emptyCountIsZero() {
        return new BinaryHeap<>(INT_COMPARATOR).count() == 0;
    }

    /**
     * @param list For any integer list...
     * @param i For any integer...
     * @return heap.push(i) increases count of 1.
     */
    @Property
    boolean pushIncreaseLength(@ForAll List<Integer> list, @ForAll int i) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int expectedCount = heap.count() + 1;

        heap.push(i);

        return expectedCount == heap.count();
    }

    /**
     * @param list For any integer list...
     * @return heap.pop(i) decreases count of 1.
     */
    @Property
    boolean popNonEmptyDecreaseLength(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int expectedCount = heap.count() - 1;

        heap.pop();

        return expectedCount == heap.count();
    }

    /**
     * @return Pop throws a <code>NoSuchElementException</code> on an empty heap.
     */
    @Example
    boolean popEmptyException() {
        try {
            new BinaryHeap<>(INT_COMPARATOR).pop();
        } catch (NoSuchElementException e) {
            return true;
        }

        return false;
    }

    /**
     * @return Peek throws a <code>NoSuchElementException</code> on an empty heap.
     */
    @Example
    boolean peekEmptyException() {
        try {
            new BinaryHeap<>(INT_COMPARATOR).peek();
        } catch (NoSuchElementException e) {
            return true;
        }

        return false;
    }

    /**
     * @param list For any non-empty list...
     * @return Peek does not change heap count.
     */
    @Property
    boolean peekNonEmptyKeepsLength(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int expectedCount = heap.count();

        heap.peek();

        return expectedCount == heap.count();
    }

    /**
     * @param list For any non-empty list...
     * @return Pops result had been previously added.
     */
    @Property
    boolean popNonEmptyBelongsToInitialHeap(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);

        return list.contains(heap.pop());
    }

    /**
     * @param list For any non-empty list...
     * @return Peek does not remove key from the heap.
     */
    @Property
    boolean peekNonEmptyKeepsInHeap(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int expected = heap.peek();

        return expected == heap.peek();
    }

    /**
     * @param list For any non-empty list of at least two elements, with unique elements...
     * @return Peek does not change heap count.
     */
    @Property
    boolean popNonEmptyRemoveFromHeap(@ForAll("twoElemList") List<Integer> list) {
        List<Integer> distinct = list.stream()
                .distinct()
                .collect(Collectors.toList());
        BinaryHeap<Integer> heap = heapFromList(distinct);
        int expected = heap.pop();

        return expected != heap.peek();
    }

    /**
     * @param list For any non-empty list...
     * @return Peek returns the minimum of previously pushed values.
     */
    @Property
    boolean peekNonEmptyPeeksMin(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int min = heap.peek();

        return list.stream()
                .allMatch(i -> min <= i);
    }

    /**
     * @param list For any non-empty list...
     * @return Pops returns the minimum of previously pushed values.
     */
    @Property
    boolean popNonEmptyPopsMin(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int min = heap.pop();

        return list.stream()
                .allMatch(i -> min <= i);
    }

    /**
     * @param list For any non-empty list...
     * @param i For any integer...
     * @return Peek retrieves the previous minimum, or retrieves the previously pushed key if it is
     * lower than the minimum key.
     */
    @Property
    boolean pushReplacesMinIfMinPeek(@ForAll @NotEmpty List<Integer> list, @ForAll int i) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int min = heap.peek();
        int expected = i < min ? i : min;

        heap.push(i);

        return expected == heap.peek();
    }

    /**
     * @param list For any non-empty list...
     * @param i For any integer...
     * @return Pop retrieves the previous minimum, or retrieves the previously pushed key if it is
     * lower than the minimum key.
     */
    @Property
    boolean pushReplacesMinIfMinPop(@ForAll @NotEmpty List<Integer> list, @ForAll int i) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int min = heap.peek();
        int expected = i < min ? i : min;

        heap.push(i);

        return expected == heap.pop();
    }
}
