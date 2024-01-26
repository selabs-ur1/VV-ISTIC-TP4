package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;


public class BinaryHeapTest {

    /** False with -2147483648
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }
    */
    @Property
    public void popReturnsMinElement(@ForAll @Unique List<@Positive Integer> elements){
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
