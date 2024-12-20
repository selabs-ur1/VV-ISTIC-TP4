package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private final ArrayList<T> heap;
    private final Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Le tas est vide.");
        }

        T min = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            bubbleDown(0);
        }

        return min;
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Le tas est vide.");
        }
        return heap.get(0);
    }

    public void push(T element) {
        heap.add(element);
        bubbleUp(heap.size() - 1);
    }

    public int count() {
        return heap.size();
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            T current = heap.get(index);
            T parent = heap.get(parentIndex);

            if (comparator.compare(current, parent) >= 0) {
                break;
            }

            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void bubbleDown(int index) {
        int size = heap.size();
        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            if (leftChild < size && comparator.compare(heap.get(leftChild), heap.get(smallest)) < 0) {
                smallest = leftChild;
            }

            if (rightChild < size && comparator.compare(heap.get(rightChild), heap.get(smallest)) < 0) {
                smallest = rightChild;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}