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

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        quicksort(array, 0, array.length - 1, comparator);
        return array;
    }

    private static <T> void quicksort(T[] array, int lo, int hi, Comparator<T> comparator) {
        if (lo >= hi || lo < 0) {
            return;
        }

        int p = partition(array, lo, hi, comparator);

        quicksort(array, lo, p - 1, comparator);
        quicksort(array, p + 1, hi, comparator);
    }

    private static <T> int partition(T[] array, int lo, int hi, Comparator<T> comparator) {
        T pivot = array[hi];
        int i = lo;

        for (int j = lo; j < hi; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        }

        T temp = array[i];
        array[i] = array[hi];
        array[hi] = temp;

        return i;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        T[] workArray = array.clone();
        topDownMergeSort(array, workArray, 0, array.length, comparator);
        return array;
    }

    private static <T> void topDownMergeSort(T[] array, T[] workArray, int lo, int hi, Comparator<T> comparator) {
        System.arraycopy(array, lo, workArray, lo, hi - lo);
        topDownSplitMerge(workArray, lo, hi, array, comparator);
    }

    private static <T> void topDownSplitMerge(T[] workArray, int lo, int hi, T[] array, Comparator<T> comparator) {
        if (hi - lo <= 1) {
            return;
        }

        int middle = (hi + lo) / 2;

        topDownSplitMerge(array, lo, middle, workArray, comparator);
        topDownSplitMerge(array, middle, hi, workArray, comparator);
        topDownMerge(workArray, lo, middle, hi, array, comparator);
    }

    private static <T> void topDownMerge(T[] workArray, int lo, int middle, int hi, T[] array, Comparator<T> comparator) {
        int i = lo, j = middle;

        for (int k = lo; k < hi; k++) {
            if (i < middle && (j >= hi || comparator.compare(workArray[i], workArray[j]) <= 0)) {
                array[k] = workArray[i];
                i++;
            } else {
                array[k] = workArray[j];
                j++;
            }
        }
    }
}
