package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import java.util.Comparator;
import java.util.List;

public class BinaryHeapTest {

    // Comparateur pour les entiers
    private Comparator<Integer> comparator = Integer::compareTo;

    // Test pour vérifier que pop() renvoie toujours le plus petit élément
    @Property
    boolean popReturnsMinimumElement(@ForAll @IntRange(min = 0, max = 100) List<Integer> elements) {
        // Créer un tas binaire
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        // Ajouter tous les éléments dans le tas
        for (int element : elements) {
            heap.push(element);
        }

        // Vérifier que l'élément retourné par pop() est toujours le plus petit restant
        Integer previousMin = null;
        while (heap.count() > 0) {
            Integer currentMin = heap.pop();

            // Vérifier que l'élément extrait est plus petit que ou égal à l'élément précédent
            if (previousMin != null) {
                if (comparator.compare(currentMin, previousMin) < 0) {
                    return false; // Si l'élément n'est pas plus grand ou égal à l'élément précédent, échouer le test
                }
            }

            previousMin = currentMin;
        }

        return true;
    }

    // Test pour vérifier que peek() ne modifie pas le tas
    @Property
    boolean peekDoesNotModifyHeap(@ForAll @IntRange(min = 0, max = 100) List<Integer> elements) {
        // Si la liste est vide, ignorer le test
        if (elements.isEmpty()) {
            return true;
        }

        // Créer un tas binaire
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        // Ajouter tous les éléments dans le tas
        for (int element : elements) {
            heap.push(element);
        }

        // Sauvegarder l'élément minimum actuel
        Integer originalPeek = heap.peek();

        // Récupérer l'élément minimum sans le retirer
        Integer peekedElement = heap.peek();

        // Vérifier que peek() retourne bien le même élément sans le supprimer
        if (!peekedElement.equals(originalPeek)) {
            return false;
        }

        // Vérifier que la taille du tas n'a pas changé après un peek()
        return heap.count() == elements.size();
    }


    // Test pour vérifier que count() retourne le bon nombre d'éléments
    @Property
    boolean countReturnsCorrectNumber(@ForAll @IntRange(min = 0, max = 100) List<Integer> elements) {
        // Créer un tas binaire
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        // Ajouter tous les éléments dans le tas
        for (int element : elements) {
            heap.push(element);
        }

        // Vérifier que count() retourne bien le nombre d'éléments dans le tas
        return heap.count() == elements.size();
    }

}
