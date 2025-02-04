package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

// https://pages.cs.wisc.edu/~mcw/cs367/lectures/heaps.html
// https://www.geeksforgeeks.org/binary-heap/
public class BinaryHeap<T> {

    private final ArrayList<T> heap;
    private final Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        if (heap.size() == 1) {
            return heap.removeFirst();
        }

        // First value is the minimal one, by definition
        final T firstValue = heap.getFirst();

        heap.set(0, heap.removeLast());
        minHeapify(0);

        return firstValue;
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.getFirst();
    }

    public void push(T element) {
        int i = heap.size();
        heap.add(element);

        // Fix the min heap property if it is violated
        while (i != 0 && comparator.compare(heap.get(i), heap.get(parent(i))) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public int count() {
        return heap.size();
    }

    // A recursive method to heapify a subtree
    // with the root at given index
    // This method assumes that the subtrees
    // are already heapified
    private void minHeapify(int key) {
        int l = left(key);
        int r = right(key);

        int smallest = key;
        if (l < heap.size() && comparator.compare(heap.get(l), heap.get(smallest)) < 0) {
            smallest = l;
        }
        if (r < heap.size() && comparator.compare(heap.get(r), heap.get(smallest)) < 0) {
            smallest = r;
        }

        if (smallest != key) {
            swap(key, smallest);
            minHeapify(smallest);
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Get the Parent index for the given index
    private int parent(int key) {
        return (key - 1) / 2;
    }

    // Get the Left Child index for the given index
    private int left(int key) {
        return 2 * key + 1;
    }

    // Get the Right Child index for the given index
    private int right(int key) {
        return 2 * key + 2;
    }
}
