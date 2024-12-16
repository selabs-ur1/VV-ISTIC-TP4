package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeap<T> {
    private final Comparator<T> comparator;
    private final ArrayList<T> heap;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        // Swap the first element with the last element
        T min = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    public void push(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public int count() {
        return heap.size();
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && comparator.compare(heap.get(index), heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallest = index;

        if (leftChildIndex < heap.size() && comparator.compare(heap.get(leftChildIndex), heap.get(smallest)) < 0) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < heap.size() && comparator.compare(heap.get(rightChildIndex), heap.get(smallest)) < 0) {
            smallest = rightChildIndex;
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
