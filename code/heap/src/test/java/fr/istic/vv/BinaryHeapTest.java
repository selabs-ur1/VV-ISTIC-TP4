package fr.istic.vv;

import net.jqwik.api.*;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class BinaryHeapTest {

    @Property
    void testPopMinimum(@ForAll("randomIntegers") Integer[] input) {
        Comparator<Integer> comparator = Integer::compareTo;

        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        for (Integer value : input) {
            heap.push(value);
        }

        ArrayList<Integer> copyList= new ArrayList<>();
        Collections.addAll(copyList, input);

        while (heap.count() > 0) {
            Integer popValue = heap.pop();

            Integer expected = Collections.min(copyList);

            System.out.println("popValue: " + popValue + " expected: " + expected);
            assert popValue.equals(expected) : "pop() ne renvoie pas la valeur minimum attendue.";

            copyList.remove(expected);
        }
    }

    @Provide
    Arbitrary<Integer[]> randomIntegers() {
        return Arbitraries.integers()
                .array(Integer[].class)
                .ofMinSize(1)
                .ofMaxSize(10);
    }
}