package fr.istic.vv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

public class BinaryHeapTest {

    // comparateur pour les tests
    private final Comparator<Integer> comparator = Integer::compareTo;

    @Property
    boolean testPopReturnsMinimumElement(@ForAll List<Integer> elements) {
        // création du binaryHeap
        BinaryHeap<Integer> bHeap = new BinaryHeap<>(comparator);
        
        //List des élements triés pour la verification
        List<Integer> sortedElements = new ArrayList<>(elements);
        Collections.sort(sortedElements);

        // Ajout des élements dans le binaryHeap
        for (Integer element : elements) {
            bHeap.push(element);
        }

        // Tant que le binaryHeap n'est pas vide on dépile.
        while (bHeap.count() > 0) {

            Integer poppedElementHeap = bHeap.pop();
            Integer poppedElementList = sortedElements.remove(0);
            if(!Objects.equals(poppedElementHeap, poppedElementList) && poppedElementHeap<=bHeap.peek()){
                return false;
            }
        }
        return true;
    }

}
