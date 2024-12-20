package fr.istic.vv;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) { 
        T[] sortedArray = array.clone();
        
        int n = sortedArray.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {

                if (comparator.compare(sortedArray[j], sortedArray[j + 1]) > 0) {

                    T temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                    swapped = true;
                }
            }
            
            if (!swapped) {
                break;
            }
        }
        
        return sortedArray;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  { 
        T[] sortedArray = array.clone();
        
        quicksortHelper(sortedArray, 0, sortedArray.length - 1, comparator);
        
        return sortedArray;
     }


     private static <T> void quicksortHelper(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < high) {

            int pivotIndex = partition(array, low, high, comparator);
            
            quicksortHelper(array, low, pivotIndex - 1, comparator);
            quicksortHelper(array, pivotIndex + 1, high, comparator);
        }
    }
    
    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
        T pivot = array[high];
        
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
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
        T[] sortedArray = array.clone();
        
        mergesortHelper(sortedArray, 0, sortedArray.length - 1, comparator);
        
        return sortedArray;
     }


     private static <T> void mergesortHelper(T[] array, int left, int right, Comparator<T> comparator) {
        if (left < right) {

            int mid = left + (right - left) / 2;
            
            mergesortHelper(array, left, mid, comparator);
            mergesortHelper(array, mid + 1, right, comparator);
            
            merge(array, left, mid, right, comparator);
        }
    }
    
    private static <T> void merge(T[] array, int left, int mid, int right, Comparator<T> comparator) {

        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        @SuppressWarnings("unchecked")
        T[] leftArray = (T[]) new Object[n1];
        @SuppressWarnings("unchecked")
        T[] rightArray = (T[]) new Object[n2];
        
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);
        
        int i = 0, j = 0;
        
        int k = left;
        
        while (i < n1 && j < n2) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

}
