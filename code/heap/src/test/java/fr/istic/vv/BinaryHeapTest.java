package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.NotEmpty;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


public class BinaryHeapTest {

    private static final Comparator<Integer> INT_COMPARATOR = Integer::compareTo;

    private static BinaryHeap<Integer> heapFromList(List<Integer> list) {
        BinaryHeap<Integer> heap = new BinaryHeap<>(INT_COMPARATOR);
        for (int i : list)
            heap.push(i);

        return heap;
    }

    @Property
    boolean countCorrespondsToDistinctPushes(@ForAll List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);
        List<Integer> distincts = list.stream().distinct().collect(Collectors.toList());

        return distincts.size() == heap.count();
    }

    @Property
    boolean pushIncreaseLength(@ForAll List<Integer> list, @ForAll int i) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int expectedCount = heap.count() + (list.contains(i) ? 0 : 1);

        heap.push(i);

        return expectedCount == heap.count();
    }

    @Property
    boolean popNonEmptyDecreaseLength(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int expectedCount = heap.count() - 1;

        heap.pop();

        return expectedCount == heap.count();
    }

    @Example
    boolean popEmptyException() {
        try {
            new BinaryHeap<Integer>(INT_COMPARATOR).pop();
        } catch (NoSuchElementException e) {
            return true;
        }

        return false;
    }

    @Example
    boolean peekEmptyException() {
        try {
            new BinaryHeap<Integer>(INT_COMPARATOR).peek();
        } catch (NoSuchElementException e) {
            return true;
        }

        return false;
    }

    @Property
    boolean peekNonEmptyKeepsLength(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int expectedCount = heap.count();

        heap.peek();

        return expectedCount == heap.count();
    }

    @Property
    boolean popNonEmptyBelongsToInitialHeap(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);

        return list.contains(heap.pop());
    }

    @Property
    boolean peekNonEmptyBelongsToHeap(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);

        return list.contains(heap.peek());
    }

    @Property
    boolean peekNonEmptyKeepsInHeap(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int expected = heap.peek();

        return expected == heap.peek();
    }

    @Property
    boolean popNonEmptyRemoveFromHeap(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int expected = heap.pop();

        return expected != heap.peek();
    }

    @Property
    boolean peekNonEmptyPeeksMin(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int min = heap.peek();

        return list.stream().allMatch(i -> min <= i);
    }

    @Property
    boolean popNonEmptyPopsMin(@ForAll @NotEmpty List<Integer> list) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int min = heap.pop();

        return list.stream().allMatch(i -> min <= i);
    }

    @Property
    boolean pushReplacesMinIfMinPeek(@ForAll @NotEmpty List<Integer> list, @ForAll int i) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int min = heap.peek();
        int expected = i < min ? i : min;

        heap.push(i);

        return expected != heap.peek();
    }

    @Property
    boolean pushReplacesMinIfMinPop(@ForAll @NotEmpty List<Integer> list, @ForAll int i) {
        BinaryHeap<Integer> heap = heapFromList(list);
        int min = heap.peek();
        int expected = i < min ? i : min;

        heap.push(i);

        return expected != heap.pop();
    }
}
