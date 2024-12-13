package fr.istic.vv;

import java.util.Comparator;

public class Sorting {


    public  static <T> void swapItemsInTab(T[]array,int i1,int i2){
        T tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }


    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        T[] result = array;
        for (int i = result.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (comparator.compare(result[j + 1], result[j]) < 0) {
                    swapItemsInTab(result, j+1, j);
                }
            }
        }
        return result;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        T[] result = array;
        quickSort(result,0,result.length-1, comparator);
        return result;
    }


    public static <T> void quickSort(T array[], int begin, int end,Comparator<T> comparator) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end, comparator);
    
            quickSort(array, begin, partitionIndex-1,comparator);
            quickSort(array, partitionIndex+1, end,comparator);
        }
    }

    private static <T> int partition(T array[], int begin, int end, Comparator<T> comparator) {
        T pivot = array[end];
        int i = (begin-1);
    
        for (int j = begin; j < end; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                swapItemsInTab(array, i, j);

            }
        }

        swapItemsInTab(array, i+1, end);
     
    
        return i+1;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        T[] result = array;
        mergeSort(result, comparator, result.length-1);
        return result;
    }

    public static <T> void mergeSort(T[] result, Comparator<T> comparator, int size) {
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        T[] left = (T[]) new Object[mid];
        T[] right = (T[]) new Object[size-mid];
        
        for (int i = 0; i < mid; i++) {
            left[i] = result[i];
        }
        for (int i = mid; i < size; i++) {
            right[i - mid] = result[i];
        }
        mergeSort(left,comparator, mid);
        mergeSort(right, comparator,size - mid);

        merge(result, left, right, mid, size - mid, comparator);
    }

    public static <T> void merge(
            T[] array, T[] left, T[] right, int leftSize, int rightSize, Comparator<T> comparator ) {

        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize) {
            if (comparator.compare(left[i], right[j]) <0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < leftSize) {
            array[k++] = left[i++];
        }
        while (j < rightSize) {
            array[k++] = right[j++];
        }
    }



    //pour afficher une tableau 
    public static <T> void displayTab(T[] array) {
        System.out.print("[");
        for (T element : array) {
            System.out.print(element + " ,");
        }
        System.out.println("]"); // Pour passer à la ligne après l'affichage
    }
}
