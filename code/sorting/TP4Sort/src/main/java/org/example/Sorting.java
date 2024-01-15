package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;
            for (int j = array.length - 1; j > i; j--) {
                if (array[j - 1] != null && array[j] != null && comparator.compare(array[j - 1], array[j]) > 0) {
                    T tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return array;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        quickSort(array, 0, array.length - 1, comparator);
        return array;
    }

    private static <T> void quickSort(T[] array, int low, int high, Comparator<T> comparator) {
        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        T pivot = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (comparator.compare(array[i], pivot) < 0) {
                i++;
            }

            while (comparator.compare(array[j], pivot) > 0) {
                j--;
            }

            if (i <= j) {
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j, comparator);

        if (high > i)
            quickSort(array, i, high, comparator);
    }
    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        T[] buffer1 = array.clone();
        T[] buffer2 = array.clone();

        T[] result = mergeSortInner(buffer1, buffer2, 0, array.length - 1, comparator);

        if (result != array) {
            System.arraycopy(result, 0, array, 0, result.length);
        }

        return array;
    }

    private static <T> T[] mergeSortInner(T[] buffer1, T[] buffer2, int startIndex, int endIndex, Comparator<T> comparator) {
        if (startIndex >= endIndex) {
            return buffer1;
        }

        // Разделение на две половины
        int middle = startIndex + (endIndex - startIndex) / 2;
        T[] sorted1 = mergeSortInner(buffer1, buffer2, startIndex, middle, comparator);
        T[] sorted2 = mergeSortInner(buffer1, buffer2, middle + 1, endIndex, comparator);
        T[] result = buffer1 == sorted1 ? buffer2 : buffer1;

        // Слияние
        int index1 = startIndex;
        int index2 = middle + 1;
        int destIndex = startIndex;

        while (index1 <= middle && index2 <= endIndex) {
            result[destIndex++] = comparator.compare(sorted1[index1], sorted2[index2]) < 0 ? sorted1[index1++] : sorted2[index2++];
        }

        while (index1 <= middle) {
            result[destIndex++] = sorted1[index1++];
        }

        while (index2 <= endIndex) {
            result[destIndex++] = sorted2[index2++];
        }

        return result;
    }

    public static void main(String[] args) {
        Integer[] intArray = {4, 2, 7, 1, 9, 5};
        System.out.println("Original Integer Array: " + Arrays.toString(intArray));

        Sorting.bubblesort(intArray, Comparator.naturalOrder());
        System.out.println("Sorted Integer Array (Bubble Sort): " + Arrays.toString(intArray));

        Sorting.quicksort(intArray, Comparator.naturalOrder());
        System.out.println("Sorted Integer Array (Quick Sort): " + Arrays.toString(intArray));

        Sorting.mergesort(intArray, Comparator.naturalOrder());
        System.out.println("Sorted Integer Array (Merge Sort): " + Arrays.toString(intArray));

        String[] strArray = {"apple", "orange", "banana", "grape", "kiwi"};
        System.out.println("\nOriginal String Array: " + Arrays.toString(strArray));

        Sorting.bubblesort(strArray, Comparator.naturalOrder());
        System.out.println("Sorted String Array (Bubble Sort): " + Arrays.toString(strArray));

        Sorting.quicksort(strArray, Comparator.naturalOrder());
        System.out.println("Sorted String Array (Quick Sort): " + Arrays.toString(strArray));

        Sorting.mergesort(strArray, Comparator.naturalOrder());
        System.out.println("Sorted String Array (Merge Sort): " + Arrays.toString(strArray));
    }
}
