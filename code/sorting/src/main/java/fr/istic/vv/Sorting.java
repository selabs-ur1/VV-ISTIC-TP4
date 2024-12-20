package fr.istic.vv;
import java.util.Comparator;

public class Sorting {

    public static <T> T[] bubbleSort(T[] array, Comparator<T> comparator) {
        T[] sortedArray = array.clone();
        int arrayLength = sortedArray.length;
        boolean hasSwaps;

        for (int pass = 0; pass < arrayLength - 1; pass++) {
            hasSwaps = false;
            for (int currentIndex = 0; currentIndex < arrayLength - pass - 1; currentIndex++) {
                if (comparator.compare(sortedArray[currentIndex], sortedArray[currentIndex + 1]) > 0) {
                    T temp = sortedArray[currentIndex];
                    sortedArray[currentIndex] = sortedArray[currentIndex + 1];
                    sortedArray[currentIndex + 1] = temp;
                    hasSwaps = true;
                }
            }

            if (!hasSwaps) {
                break;
            }
        }

        return sortedArray;
    }

    public static <T> void quickSort(T[] array, Comparator<T> comparator) {
        quickSort(array, 0, array.length - 1, comparator);
    }

    public static <T> void quickSort(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, comparator);
            quickSort(array, low, pivotIndex - 1, comparator);
            quickSort(array, pivotIndex + 1, high, comparator);
        }
    }

    public static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
        T pivotElement = array[high];
        int smallerElementIndex = low - 1;

        for (int currentIndex = low; currentIndex <= high - 1; currentIndex++) {
            if (comparator.compare(array[currentIndex], pivotElement) < 0) {
                smallerElementIndex++;
                swap(array, smallerElementIndex, currentIndex);
            }
        }

        swap(array, smallerElementIndex + 1, high);
        return smallerElementIndex + 1;
    }

    private static <T> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static <T> T[] mergeSort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }
        T[] sortedArray = array.clone();
        mergeSortRecursive(sortedArray, comparator);
        return sortedArray;
    }

    public static <T> void mergeSortRecursive(T[] array, Comparator<T> comparator) {
        mergeSort(array, 0, array.length - 1, comparator);
    }

    public static <T> void mergeSort(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int middle = low + (high - low) / 2;

            mergeSort(array, low, middle, comparator);
            mergeSort(array, middle + 1, high, comparator);
            merge(array, low, middle, high, comparator);
        }
    }

    public static <T> void merge(T[] array, int low, int middle, int high, Comparator<T> comparator) {
        int leftSubarraySize = middle - low + 1;
        int rightSubarraySize = high - middle;

        T[] leftSubarray = (T[]) new Object[leftSubarraySize];
        T[] rightSubarray = (T[]) new Object[rightSubarraySize];

        for (int i = 0; i < leftSubarraySize; i++) {
            leftSubarray[i] = array[low + i];
        }
        for (int j = 0; j < rightSubarraySize; j++) {
            rightSubarray[j] = array[middle + 1 + j];
        }
        int leftIndex = 0, rightIndex = 0, mergedIndex = low;

        while (leftIndex < leftSubarraySize && rightIndex < rightSubarraySize) {
            if (comparator.compare(leftSubarray[leftIndex], rightSubarray[rightIndex]) <= 0) {
                array[mergedIndex] = leftSubarray[leftIndex];
                leftIndex++;
            } else {
                array[mergedIndex] = rightSubarray[rightIndex];
                rightIndex++;
            }
            mergedIndex++;
        }

        while (leftIndex < leftSubarraySize) {
            array[mergedIndex] = leftSubarray[leftIndex];
            leftIndex++;
            mergedIndex++;
        }

        while (rightIndex < rightSubarraySize) {
            array[mergedIndex] = rightSubarray[rightIndex];
            rightIndex++;
            mergedIndex++;
        }
    }
}
