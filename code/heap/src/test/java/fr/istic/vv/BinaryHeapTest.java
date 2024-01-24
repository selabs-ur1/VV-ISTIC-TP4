package fr.istic.vv;
import net.jqwik.api.*;

import java.util.*;

public class BinaryHeapTest {

    //IDK why this test
    //@Property
    //boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
    //    return Math.abs(anInteger) >= 0;
    //}

    //Test that pop return the minimum elements
    @Property
    void popReturnsMinimumElement(@ForAll List<Integer> elements) {
        if (elements.size() < 2) {
            // Skip tests for heaps with less than two elements
            return;
        }

        // Create a BinaryHeap with the natural ordering comparator
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>(Comparator.naturalOrder());

        // Add elements to the heap
        for (Integer element : elements) {
            binaryHeap.push(element);
        }

        // Sort the elements in ascending order (minimum to maximum)
        Collections.sort(elements);
        //System.out.println(Arrays.toString(elements.toArray()));

        // Test pop() to ensure it returns the minimum element each time
        for (Integer expectedMin : elements) {
            Integer actualMin = binaryHeap.pop();
            //System.out.println("actualMin : " + actualMin);
            assert actualMin.equals(expectedMin);
        }

        // After popping all elements, the heap should be empty
        assert binaryHeap.count() == 0;

        // Popping from an empty heap should throw NoSuchElementException with junit
        //assertThrows(NoSuchElementException.class, () -> binaryHeap.pop());
    }


}
