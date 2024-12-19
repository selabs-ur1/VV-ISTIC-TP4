package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        T[] result = Arrays.copyOf(array, array.length);
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < result.length - 1; i++) {
                if (comparator.compare(result[i], result[i + 1]) > 0) {
                    T temp = result[i];
                    result[i] = result[i + 1];
                    result[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
        return result;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        T[] result = Arrays.copyOf(array, array.length);
        quicksortHelper(result, 0, result.length - 1, comparator);
        return result;
    }

    private static <T> void quicksortHelper(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int partitionIndex = partition(array, low, high, comparator);
            quicksortHelper(array, low, partitionIndex - 1, comparator);
            quicksortHelper(array, partitionIndex + 1, high, comparator);
        }
    }

    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
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

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        T[] result = Arrays.copyOf(array, array.length);
        mergesortHelper(result, 0, result.length - 1, comparator);
        return result;
    }

    private static <T> void mergesortHelper(T[] array, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergesortHelper(array, left, mid, comparator);
            mergesortHelper(array, mid + 1, right, comparator);

            merge(array, left, mid, right, comparator);
        }
    }

    private static <T> void merge(T[] array, int left, int mid, int right, Comparator<T> comparator) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        T[] leftArray = Arrays.copyOfRange(array, left, mid + 1);
        T[] rightArray = Arrays.copyOfRange(array, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}


