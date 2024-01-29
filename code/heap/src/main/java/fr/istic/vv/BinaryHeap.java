package fr.istic.vv;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T> {

    private List<T> heap;
    private Comparator<? super T> comparator;

    // Constructeur pour créer un tas binaire avec un comparateur spécifié
    public BinaryHeap(Comparator<? super T> comparator) {
        heap = new ArrayList<>();
        this.comparator = comparator;
    }


    // Retourne et supprime l'élément minimum du tas binaire
    public T pop() {
        if (heap.isEmpty()) {
            return null;
        }

        T element = heap.get(0);
        T lastElement = heap.get(heap.size() - 1);
        heap.remove(heap.size() - 1);

        if (heap.size() > 0) {
            // Remplace la racine du tas par le dernier élément sur le dernier niveau
            heap.set(0, lastElement);
            heapifyDown(0);
        }

        return element;
    }

    // Retourne l'élément minimum du tas binaire sans le supprimer
    public T peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    // Ajoute un élément au tas binaire
    public void push(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    // Retourne le nombre d'éléments dans le tas binaire
    public int count() {
        return heap.size();
    }

    // Réorganise les éléments vers le bas pour maintenir la propriété du tas binaire
    private void heapifyDown(int index) {
        int leftIndex = index * 2 + 1;
        int rightIndex = index * 2 + 2;
        int smallestIndex = index;

        if (leftIndex < heap.size() && comparator.compare(heap.get(leftIndex), heap.get(smallestIndex)) < 0) {
            smallestIndex = leftIndex;
        }

        if (rightIndex < heap.size() && comparator.compare(heap.get(rightIndex), heap.get(smallestIndex)) < 0) {
            smallestIndex = rightIndex;
        }

        if (smallestIndex != index) {
            swap(index, smallestIndex);
            heapifyDown(smallestIndex);
        }
    }

    // Réorganise les éléments vers le haut pour maintenir la propriété du tas binaire
    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;

        if (index > 0 && comparator.compare(heap.get(index), heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    // Échange deux éléments dans le tas binaire
    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    // Méthode toString pour afficher le tas binaire sous forme de tableau
    @Override
    public String toString() {
        int index = 0;
        int span = 1;

        StringBuilder result = new StringBuilder();

        while (index < heap.size()) {
            for (int i = index; i < index + span && i < heap.size(); i++) {
                result.append(heap.get(i)).append(" ");
            }
            result.append("\n");
            index += span;
            span *= 2;
        }

        return result.toString();
    }
}
