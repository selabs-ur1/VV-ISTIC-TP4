package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    // Swap elements if they are in the wrong order
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        return array;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
        quickSortPartitioning(array, 0, array.length - 1, comparator);
        return array;
    }

    private static <T> void quickSortPartitioning(T[] array, int begin, int end, Comparator<T> comparator) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end, comparator);
            quickSortPartitioning(array, begin, partitionIndex - 1, comparator);
            quickSortPartitioning(array, partitionIndex + 1, end, comparator);
        }
    }

    private static <T> int partition(T[] array, int begin, int end, Comparator<T> comparator) {
        T pivot = array[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        T temp = array[i];
        array[i] = array[end];
        array[end] = temp;
        return i + 1;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }

        int mid = array.length / 2;
        T[] leftArray = Arrays.copyOfRange(array, 0, mid);
        T[] rightArray = Arrays.copyOfRange(array, mid,  array.length);

        mergesort(leftArray, comparator);
        mergesort(rightArray, comparator);
        merge(array, leftArray, rightArray, comparator);

        return array;
    }

    private static <T> void merge(T[] array, T[] leftArray, T[] rightArray, Comparator<T> comparator) {
        int leftLength = leftArray.length;
        int rightLength = rightArray.length;
        int i = 0, j = 0, k = 0;

        while (i < leftLength && j < rightLength) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < leftLength) {
            array[k++] = leftArray[i++];
        }

        while (j < rightLength) {
            array[k++] = rightArray[j++];
        }
    }

}
