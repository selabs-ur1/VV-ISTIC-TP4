package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private List<T> heap;
    private Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    // Retourne et enlève le minimum élément (racine) du tas
    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        // On récupère le minimum (racine) et on déplace le dernier élément à la racine
        T min = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            downHeapify(0); // Réajuster le tas
        }
        return min;
    }

    // Retourne le minimum élément sans l'enlever
    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    // Ajoute un nouvel élément dans le tas
    public void push(T element) {
        heap.add(element);
        upHeapify(heap.size() - 1); // Réajuster le tas
    }

    // Retourne le nombre d'éléments dans le tas
    public int count() {
        return heap.size();
    }

    // Méthode pour "faire remonter" un élément
    private void upHeapify(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && comparator.compare(heap.get(index), heap.get(parentIndex)) < 0) {
            // Échanger l'élément avec son parent
            T temp = heap.get(index);
            heap.set(index, heap.get(parentIndex));
            heap.set(parentIndex, temp);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    // Méthode pour "faire descendre" un élément
    private void downHeapify(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallest = index;

        // Vérifier si l'enfant gauche est plus petit
        if (leftChildIndex < heap.size() && comparator.compare(heap.get(leftChildIndex), heap.get(smallest)) < 0) {
            smallest = leftChildIndex;
        }

        // Vérifier si l'enfant droit est plus petit
        if (rightChildIndex < heap.size() && comparator.compare(heap.get(rightChildIndex), heap.get(smallest)) < 0) {
            smallest = rightChildIndex;
        }

        // Si le plus petit n'est pas l'élément actuel, échanger et continuer à faire descendre
        if (smallest != index) {
            T temp = heap.get(index);
            heap.set(index, heap.get(smallest));
            heap.set(smallest, temp);
            downHeapify(smallest);
        }
    }
}
