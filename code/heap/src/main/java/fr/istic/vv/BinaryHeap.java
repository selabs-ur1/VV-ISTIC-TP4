package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;

public class BinaryHeap<T> {

    private Comparator<T> comparator;
    private ArrayList<T> heap;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public T pop() {
        if (heap.isEmpty())
            return null;

        T root = heap.get(0);
        T last = heap.remove(heap.size() - 1);

        if (heap.isEmpty())
            return root;

        heap.set(0, last);
        siftDown(0);
        return root;
    }

    public T peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    public void push(T element) {
        heap.add(element);
        int index = heap.size() - 1;
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (comparator.compare(heap.get(index), heap.get(parent)) < 0) {
                swap(index, parent);
                index = parent;
            } else
                break;
        }
    }

    public int count() {
        return heap.size();
    }

    private void siftDown(int index) {
        int smallest;
        int leftChild;
        int rightChild;

        while (index < heap.size() / 2) {
            smallest = index;
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;

            if (leftChild < heap.size() && comparator.compare(heap.get(leftChild), heap.get(smallest)) < 0)
                smallest = leftChild;
            if (rightChild < heap.size() && comparator.compare(heap.get(rightChild), heap.get(smallest)) < 0)
                smallest = rightChild;
            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else
                break;

        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

}