package fr.istic.vv;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import java.util.Comparator;

public class BinaryHeapTest {
    @Property
    boolean pushingElementIncreasesCount(@ForAll int element) {
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        int initialCount = heap.count();
        heap.push(element);
        return heap.count() == initialCount + 1;
    }

    @Property
    boolean poppingElementDecreasesCount(@ForAll int element) {
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        heap.push(element);
        int initialCount = heap.count();
        heap.pop();
        return heap.count() == initialCount - 1;
    }

    @Property
    boolean peekReturnsTopElement(@ForAll int element) {
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        heap.push(element);
        return heap.peek() == element;
    }

    @Property
    boolean elementsAreInOrderAfterPop(@ForAll int[] elements) {
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        for (int element : elements) {
            heap.push(element);
        }
        int previous = Integer.MIN_VALUE;
        while (heap.count() > 0) {
            int current = heap.pop();
            if (current < previous) {
                return false;
            }
            previous = current;
        }
        return true;
    }
}
