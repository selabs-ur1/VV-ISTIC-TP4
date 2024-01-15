package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryHeapTest {
    /*
     * Generate random inputs for the heap and ensures the element returned
     * by pop every time it is invoked is the minimum of the remaining elements
     * in the heap
     */

    @Property
    public void popReturnsMinElement(@ForAll @Size(min = 1) List<Integer> elements){
        Comparator<Integer> comparator = Comparator.naturalOrder();

        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(comparator);

        for(Integer e: elements){
            binaryHeap.push(e);
        }

        List<Integer> sortedElements = new ArrayList<>(elements);
        sortedElements.sort(comparator);

        for(Integer expectedMin: sortedElements){
            Integer actualMin = binaryHeap.pop();
            assertEquals(expectedMin, actualMin);
        }
    }
}
