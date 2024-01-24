package fr.istic.vv;

import com.sun.source.tree.BinaryTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    ArrayList<T> heapArr;
    private final Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.heapArr = new ArrayList();
        this.comparator = comparator;
    }

    /**
     * Source Wikipedia
     * The procedure for deleting the root from the heap (effectively extracting the maximum element in a max-heap or the minimum element in a min-heap) while retaining the heap property is as follows:
     *
     *     Replace the root of the heap with the last element on the last level.
     *     Compare the new root with its children; if they are in the correct order, stop.
     *     If not, swap the element with one of its children and return to the previous step. (Swap with its smaller child in a min-heap and its larger child in a max-heap.)
     * @return
     */
    public T pop() {
        if (heapArr.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        T minElement = heapArr.get(0);
        int lastIndex = heapArr.size() - 1;
        T lastElement = heapArr.remove(lastIndex);

        if (lastIndex > 0) {
            heapArr.set(0, lastElement);
            heapifyDown(0);
        }

        return minElement;
    }

    /**
     * Source : wikipedia
     * To add an element to a heap, we can perform this algorithm:
     *
     *     Add the element to the bottom level of the heap at the leftmost open space.
     *     Compare the added element with its parent; if they are in the correct order, stop.
     *     If not, swap the element with its parent and return to the previous step.
     */
    public void push(T element) {
        heapArr.add(element);
        heapifyUp(heapArr.size() - 1);
    }

    /**
     * Look at the top of the heap
     * @return the top element
     */
    public T peek() {
        if (heapArr.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heapArr.get(0);
    }

    /**
     * @return the size of the heap
     */
    public int count() {
        return heapArr.size();
    }


    /**
     * Swap node with it's parent when  current < parent
     */
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            T current = heapArr.get(index);
            T parent = heapArr.get(parentIndex);

            if (comparator.compare(current, parent) >= 0) {
                return; // The heap property is satisfied
            }

            // Swap current and parent
            heapArr.set(index, parent);
            heapArr.set(parentIndex, current);

            index = parentIndex;
        }
    }

    /**
     * Swap node with it children
     */
    private void heapifyDown(int index) {
        int leftChildIndex, rightChildIndex, minIndex;
        while (true) {
            leftChildIndex = 2 * index + 1;
            rightChildIndex = 2 * index + 2;
            minIndex = index;

            if (leftChildIndex < heapArr.size() && comparator.compare(heapArr.get(leftChildIndex), heapArr.get(minIndex)) < 0) {
                minIndex = leftChildIndex;
            }

            if (rightChildIndex < heapArr.size() && comparator.compare(heapArr.get(rightChildIndex), heapArr.get(minIndex)) < 0) {
                minIndex = rightChildIndex;
            }

            if (minIndex == index) {
                return; // The heap property is satisfied
            }

            // Swap current and minIndex
            T temp = heapArr.get(index);
            heapArr.set(index, heapArr.get(minIndex));
            heapArr.set(minIndex, temp);

            index = minIndex;
        }
    }

}