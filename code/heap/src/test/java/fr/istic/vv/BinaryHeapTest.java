package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryHeapTest {

    @Property
    boolean elementsArePoppedInAscendingOrder(@ForAll List<@IntRange(min = -1000, max = 1000) Integer> integers) {

        // BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());

        // BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.<Integer>naturalOrder());


        BinaryHeap<Integer> heap = new BinaryHeap<>((Comparator<Integer>) Comparator.naturalOrder());

        for (Integer i : integers) {
            heap.push(i);
        }

        List<Integer> popped = new ArrayList<>();

        while (heap.count() > 0) {
            popped.add(heap.pop());
        }

        for (int i = 1; i < popped.size(); i++) {
            if (popped.get(i) < popped.get(i - 1)) {

                return false;
            }
        }

        return true;
    }

}
