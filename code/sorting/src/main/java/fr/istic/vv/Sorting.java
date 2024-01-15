package fr.istic.vv;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {
        int n = array.length;

        T[] arrayCopy = (T[]) new Object[n];
        System.arraycopy(array,0, arrayCopy,0, n);

        boolean swapped;
        do {
            swapped = false;
            for(int i=1; i<n;i++){
                if(comparator.compare(arrayCopy[i-1], arrayCopy[i]) > 0){
                    T tmp = arrayCopy[i-1];
                    arrayCopy[i-1] = arrayCopy[i];
                    arrayCopy[i] = tmp;
                    swapped = true;
                }
            }
        } while(swapped);

        return arrayCopy;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
        if(array.length <= 1){
            return array;
        }

        int n = array.length;

        T[] arrayCopy = (T[]) new Object[n];
        System.arraycopy(array,0, arrayCopy,0, n);

        quickSortHelper(arrayCopy,0,n-1,comparator);

        return arrayCopy;
    }

    private static <T> void quickSortHelper(T[] array, int low, int high, Comparator<T> comparator){
        if(low < high){
            int pivot = partition(array,low,high,comparator);

            quickSortHelper(array, low, pivot-1, comparator);
            quickSortHelper(array, pivot+1, high, comparator);
        }
    }

    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
        T pivot = array[high];

        //temporary pivot index
        int indexPivot = low-1;

        for(int j=low; j<high; j++){
            if(comparator.compare(array[j],pivot)<=0){
                indexPivot++;

                //swap indexPivot and j
                T temp = array[indexPivot];
                array[indexPivot] = array[j];
                array[j] = temp;
            }
        }
        indexPivot++;
        //swap indexPivot and high
        T temp = array[indexPivot];
        array[indexPivot] = array[high];
        array[high] = temp;

        return indexPivot;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        if(array.length <= 1){
            return array;
        }

        int middle = array.length/2;

        T[] left = (T[]) new Object[middle];
        T[] right = (T[]) new Object[array.length - middle];

        System.arraycopy(array, 0, left, 0, middle);
        System.arraycopy(array, middle, right, 0, array.length - middle);

        left = mergesort(left, comparator);
        right = mergesort(right, comparator);

        return merge(left, right, comparator);
    }

    private static <T> T[] merge(T[] left, T[] right, Comparator<T> comparator) {
        int totalLength = left.length + right.length;
        T[] merged = (T[]) new Object[totalLength];

        int leftPt = 0;
        int rightPt = 0;
        int mergedPt = 0;

        while(leftPt < left.length && rightPt < right.length){
            if(comparator.compare(left[leftPt], right[rightPt]) <= 0){
                merged[mergedPt++] = left[leftPt++];
            }
            else {
                merged[mergedPt++] = right[rightPt++];
            }
        }

        //Soit left ou right à encore des éléments
        while(leftPt < left.length){
            merged[mergedPt++] = left[leftPt++];
        }

        while(rightPt < right.length){
            merged[mergedPt++] = right[rightPt++];
        }

        return merged;
    }

}
