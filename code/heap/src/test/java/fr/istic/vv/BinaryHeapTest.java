package fr.istic.vv;
import net.jqwik.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BinaryHeapTest {

    private final Comparator<Integer> comparator = Comparator.naturalOrder();

    @Property
    void testPopReturnsMinimum(@ForAll("randomIntegers") List<Integer> input) {
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        input.forEach(heap::push);

        List<Integer> responseList = new ArrayList<>(input);
        Collections.sort(responseList);

        while (heap.count() > 0) {
            Integer expectedMin = responseList.remove(0);
            Integer actualMin = heap.pop();
            assertThat(actualMin).isEqualTo(expectedMin);
        }
    }

    @Provide
    public Arbitrary<List<Integer>> randomIntegers() {
        return Arbitraries.integers().unique().list().ofMinSize(1).ofMaxSize(1000);
    }
}
