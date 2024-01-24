package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    /**
     * Apply bubble sort to the array
     * @param array array to sort
     * @param comparator
     * @return the array sorted
     * @param <T> type inside the array
     */
    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (comparator.compare(array[i - 1], array[i]) > 0) {
                    T temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
        return array;
    }

    /**
     * Use quicksortHelper and findPivot to perform the quicksort (recursive method)
     * @param array array to sort
     * @param comparator
     * @return the array sorted
     * @param <T>
     */
    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        quicksortHelper(array, 0, array.length - 1, comparator);
        return array;
    }

    /**
     * Find the pivot and perform the recursive sort
     * @param array
     * @param low
     * @param high
     * @param comparator
     * @param <T>
     */
    private static <T> void quicksortHelper(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivotIndex = findPivot(array, low, high, comparator);
            quicksortHelper(array, low, pivotIndex - 1, comparator);
            quicksortHelper(array, pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Find the pivot to perform quicksort
     * @param array
     * @param low
     * @param high
     * @param comparator
     * @return
     * @param <T>
     */
    private static <T> int findPivot(T[] array, int low, int high, Comparator<T> comparator) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    /**
     * Recursive method to apply mergeSort (call merge to merge both side)
     * @param array
     * @param comparator
     * @return
     * @param <T>
     */
    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        if (array.length > 1) {
            //Split the array in half
            int mid = array.length / 2;
            T[] left = Arrays.copyOfRange(array, 0, mid);
            T[] right = Arrays.copyOfRange(array, mid, array.length);

            //recursivity on each side to sort
            mergesort(left, comparator);
            mergesort(right, comparator);

            merge(array, left, right, comparator);
        }
        return array;
    }

    /**
     * Merge 2 array and sort it
     * @param array
     * @param left
     * @param right
     * @param comparator
     * @param <T>
     */
    private static <T> void merge(T[] array, T[] left, T[] right, Comparator<T> comparator) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
}
