package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {
    private final Comparator<T> comparator;
    private final ArrayList<T> heap;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Le tas est vide");
        }

        T min = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            down(0);
        }
        return min;
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Le tas est vide");
        }
        return heap.get(0);
    }

    public void push(T element) {
        heap.add(element);
        up(heap.size() - 1);
    }

    public int count() { return heap.size(); }

    private void down(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int min = i;

        if (left < heap.size() && comparator.compare(heap.get(left), heap.get(min)) < 0) {
            min = left;
        }

        if (right < heap.size() && comparator.compare(heap.get(right), heap.get(min)) < 0) {
            min = right;
        }

        if (min != i) {
            swap(i, min);
            down(min);
        }
    }

    private void up(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (comparator.compare(heap.get(i), heap.get(parent)) >= 0) {
                break;
            }
            swap(i, parent);
            i = parent;
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

}