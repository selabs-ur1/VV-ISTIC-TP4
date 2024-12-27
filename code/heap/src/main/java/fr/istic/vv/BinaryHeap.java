package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private final Comparator<T> comparator;
    private final List<T> heap;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public T pop() {
        if (this.heap.isEmpty()) throw new NoSuchElementException("Heap is empty.");
        return this.heap.remove(this.heap.size() - 1);
    }

    public T peek() {
        if (this.heap.isEmpty()) throw new NoSuchElementException("Heap is empty.");
        return this.heap.get(this.heap.size() - 1);
    }

    public void push(T element) {
        boolean added = false;
        for (int i = 0; i < heap.size(); i++) {
            if (comparator.compare(element, heap.get(i)) > 0) {
                heap.add(i, element);
                added = true;
                break;
            }
        }
        if (!added) heap.add(element);
    }

    public int count() { return heap.size(); }

}