package fr.istic.vv;

import java.util.Comparator;

public class Sorting {

    public static <T> T[] bubbleSort(T[] array, Comparator<T> comparator) {
        T[] sortedArray = array.clone();
        int length = sortedArray.length;

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (comparator.compare(sortedArray[j], sortedArray[j + 1]) > 0) {
                    swap(sortedArray, j, j + 1);
                }
            }
        }

        return sortedArray;
    }

    public static <T> T[] quickSort(T[] array, Comparator<T> comparator) {
        T[] sortedArray = array.clone();
        quickSortHelper(sortedArray, 0, sortedArray.length - 1, comparator);
        return sortedArray;
    }

    private static <T> void quickSortHelper(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int partitionIndex = partition(array, low, high, comparator);
            quickSortHelper(array, low, partitionIndex - 1, comparator);
            quickSortHelper(array, partitionIndex + 1, high, comparator);
        }
    }

    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    public static <T> T[] mergeSort(T[] array, Comparator<T> comparator) {
        T[] sortedArray = array.clone();
        mergeSortHelper(sortedArray, comparator);
        return sortedArray;
    }

    private static <T> void mergeSortHelper(T[] array, Comparator<T> comparator) {
        int length = array.length;

        if (length < 2) {
            return;
        }

        int mid = length / 2;
        T[] left = java.util.Arrays.copyOfRange(array, 0, mid);
        T[] right = java.util.Arrays.copyOfRange(array, mid, length);

        mergeSortHelper(left, comparator);
        mergeSortHelper(right, comparator);

        merge(array, left, right, comparator);
    }

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

    private static <T> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
