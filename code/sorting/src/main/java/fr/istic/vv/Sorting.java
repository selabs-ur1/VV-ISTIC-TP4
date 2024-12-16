package fr.istic.vv;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T> T[] bubbleSort(T[] array, Comparator<T> comparator) {
        if (array == null || comparator == null) {
            throw new IllegalArgumentException("Array and comparator must not be null.");
        }

        int length = array.length;
        boolean hasSwapped;

        do {
            hasSwapped = false;

            for (int i = 0; i < length - 1; i++) {
                if (comparator.compare(array[i], array[i + 1]) > 0) {
                    T temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;

                    hasSwapped = true;
                }
            }
            length--;

        } while (hasSwapped);

        return array;
    }

    public static <T> T[] quickSort(T[] array, Comparator<T> comparator) {
        if (array == null || comparator == null) {
            throw new IllegalArgumentException("Array and comparator must not be null.");
        }

        quickSort(array, 0, array.length - 1, comparator);
        return array;
    }

    private static <T> void quickSort(T[] array, int start, int end, Comparator<T> comparator) {
        if (start < end) {
            T pivotElement = array[end];
            int partitionIndex = start - 1;

            for (int currentIndex = start; currentIndex < end; currentIndex++) {
                if (comparator.compare(array[currentIndex], pivotElement) <= 0) {
                    partitionIndex++;
                    T temp = array[partitionIndex];
                    array[partitionIndex] = array[currentIndex];
                    array[currentIndex] = temp;
                }
            }

            T temp = array[partitionIndex + 1];
            array[partitionIndex + 1] = array[end];
            array[end] = temp;

            int pivotIndex = partitionIndex + 1;
            quickSort(array, start, pivotIndex - 1, comparator);
            quickSort(array, pivotIndex + 1, end, comparator);
        }
    }

    public static <T> T[] mergeSort(T[] array, Comparator<T> comparator) {
        if (array == null || comparator == null) {
            throw new IllegalArgumentException("Array and comparator must not be null.");
        }

        if (array.length <= 1) {
            return array;
        }

        int middle = array.length / 2;

        T[] left = Arrays.copyOfRange(array, 0, middle);
        T[] right = Arrays.copyOfRange(array, middle, array.length);

        left = mergeSort(left, comparator);
        right = mergeSort(right, comparator);

        return merge(left, right, comparator);
    }

    private static <T> T[] merge(T[] left, T[] right, Comparator<T> comparator) {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(left.getClass().getComponentType(), left.length + right.length);
        int leftIndex = 0, rightIndex = 0, resultIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (comparator.compare(left[leftIndex], right[rightIndex]) <= 0) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }

        return result;
    }

}
