package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class BinaryHeapTest {
    //Test that pop return the minimum elements
    @Property
    void popReturnsMinimumElement(@ForAll List<Integer> elements) {
        if (elements.size() < 2) {
            // Skip tests for heaps with less than two elements
            return;
        }

        // Create a BinaryHeap with the natural ordering comparator
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());

        // Add elements to the heap
        for (Integer element : elements) {
            heap.push(element);
        }

        // Sort the elements in ascending order (minimum to maximum)
        Collections.sort(elements);
        //System.out.println(Arrays.toString(elements.toArray()));

        // Test pop() to ensure it returns the minimum element each time
        for (Integer expectedMin : elements) {
            Integer actualMin = heap.pop();
            //System.out.println("actualMin : " + actualMin);
            assert actualMin.equals(expectedMin);
        }

        // After popping all elements, the heap should be empty
        assert heap.count() == 0;

        // Popping from an empty heap should throw NoSuchElementException with junit
        //assertThrows(NoSuchElementException.class, () -> binaryHeap.pop());
    }
}
