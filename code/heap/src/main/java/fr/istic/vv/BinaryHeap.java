package fr.istic.vv;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A `BinaryHeap` implementation
 *
 * @param <T>
 */
public class BinaryHeap<T> {

    private final Comparator<T> comparator;
    private T[] heap;

    /**
     * A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
     *
     * @param comparator
     */
    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = (T[]) new Object[]{};
    }

    /**
     * `pop` returns and removes the minimum object in the heap.
     * If the heap is empty it throws a `NotSuchElementException`.
     *
     * @return
     */
    public T pop() {
        if (this.heap.length == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        T ret = this.heap[0];
        if (this.heap.length == 1) {
            this.heap = (T[]) new Object[]{};
        } else {
            this.heap[0] = this.heap[this.heap.length - 1];
            this.heap = Arrays.copyOf(heap, heap.length - 1);
            heapify(0, comparator);
//            for (int i = heap.length / 2 - 1; i >= 0; i--) {
//                heapify(i, comparator);
//            }
        }
        return ret;
    }

    /**
     * `peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
     *
     * @return
     */
    public T peek() {
        return this.heap[0];
    }

    /**
     * `push` adds an element to the `BinaryHeap`.
     *
     * @param element
     */
    public void push(T element) {
        this.heap = Arrays.copyOf(heap, heap.length + 1);
        int index = this.heap.length - 1;
        heap[index] = element;
        if (this.heap.length > 1) {
            for (int i = heap.length / 2 - 1; i >= 0; i--) {
                heapify(i, comparator);
            }
            while (index > 0 && comparator.compare(heap[(index - 1) / 2], heap[index]) > 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }
    }

    /**
     * `count` returns the number of elements in the `BinaryHeap`.
     *
     * @return
     */
    public int count() {
        return this.heap.length;
    }

    private void heapify(int smallest, Comparator<T> comparator) {
        int i = smallest;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < this.heap.length && comparator.compare(this.heap[left], this.heap[smallest]) < 0) {
            smallest = left;
        }
        if (right < this.heap.length && comparator.compare(this.heap[right], this.heap[smallest]) < 0) {
            smallest = right;
        }
        if (smallest != i) {
            this.swap(i, smallest);
            this.heapify(smallest, comparator);
        }
    }

    /**
     * Utilitary method for swapping elements at indexes
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    /**
     * Utilitary method to print the heap inline
     */
    public void print() {
        System.out.println(Arrays.toString(heap));
    }

}