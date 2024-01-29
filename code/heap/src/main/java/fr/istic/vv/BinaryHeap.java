package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryHeap<T> {

    private final List<T> heap;
    private final Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public T pop() {
        T min = heap.get(0);
        T last = heap.remove(heap.size() - 1);

        if(!heap.isEmpty()) {
            heap.set(0, last);
            down(0);
        }
        return min;
    }

    private void down(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallestIndex = index;

        if(leftChildIndex < heap.size() && comparator.compare(heap.get(leftChildIndex), heap.get(smallestIndex)) < 0) {
            smallestIndex = leftChildIndex;
        }

        if(rightChildIndex < heap.size() && comparator.compare(heap.get(rightChildIndex), heap.get(smallestIndex)) < 0) {
            smallestIndex = rightChildIndex;
        }

        if(smallestIndex != index) {
            swap(index, smallestIndex);
            down(smallestIndex);
        }
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T element) {
        heap.add(element);
        int index = heap.size() - 1;
        while(index > 0) {
            int parentIndex = (index - 1) / 2;
            if(comparator.compare(heap.get(index), heap.get(parentIndex)) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
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

    public int count() {
        return heap.size();
    }

}