package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryHeap<T> {


    private final Comparator<T> comparator;
    private final List<T> heap;
    public BinaryHeap(Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException();
        }
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }


    public T pop() {
        if (heap.isEmpty()) {
            throw new IllegalStateException();
        }

        T result = heap.getFirst();
        T lastElement = heap.removeLast();

        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }

        return result;
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new IllegalStateException();
        }
        return heap.getFirst();
    }

    public void push(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public int count() {
        return heap.size();
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        if (parentIndex >= 0 && comparator.compare(heap.get(index), heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    private void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallest = index;

        if (leftChild < heap.size() && comparator.compare(heap.get(leftChild), heap.get(smallest)) < 0) {
            smallest = leftChild;
        }
        if (rightChild < heap.size() && comparator.compare(heap.get(rightChild), heap.get(smallest)) < 0) {
            smallest = rightChild;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

}