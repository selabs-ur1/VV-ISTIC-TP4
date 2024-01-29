package fr.istic.vv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        T temp;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(comparator.compare(array[j-1], array[j]) > 0){
                    swap(array, j-1, j);
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

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
        if (array.length == 0) {
            return array;
        }
        T[] result = Arrays.copyOf(array, array.length);
        int pivot = result.length / 2;
        quicksort(result, comparator, 0, pivot - 1);
        quicksort(result, comparator, pivot, array.length - 1);
        return result;
    }

    private static <T> void quicksort(T[] array, Comparator<T> comparator, int left, int right) {
        int index = partition(array, comparator, left, right);
        if (left < index - 1) {
            quicksort(array, comparator, left, index - 1);
        }
        if (index < right) {
            quicksort(array, comparator, index, right);
        }
    }

    private static <T> int partition(T[] array, Comparator<T> comparator, int left, int right) {
        int i = left;
        int j = right;
        T pivot = array[(left + right) / 2];

        while (i <= j) {
            while (comparator.compare(array[i], pivot) < 0) {
                i++;
            }
            while (comparator.compare(array[j], pivot) > 0) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        return i;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }
        T[] result = Arrays.copyOf(array, array.length);
        int pivot = result.length / 2;
        T[] left = Arrays.copyOfRange(result, 0, pivot);
        T[] right = Arrays.copyOfRange(result, pivot, result.length);
        return merge(mergesort(left, comparator), mergesort(right, comparator), comparator);
    }

    private static <T> T[] merge(T[] left, T[] right, Comparator<T> comparator) {
        int leftIndex = 0;
        int rightIndex = 0;
        ArrayList<T> result = new ArrayList<T>();
        while (leftIndex < left.length && rightIndex < right.length) {
            if (comparator.compare(left[leftIndex], right[rightIndex]) < 0) {
                result.add(left[leftIndex]);
                leftIndex++;
            } else {
                result.add(right[rightIndex]);
                rightIndex++;
            }
        }
        while (leftIndex < left.length) {
            result.add(left[leftIndex]);
            leftIndex++;
        }
        while (rightIndex < right.length) {
            result.add(right[rightIndex]);
            rightIndex++;
        }
        return result.toArray(left);
    }

}
