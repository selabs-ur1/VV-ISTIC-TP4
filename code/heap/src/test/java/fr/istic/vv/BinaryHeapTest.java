package fr.istic.vv;
import net.jqwik.api.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class BinaryHeapTest {

    @Property
    boolean testHeapPopMaintainsMinProperty(@ForAll("randomIntegers") List<Integer> elements) {
        // On crée un BinaryHeap avec un comparateur pour Integer
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Comparator.naturalOrder());

        // On ajoute tous les éléments au tas
        elements.forEach(heap::push);

        // On garde une copie triée des éléments
        List<Integer> sortedElements = new ArrayList<>(elements);
        sortedElements.sort(Comparator.naturalOrder());

        // On effectue des appels à pop et on vérifie que l'élément retourné est toujours le minimum
        for (Integer expectedMin : sortedElements) {
            Integer actualMin = heap.pop();
            if(!actualMin.equals(expectedMin)){
                return false;
            }
        }
        return true;
    }

    //provider a list of random number
    @Provide
    Arbitrary<List<Integer>> randomIntegers() {
        return Arbitraries.integers().list();
    }
}
