package fr.istic.vv;

import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    /**
     * (https://en.wikipedia.org/wiki/Bubble_sort)
     *
     * @param array
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        while (n > 1) {
            int newn = 0;
            for (int i = 1; i < n; i++) {
                if (comparator.compare(array[i - 1], array[i]) > 0) {
                    T tmp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = tmp;
                    newn = i;
                }
            }
            n = newn;
        }
        return array;
    }

    /**
     * @param array
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        return quicksort(array, 0, array.length - 1, comparator);
    }

    public static <T> T[] quicksort(T[] array, int lo, int hi, Comparator<T> comparator) {
        if (lo < hi) {
            int pi = partition(array, lo, hi, comparator);

            quicksort(array, lo, pi - 1, comparator);
            quicksort(array, pi + 1, hi, comparator);
        }
        return array;
    }

    public static <T> int partition(T[] array, int lo, int hi, Comparator<T> comparator) {
        T pivot = array[hi];
        int i = lo - 1;

        for (int j = lo; j < hi; j++) {
            if (comparator.compare(array[j], pivot) < 0) {
                i++;
                T tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        T tmp = array[i + 1];
        array[i + 1] = array[hi];
        array[hi] = tmp;

        return i + 1;
    }

    /**
     * @param array
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        splitDown(array, 0, array.length, comparator);
        return array;
    }

    public static <T> void splitDown(T[] array, int start, int end, Comparator<T> comparator) {
        if (end - start > 1) {
            int mid = (start + end) / 2;
            splitDown(array, start, mid, comparator);
            splitDown(array, mid, end, comparator);

            mergeUp(array, start, mid, end, comparator);
        }
    }

    public static <T> void mergeUp(T[] array, int start, int middle, int end, Comparator<T> comparator) {
        T[] ret = (T[]) new Object[end - start];
        int i = 0, j = 0, k = 0;
        while (i < middle - start && j < end - middle) {
            if (comparator.compare(array[start + i], array[middle + j]) <= 0) {
                ret[k++] = array[start + i++];
            } else {
                ret[k++] = array[middle + j++];
            }
        }
        while (i < middle - start) {
            ret[k++] = array[start + i++];
        }
        while (j < end - middle) {
            ret[k++] = array[middle + j++];
        }
        System.arraycopy(ret, 0, array, start, ret.length);
    }

}
