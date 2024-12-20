package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeExample;
import java.util.Arrays;

public class BinaryHeapTest {

    private BinaryHeap<Integer> heap;

    @BeforeExample
    void setup() {
        heap = new BinaryHeap<>(Integer::compareTo);
    }
    @Property
    void popReturnsMinimumElement(@ForAll int[] elements) {
        Arrays.stream(elements).forEach(heap::push);

        Integer previousMin = null;

        while (heap.count() > 0) {
            Integer currentMin = heap.pop();

            if (previousMin != null) {
                Assume.that(currentMin >= previousMin);
            }

            previousMin = currentMin;
        }
    }
}
