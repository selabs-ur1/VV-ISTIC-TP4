package fr.istic.vv;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class BinaryHeap<T> {
    private final PriorityQueue<T> heap;

    public BinaryHeap(Comparator<T> comparator) {
        this.heap = new PriorityQueue<>(comparator);
    }

    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.poll();
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.peek();
    }

    public void push(T element) {
        heap.offer(element);
    }

    public int count() {
        return heap.size();
    }
}
