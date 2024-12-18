package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Comparator;
//import assert true
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BinaryHeapTest {
    /*@Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }*/
    @Provide
    Arbitrary<BinaryHeap<Integer>> randomIntHeap() {
        return Arbitraries.integers().array(Integer[].class).ofMinSize(0).ofMaxSize(100).map(array -> {
            BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.comparingInt(i -> i));
            for (Integer i : array) {
                heap.push(i);
            }
            return heap;
        });
    }
    @Provide
    Arbitrary<BinaryHeap<String>> randomStringHeap() {
        return Arbitraries.strings().array(String[].class).ofMinSize(0).ofMaxSize(100).map(array -> {
            BinaryHeap<String> heap = new BinaryHeap<>(String::compareTo);
            for (String i : array) {
                heap.push(i);
            }
            return heap;
        });
    }
    @Property
    //ensures that the element returned by pop every time it is invoked is the minimum of the remaining elements in the heap.
    void popReturnsMinimumElement(@ForAll("randomIntHeap") BinaryHeap<Integer> heap) {
        if (heap.size == 0) {
            return;
        }
        Integer min = heap.pop();
        while ((heap.size) > 0) {
            Integer next = heap.pop();
            assertTrue(min <= next);
            min = next;
        }
    }
    @Property
    //ensures that the element returned by pop every time it is invoked is the minimum of the remaining elements in the heap.
    void popReturnsMinimumElementString(@ForAll("randomStringHeap") BinaryHeap<String> heap) {
        if (heap.size == 0) {
            return;
        }
        String min = heap.pop();
        while ((heap.size) > 0) {
            String next = heap.pop();
            assertTrue(min.compareTo(next) <= 0);
            min = next;
        }
    }
}
