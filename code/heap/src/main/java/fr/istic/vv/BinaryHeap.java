package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private final List<T> heap;
    private final Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T root = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            moveItemDown(0);
        }
        return root;
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public void push(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null element not allowed");
        }
        heap.add(element); // on ajoute à la fin de la liste
        moveItemUp(heap.size() - 1); // on le remonte la place de son parent si nécéssaire 

    }

    public int count() {
        return heap.size();
    }

    private void swap(int i, int j) {
        T tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    private void moveItemUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && comparator.compare(heap.get(index), heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void moveItemDown(int index) {
        int size = heap.size();
        int leftChildIndex;
        int rightChildIndex;
        int smallestChildIndex;
        while ((leftChildIndex = 2 * index + 1) < size) {
            rightChildIndex = leftChildIndex + 1;
            smallestChildIndex = leftChildIndex;

            if (rightChildIndex < size && comparator.compare(heap.get(rightChildIndex),heap.get(leftChildIndex)) < 0) {
                smallestChildIndex = rightChildIndex;
            }

            if (comparator.compare(heap.get(index),heap.get(smallestChildIndex)) <= 0) {
                break;
            }

            swap(index, smallestChildIndex);
            index = smallestChildIndex;
        }
    }


    public void displayHeap() {
        System.out.print("[");
        for (T element : heap) {
            System.out.print(element + " ,");
        }
        System.out.println("]"); // Pour passer à la ligne après l'affichage
    }


}