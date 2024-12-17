package fr.istic.vv;

import java.util.Comparator;

public class Sorting {

    // https://en.wikipedia.org/wiki/Bubble_sort#Pseudocode_implementation
    public static <T> T[] bubblesort(T[] array_, Comparator<T> comparator) {
        T[] array = array_.clone(); // cloning to not modify parameter array
        int n = array_.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i <= n - 1; i++) {
                if (comparator.compare(array[i - 1], array[i]) > 0) {
                    // swap
                    T temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
        return array;
    }

    // About the algorithm
    // https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme
    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        T[] result = array.clone(); // cloning to not modify parameter array
        quicksortHelper(result, 0, result.length - 1, comparator); // We want to sort all the array
        return result;
    }

    // Implementation we use
    // https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme
    // Sorts (a portion of) an array, divides it into partitions, then sorts those
    private static <T> void quicksortHelper(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < 0 || high < 0 || low >= high) {
            return;
        }
        int p = partition(array, low, high, comparator);
        quicksortHelper(array, low, p, comparator); // Note: the pivot is now included
        quicksortHelper(array, p + 1, high, comparator);
    }

    // Divides array into two partitions
    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
        // Pivot value
        T pivot = array[low]; // Choose the first element as the pivot

        // Left index
        int i = low - 1;
        // Right index
        int j = high + 1;

        while (true) {
            // Move the left index to the right at least once and while the element at
            // the left index is less than the pivot
            do {
                i++;
            } while (comparator.compare(array[i], pivot) < 0);

            // Move the right index to the left at least once and while the element at
            // the right index is greater than the pivot
            do {
                j--;
            } while (comparator.compare(array[j], pivot) > 0);

            // If the indices crossed, return
            if (i >= j) {
                return j;
            }

            // Swap the elements at the left and right indices
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // https://en.wikipedia.org/wiki/Merge_sort
    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        T[] arrayToSort = array.clone(); // clone here to avoid messing parameter array
        TopDownMergeSort(arrayToSort, comparator);
        return arrayToSort;
    }

    // https://en.wikipedia.org/wiki/Merge_sort#Top-down_implementation
    private static <T> void TopDownMergeSort(T[] arrayToSort, Comparator<T> comparator) {
        T[] workingArray = arrayToSort.clone();
        TopDownSplitMerge(arrayToSort, 0, workingArray.length, workingArray, comparator);
    }

    // Split A[] into 2 runs, sort both runs into B[], merge both runs from B[] to A[]
    // iBegin is inclusive; iEnd is exclusive (A[iEnd] is not in the set).
    private static <T> void TopDownSplitMerge(T[] B, int iBegin, int iEnd, T[] A, Comparator<T> comparator) {
        // if run size == 1 consider it sorted
        if (iEnd - iBegin <= 1) {
            return;
        }
        // split the run longer than 1 item into halves
        int iMiddle = (iEnd + iBegin) / 2;              // iMiddle = mid point
        // recursively sort both runs from array A[] into B[]
        TopDownSplitMerge(A, iBegin, iMiddle, B, comparator);  // sort the left  run
        TopDownSplitMerge(A, iMiddle, iEnd, B, comparator);  // sort the right run
        // merarrayToSortge the resulting runs from array B[] into A[]
        TopDownMerge(B, iBegin, iMiddle, iEnd, A, comparator);
    }

    //  Left source half is A[ iBegin:iMiddle-1].
    // Right source half is A[iMiddle:iEnd-1   ].
    // Result is            B[ iBegin:iEnd-1   ].
    private static <T> void TopDownMerge(T[] B, int iBegin, int iMiddle, int iEnd, T[] A, Comparator<T> comparator) {
        int i = iBegin;
        int j = iMiddle;

        // While there are elements in the left or right runs...
        for (int k = iBegin; k < iEnd; k++) {
            // If left run head exists and is <= existing right run head.
            if (i < iMiddle && (j >= iEnd || comparator.compare(A[i], A[j]) <= 0)) {
                B[k] = A[i];
                i = i + 1;
            } else {
                B[k] = A[j];
                j = j + 1;
            }
        }
    }
}

