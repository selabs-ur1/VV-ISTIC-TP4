package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private final ArrayList<T> heap;
    private final Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        // On utilise un ArrayList pour stocker les éléments
        this.heap = new ArrayList<>();
    }

    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        // On récupère le premier élément (le plus petit)
        T result = heap.get(0);
        int lastIndex = heap.size() - 1;
        if (lastIndex > 0) {
            heap.set(0, heap.get(lastIndex));
        }
        heap.remove(lastIndex);
        heapifyDown(0);
        return result;
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public void push(T element) {
        heap.add(element);
        int currentIndex = heap.size() - 1;
        heapifyUp(currentIndex);
    }

    public int count() {
        return heap.size();
    }

    private void heapifyUp(int index) {
        if (heap.isEmpty()) return;
        int parent = (index - 1) / 2;
        boolean swappedSomething = false;
        while (index > 0 && comparator.compare(heap.get(index), heap.get(parent)) < 0) {
            swap(index, parent);
            swappedSomething = true;
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {

        boolean continueHeapify = true;
        int size = heap.size();
        while (continueHeapify) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && comparator.compare(heap.get(left), heap.get(smallest)) < 0) {
                smallest = left;
            }
            if (right < size && comparator.compare(heap.get(right), heap.get(smallest)) < 0) {
                smallest = right;
            }

            if (smallest == index) {

                continueHeapify = false;
            } else {
                swap(index, smallest);
                index = smallest;
            }
        }
    }

    private void swap(int i, int j) {
        T tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

}
