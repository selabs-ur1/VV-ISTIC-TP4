package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class BinaryHeapTest {

    @Property
    boolean popReturnsMinimum(@ForAll List<Integer> elements) {
        if(elements.isEmpty()) {
            return true;
        }

        Comparator<Integer> comparator = Comparator.naturalOrder();
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        elements.forEach(heap::push);

        Integer[] sortedElements = elements.toArray(new Integer[0]);
        Arrays.sort(sortedElements);

        for(int i = 0; i < sortedElements.length; i++) {
            Integer min = heap.pop();
            if(!min.equals(sortedElements[i])) {
                return false;
            }
        }
        return true;
    }

}
