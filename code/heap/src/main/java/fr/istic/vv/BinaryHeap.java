package fr.istic.vv;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {
    private final Comparator<T> comparator;
    private final List<T> heap;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public void push(T element) {
        heap.add(element);
        siftUp(heap.size() - 1);
    }

    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T minElement = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            siftDown(0);
        }
        return minElement;
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public int count() {
        return heap.size();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (comparator.compare(heap.get(index), heap.get(parentIndex)) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        int leftChildIndex;
        int rightChildIndex;
        int smallest;

        while ((leftChildIndex = 2 * index + 1) < heap.size()) {
            rightChildIndex = leftChildIndex + 1;
            smallest = index;

            if (comparator.compare(heap.get(leftChildIndex), heap.get(smallest)) < 0) {
                smallest = leftChildIndex;
            }
            if (rightChildIndex < heap.size() && comparator.compare(heap.get(rightChildIndex), heap.get(smallest)) < 0) {
                smallest = rightChildIndex;
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
