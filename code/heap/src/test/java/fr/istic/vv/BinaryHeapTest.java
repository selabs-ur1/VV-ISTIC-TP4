package fr.istic.vv;
import net.jqwik.api.*;

import java.util.*;

public class BinaryHeapTest {

    @Property
    void popReturnsMinimumElement(@ForAll List<Integer> elements) {
        if (elements.size() < 2) {
            // Ignorer les tests pour les tas avec moins de deux éléments
            return;
        }

        // Créer un tas binaire avec le comparateur d'ordre naturel
        BinaryHeap<Integer> binaryHeap;
        binaryHeap = new BinaryHeap<>(Comparator.naturalOrder());

        // Ajouter des éléments au tas
        for (Integer element : elements) {
            binaryHeap.push(element);
        }

        // Trier les éléments par ordre croissant (minimum à maximum)
        Collections.sort(elements);

        // Tester pop() pour s'assurer qu'il renvoie l'élément minimum à chaque fois
        for (Integer expectedMin : elements) {
            Integer actualMin = binaryHeap.pop();
            assert actualMin.equals(expectedMin);
        }

        // Après avoir retiré tous les éléments, le tas devrait être vide
        assert binaryHeap.count() == 0;

    }
}
