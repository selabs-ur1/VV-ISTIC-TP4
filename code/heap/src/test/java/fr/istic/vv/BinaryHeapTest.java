package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeapTest {

    @Property
    boolean peekTest(@ForAll("randomIntegerLists") java.util.List<Integer> elements) {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        elements.forEach(heap::push);

        if (elements.isEmpty()) {
            try {
                heap.peek();
                return false;
            } catch (NoSuchElementException e) {
                return true;
            }
        } else {
            int smallest = elements.stream().min(Integer::compareTo).orElseThrow();
            return heap.peek().equals(smallest);
        }
    }

    @Property
    boolean countTest(@ForAll("randomIntegerLists") java.util.List<Integer> elements) {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        elements.forEach(heap::push);

        int sizeBeforePops = heap.count();
        for (int i = 0; i < sizeBeforePops; i++) {
            heap.pop();
            if (heap.count() != sizeBeforePops - (i + 1)) {
                return false;
            }
        }
        return heap.count() == 0;
    }

    @Property
    boolean pushTest(@ForAll("randomIntegerLists") java.util.List<Integer> elements) {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());
        elements.forEach(heap::push);

        java.util.List<Integer> sortedElements = new java.util.ArrayList<>(elements);
        sortedElements.sort(Comparator.naturalOrder());

        for (int i = 0; i < sortedElements.size(); i++) {
            if (!heap.pop().equals(sortedElements.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Provide
    Arbitrary<java.util.List<Integer>> randomIntegerLists() {
        return Arbitraries.integers().between(0, 100).list().ofMaxSize(100);
    }
}
