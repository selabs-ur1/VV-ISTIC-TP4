package fr.istic.vv;

import net.jqwik.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BinaryHeapTest {

    @Property
    void popReturnsMinimum(@ForAll("heapAndElements") HeapAndElements heapAndElements) {
        BinaryHeap<Integer> heap = heapAndElements.heap;

        List<Integer> elements = heapAndElements.elements;
        Collections.sort(elements);

        for (int i = 0; i < elements.size(); i++) {
            assertEquals(elements.get(i), heap.pop());
            assertEquals(elements.size() - i - 1, heap.count());
        }

        assertThrows(NoSuchElementException.class, heap::pop);
    }

    @Provide
    Arbitrary<HeapAndElements> heapAndElements() {
        Arbitrary<Comparator<Integer>> comparatorArbitrary = Arbitraries.of(Comparator.naturalOrder());

        return comparatorArbitrary.flatMap(
                comparator -> Arbitraries.integers()
                        .list()
                        .ofMinSize(1)
                        .flatMap(elements -> {
                            BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
                            for (Integer element : elements) {
                                heap.push(element);
                            }
                            return Arbitraries.just(new HeapAndElements(heap, elements));
                        })
        );
    }

    static class HeapAndElements {
        BinaryHeap<Integer> heap;
        List<Integer> elements;

        HeapAndElements(BinaryHeap<Integer> heap, List<Integer> elements) {
            this.heap = heap;
            this.elements = elements;
        }
    }
}
