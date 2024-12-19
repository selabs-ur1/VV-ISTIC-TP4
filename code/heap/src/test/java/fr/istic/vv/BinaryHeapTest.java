package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.lifecycle.BeforeTry;

import java.util.Comparator;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BinaryHeapTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min=Integer.MIN_VALUE +1) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    private BinaryHeap<Integer> minHeap;
    private BinaryHeap<Integer> maxHeap;

    @BeforeTry
    void setup() {
        minHeap = new BinaryHeap<>((Comparator<Integer>)Comparator.naturalOrder());
        maxHeap = new BinaryHeap<>((Comparator<Integer>)Comparator.reverseOrder());
    }

    @Provide
    Arbitrary<Integer> integers() {
        return Arbitraries.integers().between(-2000, 2000);
    }

    @Provide
    Arbitrary<List<Integer>> distinctIntegers() {
        return Arbitraries.integers().between(-2000, 2000).list().uniqueElements().ofMinSize(1).ofMaxSize(100);
    }


    @Property
    void testPushAndPeekMinHeap(@ForAll("integers") int element) {
        minHeap.push(element);
        assertEquals(minHeap.peek(), element);
    }

    @Property
    void testPushAndPopMinHeapEqualsToElementPush(@ForAll("integers") int element) {
        minHeap.push(element);
        assertEquals(minHeap.pop(), element);
    }

    @Property
    void testPushAndPopMinHeapRemoveInHeap(@ForAll("integers") int element) {
        minHeap.push(element);
        minHeap.pop();
        assertEquals(minHeap.count(), 0);
    }

    @Property
    void testPushAndPeekMaxHeap(@ForAll("integers") int element) {
        maxHeap.push(element);
        assertEquals(maxHeap.peek(), element);
    }

    @Property
    void testPushAndPopMaxHeapEqualsToElementPush(@ForAll("integers") int element) {
        maxHeap.push(element);
        assertEquals(maxHeap.pop(), element);
    }
    @Property
    void testPushAndPopMaxHeapRemoveInHeap(@ForAll("integers") int element) {
        maxHeap.push(element);
        maxHeap.pop();
        assertEquals(maxHeap.count(), 0);
    }

    @Property
    void testOrderInMinHeap(@ForAll("distinctIntegers") List<Integer> elements) {


        for(Integer integer : elements){
            minHeap.push(integer);
        }

        List<Integer> sorted = elements.stream()
                .sorted()
                .toList();

        boolean result = minHeap.count() == elements.size();
        for (Integer expected : sorted) {
            result =  result && minHeap.pop().equals(expected);
        }

        assertTrue(result && minHeap.count() == 0);
    }

    @Property
    void testOrderInMaxHeap(@ForAll("distinctIntegers") List<Integer> elements) {
        for(Integer integer : elements){
            maxHeap.push(integer);
        }

        List<Integer> sorted = elements.stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        boolean result = maxHeap.count() == elements.size();
        for (Integer expected : sorted) {
            result =  result && maxHeap.pop().equals(expected);
        }

        assertTrue(result && minHeap.count() == 0);
    }


}
