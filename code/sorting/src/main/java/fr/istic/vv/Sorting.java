package fr.istic.vv;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) { 
        T[] arrayToSort = array.clone();
        for (int i = 0; i < arrayToSort.length; i++) {
            for (int j = 0; j < i-1; j++) {
                if(comparator.compare(arrayToSort[j], arrayToSort[j+1]) > 0) {
                    T temp = arrayToSort[j];
                    arrayToSort[j] = arrayToSort[j+1];
                    arrayToSort[j+1] = temp;
                }
            }
        }
        return arrayToSort;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  { 
        T[] arrayToSort = array.clone();
        quicksortFirst(arrayToSort,comparator,0,arrayToSort.length);
        return arrayToSort;
    }
    public static <T> void quicksortFirst(T[] array, Comparator<T> comparator,int begin, int end)  { 
        if(begin<end){
            int partitionIndex = partition(array, begin, end,comparator);

        quicksortFirst(array,comparator, begin, partitionIndex-1);
        quicksortFirst(array,comparator, partitionIndex+1, end);
        }
    }
    public static <T> int partition(T[] array,int begin,int end,Comparator<T> comparator)  {
        T pivot = array[end];
        int i = begin-1;

        for (int j = begin; j < end; j++) {
            if(comparator.compare(array[j], pivot) < 0){
                i++;

                T swapTemp = array[i];
                array[i] = array[j];
                array[j] = swapTemp;
            }
        }

        T swapTemp  = array[i+1];
        array[i+1] = array[end];
        array[end] = swapTemp;

        return i++;
    }


    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length < 2) {
            return array; 
        }
    
        T[] arrayToSort = array.clone();
        T[] tempArray = array.clone(); 
        mergesortHelper(arrayToSort, tempArray, 0, arrayToSort.length - 1, comparator);
        return arrayToSort;
    }
    

    private static <T> void mergesortHelper(T[] array, T[] tempArray, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int middle = (left + right) / 2;
    
            mergesortHelper(array, tempArray, left, middle, comparator);
            mergesortHelper(array, tempArray, middle + 1, right, comparator);
    
            merge(array, tempArray, left, middle, right, comparator);
        }
    }
    
    private static <T> void merge(T[] array, T[] tempArray, int left, int middle, int right, Comparator<T> comparator) {
    
        int i = 0;       
        int j = 0;
        int k = 0;
    
        while (i <= middle && j <= right) {
            if (comparator.compare(tempArray[i], tempArray[j]) <= 0) {
                array[k++] = tempArray[i++];
            } else {
                array[k++] = tempArray[j++];
            }
        }
    
        while (i < left) {
            array[k++] = tempArray[i++];
        }
        while (i < right) {
            array[k++] = tempArray[i++];
        }
    }
    
}
