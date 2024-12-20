package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;


public class BinaryHeapTest {

    @Property
    boolean heapAlwaysPopsMinimum(@ForAll @Size(value = 0, max = 100) List<Integer> elements) {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.naturalOrder());

        for (Integer element : elements) {
            heap.push(element);
        }

        List<Integer> poppedElements = new ArrayList<>(elements.size());
        while (heap.count() > 0) {
            try {
                poppedElements.add(heap.pop());
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        List<Integer> sortedElements = new ArrayList<>(elements);
        sortedElements.sort(Comparator.naturalOrder());

        return poppedElements.equals(sortedElements);
    }
}
