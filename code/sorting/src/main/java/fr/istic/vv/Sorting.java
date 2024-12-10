package fr.istic.vv;

import java.util.Comparator;

public class Sorting {

    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        if(n == 0) return array;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

        }
        return array;
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static <T> int partitionner(T[] array, int first, int last, int pivot, Comparator<T> comparator) {
        swap(array, pivot, last);
        int j = first;
        for (int i = first; i < last; i++) {
            if (comparator.compare(array[i], array[last]) <= 0) {
                swap(array, i, j);
                j++;
            }
        }
        swap(array, j, last);
        return j;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        if(array.length == 0) return array;
        int first = 0;
        int last = array.length - 1;
        quicksort(array, first, last, comparator);
        return array;
    }

    private static <T> void quicksort(T[] array, int first, int last, Comparator<T> comparator) {
        if(first < last) {
            int pivot = (first + last) / 2;
            pivot = partitionner(array, first, last, pivot, comparator);
            quicksort(array, first, pivot - 1, comparator);
            quicksort(array, pivot + 1, last, comparator);
        }
    }

    public static <T> void mergesort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) return;
    
        if (array.length == 2) {
            if (comparator.compare(array[0], array[1]) > 0) {
                T temp = array[0];
                array[0] = array[1];
                array[1] = temp;
            }
            return;
        }
    
        int mid = array.length / 2;
        T[] left = (T[]) new Object[mid];
        T[] right = (T[]) new Object[array.length - mid];
    
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);
    
        mergesort(left, comparator);
        mergesort(right, comparator);
    
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
}
