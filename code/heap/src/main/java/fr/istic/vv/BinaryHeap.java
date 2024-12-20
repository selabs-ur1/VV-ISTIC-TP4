package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Minimum binary heap data structure.
 *
 * @param <T> Type of heap keys.
 */
public class BinaryHeap<T> {

    private final Comparator<T> comparator;
    private final List<T> heap = new ArrayList<>();

    /**
     * Constructor of the class.
     *
     * @param comparator Comparator used to compare keys.
     */
    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Pops the smallest key of the heap, removing the element from the heap.
     *
     * @return The smallest key of the heap.
     */
    public T pop() {
        int count = heap.size();

        if (count == 0)
            throw new NoSuchElementException("the heap is currently empty");

        T result = heap.remove(0);
        if (--count == 0)
            return result;

        // We move the last element of the heap to the root.
        T lastElement = heap.remove(count - 1);
        if (heap.isEmpty())
            heap.add(lastElement);
        else
            heap.add(0, lastElement);

        // Then, we "percolate it down" to preserve heap property.

        int currIndex = 0;              // Newly added element index.
        int leftIndex = 1;      // Index of its child parent.
        int rightIndex = 2;
        T currKey = heap.get(currIndex);      // Key of newly added element.

        // Swap current key and child with the smaller key, until we reach a leaf.
        while (leftIndex < count || rightIndex < count) {
            int indexToSwap;    // Index of the key to swap with the current key.
            if (leftIndex < count && rightIndex < count) {
                // If there is two child, choose the smallest child.
                int leftRightCmp =
                        comparator.compare(heap.get(leftIndex), heap.get(rightIndex));
                indexToSwap = leftRightCmp < 0 ? leftIndex : rightIndex;

                // If the child key is bigger than the current key, use the other child key.
                if (comparator.compare(currKey, heap.get(indexToSwap)) < 0)
                    indexToSwap = leftRightCmp < 0 ? rightIndex : leftIndex;
            } else if (rightIndex >= count) // ...otherwise choose the only possible child.
                indexToSwap = leftIndex;
            else
                indexToSwap = rightIndex;

            T valueToSwap = heap.get(indexToSwap);

            // If the chosen key is still greater than the current key, stop the percolation.
            if (comparator.compare(currKey, valueToSwap) < 0)
                break;

            // Swap the keys.
            heap.set(currIndex, valueToSwap);
            heap.set(indexToSwap, currKey);

            // Update indices.
            currIndex = indexToSwap;
            leftIndex = currIndex * 2 + 1;
            rightIndex = currIndex * 2 + 2;
        }

        return result;
    }

    /**
     * Get the smallest key of the heap.
     *
     * @return The smallest key of the heap.
     */
    public T peek() {
        if (heap.isEmpty())
            throw new NoSuchElementException("the heap is currently empty");

        return heap.get(0);
    }

    /**
     * Push a key to the heap.
     *
     * @param element Key to push to the heap.
     */
    public void push(T element) {
        // Add the element to the heap.
        heap.add(element);

        int count = heap.size();
        int currIndex = count - 1;  // Newly added element index.
        int parentIndex;            // Index of its parent.
        T currKey;                  // Key of newly added element.
        T parentKey;                // Key of its parent.

        // Swapping current and parent until we reach the root or the tree or we encounter a smaller
        // parent key.
        while (currIndex != 0) {
            parentIndex = (currIndex - 1) / 2;

            currKey = heap.get(currIndex);
            parentKey = heap.get(parentIndex);

            // We stop if the parent's key is smaller.
            if (comparator.compare(currKey, parentKey) >= 0)
                break;

            // ...otherwise we swap the keys.
            heap.set(currIndex, parentKey);
            heap.set(parentIndex, currKey);

            currIndex = parentIndex;
        }
    }

    /**
     * @return The size of the heap.
     */
    public int count() {
        return heap.size();
    }

}