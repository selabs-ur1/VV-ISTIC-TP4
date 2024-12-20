package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import java.util.*;

class BinaryHeapTest {

    @Property
    void testHeapProperty(@ForAll List<@IntRange(min = -1000, max = 1000) Integer> input) {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        // Push all elements into the heap
        for (Integer element : input) {
            heap.push(element);
        }

        // Collect elements by popping from the heap
        List<Integer> poppedElements = new ArrayList<>();
        while (heap.count() > 0) {
            poppedElements.add(heap.pop());
        }

        // Ensure the popped elements are sorted
        for (int i = 1; i < poppedElements.size(); i++) {
            assert poppedElements.get(i) >= poppedElements.get(i - 1);
        }
    }
}
