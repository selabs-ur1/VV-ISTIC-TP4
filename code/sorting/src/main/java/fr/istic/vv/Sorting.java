package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {
        int i, j;
        T temp;
        boolean swapped;
        for (i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (j = 0; j < array.length - i - 1; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
        return null;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
        quicksort(array, 0, array.length - 1, comparator);
        return array;
    }

    private static <T> void quicksort(T[] array, int low, int high, Comparator<T> comparator) {
        if (low >= high) return;

        T pivot = array[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        }

        array[high] = array[i];
        array[i] = pivot;

        quicksort(array, low, i - 1, comparator);
        quicksort(array, i + 1, high, comparator);
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) return array;

        int mid = array.length / 2;
        T[] left = Arrays.copyOfRange(array, 0, mid);
        T[] right = Arrays.copyOfRange(array, mid, array.length);

        mergesort(left, comparator);
        mergesort(right, comparator);

        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) array[k++] = left[i++];
        while (j < right.length) array[k++] = right[j++];

        return array;
    }
}
