package fr.istic.vv;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {
        T[] result = array.clone();
        int n = result.length;
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - i - 1; j++) {
                if(comparator.compare(result[j], result[j + 1]) > 0) {
                    T temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }
    return result;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        T[] result = array.clone();
        quicksortHelper(result  , 0, result.length - 1, comparator);
        return result;

    }

    private static <T> void quicksortHelper(T[] array, int low, int high, Comparator<T> comparator) {
        if( low < high ) {
            int i = partition(array, low, high, comparator);
            quicksortHelper(array, low, i-1, comparator);
            quicksortHelper(array, i+1, high, comparator);
        }
    }

    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if(comparator.compare(array[j], pivot) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }



    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        T[] result = array.clone();
        T[] temp = array.clone();
        mergesortHelper(result, temp, 0, result.length - 1, comparator);
        return result;

    }

    private static <T> void mergesortHelper(T[] array, T[]temp, int left, int right, Comparator<T> comparator) {
        if(left < right) {
            int middle = (left + right) / 2;
            mergesortHelper(array, temp, left, middle, comparator);
            mergesortHelper(array, temp, middle + 1, right, comparator);
            merge(array, temp, left, middle, right, comparator);
        }
    }

    private static <T> void merge(T[] array, T[] temp, int left, int mid, int right, Comparator<T> comparator) {

        System.arraycopy(array, left, temp, left, right - left + 1);

        int i = left;
        int j = mid + 1;
        int k = left;

        while(i <= mid && j <= right) {
            if(comparator.compare(temp[i], temp[j]) <= 0)
                array[k++] = temp[i++];
            else
                array[k++] = temp[j++];
        }
        while(i <= mid) {
            array[k++] = temp[i++];
        }

        while (j <= right) {
            array[k++] = temp[j++];
        }

        
    }

}
