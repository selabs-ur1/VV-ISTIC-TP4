package fr.istic.vv;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) { 
        return null;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
        int debut = 0;
        int fin = array.length - 1;
        quicksortvar(array,comparator,debut,fin);
        return array;
    }

    private static <T> T[] quicksortvar(T[] array, Comparator<T> comparator, int debut, int fin) {
        if (debut < fin) {
            int pivot = swap(array, comparator, debut, fin);
            quicksortvar(array, comparator, debut, pivot - 1);
            quicksortvar(array, comparator, pivot + 1, fin);
        }
        return array;
        
    }

    private static <T> int swap(T[] array, Comparator<T> comparator, int debut, int fin) {
        int pivot = debut;
        for (int i = debut + 1; i <= fin; i++) {
            if (comparator.compare(array[i], array[debut]) < 0) {
                pivot++;
                T temp = array[pivot];
                array[pivot] = array[i];
                array[i] = temp;
            }
        }
        T temp = array[debut];
        array[debut] = array[pivot];
        array[pivot] = temp;
        return pivot;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) { return null; }

}
