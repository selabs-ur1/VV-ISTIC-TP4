package fr.istic.vv;
import java.util.Comparator;

public class Sorting {

    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (comparator.compare(array[i - 1], array[i]) > 0) {
                    // Swap elements if they are in the wrong order
                    T temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        return array;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        quicksort(array, 0, array.length - 1, comparator);
        return array;
    }

    private static <T> void quicksort(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int partitionIndex = partition(array, low, high, comparator);

            quicksort(array, low, partitionIndex - 1, comparator);
            quicksort(array, partitionIndex + 1, high, comparator);
        }
    }

    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;

                // Swap elements at i and j
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap pivot with the element at i+1
        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        if (array.length > 1) {
            int mid = array.length / 2;

            T[] leftArray = java.util.Arrays.copyOfRange(array, 0, mid);
            T[] rightArray = java.util.Arrays.copyOfRange(array, mid, array.length);

            mergesort(leftArray, comparator);
            mergesort(rightArray, comparator);

            merge(array, leftArray, rightArray, comparator);
        }

        return array;
    }

    private static <T> void merge(T[] array, T[] leftArray, T[] rightArray, Comparator<T> comparator) {
        int i = 0, j = 0, k = 0;

        while (i < leftArray.length && j < rightArray.length) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < leftArray.length) {
            array[k++] = leftArray[i++];
        }

        while (j < rightArray.length) {
            array[k++] = rightArray[j++];
        }
    }
}
