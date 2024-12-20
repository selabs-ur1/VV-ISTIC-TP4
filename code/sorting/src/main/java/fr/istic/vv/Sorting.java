package fr.istic.vv;

import java.util.Comparator;

public class Sorting {

    public static <T> T[] bubblesort(T[] arr, Comparator<T> cmp) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (cmp.compare(arr[j], arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static <T> T[] quicksort(T[] arr, Comparator<T> cmp) {
        quicksortHelper(arr, 0, arr.length - 1, cmp);
        return arr;
    }

    private static <T> void quicksortHelper(T[] arr, int low, int high, Comparator<T> cmp) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high, cmp);
            quicksortHelper(arr, low, pivotIndex - 1, cmp);
            quicksortHelper(arr, pivotIndex + 1, high, cmp);
        }
    }

    private static <T> int partition(T[] arr, int low, int high, Comparator<T> cmp) {
        T pivot = arr[high];
        int idx = low - 1;

        for (int j = low; j < high; j++) {
            if (cmp.compare(arr[j], pivot) <= 0) {
                idx++;
                T temp = arr[idx];
                arr[idx] = arr[j];
                arr[j] = temp;
            }
        }

        T temp = arr[idx + 1];
        arr[idx + 1] = arr[high];
        arr[high] = temp;

        return idx + 1;
    }

    public static <T> T[] mergesort(T[] arr, Comparator<T> cmp) {
        if (arr.length <= 1) {
            return arr;
        }

        int mid = arr.length / 2;

        T[] left = java.util.Arrays.copyOfRange(arr, 0, mid);
        T[] right = java.util.Arrays.copyOfRange(arr, mid, arr.length);

        mergesort(left, cmp);
        mergesort(right, cmp);

        merge(arr, left, right, cmp);

        return arr;
    }

    private static <T> void merge(T[] arr, T[] left, T[] right, Comparator<T> cmp) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (cmp.compare(left[i], right[j]) <= 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}