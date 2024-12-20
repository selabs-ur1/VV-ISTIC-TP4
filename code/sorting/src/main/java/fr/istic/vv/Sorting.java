package fr.istic.vv;

import java.util.Comparator;
import java.util.Arrays;
import java.util.Random;

class Sorting {

    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        T[] arr = Arrays.copyOf(array, array.length);
        boolean Chose = true;
        boolean swapped;

        do {
            swapped = false;

            for (int i = 0; i < arr.length - 1; i++) {
                if (comparator.compare(arr[i], arr[i+1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i+1];

                    arr[i+1] = temp;
                    swapped = true;
                }
            }

        } while (swapped);
        return arr;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        T[] arr = Arrays.copyOf(array, array.length);

        Random rand = new Random();
        quicksortHelper(arr, 0, arr.length-1, comparator, rand);

        return arr;
    }

    private static <T> void quicksortHelper(T[] arr, int low, int high, Comparator<T> comparator, Random rand) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high, comparator, rand);

            quicksortHelper(arr, low, pivotIndex-1, comparator, rand);
            quicksortHelper(arr, pivotIndex+1, high, comparator, rand);
        }
    }

    private static <T> int partition(T[] arr, int low, int high, Comparator<T> comparator, Random rand) {

        int randomPivotIndex = low + rand.nextInt(high - low + 1);
        swap(arr, randomPivotIndex, high);
        T pivot = arr[high];

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(arr[j], pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, high);
        return i+1;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        T[] arr = Arrays.copyOf(array, array.length);
        mergesortHelper(arr, 0, arr.length-1, comparator);
        return arr;
    }

    private static <T> void mergesortHelper(T[] arr, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergesortHelper(arr, left, mid, comparator);
            mergesortHelper(arr, mid+1, right, comparator);
            merge(arr, left, mid, right, comparator);
        }
    }

    private static <T> void merge(T[] arr, int left, int mid, int right, Comparator<T> comparator) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        T[] L = Arrays.copyOfRange(arr, left, mid+1);
        T[] R = Arrays.copyOfRange(arr, mid+1, right+1);

        int i = 0, j = 0, k = left;

        int count = 0;

        while (i < n1 && j < n2) {
            count++;
            if (comparator.compare(L[i], R[j]) <= 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    private static <T> void swap(T[] arr, int i, int j) {

        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
