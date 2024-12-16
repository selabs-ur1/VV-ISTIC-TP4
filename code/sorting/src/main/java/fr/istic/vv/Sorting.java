package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sorting {

    public static <T> List<T> bubblesort(List<T> array, Comparator<T> comparator) {
        int length = array.size();

        List<T> result = new ArrayList<>(array);

        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < length; i++)
                if (comparator.compare(result.get(i - 1), result.get(i)) > 0) {
                    T tmp = result.get(i);
                    result.set(i, result.get(i - 1));
                    result.set(i - 1, tmp);
                    swapped = true;
                }
        } while (swapped);

        return result;
    }

    public static <T> List<T> quicksort(List<T> array, Comparator<T> comparator) {
        List<T> result = new ArrayList<>(array);
        quicksortSub(result, 0, array.size(), comparator);

        return result;
    }

    private static <T> void quicksortSub(List<T> array, int start, int end,
            Comparator<T> comparator) {
        if (start >= end || start < 0)
            return;

        int middle = partition(array, start, end, comparator);

        quicksortSub(array, start, middle, comparator);
        quicksortSub(array, middle + 1, end, comparator);
    }

    private static <T> int partition(List<T> array, int start, int end, Comparator<T> comparator) {
        T pivot = array.get(end - 1);
        int i = start;

        for (int j = start; j < end - 1; j++)
            if (comparator.compare(array.get(j), pivot) <= 0) {
                T tmp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, tmp);
                i++;
            }

        T tmp = array.get(i);
        array.set(i, array.get(end - 1));
        array.set(end - 1, tmp);

        return i;
    }

    public static <T> List<T> mergesort(List<T> array, Comparator<T> comparator) {
        List<T> result = new ArrayList<>(array);
        mergesortSub(result, 0, result.size(), comparator);

        return result;
    }

    private static <T> void mergesortSub(List<T> array, int start, int end,
            Comparator<T> comparator) {
        int middle = (start + end) / 2;

        // We do not a single element.
        if (middle == start)
            return;

        // Sort recursively the two sub arrays.
        mergesortSub(array, start, middle, comparator);
        mergesortSub(array, middle, end, comparator);

        // Merge results of recursive sorts.
        merge(array, start, middle, end, comparator);
    }

    private static <T> void merge(List<T> array, int start, int middle, int end,
            Comparator<T> comparator) {
        List<T> arrayCpy = new ArrayList<>(array);
        int ileft = start;      // Current index over the left array.
        int iright = middle;    // Current index over the right array.
        int writeIdx = ileft;   // Write index over the array.

        // Until we reach the end of the array to write...
        while (writeIdx < end) {
            if (ileft == middle)    // No element remains in the left array.
                array.set(writeIdx, arrayCpy.get(iright++));
            else if (iright == end) // No element remains in the right array.
                array.set(writeIdx, arrayCpy.get(ileft++));
            else {  // There is elements in both left and right array.
                T leftElt = arrayCpy.get(ileft);
                T rightElt = arrayCpy.get(iright);
                int cmp = comparator.compare(leftElt, rightElt);

                // Select the appropriate element to merge over comparison's result.
                if (cmp <= 0) {
                    array.set(writeIdx, leftElt);
                    ileft++;
                } else {
                    array.set(writeIdx, rightElt);
                    iright++;
                }
            }

            writeIdx++;
        }
    }

}
