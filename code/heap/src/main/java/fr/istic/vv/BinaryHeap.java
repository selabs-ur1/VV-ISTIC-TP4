package fr.istic.vv;

import java.util.Comparator;

public class BinaryHeap<T> {

    private Comparator<T> comparator;
    private T[] heap;

    private int size;

    private int capacity;

    public BinaryHeap(Comparator<T> comparator, int capacity) {
        this.comparator = comparator;
        this.capacity = capacity;
        this.size = 0;
        this.heap = (T[]) new Object[capacity];
    }

    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        T result = heap[0];
        heap[0] = heap[size - 1];
        size--;

        int current = 0;
        int left = 2 * current + 1;
        int right = 2 * current + 2;
        int smallest = current;

        while (left < size) {
            if (comparator.compare(heap[left], heap[smallest]) < 0) {
                smallest = left;
            }

            if (right < size && comparator.compare(heap[right], heap[smallest]) < 0) {
                smallest = right;
            }

            if (smallest == current) {
                break;
            }

            swap(current, smallest);
            current = smallest;
            left = 2 * current + 1;
            right = 2 * current + 2;
        }

        return result;
    }

    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        return heap[0];
    }

    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public void push(T element) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full");
        }

        heap[size] = element;
        size++;

        int current = size - 1;
        int parent = (current - 1) / 2;

        while (current > 0 && comparator.compare(heap[current], heap[parent]) < 0) {
            swap(current, parent);
            current = parent;
            parent = (current - 1) / 2;
        }
    }

    // la méthode count() doit retourner le nombre d'éléments dans le tas
    public int count() {
        return size;
    }

}