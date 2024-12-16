package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import net.jqwik.api.*;

public class BinaryHeapTest {

    @Property
    void pushAddsElements(@ForAll List<Integer> elements) {
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        elements.forEach(heap::push);
        Assume.that(elements.size() == heap.count());
    }

    @Property
    void peekReturnsMinElement(@ForAll List<Integer> elements) {
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        elements.forEach(heap::push);

        List<Integer> sortedElements = new ArrayList<>(elements);
        sortedElements.sort(comparator);

        if (!sortedElements.isEmpty()) {
            Assume.that(sortedElements.get(0) == heap.peek());
        }
    }

    @Property
    void popReturnsAndRemovesMinElement(@ForAll List<Integer> elements) {
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        elements.forEach(heap::push);

        List<Integer> sortedElements = new ArrayList<>(elements);
        sortedElements.sort(comparator);

        while (heap.count() > 0) {
            Integer poppedElement = heap.pop();
            Assume.that(sortedElements.remove(0) == poppedElement);
        }
    }

    @Property
    void countReturnsCorrectNumber(@ForAll List<Integer> elements) {
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        elements.forEach(heap::push);

        Assume.that(elements.size() == heap.count());

        if (!elements.isEmpty()) {
            heap.pop();
            Assume.that(elements.size() - 1 == heap.count());
        }
    }

    @Example
    void popThrowsExceptionWhenHeapIsEmpty() {

        boolean exceptionThrown = false;
        try {
            Comparator<Integer> comparator = Integer::compareTo;
            BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
            heap.pop();
        } catch (NoSuchElementException e) {
            exceptionThrown = true;
        }
        Assume.that(exceptionThrown);
    }

    @Example
    void peekThrowsExceptionWhenHeapIsEmpty() {

        boolean exceptionThrown = false;
        try {
            Comparator<Integer> comparator = Integer::compareTo;
            BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
            heap.peek();
        } catch (NoSuchElementException e) {
            exceptionThrown = true;
        }
        Assume.that(exceptionThrown);
    }
}
