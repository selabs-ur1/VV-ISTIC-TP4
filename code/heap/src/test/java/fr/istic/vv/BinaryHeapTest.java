package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.Positive;
import java.util.Comparator;
import java.util.List;

public class BinaryHeapTest {

    @Property
    boolean testPushAndPop(@ForAll @Size(min = 1, max = 100) List<@Positive Integer> elements) {
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator, elements.size());

        for (Integer element : elements) {
            heap.push(element);
        }

        elements.sort(comparator);
        for (Integer element : elements) {
            Integer actual = heap.pop();
            if (!element.equals(actual)) {
                return false;
            }
        }

        return true;
    }

    @Property
    boolean testPeek(@ForAll @Size(min = 1, max = 100) List<@Positive Integer> elements) {
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator, elements.size());

        for (Integer element : elements) {
            heap.push(element);
        }

        elements.sort(comparator);
        for (Integer element : elements) {
            Integer actual = heap.peek();
            if (!element.equals(actual)) {
                return false;
            }
            heap.pop();
        }

        return true;
    }

    @Property
    boolean testCount(@ForAll @Size(min = 1, max = 100) List<@Positive Integer> elements) {
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator, elements.size());

        for (Integer element : elements) {
            heap.push(element);
        }

        return heap.count() == elements.size();
    }

    @Property
    boolean testPopEmptyHeap() {
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator, 10);

        if(heap.count() != 0) {
            return false;
        }

        return true;
    }

    @Property
    boolean testPushFullHeap(@ForAll @Size(10) List<@Positive Integer> elements) {
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator, 10);

        for (Integer element : elements) {
            heap.push(element);
        }

        try {
            heap.push(0);
        } catch (IllegalStateException e) {
            return true;
        }

        return false;
    }
}