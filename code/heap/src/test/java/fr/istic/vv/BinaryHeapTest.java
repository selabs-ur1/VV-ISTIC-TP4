package fr.istic.vv;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import net.jqwik.api.*;

public class BinaryHeapTest {
  

    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {

        if (anInteger == Integer.MIN_VALUE) {
            return Math.abs((long) anInteger) > 0;
        }
        return Math.abs(anInteger) >= 0;
    }

    @Provide
    Arbitrary<BinaryHeap<Integer>> integerHeapArbitrary() {
        return Arbitraries.integers()
                .list()
                .ofMinSize(1)
                .ofMaxSize(100)
                .map(list -> {
                    BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compare);
                    list.forEach(heap::push);
                    return heap;
                });
    }

    @Property
    boolean pushIncreasesHeapSize(@ForAll int element) {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compare);
        int initialSize = heap.count();

        heap.push(element);

        return heap.count() == initialSize + 1;
    }

    @Property
    boolean popReducesHeapSize(@ForAll("integerHeapArbitrary") BinaryHeap<Integer> heap) {
        int initialSize = heap.count();

        Assume.that(initialSize > 0);

        heap.pop();

        return heap.count() == initialSize - 1;
    }

    @Property
    boolean popReturnsMinimumElement(@ForAll("integerHeapArbitrary") BinaryHeap<Integer> heap) {
        ArrayList<Integer> copyList = new ArrayList<>(heap.heap);
        Integer expectedMinimum = copyList.stream().min(Integer::compare).orElseThrow();

        Integer poppedElement = heap.pop();

        return poppedElement.equals(expectedMinimum);
    }

    @Property
    boolean peekDoesNotChangeHeapSize(@ForAll("integerHeapArbitrary") BinaryHeap<Integer> heap) {
        int initialSize = heap.count();

        heap.peek();

        return heap.count() == initialSize;
    }

    @Property
    boolean popThrowsExceptionWhenEmpty() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compare);

        try {
            heap.pop();
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

}
