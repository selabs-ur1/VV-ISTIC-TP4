package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;

import java.util.Comparator;
import java.util.List;


public class BinaryHeapTest {
    @Provide
    Arbitrary<List<Integer>> randomIntegers() {
        return Arbitraries.integers().list().ofSize(10);
    }

    @Provide
    Arbitrary<Comparator<Integer>> integerComparator() {
        return Arbitraries.of(
                Comparator.naturalOrder()
        );
    }

    @Property
    boolean elementPoppedIsAlwaysTheLowest(
            @ForAll("randomIntegers") @Size(max = 1000) List<Integer> integers,
            @ForAll("integerComparator") Comparator<Integer> comparator) {
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        for (Integer element: integers) {
            heap.push(element);
        }

        int previous = heap.pop();

        while (heap.count() != 0) {
            int current = heap.pop();
            if (comparator.compare(current, previous) < 0) return false;
            previous = current;
        }

        return true;
    }
}
